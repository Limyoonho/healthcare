package project.healthcare.dto;

import lombok.Data;
import project.healthcare.entity.PillEntity;
import project.healthcare.entity.UserEntity;

@Data
public class UserDTO {
    private int userNo;
    private String userId;
    private String userPw;
    private String uName;
    private String userAuth;
    private String appendDate;
    private String updateDate;
    private String nickName;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .userId(userId)
                .userPw(userPw)
                .uName(uName)
                .userAuth(userAuth)
                .appendDate(appendDate)
                .updateDate(updateDate)
                .nickName(nickName)
                .build();
    }
}