package com.example.demo.controller;

import com.example.demo.dto.BookDTO;
import com.example.demo.response.BookListResponse;
import com.example.demo.response.BookResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<BookDTO> bookShelf = new ArrayList<>();

    @GetMapping
    public ResponseEntity<BookListResponse> getAllBooks() {
        String message = "List retrieved!";

        BookListResponse bookResponse = BookListResponse.builder()
                .message(message)
                .books(bookShelf)
                .build();

        return ResponseEntity.ok(bookResponse);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable String bookId) {

        BookDTO bookDTO = bookShelf.stream()
                .filter(b -> b.getId().equals(bookId))
                .findAny().orElse(null);

        String message = "Book found!";
        if (bookDTO == null) {
            return ResponseEntity.notFound().build();
        }

        BookResponse bookResponse = BookResponse.builder().message(message).bookDTO(bookDTO).build();
        return ResponseEntity.ok().body(bookResponse);
    }

    @PostMapping
    public ResponseEntity<BookResponse> addNewBook(@RequestBody BookDTO bookDTO) {
        bookShelf.add(bookDTO);

        String message = "Book added!";

        BookResponse bookResponse = BookResponse.builder()
                .message(message)
                .bookDTO(bookDTO)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(bookResponse);
    }

}
