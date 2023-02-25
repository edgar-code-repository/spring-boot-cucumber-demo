Feature: Book feature

  Scenario: Add book to catalog
    Given given a book with id "101" title "Book A" author "John Doe"
    When  the book is added to the catalog
    Then  execution returns success with code 201


  Scenario: Retrieve books from catalog
    Given that we have some new books
    When  the books are added to the catalog
    Then  books are retrieved successfully


