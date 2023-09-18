package project.healthcare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PillController {
    @RequestMapping("/home")
    public String test(){
        return "index1";
    }
}
