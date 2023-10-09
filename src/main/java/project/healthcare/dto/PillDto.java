package project.healthcare.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PillDto {
    private String company;

    private String product;

    private String baseStandard;

    private String effect;

    private String srvUse;

    private String intakeHint;

    private String image;

    private String exDate;
}