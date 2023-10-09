package project.healthcare.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.healthcare.Entity.SurveyEntity;
import project.healthcare.Repository.SurveyTableRepository;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/home")
public class SurveyController {
    @Autowired
    private SurveyTableRepository surveyTableRepository;

    @RequestMapping("/main")
    public String main() {
        return "index1";
    }

    @GetMapping("/survey")
    public String showSurveyForm(Model model) {
        // 폼을 보여주는 로직 (선택적)
        return "survey"; // surveyForm.html을 보여줌
    }

    @PostMapping("/submit")
    public String submitSurvey(
            @RequestParam(name = "nutrient_necessity") String nutrientNecessity,
            @RequestParam(name = "age_group") String ageGroup,
            @RequestParam(name = "nutrient_frequency") String nutrientFrequency,
            @RequestParam(name = "nutrient_form") String nutrientForm,
            @RequestParam(name = "discomfort_area") String discomfortArea,
            @RequestParam(name = "desired_function") String desiredFunction,
            @RequestParam(name = "desired_ingredients", required = false) List<String> desiredIngredients
    ) {
        // 폼 데이터 처리 로직
        SurveyEntity response = new SurveyEntity();
        response.setNutrient_necessity(nutrientNecessity);
        response.setAge_group(ageGroup);
        response.setNutrient_frequency(nutrientFrequency);
        response.setNutrient_form(nutrientForm);
        response.setDiscomfort_area(discomfortArea);
        response.setDesired_function(desiredFunction);
        if (desiredIngredients != null) {
            response.setDesired_ingredients(String.join(", ", desiredIngredients));
        }

         surveyTableRepository.save(response); // 엔티티를 데이터베이스에 저장

        return "confirmation"; // 제출 확인 페이지를 보여줌
    }

}