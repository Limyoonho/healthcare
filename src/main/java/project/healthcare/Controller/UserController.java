package project.healthcare.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import project.healthcare.dto.UserDTO;
import project.healthcare.Service.UserService;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    @PostMapping("/join")
    public String join(UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }
}
