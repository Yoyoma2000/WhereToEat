# "Where to eat?" Program

## A database for restaurants

**Why I'm making this program:** 
<br>
I want to make a program that I can use to store information about restaurants. 
I love food, but when my family asks me where we should eat, I blank 9 times out of 10. 
So not only will this program have information about restaurants, it can also help choose for me.
How it chooses can be random, but I could also specify certain types of restaurant before it chooses.
This program is basically for anyone who can never seem to pick a restaurant to eat at, 
or just for anyone who wants information about their local restaurants.
As a database, the program will also sort and filter restaurants if users
just want to see their options.
<br>

*Basic* Functions:
- Stores restaurant informations (arbitrary amount)
- List restaurants
- Determine list size
- Gives restaurant’s information at request
- Save restaurant database
- Load restaurant database

**Special** functions:
- Chooses a restaurant at random
- Chooses a restaurant at random, but within specific filters (by information)
- Lists restaurants, but within specific filters (by information)
- Sorts restaurants by numerical ranking (price, rating, distance)
- Search for restaurant by information (search function)
- Save filter/sort/search status
- Load filter/sort/search status
- findDistance(Location otherLocation) method in location object
- Mark restaurants as favorite
- Determine list size, but within specific filters (by information)

## User Stories

**Phase 1:**
- As a user, I want to be able to store information about a restaurant.
- As a user, I want to be able to see the list of restaurant entries. 
- As a user, I want to be able to see the amount of restaurants in the database.
- As a user, I want to be able to access a restaurant's information from the database.

**Phase 2:**
- As a user, I want to be able to save the database before closing the program.
- As a user, I want to be able to load the database when opening the program.