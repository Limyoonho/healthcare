package project.healthcare.dto;

import lombok.Data;

@Data
public class ChangeForm {
    private String newNickname;
    private String currentPassword;
    private String newPassword;
    private String confirmNewPassword;
    private String newUsername;
}
