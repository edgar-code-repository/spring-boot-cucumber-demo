package com.example.demo.response;

import com.example.demo.dto.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookListResponse {

    private String message;

    private List<BookDTO> books;

}
