Feature: Book feature

  Scenario: Add book to catalog
    Given given a book with id "101" title "Book A" author "John Doe" and published in 1980
    When  the book is added to the catalog
    Then  execution returns success with code 201


  Scenario: Retrieve books from catalog
    Given given that we have some new books
    When  the books are added to the catalog
    Then  books are retrieved successfully


  Scenario: Update book from catalog
    Given given a book with id "999" title "Book 999" author "John Doe" and published in 1985
    When the book is added to the catalog
    And the book with id "999" title "Book 999 update" author "John Doe" and published in 1984 is updated
    Then execution returns success with code 200


  Scenario: Delete book from catalog
    Given given a book with id "999" title "Book 999" author "John Doe" and published in 1985
    When the book with id "999" is deleted
    Then execution returns success with code 200


