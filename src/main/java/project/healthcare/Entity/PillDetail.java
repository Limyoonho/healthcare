package project.healthcare.Entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Data
@Builder
@Entity
@Table(name = "pill_detail")
public class PillDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "company", nullable = false)
    private String company;


    @Column(name = "product", nullable = false)
    private String product;

    @Column(name = "baseStandard", nullable = false)
    private String baseStandard;

    @Column(name = "effect", nullable = false)
    private String effect;

    @Column(name = "SRV_USE", nullable = false)
    private String srvUse;

    @Column(name = "INTAKE_HINT", nullable = false)
    private String intakeHint;

    @Column(name = "image")
    private String image;

}