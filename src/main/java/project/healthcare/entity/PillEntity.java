package project.healthcare.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@Data
@Builder
@Entity
@Table(name = "pill")
public class PillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "product", nullable = false)
    private String product;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "effect", nullable = false, length = 3000)
    private String[] effect;

    @Column(name = "detail", nullable = false, length = 3000)
    private String[] detail;

    @Column(name = "image")
    private String image;

}