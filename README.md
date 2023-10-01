# CSC207-project

## Problem Domain:
A secondhand book-selling website.

* Allow users to create listings to sell books, and search current listings to buy books using filters.
  * Categorize books by genre (e.g. nonfiction, romance), date published, price, etc (see Class below for more info)
* Chat features allowing buyers + sellers to communicate
* Allows users to access location of potential counterparties
* Use of an API to access data about books and make sure buyers provide correct information on their book listing by 
* Cross-checking it with OpenLibrary listings.


## High-Level App Description
* A user Interface which allows the user to:
* Sign in/create an account
* Search for books to buy by typing in title/author/isbn AND/OR apply filters (genre, location, price, etc)
  * Choose from the list of books that appear from the search
    *   See info about said book (price, amount of listings, etc)
      * Click on a listing to view it
        * Option to chat with the seller
* Option to create listing
  - Enter title of the book and pick existing edition from OpenLibrary list
    - Enter additional information (pictures, condition, price)
* View chats
* View profile
  - Change email
  - Delete profile
  - Sign out
* Send payment for shipped books (potential)

* Classes:
* Users
  * Buyers
  * Sellers (who may also be buyers, and vice-versa)
  * Employees/Admin
  * Parameters:
    * unique ID/username
    * Email
    * Password
    * location (for search) (applicable to buyers/sellers only)
    * Performance rating (applicable to buyers/sellers only)
  * Books
    * Parameters:
      * Title
      * Genre
      * Year
      * Author
      * Summary
      * ISBN
      * Amount in stock
      * Listings (Array):
        - Price
        - Condition
        - Picture(s)
  * Use of 2 APIs:
    * OpenLibrary APIs (e.g. ISBN) for book info lookup
    * GoogleChat for chatting feature

## Links to API Documentation:
https://openlibrary.org/dev/docs/api/books

https://developers.google.com/chat/api/reference/rest

## API Tool: https://hoppscotch.io/

Example: Using ISBN, 9781789543537
https://openlibrary.org/api/books?bibkeys=ISBN:9781789543537&jscmd=data&format=json
![Screenshot 2023-10-01 012331](https://github.com/Nourdles/CSC207-project/assets/128332497/e6df3612-a576-461b-ac7c-8980935f76bb)

## Example Response Body Output

**For OpenLibrary:**
  * add screenshot once we have the code (expected output temporarily moved to `package.json`)

**For Google Chat**
  * add screenshot once we have the code

