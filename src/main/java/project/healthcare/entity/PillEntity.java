package project.healthcare.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@NoArgsConstructor
@Slf4j
@Entity
@Table(name = "pills")
public class PillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "detail", nullable = false)
    private String detil;

    @Column(name = "link", nullable = false)
    private String link;

    @Column(name = "photo", nullable = false)
    private String photo;

    private String[] details;
}
