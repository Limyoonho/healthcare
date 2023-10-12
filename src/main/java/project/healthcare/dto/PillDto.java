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

    private String effect;

    private String detail;

    private String image;

    public static PillDto toPillDto(PillEntity pillEntity) {
        PillDto pillDto = new PillDto();

        pillDto.setId(pillEntity.getId());
        pillDto.setCompany(pillEntity.getCompany());
        pillDto.setProduct(pillDto.getProduct());
        pillDto.setCategory(pillDto.getCategory());
        pillDto.setEffect(pillDto.getEffect());
        pillDto.setDetail(pillDto.getDetail());
        pillDto.setImage(pillDto.getImage());

        return pillDto;
    }
}