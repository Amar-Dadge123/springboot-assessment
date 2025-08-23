package com.example.springboot.assessment.controller;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.springboot.assessment.constant.AppConstant;
import com.example.springboot.assessment.dto.ApiResponse;
import com.example.springboot.assessment.dto.BookRequestDTO;
import com.example.springboot.assessment.dto.BookResponseDTO;
import com.example.springboot.assessment.service.BookService;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private BookRequestDTO bookRequestDTO;
    private BookResponseDTO bookResponseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        bookRequestDTO = new BookRequestDTO();
        bookRequestDTO.setTitle("Test Book");
        bookRequestDTO.setAuthor("Test Author");
        bookRequestDTO.setPublicationYear(2024);

        bookResponseDTO = new BookResponseDTO();
        bookResponseDTO.setId(1L);
        bookResponseDTO.setTitle("Test Book");
        bookResponseDTO.setAuthor("Test Author");
        bookResponseDTO.setPublicationYear(2024);

    }

    @Test
    void testCreateBook() {
        when(bookService.createBook(bookRequestDTO)).thenReturn(bookResponseDTO);

        ResponseEntity<ApiResponse<BookResponseDTO>> response = bookController.createBook(bookRequestDTO);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(AppConstant.SUCCESS);
        assertThat(response.getBody().getMessage()).isEqualTo(AppConstant.BOOK_CREATED);
        assertThat(response.getBody().getData()).isEqualTo(bookResponseDTO);

        verify(bookService, times(1)).createBook(bookRequestDTO);
    }

    @Test
    void testGetBookById() {
        when(bookService.getBookById(1L)).thenReturn(bookResponseDTO);

        ResponseEntity<ApiResponse<BookResponseDTO>> response = bookController.getBookById(1L);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getData().getId()).isEqualTo(1L);

        verify(bookService, times(1)).getBookById(1L);
    }

    @Test
    void testGetAllBooks() {
        List<BookResponseDTO> books = Arrays.asList(bookResponseDTO);
        when(bookService.getAllBooks()).thenReturn(books);

        ResponseEntity<ApiResponse<List<BookResponseDTO>>> response = bookController.getAllBooks();

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getData()).hasSize(1);
        assertThat(response.getBody().getData().get(0)).isEqualTo(bookResponseDTO);

        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    void testUpdateBook() {
        when(bookService.updateBook(1L, bookRequestDTO)).thenReturn(bookResponseDTO);

        ResponseEntity<ApiResponse<BookResponseDTO>> response = bookController.updateBook(1L, bookRequestDTO);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).isEqualTo(AppConstant.BOOK_UPDATED);

        verify(bookService, times(1)).updateBook(1L, bookRequestDTO);
    }

    @Test
    void testDeleteBook() {
        doNothing().when(bookService).deleteBook(1L);

        ResponseEntity<ApiResponse<Void>> response = bookController.deleteBook(1L);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getMessage()).isEqualTo(AppConstant.BOOK_DELETED);
        assertThat(response.getBody().getData()).isNull();

        verify(bookService, times(1)).deleteBook(1L);
    }
}
