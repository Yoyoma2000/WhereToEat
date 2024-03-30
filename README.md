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
- As a user, I want to be prompted to save right before closing the program.
- As a user, I want to have the program randomly choose a restaurant for me
- As a user, I want to sort the database

# Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by going to 
  add restaurant tab
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by 
  filling in the fields in add restaurant tab and pressing add.
- You can locate my visual component by looking at home screen, buttons, and save/load
- You can save the state of my application by going to file tab and pressing save icon
- You can reload the state of my application by going to load tab and pressing load icon

# Phase 4: Task 2
Sat Mar 30 02:44:02 PDT 2024
Added new restaurant: BBQ King
Sat Mar 30 02:45:29 PDT 2024
Added new restaurant: Pho You
Sat Mar 30 02:45:33 PDT 2024
Reset database to original state
Sat Mar 30 02:45:46 PDT 2024
Sorted database in descending order by: name
Sat Mar 30 02:45:57 PDT 2024
Sorted database in descending order by: rating
Sat Mar 30 02:46:03 PDT 2024
Sorted database in ascending order by: rating
Sat Mar 30 02:46:32 PDT 2024
Attempted search on database
Sat Mar 30 02:46:41 PDT 2024
Toggled favourite for: Pho You
Sat Mar 30 02:46:54 PDT 2024
Added new menu item to: Pho You
Sat Mar 30 02:47:00 PDT 2024
Toggled favourite for: Pho You