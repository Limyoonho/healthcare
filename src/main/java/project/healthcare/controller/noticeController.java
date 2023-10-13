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
import project.healthcare.entity.Notice;
import project.healthcare.service.NoticeService;
import project.healthcare.service.UserService;

@Controller
public class noticeController {
    @Autowired
    private NoticeService noticeService;

    @Autowired
    private UserService userService;

    @GetMapping("/notice-board")
    public String question() {
        return "noticeBoard";
    }

    @PostMapping("/notice/writepro")
    public String noticeWritePro(Notice notice, Model model) {
        noticeService.write(notice);

        return "redirect:/notice/list";
    }

    @GetMapping("/notice/list")
    public String noticeList(Model model,
                           @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                            String searchKeyword) {

        Page<Notice> list = null;

        if(searchKeyword == null) {
            list = noticeService.noticeList(pageable);
        }else{
            list = noticeService.noticeSearchList(searchKeyword, pageable);
        }

        UserDTO user=userService.getCurrentUser();
        model.addAttribute("user",user);

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage -4, 1);
        int endPage = Math.min(nowPage + 4, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "noticeList";
    }

    @GetMapping("/notice/view")
    public String noticeView(Model model, Integer id) {
        UserDTO user=userService.getCurrentUser();
        model.addAttribute("view", noticeService.noticeView(id));
        model.addAttribute("user", user);
        return "noticeView";
    }

    @GetMapping("/notice/delete")
    public String noticeUpdate(Integer id){
        noticeService.noticeUpdate(id);

        return "redirect:/notice/list";
    }

    @GetMapping("/notice/modify/{id}")
    public String noticeModify(@PathVariable("id") Integer id, Model model){
        model.addAttribute("view", noticeService.noticeId(id));
        return "noticeModify";
    }

    @PostMapping("/notice/update/{id}")
    public String noticeUpdate(@PathVariable("id") Integer id, Notice notice){

        Notice noticetemp = noticeService.noticeId(id);
        noticetemp.setTitle(notice.getTitle());
        noticetemp.setContent(notice.getContent());

        noticeService.noticeModify(noticetemp);

        return "redirect:/notice/list";
    }
}