package project.healthcare.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.healthcare.dto.UserDTO;
import project.healthcare.entity.Help;
import project.healthcare.service.HelpService;
import project.healthcare.service.UserService;

@Controller
public class helpController {
    @Autowired
    private HelpService helpService;

    @Autowired
    private UserService userService;

    @GetMapping("/help-board")
    public String question() {
        return "helpBoard";
    }

    @PostMapping("/help/writepro")
    public String helpWritePro(Help help, Model model) {
        helpService.write(help);

        return "redirect:/help/list";
    }

    @GetMapping("/help/list")
    public String helpList(Model model,
                           @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                            String searchKeyword) {

        Page<Help> list = null;

        if(searchKeyword == null) {
            list = helpService.helpList(pageable);
        }else{
            list = helpService.helpSearchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage -4, 1);
        int endPage = Math.min(nowPage + 4, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "helpList";
    }

    @GetMapping("/help/view")
    public String helpView(Model model, Integer id) {
        UserDTO user=userService.getCurrentUser();
        model.addAttribute("view", helpService.helpView(id));
        model.addAttribute("user", user);
        return "helpView";
    }

    @GetMapping("/help/delete")
    public String helpUpdate(Integer id){
        helpService.helpUpdate(id);

        return "redirect:/help/list";
    }

    @GetMapping("/help/modify/{id}")
    public String helpModify(@PathVariable("id") Integer id, Model model){
        model.addAttribute("view", helpService.helpId(id));
        return "helpModify";
    }

    @PostMapping("/help/update/{id}")
    public String helpUpdate(@PathVariable("id") Integer id, Help help){

        Help helptemp = helpService.helpId(id);
        helptemp.setTitle(help.getTitle());
        helptemp.setContent(help.getContent());

        helpService.helpModify(helptemp);

        return "redirect:/help/list";
    }
}