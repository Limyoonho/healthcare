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
    private String name;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "detail", nullable = false)
    private String detail;

    @Column(name = "link", nullable = false)
    private String link;

    @Column(name = "photo", nullable = false)
    private String photo;

    private String[] details;
}
