# CSC207-project
Repo for our "Minimum 85%" group project for CSC207

Problem Domain:
A secondhand bookselling website.
    
(Potentially: A payment system for shipped books)
* Allow users to create listings to sell books, and search current listings to buy books using filters.
  * Categorize books by genre (e.g. nonfiction, romance), date published, price, etc (see Class below for more info)
* Chat features allowing buyers + sellers to communicate
* Allows users to access location of potential counterparties
* Use of an API to access data about books and make sure buyers provide correct information on their book listing by 
* cross checking it with OpenLibrary listings.


High-Level App Description
A user Interface which allows the user to:
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
* Change email
* Delete profile
* Sign out
* Send payment for shipped books

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
      * Price
      * Condition
      * Picture(s)
  * Use of 2 APIs:
    * OpenLibrary APIs (e.g. ISBN) for book info lookup
    * GoogleChat for chatting feature

Links to API Documentation:
https://openlibrary.org/dev/docs/api/books
https://developers.google.com/chat/api/reference/rest

API Tool: https://hoppscotch.io/

Example: Using ISBN, 9781789543537
https://openlibrary.org/api/books?bibkeys=ISBN:9781789543537&jscmd=data&format=json

Example Response Body Output

{
* "ISBN:9781789543537": {
  * "url": "https://openlibrary.org/books/OL34697347M/Powers_and_Thrones",
  * "key": "/books/OL34697347M",
  * "title": "Powers and Thrones",
  * "subtitle": "A New History of the Middle Ages",
  * "authors": [
    * {
    * "url": "https://openlibrary.org/authors/OL7163768A/Dan_Jones",
    * "name": "Dan Jones"
  * }
  * ],
  * "pagination": "720",
  * "identifiers": {
    * "isbn_13": [
      * "9781789543537"
    * ],
    * "openlibrary": [
      * "OL34697347M"
    * ]
    * },
    * "classifications": {
      * "lc_classifications": [
        * "D117"
      * ]
      * },
  * "publishers": [
    * {
      * "name": "Head of Zeus"
    * }
    * ],
    * "publish_date": "2021",
    * "subjects": [
      * {
        * "name": "World history",
        * "url": "https://openlibrary.org/subjects/world_history"
      * },
      * {
        * "name": "Middle Ages",
        * "url": "https://openlibrary.org/subjects/middle_ages"
      * },
      * {
        * "name": "Medieval Civilization",
        * "url": "https://openlibrary.org/subjects/medieval_civilization"
      * }
    * ]
  * }
* }

Example Java API Call Output (extracting relevant data)

