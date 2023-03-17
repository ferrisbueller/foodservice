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

[Link](https://api.postman.com/collections/10129144-ee2eff3c-134f-49c1-8a6e-06cb4e112004?access_key=PMAT-01GVNKVD229SMZZMYV7DQ3839S) to a sample request postman collection.

* <b>Challenges to scale the service horizontally</b> : Even if we deploy multiple servers with the same application code, database is centralized and will be the single point of contention. To achieve high performance, eventually the database will have to be sharded. Some ideas for sharding the data, all the user related data for example user info, cart items , order history can sharded via hashing the the user-id. All the inventory related data for e.g. category , items can be replicated at each of the user shards for good data locality. However this will lead to usual consistency problems for replicated data. 
* Currently the service only allows searching the items through category based navigation. We can speed up this search by tokenising item names/titles/descriptions and create an <b>inverted text index</b> over the same information to discover items based on keyword search. 
* In the current implementation the entire checkout happens in a single transaction. This could lead to bad user experience two ways : latency to acquire relevant locks to carry out the transaction, plus frequent failure of transactions including popular items, even though the items were available when adding them to the cart. One way to fail fast (in case of eventual inventory unavailability) is to maintain a in-memory count of various item inventories and make cache write through. The orders in progress will make changes to cache as they are making new entries in the database. If the transaction fails, they will adjust the inventory counts accordingly (keeping track of which records were updated in a sorted list of items). Meanwhile, the orders in queue  can continuously poll the cache for inventory availability and fail fast. This might lead the false failures which might be ok, given the user will go back to the item search interface, to look for a similar item and find that the item is actually available and try again. The user can also be given the option to exclude non available items and proceed with the rest of the items. There has to be a background process to ensure eventual consistency in the in memory cache.

