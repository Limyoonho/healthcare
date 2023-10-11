package project.healthcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.healthcare.entity.SurveyEntity;
import project.healthcare.repository.SurveyTableRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Controller
@RequestMapping("/take-care")
public class SurveyController {
    @Autowired
    private SurveyTableRepository surveyTableRepository;

    @GetMapping("/survey")
    public String showSurveyForm(Model model) {
        return "survey";
    }

    @PostMapping("/submit")
    public String submitSurvey(
            @RequestParam("nutrient_necessity") String nutrientNecessity,
            @RequestParam("age_group") String ageGroup,
            @RequestParam("nutrient_frequency") String nutrientFrequency,
            @RequestParam("nutrient_form") String nutrientForm,
            @RequestParam("discomfort_area") String discomfortArea,
            @RequestParam("desired_function") String desiredFunction,
            @RequestParam("desired_ingredients") List<String> desiredIngredients,
            Model model
    ) {
        SurveyEntity surveyEntity = new SurveyEntity(
                nutrientNecessity,
                ageGroup,
                nutrientFrequency,
                nutrientForm,
                discomfortArea,
                desiredFunction,
                desiredIngredients
        );

        List<List<String>> imageUrlsList = getImageUrlsBasedOnSelections(discomfortArea, desiredFunction, desiredIngredients);

        surveyTableRepository.save(surveyEntity);

        model.addAttribute("surveyEntity", surveyEntity);
        model.addAttribute("imageUrlsList", imageUrlsList); // 이미지 URL 리스트를 모델에 추가

        return "confirmation";
    }

    private List<List<String>> getImageUrlsBasedOnSelections(String discomfortArea, String desiredFunction,
                                                             List<String> desiredIngredients) {
        List<List<String>> imageUrlsList = new ArrayList<>();

        // "눈"에 대한 경우의 수
        if ("눈".equals(discomfortArea)) {
            if ("피로회복".equals(desiredFunction) &&
                    desiredIngredients.contains("비타민C") &&
                    desiredIngredients.contains("유산균(프로바이오틱스)")) {
                // 눈, 피로회복, 비타민C, 유산균(프로바이오틱스)
                List<String> imageUrls1 = new ArrayList<>();
                imageUrls1.add("/img/badge.png");
                imageUrls1.add("/img/bitamin.jpeg");
                imageUrls1.add("/img/bitamin.jpeg");
                imageUrls1.add("/img/bitamin.jpeg");
                imageUrls1.add("/img/bitamin.jpeg");
                imageUrls1.add("/img/bitamin.jpeg");
                imageUrlsList.add(imageUrls1);
            } else {
                // 눈, 다른 경우
                List<String> imageUrls2 = new ArrayList<>();
                imageUrls2.add("/img/mail.png");
                imageUrls2.add("/img/mail.png");
                imageUrls2.add("/img/mail.png");
                imageUrls2.add("/img/mail.png");
                imageUrls2.add("/img/mail.png");
                imageUrls2.add("/img/mail.png");

                imageUrlsList.add(imageUrls2);
            }
        }

        // "간"에 대한 경우의 수
        else if ("간".equals(discomfortArea)) {
            if ("운동보조".equals(desiredFunction) &&
                    desiredIngredients.contains("유산균(프로바이오틱스)") &&
                    desiredIngredients.contains("오메가3")) {
                // 간, 운동보조, 유산균(프로바이오틱스), 오메가3
                List<String> imageUrls3 = new ArrayList<>();
                imageUrls3.add("/img/bitamin2.jpeg");
                imageUrls3.add("/img/bitamin3.jpeg");
                imageUrls3.add("/img/bitamin3.jpeg");
                imageUrls3.add("/img/bitamin3.jpeg");
                imageUrls3.add("/img/bitamin3.jpeg");
                imageUrls3.add("/img/bitamin3.jpeg");
                imageUrlsList.add(imageUrls3);
            } else {
                // 간, 다른 경우
                List<String> imageUrls4 = new ArrayList<>();
                imageUrls4.add("/img/mail.png");
                imageUrls4.add("/img/mail.png");
                imageUrls4.add("/img/mail.png");
                imageUrls4.add("/img/mail.png");
                imageUrls4.add("/img/mail.png");
                imageUrls4.add("/img/mail.png");
                imageUrlsList.add(imageUrls4);
            }
        }

        // "폐"에 대한 경우의 수
        else if ("폐".equals(discomfortArea)) {
            if ("노화방지".equals(desiredFunction) &&
                    desiredIngredients.contains("오메가3") &&
                    desiredIngredients.contains("콜라겐")) {
                // 폐, 노화방지, 오메가3, 콜라겐
                List<String> imageUrls5 = new ArrayList<>();
                imageUrls5.add("/img/key.png");
                imageUrls5.add("/img/key.png");
                imageUrls5.add("/img/key.png");
                imageUrls5.add("/img/key.png");
                imageUrls5.add("/img/key.png");
                imageUrls5.add("/img/Lock.png");
                imageUrlsList.add(imageUrls5);
            } else {
                // 폐, 다른 경우
                List<String> imageUrls6 = new ArrayList<>();
                imageUrls6.add("/img/mail.png");
                imageUrls6.add("/img/mail.png");
                imageUrls6.add("/img/mail.png");
                imageUrls6.add("/img/mail.png");
                imageUrls6.add("/img/mail.png");
                imageUrls6.add("/img/mail.png");
                imageUrlsList.add(imageUrls6);
            }
        }

        // "코"에 대한 경우의 수
        else if ("코".equals(discomfortArea)) {
            if ("건강기능".equals(desiredFunction) &&
                    desiredIngredients.contains("비타민C") &&
                    desiredIngredients.contains("아르기닌")) {
                // 코, 건강기능, 비타민C, 아르기닌
                List<String> imageUrls7 = new ArrayList<>();
                imageUrls7.add("/img/map-image.png");
                imageUrls7.add("/img/person.png");
                imageUrls7.add("/img/person.png");
                imageUrls7.add("/img/person.png");
                imageUrls7.add("/img/person.png");
                imageUrls7.add("/img/person.png");
                imageUrlsList.add(imageUrls7);
            } else {
                // 코, 다른 경우
                List<String> imageUrls8 = new ArrayList<>();
                imageUrls8.add("/img/mail.png");
                imageUrls8.add("/img/mail.png");
                imageUrls8.add("/img/mail.png");
                imageUrls8.add("/img/mail.png");
                imageUrls8.add("/img/mail.png");
                imageUrls8.add("/img/mail.png");
                imageUrlsList.add(imageUrls8);
            }
        }

        // "없음"에 대한 경우의 수 (디폴트)
        else {
            // 없음, 다른 경우
            List<String> imageUrlsDefault = new ArrayList<>();
            imageUrlsDefault.add("/img/mail.png");
            imageUrlsDefault.add("/img/mail.png");
            imageUrlsDefault.add("/img/mail.png");
            imageUrlsDefault.add("/img/mail.png");
            imageUrlsDefault.add("/img/mail.png");
            imageUrlsDefault.add("/img/mail.png");
            imageUrlsList.add(imageUrlsDefault);
        }

        return imageUrlsList;
    }
}