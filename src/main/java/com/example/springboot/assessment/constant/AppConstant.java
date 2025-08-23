package com.example.springboot.assessment.constant;

public class AppConstant {

    private AppConstant() {
    }
    // API Base Paths
    public static final String API_BASE_PATH = "/api/books";

    // Book Endpoints
    public static final String CREATE_BOOK = "/create";
    public static final String GET_BOOK = "/get/{id}";
    public static final String GET_ALL_BOOKS = "/getAll";
    public static final String UPDATE_BOOK = "/update/{id}";
    public static final String DELETE_BOOK = "/delete/{id}";

    // Success Messages
    public static final String BOOK_CREATED = "Book created successfully!";
    public static final String BOOK_UPDATED = "Book updated successfully!";
    public static final String BOOK_DELETED = "Book deleted successfully!";
    public static final String BOOK_FETCHED = "Book fetched successfully!";
    public static final String SUCCESS = "success";

    // Exception Messages
    public static final String BOOK_NOT_FOUND = "Book not found with id: ";
    public static final String VALIDATION_FAILED = "Validation failed. Please check your input.";
    public static final String ERROR = "error";
}
