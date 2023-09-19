package project.healthcare.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PillDto {
    private String name;
    private String category;
    private String detail;
    private String link;
    private String photo;
    private String[] details;
}
