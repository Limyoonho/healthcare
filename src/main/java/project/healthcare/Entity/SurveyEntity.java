package project.healthcare.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
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

    @Column(name = "image_url")
    private String imageUrl;

    @ElementCollection
    @CollectionTable(name = "desired_ingredients", joinColumns = @JoinColumn(name = "survey_id"))
    @Column(name = "ingredient")
    private List<String> desiredIngredients;




    public SurveyEntity(
            String nutrientNecessity,
            String ageGroup,
            String nutrientFrequency,
            String nutrientForm,
            String discomfortArea,
            String desiredFunction,
            List<String> desiredIngredients
    ) {
        this.nutrient_necessity = nutrientNecessity;
        this.age_group = ageGroup;
        this.nutrient_frequency = nutrientFrequency;
        this.nutrient_form = nutrientForm;
        this.discomfort_area = discomfortArea;
        this.desired_function = desiredFunction;
        this.desiredIngredients = desiredIngredients;
    }


    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getAge_group() {
        return  age_group;
    }

    public String getDiscomfort_area(){return discomfort_area;}
}