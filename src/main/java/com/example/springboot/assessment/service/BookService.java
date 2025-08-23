package com.example.springboot.assessment.service;

import java.util.List;

import com.example.springboot.assessment.dto.BookRequestDTO;
import com.example.springboot.assessment.dto.BookResponseDTO;

public interface BookService {

    BookResponseDTO createBook(BookRequestDTO bookRequestDTO);

    BookResponseDTO getBookById(Long id);

    List<BookResponseDTO> getAllBooks();

    BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO);

    void deleteBook(Long id);
}
