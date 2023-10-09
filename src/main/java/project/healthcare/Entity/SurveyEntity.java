package project.healthcare.Entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@Entity
@Table(name = "survey")
public class SurveyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public String getNutrient_necessity() {
        return nutrient_necessity;
    }

    public void setNutrient_necessity(String nutrient_necessity) {
        this.nutrient_necessity = nutrient_necessity;
    }

    public String getAge_group() {
        return age_group;
    }

    public void setAge_group(String age_group) {
        this.age_group = age_group;
    }

    public String getNutrient_frequency() {
        return nutrient_frequency;
    }

    public void setNutrient_frequency(String nutrient_frequency) {
        this.nutrient_frequency = nutrient_frequency;
    }

    public String getNutrient_form() {
        return nutrient_form;
    }

    public void setNutrient_form(String nutrient_form) {
        this.nutrient_form = nutrient_form;
    }

    public String getDiscomfort_area() {
        return discomfort_area;
    }

    public void setDiscomfort_area(String discomfort_area) {
        this.discomfort_area = discomfort_area;
    }

    public String getDesired_function() {
        return desired_function;
    }

    public void setDesired_function(String desired_function) {
        this.desired_function = desired_function;
    }

    public String getDesired_ingredients() {
        return desired_ingredients;
    }

    public void setDesired_ingredients(String desired_ingredients) {
        this.desired_ingredients = desired_ingredients;
    }

    @Column(name = "desired_ingredients", nullable = false)
    private String desired_ingredients;

}