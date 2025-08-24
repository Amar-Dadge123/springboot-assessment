package com.example.springboot.assessment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Book request data transfer object")
public class BookRequestDTO {

    @NotBlank(message = "Title is required")
    @Schema(description = "Book title", example = "Gitangli", required = true)
    private String title;

    @NotBlank(message = "Author is required")
    @Schema(description = "Book author", example = "Rabindranath Tagore", required = true)
    private String author;

    @NotNull(message = "Publication year is required")
    @Min(value = 1500, message = "Publication year must be after 1500")
    @Max(value = 2025, message = "Publication year cannot be in the future")
    @Schema(description = "Publication year", example = "2023")
    private Integer publicationYear;
}
