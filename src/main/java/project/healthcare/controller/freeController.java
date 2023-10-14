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
import project.healthcare.entity.Free;
import project.healthcare.service.FreeService;
import project.healthcare.service.UserService;

@Controller
public class freeController {
    @Autowired
    private FreeService freeService;

    @Autowired
    private UserService userService;

    @GetMapping("/free-board")
    public String question() {
        return "freeBoard";
    }

    @PostMapping("/free/writepro")
    public String freeWritePro(Free free, Model model) {
        freeService.write(free);

        return "redirect:/free/list";
    }

    @GetMapping("/free/list")
    public String freeList(Model model,
                           @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                            String searchKeyword) {

        Page<Free> list = null;

        if(searchKeyword == null) {
            list = freeService.freeList(pageable);
        }else{
            list = freeService.freeSearchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage -4, 1);
        int endPage = Math.min(nowPage + 4, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "freeList";
    }

    @GetMapping("/free/view")
    public String freeView(Model model, Integer id) {
        UserDTO user=userService.getCurrentUser();
        model.addAttribute("view", freeService.freeView(id));
        model.addAttribute("user", user);
        return "freeView";
    }

    @GetMapping("/free/delete")
    public String freeUpdate(Integer id){
        freeService.freeUpdate(id);

        return "redirect:/free/list";
    }

    @GetMapping("/free/modify/{id}")
    public String freeModify(@PathVariable("id") Integer id, Model model){
        model.addAttribute("view", freeService.freeId(id));
        return "freeModify";
    }

    @PostMapping("/free/update/{id}")
    public String freeUpdate(@PathVariable("id") Integer id, Free free){

        Free freetemp = freeService.freeId(id);
        freetemp.setTitle(free.getTitle());
        freetemp.setContent(free.getContent());

        freeService.freeModify(freetemp);

        return "redirect:/free/list";
    }
}