package project.healthcare.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class BoardController {
    @RequestMapping("/take-care")
    public String main(Model model) {
        return "main";
    }

    @RequestMapping("/search-page")
    public String searchPage(@RequestParam("query") String query, Model model) {
        String[] queryList = query.split(" ");

        model.addAttribute("searchKeyword", query);

        return "searchPage";
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
        return "join";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("map")
    public String map() { return "map";}
}
