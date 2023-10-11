package project.healthcare.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.healthcare.dto.PasswordChangeForm;
import project.healthcare.dto.UserDTO;
import project.healthcare.dto.UsernameChangeForm;
import project.healthcare.service.UserService;


@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/user/save")
    public String saveUser(UserDTO userDTO) {
        userService.saveUser(userDTO);
        return "redirect:/login";
    }

    @GetMapping("/mypage")
    public String userProfile(Model model) {
        UserDTO user=userService.getCurrentUser();
        model.addAttribute("user",user);
        return "/mypage";
    }
    @GetMapping("/mypage/changepw")
    public String showChangePasswordForm(Model model) {
        model.addAttribute("passwordChangeForm",new PasswordChangeForm());
        return "changePw";
    }

    @PostMapping("/mypage/changepw")
    public String processChangePassword(@ModelAttribute("passwordChangeForm") PasswordChangeForm form) {
        UserDTO currentUser=userService.getCurrentUser();
        String newPassword=form.getNewPassword();
        if(!passwordEncoder.matches(form.getCurrentPassword(),currentUser.getUserPw())) {
            return "redirect:/mypage/changepw?error=incorrectCurrentPassword";
        }
        if (!form.getNewPassword().equals(form.getConfirmNewPassword())) {
            return "redirect:/mypage/changepw?error=newPasswordMismatch";
        }

        userService.updatePassword(currentUser.getUserId(), newPassword);
        return "redirect:/logout";
    }

    @GetMapping("/mypage/changename")
    public String showChageUsernameForm(Model model) {
        model.addAttribute("usernameChangeForm",new UsernameChangeForm());
        return "changename";
    }

    @PostMapping("/mypage/changename")
    public String processChangeUsername(@ModelAttribute("usernameChangeForm") UsernameChangeForm form) {
        UserDTO currentUser=userService.getCurrentUser();
        String newUsername=form.getNewUsername();

        userService.updateUsername(currentUser.getUserId(),newUsername);

        return "redirect:/mypage";
    }
}