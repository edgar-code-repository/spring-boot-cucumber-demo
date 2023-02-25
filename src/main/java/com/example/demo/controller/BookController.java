package com.example.demo.controller;

import com.example.demo.dto.BookDTO;
import com.example.demo.response.BookListResponse;
import com.example.demo.response.BookResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private static List<BookDTO> bookShelf = new ArrayList<>();

    @GetMapping
    public ResponseEntity<BookListResponse> getShelf() {
        String message = "List retrieved!";

        BookListResponse bookListResponse = BookListResponse.builder()
                .message(message)
                .books(bookShelf)
                .build();

        return ResponseEntity.ok().body(bookListResponse);
    }


    @GetMapping("/{bookId}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable String bookId) {

        BookDTO bookDTO = bookShelf.stream()
                .filter(b -> b.getId().equals(bookId))
                .findAny().orElse(null);

        String message = "Book found!";
        if (bookDTO == null) {
            message = "Book not found!";
        }

        BookResponse bookResponse = BookResponse.builder()
                .message(message)
                .bookDTO(bookDTO)
                .build();

        return ResponseEntity.ok().body(bookResponse);
    }

    @PostMapping
    public ResponseEntity<BookResponse> createNewBook(@RequestBody BookDTO bookDTO) {
        bookShelf.add(bookDTO);

        String message = "Book added!";
        BookResponse bookResponse = BookResponse.builder()
                .message(message)
                .bookDTO(bookDTO)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(bookResponse);
    }


    @PutMapping("/{bookId}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable String bookId, @RequestBody BookDTO bookDTO) {
        BookResponse bookResponse = BookResponse.builder().message("Book was updated!").bookDTO(bookDTO).build();
        long count = bookShelf.stream().filter(b -> b.getId().equals(bookId)).count();

        if (count == 0) {
            bookResponse.setMessage("Book was not found!");
        }
        else {
            bookShelf.stream()
                    .filter(b -> b.getId().equals(bookId))
                    .forEach(b -> {
                        b.setTitle(bookDTO.getTitle());
                        b.setAuthor(bookDTO.getAuthor());
                        b.setYear(bookDTO.getYear());
                    });
        }
        return ResponseEntity.ok(bookResponse);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<BookResponse> deleteBook(@PathVariable String bookId) {
        BookResponse bookResponse = BookResponse.builder().message("Book was deleted!").bookDTO(null).build();
        long count = bookShelf.stream().filter(b -> b.getId().equals(bookId)).count();

        if (count == 0) {
            bookResponse.setMessage("Book was not found!");
        }
        else {
            bookShelf = bookShelf.stream()
                    .filter(b -> !b.getId().equals(bookId))
                    .collect(Collectors.toList());
        }
        return ResponseEntity.ok(bookResponse);
    }

}
