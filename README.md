# Food Service

Done as a part of an assignment. 

The parts related to authentication and authorization were mostly picked from this blog [post](https://www.bezkoder.com/spring-boot-security-login-jwt/)

Api's added as a part of the project: 

## Auth Controller

* <i>/api/auth/signup</i> - To Register a new user. Can specify roles in the request.
* <i>/api/auth/signin</i> - To sign in using password. JWT Token set in cookie. Has a timeout.
* <i>/api/auth/signout</i> - To Sign Out.

All the following endpoints have a pre-authorize step. Need to be signed in for the same. 

## Food Service Controller

* <i>/api/food/get-all-cat</i> - Get All Categories
* <i>/list-item-by-cat-id/{categoryId}</i> - List all items for a particular category
* <i>/add-items-to-user-cart/{username}</i> - All items to user's cart 
* <i>/get-cart-entries-for-user/{username}</i> - Get all items (and their inventory count) in user's cart 
* <i>/checkout-cart/{username}</i> - Checkout user cart and create an order id.
* <i>/order-history/{username}</i> - Get order history for a user



