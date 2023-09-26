package project.healthcare.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Help {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;

//    @Column(name = "DEL_AT", nullable = false)
//    private String delAt;
}
