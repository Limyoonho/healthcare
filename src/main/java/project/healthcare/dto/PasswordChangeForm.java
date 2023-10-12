package project.healthcare.dto;

import lombok.Data;

@Data
public class PasswordChangeForm {
    private String currentPassword;
    private String newPassword;
    private String confirmNewPassword;
}
