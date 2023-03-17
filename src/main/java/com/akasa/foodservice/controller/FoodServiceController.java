package com.akasa.foodservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akasa.foodservice.entity.FoodCategory;
import com.akasa.foodservice.entity.FoodItem;
import com.akasa.foodservice.entity.FoodServiceUser;
import com.akasa.foodservice.entity.UserCartEntry;
import com.akasa.foodservice.payload.AddToCartFoodItemEntry;
import com.akasa.foodservice.payload.AddToCartRequest;
import com.akasa.foodservice.payload.AddToCartResponse;
import com.akasa.foodservice.payload.CheckoutResponse;
import com.akasa.foodservice.payload.FoodItemCartEntryPayload;
import com.akasa.foodservice.payload.OrderHistoryResponse;
import com.akasa.foodservice.repository.CategoryRepository;
import com.akasa.foodservice.repository.FoodItemRepository;
import com.akasa.foodservice.repository.UserCartEntryRepository;
import com.akasa.foodservice.repository.UserRepository;
import com.akasa.foodservice.service.UserCartServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/food")
public class FoodServiceController {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserCartEntryRepository userCartEntryRepository;

	@Autowired
	FoodItemRepository foodItemRepository;

	@Autowired
	UserCartServiceImpl userCartService;

	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}

	@GetMapping(value = "/list-item-by-cat-id/{categoryId}",
		produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public List<FoodItem> findFoodItemsByCategory(@PathVariable Long categoryId) {
		Optional<FoodCategory> category = categoryRepository.findById(categoryId);
		List<FoodItem> foodItemList = new ArrayList<>();
		category.ifPresent(x -> foodItemList.addAll(x.getFoodItems()));
		return foodItemList;
	}

	@GetMapping(value = "/get-all-cat",
		produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public List<FoodCategory> getAllCategories() {
		return categoryRepository.findAll();
	}

	@GetMapping(value = "/get-cart-entries-for-user/{username}" ,
	produces =  MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public List<FoodItemCartEntryPayload> getCartEntriesForUser(@PathVariable String username) {
		Optional<FoodServiceUser> user = userRepository.findByUsername(username);
		List<FoodItemCartEntryPayload> toReturn = new ArrayList<>();
		user.ifPresent(u -> userCartEntryRepository.findByUserId(u.getId()).forEach(entry ->
			toReturn.add(new FoodItemCartEntryPayload(entry))));
		return toReturn;
	}

	@PostMapping(value = "/add-items-to-user-cart/{username}" , consumes
		= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public AddToCartResponse addItemsToUserCart(@RequestBody  AddToCartRequest request,
		@PathVariable String username) {
		return userCartService.addItemsToUserCart(username, request.getItemsToAdd());
	}

	@PostMapping(value = "/checkout-cart/{username}" , produces =  MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public CheckoutResponse checkoutUserCart(@PathVariable String username) {
		return userCartService.checkoutUserCart(username);
	}

	@GetMapping(value = "/order-history/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('USER')")
	public OrderHistoryResponse getOrderHistory(@PathVariable String username) {
		return userCartService.getOrderHistory(username);
	}


	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
