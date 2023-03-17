package com.akasa.foodservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akasa.foodservice.entity.FoodItem;
import com.akasa.foodservice.entity.FoodServiceUser;
import com.akasa.foodservice.entity.Order;
import com.akasa.foodservice.entity.OrderEntry;
import com.akasa.foodservice.entity.UserCartEntry;
import com.akasa.foodservice.payload.AddToCartFoodItemEntry;
import com.akasa.foodservice.payload.AddToCartResponse;
import com.akasa.foodservice.payload.CheckoutResponse;
import com.akasa.foodservice.payload.FoodItemCartEntryPayload;
import com.akasa.foodservice.payload.OrderHistoryResponse;
import com.akasa.foodservice.repository.FoodItemRepository;
import com.akasa.foodservice.repository.OrderEntryRepository;
import com.akasa.foodservice.repository.OrderRepository;
import com.akasa.foodservice.repository.UserCartEntryRepository;
import com.akasa.foodservice.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserCartServiceImpl implements UserCartService {

	@Autowired
	FoodItemRepository foodItemRepository;

	@Autowired
	UserCartEntryRepository userCartEntryRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderEntryRepository orderEntryRepository;


	@Override
	public AddToCartResponse addItemsToUserCart(String username,
		List<AddToCartFoodItemEntry> itemsToAdd) {
		try {
			List<Long> idsToAdd = itemsToAdd.stream().
				map(AddToCartFoodItemEntry::getId).collect(Collectors.toList());
			List<FoodItem> foodItemsToAdd = foodItemRepository.findAllById(idsToAdd);
			Map<Long, FoodItem> itemMapToAdd =
				foodItemsToAdd.stream().collect(Collectors.toMap(FoodItem::getId, Function
					.identity()));
			FoodServiceUser user = userRepository.findByUsername(username)
				.orElseThrow(() -> new Exception("Unable to find user"));
			List<UserCartEntry> currentCartItems = new ArrayList<>(
				userCartEntryRepository.findByUserId(user.getId()));
			Map<Long, UserCartEntry> currentCartItemMap =
				currentCartItems.stream().collect(Collectors.toMap(x -> x.getItem().getId(),
					Function.identity()));
			AtomicBoolean validTransaction = new AtomicBoolean(true);
			List<AddToCartFoodItemEntry> failedItems = new ArrayList<>();
			for (AddToCartFoodItemEntry x : itemsToAdd) {
				Long requestedInventory = x.getRequestInventory();
				Long totalInventory = itemMapToAdd.get(x.getId()).getInventorySize();
				Long inventoryInCart = currentCartItemMap.containsKey(x.getId())
					? currentCartItemMap.get(x.getId()).getInventoryCount() : 0;
				if (requestedInventory + inventoryInCart > totalInventory) {
					validTransaction.set(false);
					failedItems.add(x);
					break;
				}
			}
			if (validTransaction.get()) {
				for (AddToCartFoodItemEntry x : itemsToAdd) {
					userCartEntryRepository.saveUserCartEntry(user, itemMapToAdd.get(x.getId()),
						x.getRequestInventory(),
						currentCartItemMap.get(x.getId()));
				}
				return new AddToCartResponse("Success", null);
			} else {
				return new AddToCartResponse("Found an invalid entry", failedItems);
			}

		} catch (Exception e) {
			return new AddToCartResponse(e.getMessage(), null);
		}
	}

	@Override
	@Transactional
	public CheckoutResponse checkoutUserCart(String username) {
		try {
			Long orderId = 1L;
			Double totalOrderPrice = 0.0;
			FoodServiceUser user = userRepository.findByUsername(username)
				.orElseThrow(() -> new Exception("Unable to find user"));
			List<UserCartEntry> currentCartItems = new ArrayList<>(
				userCartEntryRepository.findByUserId(user.getId()));
			if (currentCartItems.size() == 0) {
				return new CheckoutResponse("No Items in cart");
			}
			boolean validTransaction = true;

			FoodItemCartEntryPayload failedItem = null;
			for (UserCartEntry cartEntry : currentCartItems) {
				Long totalInventory = cartEntry.getItem().getInventorySize();
				if (cartEntry.getInventoryCount() > totalInventory) {
					validTransaction = false;
					failedItem = new FoodItemCartEntryPayload(cartEntry);
					break;
				}
				totalOrderPrice +=
					cartEntry.getInventoryCount() * cartEntry.getItem().getPrice();
			}
			if (validTransaction) {
				// create an order
				Order order = orderRepository.createNewOrder(user, totalOrderPrice);
				orderId = order.getId();
				for (UserCartEntry cartEntry : currentCartItems) {
					// create new order entry
					OrderEntry orderEntry = orderEntryRepository.
						createNewEntry(order, cartEntry.getItem(), cartEntry.getInventoryCount());
					// delete cart entry
					userCartEntryRepository.deleteById(cartEntry.getId());
					// update item inventory size
					FoodItem item = cartEntry.getItem();
					item.setInventorySize(item.getInventorySize() - cartEntry.getInventoryCount());
					foodItemRepository.save(item);
				}
				return new CheckoutResponse("Success" , orderId, totalOrderPrice);
			} else {
				return new CheckoutResponse(failedItem);
			}


		} catch (Exception e) {
			return new CheckoutResponse("Failed to checkout : "+ e.getMessage()
				, -1L , -1D);
		}
	}

	@Override
	public OrderHistoryResponse getOrderHistory(String username) {
		try {
			FoodServiceUser user = userRepository.findByUsername(username)
				.orElseThrow(() -> new Exception("Unable to find user"));
			return new OrderHistoryResponse(user.getOrders());
		} catch (Exception e) {
			return new OrderHistoryResponse();
		}
	}
}
