package com.example.springboot.assessment.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.assessment.constant.AppConstant;
import com.example.springboot.assessment.dto.ApiResponse;
import com.example.springboot.assessment.dto.BookRequestDTO;
import com.example.springboot.assessment.dto.BookResponseDTO;
import com.example.springboot.assessment.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(AppConstant.API_BASE_PATH)

public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // CREATE
    @PostMapping(AppConstant.CREATE_BOOK)
    public ResponseEntity<ApiResponse<BookResponseDTO>> createBook(@Valid @RequestBody BookRequestDTO bookRequestDTO) {
        BookResponseDTO createdBook = bookService.createBook(bookRequestDTO);
        return ResponseEntity.ok(new ApiResponse<>(AppConstant.SUCCESS, AppConstant.BOOK_CREATED, createdBook));
    }

    // GET BY ID
    @GetMapping(AppConstant.GET_BOOK)
    public ResponseEntity<ApiResponse<BookResponseDTO>> getBookById(@PathVariable Long id) {
        BookResponseDTO book = bookService.getBookById(id);
        return ResponseEntity.ok(new ApiResponse<>(AppConstant.SUCCESS, AppConstant.BOOK_FETCHED, book));
    }

    // GET ALL
    @GetMapping(AppConstant.GET_ALL_BOOKS)
    public ResponseEntity<ApiResponse<List<BookResponseDTO>>> getAllBooks() {
        List<BookResponseDTO> books = bookService.getAllBooks();
        return ResponseEntity.ok(new ApiResponse<>(AppConstant.SUCCESS, AppConstant.BOOK_FETCHED, books));
    }

    // UPDATE
    @PutMapping(AppConstant.UPDATE_BOOK)
    public ResponseEntity<ApiResponse<BookResponseDTO>> updateBook(@PathVariable Long id,
            @Valid @RequestBody BookRequestDTO bookRequestDTO) {
        BookResponseDTO updatedBook = bookService.updateBook(id, bookRequestDTO);
        return ResponseEntity.ok(new ApiResponse<>(AppConstant.SUCCESS, AppConstant.BOOK_UPDATED, updatedBook));
    }

    // DELETE
    @DeleteMapping(AppConstant.DELETE_BOOK)
    public ResponseEntity<ApiResponse<Void>> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok(new ApiResponse<>(AppConstant.SUCCESS, AppConstant.BOOK_DELETED, null));
    }

}
