package project.healthcare.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;
import project.healthcare.dto.PillDto;
import project.healthcare.dto.UserDTO;
import project.healthcare.entity.PillEntity;
import project.healthcare.entity.SurveyEntity;
import project.healthcare.repository.PillRepository;
import project.healthcare.repository.SurveyTableRepository;
import project.healthcare.service.UserService;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
@Controller
public class BoardController {
    @Autowired
    private PillRepository pillRepository;
    @Autowired
    private PillEntity pillEntity;
    @Autowired
    private SurveyTableRepository surveyTableRepository;
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String main(Model model) {
        String[] products = new String[6];
        String[] images = new String[6];
        String[] company = new String[6];
        String[] effects = new String[6];
        String[] details = new String[6];

        for (int i = 1; i < 7; i++) {
            pillEntity = pillRepository.findById(i);
            products[i-1] = pillEntity.getProduct();
            company[i-1] = pillEntity.getCompany();
            effects[i-1] = String.join(", ", pillEntity.getEffect());
            details[i-1] = String.join(", ", pillEntity.getDetail());

            if (pillEntity.getImage() == null) {
                images[i-1] = null;
            } else {
                images[i-1] = pillEntity.getImage();
            }

            model.addAttribute("product_" + i, products[i-1]);
            model.addAttribute("image_" + i, images[i-1]);
            model.addAttribute("company_" + i, company[i-1]);
            model.addAttribute("effect_" + i, effects[i-1]);
            model.addAttribute("detail_" + i, details[i-1]);
        }
		
        return "main";
    }

    @RequestMapping("/anonyhealth")
    public String anonyhealth() {
        String excer=URLEncoder.encode("5분 운동", StandardCharsets.UTF_8);
        return "redirect:https://www.youtube.com/results?search_query="+excer;
    }

    @RequestMapping("/userhealth")
    public String userhealth() {
        UserDTO user = userService.getCurrentUser();
        SurveyEntity surveyEntity = new SurveyEntity();
        surveyEntity = surveyTableRepository.findByNameAndEmail(user.getUName(), user.getUserId());
        if (surveyEntity==null) {
            String excer = URLEncoder.encode("5분 운동", StandardCharsets.UTF_8);
            return "redirect:https://www.youtube.com/results?search_query=" + excer;
        } else {
            String function = URLEncoder.encode(surveyEntity.getDesired_function() + " 운동", StandardCharsets.UTF_8);
            String excer = URLEncoder.encode("운동", StandardCharsets.UTF_8);
            String link = "https://www.youtube.com/results?search_query=" + function;
            return "redirect:" + link;
        }
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

        List<PillEntity> inCategory = pillRepository.findByCategory(category);
        List<PillEntity> otherPills = new ArrayList<>();

        Random random = new Random();

        while (otherPills.size() < 4 && inCategory.size() > 0) {
            int randomIndex = random.nextInt(inCategory.size());

            PillEntity selectedPill = inCategory.get(randomIndex);

            if (!selectedPill.equals(pillEntity) && !otherPills.contains(selectedPill)) {
                otherPills.add(selectedPill);
            }
        }

        model.addAttribute("image", image);
        model.addAttribute("product", product);
        model.addAttribute("company", company);
        model.addAttribute("category", category);
        model.addAttribute("effect", effects);
        model.addAttribute("detail", details);
        model.addAttribute("otherPills", otherPills);

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

    @RequestMapping("/notice")
    public String notice() {
        return "notice";
    }

    @RequestMapping("/help-board")
    public String helpBoard() {
        return "helpBoard";
    }

    @RequestMapping("/join")
    public String join() {
        return "register";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        String uri=request.getHeader("Referer");

        if (uri==null) {
            Map<String, ?> paramMap = RequestContextUtils.getInputFlashMap(request);
            uri = (String) paramMap.get("referer");

            request.getSession().setAttribute("prevPage", uri);
        } else {
            request.getSession().setAttribute("prevPage",uri);
        }
        return "login";
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