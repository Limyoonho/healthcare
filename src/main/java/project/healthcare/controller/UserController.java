//package project.healthcare.controller;
//
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import project.healthcare.dto.UserDTO;
//import project.healthcare.service.UserService;
//
//
//@Controller
//@AllArgsConstructor
//public class UserController {
//
//    private final UserService userService;
//    @PostMapping("/user/save")
//    public String saveUser(UserDTO userDTO) {
//        userService.saveUser(userDTO);
//        return "redirect:/login";
//    }
//}