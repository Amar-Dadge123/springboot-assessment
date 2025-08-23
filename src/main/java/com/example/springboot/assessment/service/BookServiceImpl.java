package com.example.springboot.assessment.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.springboot.assessment.constant.AppConstant;
import com.example.springboot.assessment.dto.BookRequestDTO;
import com.example.springboot.assessment.dto.BookResponseDTO;
import com.example.springboot.assessment.entity.Book;
import com.example.springboot.assessment.exception.ResourceNotFoundException;
import com.example.springboot.assessment.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {
        Book book = modelMapper.map(bookRequestDTO, Book.class);
        Book savedBook = bookRepository.save(book);
        return modelMapper.map(savedBook, BookResponseDTO.class);
    }

    @Override
    public BookResponseDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.BOOK_NOT_FOUND + id));
        return modelMapper.map(book, BookResponseDTO.class);
    }

    @Override
    public List<BookResponseDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(book -> modelMapper.map(book, BookResponseDTO.class))
                .toList();
    }

    @Override
    public BookResponseDTO updateBook(Long id, BookRequestDTO bookRequestDTO) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.BOOK_NOT_FOUND + id));

        modelMapper.map(bookRequestDTO, existingBook);

        Book updatedBook = bookRepository.save(existingBook);
        return modelMapper.map(updatedBook, BookResponseDTO.class);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.BOOK_NOT_FOUND + id));
        bookRepository.delete(book);
    }

}
