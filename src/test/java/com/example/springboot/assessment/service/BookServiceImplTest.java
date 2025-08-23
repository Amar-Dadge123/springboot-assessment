package com.example.springboot.assessment.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.example.springboot.assessment.constant.AppConstant;
import com.example.springboot.assessment.dto.BookRequestDTO;
import com.example.springboot.assessment.dto.BookResponseDTO;
import com.example.springboot.assessment.entity.Book;
import com.example.springboot.assessment.exception.ResourceNotFoundException;
import com.example.springboot.assessment.repository.BookRepository;

class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    private BookRequestDTO bookRequestDTO;
    private Book book;
    private BookResponseDTO bookResponseDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        bookRequestDTO = new BookRequestDTO();
        bookRequestDTO.setTitle("Test Book");
        bookRequestDTO.setAuthor("Test Author");
        bookRequestDTO.setPublicationYear(2024);

        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setPublicationYear(2024);

        bookResponseDTO = new BookResponseDTO();
        bookResponseDTO.setId(1L);
        bookResponseDTO.setTitle("Test Book");
        bookResponseDTO.setAuthor("Test Author");
        bookResponseDTO.setPublicationYear(2024);

    }

    @Test
    void testCreateBook() {
        when(modelMapper.map(bookRequestDTO, Book.class)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(modelMapper.map(book, BookResponseDTO.class)).thenReturn(bookResponseDTO);

        BookResponseDTO result = bookService.createBook(bookRequestDTO);

        assertThat(result).isEqualTo(bookResponseDTO);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testGetBookById_Success() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(modelMapper.map(book, BookResponseDTO.class)).thenReturn(bookResponseDTO);

        BookResponseDTO result = bookService.getBookById(1L);

        assertThat(result).isEqualTo(bookResponseDTO);
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testGetBookById_NotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            bookService.getBookById(1L);
        });

        assertThat(exception.getMessage()).isEqualTo(AppConstant.BOOK_NOT_FOUND + 1L);
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = new ArrayList<>();
        books.add(book);

        when(bookRepository.findAll()).thenReturn(books);
        when(modelMapper.map(book, BookResponseDTO.class)).thenReturn(bookResponseDTO);

        List<BookResponseDTO> result = bookService.getAllBooks();

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(bookResponseDTO);
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testUpdateBook_Success() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(modelMapper.map(bookRequestDTO, Book.class)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(modelMapper.map(book, BookResponseDTO.class)).thenReturn(bookResponseDTO);
        BookResponseDTO result = bookService.updateBook(1L, bookRequestDTO);
        assertThat(result).isEqualTo(bookResponseDTO);
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void testUpdateBook_NotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            bookService.updateBook(1L, bookRequestDTO);
        });

        assertThat(exception.getMessage()).isEqualTo(AppConstant.BOOK_NOT_FOUND + 1L);
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteBook_Success() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        doNothing().when(bookRepository).delete(book);

        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).delete(book);
    }

    @Test
    void testDeleteBook_NotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            bookService.deleteBook(1L);
        });

        assertThat(exception.getMessage()).isEqualTo(AppConstant.BOOK_NOT_FOUND + 1L);
        verify(bookRepository, times(1)).findById(1L);
    }
}
