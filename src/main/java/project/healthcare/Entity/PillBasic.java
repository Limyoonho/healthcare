package project.healthcare.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Data
@Builder
@ToString
@Entity
@Table(name = "pill_basic")
public class PillBasic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "product", nullable = false)
    private String product;

    @Column(name = "ex_date", nullable = false)
    private String exDate;

    @Builder
    public PillBasic(String company, String product, String exDate){
        this.company = company;
        this.product = product;
        this.exDate = exDate;
    }
}