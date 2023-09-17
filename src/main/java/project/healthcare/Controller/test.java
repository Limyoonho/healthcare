package project.healthcare.Controller;

import project.healthcare.Entity.TestTable;
import project.healthcare.Service.TestTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class test {

    @Autowired
    TestTableService testTableService; // command left mouse click

    @RequestMapping("/home")
    public String home(Model model){
        List<TestTable> list = testTableService.findAll();
        model.addAttribute("list",list);
        model.addAttribute("hi","hiEveryOne");
    return "index1";
    }

}
