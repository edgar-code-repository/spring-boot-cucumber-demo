package com.example.demo.steps;

import com.example.demo.base.TestBase;
import com.example.demo.dto.BookDTO;
import com.example.demo.response.BookResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

@Slf4j
public class BookSteps extends TestBase {

    private BookDTO bookDTO;
    private List<BookDTO> bookList;
    private ResponseEntity<BookResponse> bookResponse;

    @Given("^given a book with id \"([^\"]*)\" title \"([^\"]*)\" author \"([^\"]*)\"$")
    public void given_a_new_book(String id, String title, String author) {
        log.info("Given a new book with id: " + id);

        bookDTO = new BookDTO();
        bookDTO.setId(id);
        bookDTO.setTitle(title);
        bookDTO.setAuthor(author);
    }

    @Given("^that we have some new books$")
    public void given_some_new_books() {
        log.info("Given some new books...");

        bookList = new ArrayList<>();

        BookDTO bookDTO = new BookDTO();
        bookDTO.setId("1001");
        bookDTO.setTitle("Book 1001");
        bookDTO.setAuthor("author");

        bookList.add(bookDTO);

        bookDTO = new BookDTO();
        bookDTO.setId("1002");
        bookDTO.setTitle("Book 1002");
        bookDTO.setAuthor("author");

        bookList.add(bookDTO);

        bookDTO = new BookDTO();
        bookDTO.setId("1003");
        bookDTO.setTitle("Book 1003");
        bookDTO.setAuthor("author");

        bookList.add(bookDTO);

    }

    @When("^the book is added to the catalog$")
    public void a_book_is_added() {
        String urlAddBook = "http://localhost:8080/books";
        log.info("A book is added calling url: " + urlAddBook);

        HttpEntity<BookDTO> entity = new HttpEntity<>(bookDTO, null);
        this.bookResponse = restTemplate.exchange(urlAddBook, HttpMethod.POST, entity, BookResponse.class);
    }

    @When("^the books are added to the catalog$")
    public void books_are_added() {
        log.info("Books are added...");

        String urlAddBook = "http://localhost:8080/books";

        if (bookList != null && bookList.size() > 0) {
            log.info("Books size: " + bookList.size());

            for (BookDTO book: bookList) {
                HttpEntity<BookDTO> entity = new HttpEntity<>(book, headers);
                ResponseEntity<BookResponse> bookResponse = restTemplate.exchange(urlAddBook, HttpMethod.POST, entity, BookResponse.class);

                log.info("bookResponse status: " + bookResponse.getStatusCode().value());
                if (bookResponse.getStatusCodeValue() != 201) {
                    fail();
                }
            }
        }
        else {
            fail();
        }

    }

    @Then("^execution returns success with code (\\d+)$")
    public void execution_returns_code(int httpCode) {
        log.info("Execution returns with code : " + httpCode);

        if (bookResponse != null) {
            assertThat(bookResponse.getStatusCodeValue()).isEqualTo(httpCode);
        } else {
            fail();
        }
    }

    @Then("^books are retrieved successfully$")
    public void books_retrieved_successfully() {
        log.info("Books retrieved succesfully...");

        if (bookList != null && bookList.size() > 0) {
            for (BookDTO book: bookList) {
                String urlGetBook = "http://localhost:8080/books/" + book.getId();
                HttpEntity entity = new HttpEntity<>(null, null);
                ResponseEntity<BookResponse> bookResponse = restTemplate.exchange(urlGetBook, HttpMethod.GET, null, BookResponse.class);

                if (bookResponse.getStatusCodeValue() != 200 ) {
                    fail();
                }
                else {
                    assertThat(bookResponse.getBody().getBookDTO() != null);
                    assertThat(bookResponse.getBody().getBookDTO().getId() == book.getId());
                }

            }
        }
        else {
            fail();
        }

    }

}
