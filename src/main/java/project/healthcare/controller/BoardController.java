package project.healthcare.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.healthcare.dto.PillDto;
import project.healthcare.entity.PillEntity;
import project.healthcare.repository.PillRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
public class BoardController {
    @Autowired
    private PillRepository pillRepository;
    @Autowired
    private PillEntity pillEntity;

    @RequestMapping("/take-care")
    public String main(Model model) {
        String[] products = new String[6];
        String[] images = new String[6];

        for (int i = 7; i < 13; i++) {
            pillEntity = pillRepository.findById(i);
            products[i-7] = pillEntity.getProduct();
            if (pillEntity.getImage() == null) {
                images[i-7] = null;
            } else {
                images[i-7] = pillEntity.getImage();
            }

            model.addAttribute("product_" + (i-6), products[i-7]);
            model.addAttribute("image_" + (i-6), images[i-7]);
        }

        return "main";
    }

    @RequestMapping("/pill")
    public String pillDetail(@RequestParam("product") Object product,
                             @RequestParam("company") Object company,
                             @RequestParam("effect") Object effect,
                             @RequestParam("detail") Object detail,
                             @RequestParam("category") Object category,
                             @RequestParam("image") Object image,
                             Model model) throws IOException {

        pillEntity = pillRepository.findByCompanyAndProduct(company, product);

        String effects = String.join(", ", pillEntity.getEffect());
        String details = String.join(", ", pillEntity.getDetail());

        model.addAttribute("image", image);
        model.addAttribute("product", product);
        model.addAttribute("company", company);
        model.addAttribute("category", category);
        model.addAttribute("effect", effects);
        model.addAttribute("detail", details);

        return "pill";
    }

    @RequestMapping("/search-page")
    public String searchPage(@RequestParam("query") String query, Model model) {
        String[] queryList = query.split(" ");

        List<PillEntity> pillList = new ArrayList<>();
        List<PillEntity> tempPillList = pillRepository.findAll();

        for (PillEntity tempPill : tempPillList) {
            for (String keyword : queryList) {
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
        }

        model.addAttribute("searchKeyword", query);
        model.addAttribute("searchCount", pillList.size());
        model.addAttribute("pillList", pillList);

        return "searchPage";
    }

    @RequestMapping("/category/eyes")
    public String eyes(Model model) {
        String category = "눈";

        List<PillEntity> pillList = new ArrayList<>();
        List<PillEntity> tempPillList = pillRepository.findAll();

        for (PillEntity tempPill : tempPillList) {
            if (tempPill.getCategory().equals("눈")) {
                pillList.add(tempPill);
            }
        }

        model.addAttribute("pillList", pillList);

        return "eyes";
    }

    @RequestMapping("/category/skincare")
    public String skincare(Model model) {
        String category = "피부";

        List<PillEntity> pillList = new ArrayList<>();
        List<PillEntity> tempPillList = pillRepository.findAll();

        for (PillEntity tempPill : tempPillList) {
            if (tempPill.getCategory().equals("피부")) {
                pillList.add(tempPill);
            }
        }

        model.addAttribute("pillList", pillList);

        return "skincare";
    }

    @RequestMapping("/category/fat")
    public String fat(Model model) {
        String category = "체지방";

        List<PillEntity> pillList = new ArrayList<>();
        List<PillEntity> tempPillList = pillRepository.findAll();

        for (PillEntity tempPill : tempPillList) {
            if (tempPill.getCategory().equals("체지방")) {
                pillList.add(tempPill);
            }
        }

        model.addAttribute("pillList", pillList);

        return "fat";
    }

    @RequestMapping("/category/blood")
    public String blood_circulation(Model model) {
        String category = "혈관 및 혈액 순환";

        List<PillEntity> pillList = new ArrayList<>();
        List<PillEntity> tempPillList = pillRepository.findAll();

        for (PillEntity tempPill : tempPillList) {
            if (tempPill.getCategory().equals("혈관 및 혈액 순환")) {
                pillList.add(tempPill);
            }
        }

        model.addAttribute("pillList", pillList);

        return "blood";
    }

    @RequestMapping("/category/intestine")
    public String intestine(Model model) {
        String category = "장";

        List<PillEntity> pillList = new ArrayList<>();
        List<PillEntity> tempPillList = pillRepository.findAll();

        for (PillEntity tempPill : tempPillList) {
            if (tempPill.getCategory().equals("장")) {
                pillList.add(tempPill);
            }
        }

        model.addAttribute("pillList", pillList);

        return "intestine";
    }

    @RequestMapping("/category/sleep")
    public String sleep(Model model) {
        String category = "스트레스 및 수면";

        List<PillEntity> pillList = new ArrayList<>();
        List<PillEntity> tempPillList = pillRepository.findAll();

        for (PillEntity tempPill : tempPillList) {
            if (tempPill.getCategory().equals("스트레스 및 수면")) {
                pillList.add(tempPill);
            }
        }

        model.addAttribute("pillList", pillList);

        return "sleep";
    }

    @RequestMapping("/category/cholesterol")
    public String cholesterol(Model model) {
        String category = "콜레스테롤";

        List<PillEntity> pillList = new ArrayList<>();
        List<PillEntity> tempPillList = pillRepository.findAll();

        for (PillEntity tempPill : tempPillList) {
            if (tempPill.getCategory().equals("콜레스테롤")) {
                pillList.add(tempPill);
            }
        }

        model.addAttribute("pillList", pillList);

        return "cholesterol";
    }

    @RequestMapping("/portfolio")
    public String portfolio() {
        return "portfolio";
    }

    @RequestMapping("/notice")
    public String notice() {
        return "notice";
    }

    @RequestMapping("/help-board")
    public String helpBoard() {
        return "helpBoard";
    }

    @RequestMapping("/my-page")
    public String myPage() {
        return "myPage";
    }

    @RequestMapping("/join")
    public String join() {
        return "register";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/map")
    public String map() { return "map";}

    @RequestMapping("/health-map")
    public String healthMap() { return "healthMap";}
}