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
import com.example.springboot.assessment.dto.BookRequestDTO;
import com.example.springboot.assessment.dto.BookResponseDTO;
import com.example.springboot.assessment.dto.Response;
import com.example.springboot.assessment.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping(AppConstant.API_BASE_PATH)
@Tag(name = "Book API", description = "Endpoints for managing books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // CREATE
    @PostMapping(AppConstant.CREATE_BOOK)
    @Operation(
            summary = "Create a new Book",
            description = "Adds a new book to the system. Required fields: title, author, publicationYear"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Book created successfully",
            content = @Content(schema = @Schema(implementation = Response.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid input data - validation failed",
            content = @Content
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content
    )
    public ResponseEntity<Response<BookResponseDTO>> createBook(
            @Parameter(
                    description = "Book request object",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = BookRequestDTO.class),
                            examples = @ExampleObject(
                                    name = "Sample Book Request",
                                    summary = "Example of a valid book creation request",
                                    value = """
                    {
                        "title": "Gitanjali",
                        "author": "Rabindranath Tagore",
                        "publicationYear": 1910
                        
                    }
                    """
                            )
                    )
            )
            @Valid @RequestBody BookRequestDTO bookRequestDTO) {
        BookResponseDTO createdBook = bookService.createBook(bookRequestDTO);
        return ResponseEntity.ok(new Response<>(AppConstant.SUCCESS, AppConstant.BOOK_CREATED, createdBook));
    }

    // GET BY ID
    @GetMapping(AppConstant.GET_BOOK)
    @Operation(
            summary = "Get book by ID",
            description = "Retrieves a specific book by its unique identifier"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Book found successfully",
            content = @Content(schema = @Schema(implementation = Response.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Book not found with the given ID",
            content = @Content
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content
    )
    public ResponseEntity<Response<BookResponseDTO>> getBookById(
            @Parameter(
                    description = "ID of the book to be retrieved",
                    required = true,
                    example = "1",
                    schema = @Schema(type = "integer", format = "int64")
            )
            @PathVariable Long id) {
        BookResponseDTO book = bookService.getBookById(id);
        return ResponseEntity.ok(new Response<>(AppConstant.SUCCESS, AppConstant.BOOK_FETCHED, book));
    }

    // GET ALL
    @GetMapping(AppConstant.GET_ALL_BOOKS)
    @Operation(
            summary = "Get all books",
            description = "Retrieves a list of all books available in the system"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Books retrieved successfully",
            content = @Content(schema = @Schema(implementation = Response.class))
    )
    @ApiResponse(
            responseCode = "204",
            description = "No books found",
            content = @Content
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content
    )
    public ResponseEntity<Response<List<BookResponseDTO>>> getAllBooks() {
        List<BookResponseDTO> books = bookService.getAllBooks();
        return ResponseEntity.ok(new Response<>(AppConstant.SUCCESS, AppConstant.BOOK_FETCHED, books));
    }

    // UPDATE
    @PutMapping(AppConstant.UPDATE_BOOK)
    @Operation(
            summary = "Update book by ID",
            description = "Updates an existing book with the provided details. All fields will be updated."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Book updated successfully",
            content = @Content(schema = @Schema(implementation = Response.class))
    )
    @ApiResponse(
            responseCode = "400",
            description = "Invalid input data - validation failed",
            content = @Content
    )
    @ApiResponse(
            responseCode = "404",
            description = "Book not found with the given ID",
            content = @Content
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content
    )
    public ResponseEntity<Response<BookResponseDTO>> updateBook(
            @Parameter(
                    description = "ID of the book to be updated",
                    required = true,
                    example = "1",
                    schema = @Schema(type = "integer", format = "int64")
            )
            @PathVariable Long id,
            @Parameter(
                    description = "Updated book details",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = BookRequestDTO.class),
                            examples = @ExampleObject(
                                    name = "Sample Update Request",
                                    summary = "Example of a valid book update request",
                                    value = """
                    {
                        "title": "Spring Boot in Action - Updated Edition",
                        "author": "Craig Walls",
                        "publicationYear": 2023
                    }
                    """
                            )
                    )
            )
            @Valid @RequestBody BookRequestDTO bookRequestDTO) {
        BookResponseDTO updatedBook = bookService.updateBook(id, bookRequestDTO);
        return ResponseEntity.ok(new Response<>(AppConstant.SUCCESS, AppConstant.BOOK_UPDATED, updatedBook));
    }

    // DELETE
    @DeleteMapping(AppConstant.DELETE_BOOK)
    @Operation(
            summary = "Delete book by ID",
            description = "Permanently deletes a specific book by its unique identifier"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Book deleted successfully",
            content = @Content(schema = @Schema(implementation = Response.class))
    )
    @ApiResponse(
            responseCode = "404",
            description = "Book not found with the given ID",
            content = @Content
    )
    @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content
    )
    public ResponseEntity<Response<Void>> deleteBook(
            @Parameter(
                    description = "ID of the book to be deleted",
                    required = true,
                    example = "1",
                    schema = @Schema(type = "integer", format = "int64")
            )
            @PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok(new Response<>(AppConstant.SUCCESS, AppConstant.BOOK_DELETED, null));
    }
}
