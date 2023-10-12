package project.healthcare.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "survey")
public class SurveyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "nutrient_necessity", nullable = false)
    private String nutrient_necessity;

    @Column(name = "age_group", nullable = false)
    private String age_group;

    @Column(name = "nutrient_frequency", nullable = false)
    private String nutrient_frequency;

    @Column(name = "nutrient_form", nullable = false)
    private String nutrient_form;

    @Column(name = "discomfort_area", nullable = false)
    private String discomfort_area;

    @Column(name = "desired_function", nullable = false)
    private String desired_function;

    @Column(name = "desired_ingredients", nullable = false)
    private String[] desired_ingredients;

    @Column(name = "ingredient", nullable = false)
    private String ingredient;
}
