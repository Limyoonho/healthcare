package project.healthcare.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.healthcare.dto.ChangeForm;
import project.healthcare.dto.UserDTO;
import project.healthcare.entity.SurveyEntity;
import project.healthcare.repository.SurveyTableRepository;
import project.healthcare.service.UserService;


@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private SurveyTableRepository surveyTableRepository;
    @PostMapping("/user/save")
    public String saveUser(UserDTO userDTO) {
        userService.saveUser(userDTO);
        return "redirect:/login";
    }

    @GetMapping("/mypage")
    public String userProfile(Model model) {
        UserDTO user=userService.getCurrentUser();
        model.addAttribute("user",user);

        SurveyEntity surveyResult = new SurveyEntity();

        if (surveyTableRepository.findByNameAndEmail(user.getUName(), user.getUserId()) == null) {
            surveyResult.setName(user.getUName());
            surveyResult.setEmail(user.getUserId());
            surveyResult.setAge_group("");
            surveyResult.setNutrient_necessity("");
            surveyResult.setNutrient_frequency("");
            surveyResult.setNutrient_form("");
            surveyResult.setDiscomfort_area("");
            surveyResult.setDesired_function("");
            surveyResult.setDesired_ingredients(null);
            surveyResult.setIngredient("");
        } else {
            surveyResult = surveyTableRepository.findByNameAndEmail(user.getUName(), user.getUserId());
        }
        model.addAttribute("surveyResult", surveyResult);

        return "/mypage";
    }
    @GetMapping("/mypage/changepw")
    public String showChangePasswordForm(Model model) {
        model.addAttribute("ChangeForm",new ChangeForm());
        return "changePw";
    }

    @PostMapping("/mypage/changepw")
    public String processChangePassword(@ModelAttribute("ChangeForm") ChangeForm form) {
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
    public String showChangeUsernameForm(Model model) {
        model.addAttribute("ChangeForm",new ChangeForm());
        return "changename";
    }

    @PostMapping("/mypage/changename")
    public String processChangeUsername(@ModelAttribute("ChangeForm") ChangeForm form) {
        UserDTO currentUser=userService.getCurrentUser();
        String newUsername=form.getNewUsername();

        userService.updateUsername(currentUser.getUserId(),newUsername);

        return "redirect:/mypage";
    }

    @GetMapping("/mypage/changenickname")
    public String showChangeNicknameForm(Model model) {
        model.addAttribute("ChangeForm",new ChangeForm());
        return "changenickname";
    }

    @PostMapping("/mypage/changenickname")
    public String processChangeNickname(@ModelAttribute("ChangeForm") ChangeForm form) {
        UserDTO currentUser=userService.getCurrentUser();
        String newNickname=form.getNewNickname();

        userService.updateNickname(currentUser.getUserId(),newNickname);

        return "redirect:/mypage";
    }
}