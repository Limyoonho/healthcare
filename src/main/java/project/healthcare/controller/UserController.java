package project.healthcare.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import project.healthcare.dto.UserDTO;
import project.healthcare.Service.UserService;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/user/save")
    public String saveUser(UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }
}