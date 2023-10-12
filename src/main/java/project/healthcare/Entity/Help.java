package project.healthcare.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@Entity
@Data
public class Help {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="title", length=32)
    private String title;

    @Column(name="content", length=65535)
    private String content;

//    @Column(name = "DEL_AT", nullable = false)
//    private String delAt;
    @Column(name = "create_date")
    private String create_date;

    @Column(name = "view_count", nullable = false, columnDefinition = "int default 0")
    private Integer view_count;

    @Column(name="user", nullable=false)
    private String write_user;

}