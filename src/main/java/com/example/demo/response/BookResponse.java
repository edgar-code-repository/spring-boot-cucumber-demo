package com.example.demo.response;

import com.example.demo.dto.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    private String message;

    private BookDTO bookDTO;

}
