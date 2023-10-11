package project.healthcare.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PillDto {
    private Long id;

    private String company;

    private String product;

    private String category;

    private String effect;

    private String detail;

    private String image;

}