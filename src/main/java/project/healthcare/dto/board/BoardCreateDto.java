package project.healthcare.dto.board;

import lombok.Data;

@Data
public class BoardCreateDto {
    private String name;
    private String title;
    private String content;
}
