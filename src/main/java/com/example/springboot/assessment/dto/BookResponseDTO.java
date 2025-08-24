package com.example.springboot.assessment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Book response data transfer object")
public class BookResponseDTO {

    @Schema(description = "Book ID", example = "1")
    private Long id;
    @Schema(description = "Book title", example = "Spring Boot in Action")
    private String title;
    @Schema(description = "Book author", example = "Craig Walls")
    private String author;
    @Schema(description = "Publication year", example = "2023")
    private Integer publicationYear;
}
