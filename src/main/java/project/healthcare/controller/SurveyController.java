package project.healthcare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.healthcare.dto.UserDTO;
import project.healthcare.entity.PillEntity;
import project.healthcare.entity.SurveyEntity;
import project.healthcare.repository.PillRepository;
import project.healthcare.repository.SurveyTableRepository;
import project.healthcare.service.UserService;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class SurveyController {
    @Autowired
    private SurveyTableRepository surveyTableRepository;
    @Autowired
    private PillRepository pillRepository;
    @Autowired
    private UserService userService;

    public SurveyController() {
    }

    @GetMapping("/survey")
    public String showSurveyForm(Model model) {
        return "survey";
    }

    @PostMapping("/survey/submit")
    public String submitSurvey(
            @RequestParam("nutrient_necessity") String nutrientNecessity,
            @RequestParam("age_group") String ageGroup,
            @RequestParam("nutrient_frequency") String nutrientFrequency,
            @RequestParam("nutrient_form") String nutrientForm,
            @RequestParam("discomfort_area") String discomfortArea,
            @RequestParam("desired_function") String desiredFunction,
            @RequestParam("desired_ingredients") String[] desiredIngredients,
            Model model
    ) {
        SurveyEntity surveyEntity = new SurveyEntity();

        UserDTO user = userService.getCurrentUser();

        surveyEntity.setName(user.getUName());
        surveyEntity.setEmail(user.getUserId());
        surveyEntity.setNutrient_necessity(nutrientNecessity);
        surveyEntity.setAge_group(ageGroup);
        surveyEntity.setNutrient_frequency(nutrientFrequency);
        surveyEntity.setNutrient_form(nutrientForm);
        surveyEntity.setDiscomfort_area(discomfortArea);
        surveyEntity.setDesired_function(desiredFunction);
        surveyEntity.setDesired_ingredients(desiredIngredients);
        surveyEntity.setIngredient(String.join(", ", surveyEntity.getDesired_ingredients()));

        surveyTableRepository.save(surveyEntity);

        PillEntity recommendPill = surResult(discomfortArea, desiredFunction, surveyEntity.getIngredient());

        String image = recommendPill.getImage();
        String product = recommendPill.getProduct();
        String effect = String.join(", ", recommendPill.getEffect());
        String detail = String.join(", ", recommendPill.getDetail());

        model.addAttribute("surveyEntity", surveyEntity);
        model.addAttribute("image", image);
        model.addAttribute("product", product);
        model.addAttribute("effect", effect);
        model.addAttribute("detail", detail);

        return "confirmation";
    }

    public PillEntity surResult(String category, String function, String details ) {
        PillEntity recommendPill = new PillEntity();

        switch (category) {
            case "눈/스트레스/수면":
                if (function.equals("피로회복") || function.equals("운동보조")) {
                    if (details.contains("비타민")) {
                        recommendPill = searchPill("비타민").get(0);
                    } else {
                        recommendPill = searchPill("피로 회복").get(0);
                    }
                } else {
                    if (details.contains("루테인")) {
                        recommendPill = searchPill("루테인").get(0);
                    } else {
                        recommendPill = searchPill("스트레스").get(0);
                    }
                } break;
            case "혈관/혈액순환":
                if (function.equals("노화방지") || function.equals("건강기능")) {
                    if (details.contains("비타민")) {
                        recommendPill = searchPill("비타민").get(0);
                    } else {
                        recommendPill = searchPill("노화 방지").get(0);
                    }
                } else {
                    recommendPill = searchPill("콜레스테롤").get(0);
                }
                break;
            case "체지방/콜레스테롤":
                if (function.equals("건강기능") || function.equals("운동보조")) {
                    if (details.contains("콜라겐")) {
                        recommendPill = searchPill("콜라겐").get(0);
                    } else {
                        recommendPill = searchPill("다이어트").get(0);
                    }
                } else {
                    recommendPill = searchPill("콜레스테롤").get(0);
                }
                break;
            case "피부/장":
                if (function.equals("노화방지")) {
                    if (details.contains("루테인")) {
                        recommendPill = searchPill("루테인").get(0);
                    } else {
                        recommendPill = searchPill("노화방지").get(0);
                    }
                } else {
                    if (details.contains("유산균(프로바이오틱스)")) {
                        recommendPill = searchPill("유산균").get(0);
                    } else {
                        recommendPill = searchPill("피부").get(0);
                    }
                }
                break;
            case "없음":
                if (function.equals("피로회복") || function.equals("운동보조")) {
                    if (details.contains("비타민")) {
                        recommendPill = searchPill("비타민").get(0);
                    } else {
                        recommendPill = searchPill("카제인").get(0);
                    }
                } else {
                    recommendPill = searchPill("건강").get(0);
                }
                break;
        }

        return recommendPill;
    }

    public List<PillEntity> searchPill(String keyword) {
        List<PillEntity> pillList = pillRepository.findAll();
        List<PillEntity> tempPillList = pillRepository.findAll();

        for (PillEntity tempPill : tempPillList) {
            if (tempPill.getCategory().contains(keyword) && !pillList.contains(tempPill)) {
                pillList.add(tempPill);
            } else if (tempPill.getProduct().contains(keyword) && !pillList.contains(tempPill)) {
                pillList.add(tempPill);
            } else if (tempPill.getCompany().contains(keyword) && !pillList.contains(tempPill)) {
                pillList.add(tempPill);
            } else if (Arrays.toString(tempPill.getEffect()).contains(keyword) && !pillList.contains(tempPill)) {
                pillList.add(tempPill);
            } else if (Arrays.toString(tempPill.getDetail()).contains(keyword) && !pillList.contains(tempPill)) {
                pillList.add(tempPill);
            }
        }

        return pillList;
    }
}