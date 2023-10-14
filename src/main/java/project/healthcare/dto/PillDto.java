package project.healthcare.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.healthcare.entity.PillEntity;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PillDto {
    private Long id;

    private String company;

    private String product;

    private String category;

    private String[] effect;

    private String[] detail;

    private String image;

    public PillEntity toPillEntity() {
        return PillEntity.builder()
                .id(id)
                .company(company)
                .product(product)
                .category(category)
                .effect(effect)
                .detail(detail)
                .image(image)
                .build();
    }
}