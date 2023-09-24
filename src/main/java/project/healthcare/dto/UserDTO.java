package project.healthcare.dto;

import lombok.Data;
import project.healthcare.entity.UserEntity;

@Data
public class UserDTO {
    private int userNo;
    private String userId;
    private String userPw;
    private String userName;
    private String userAuth;
    private String appendDate;
    private String updateDate;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .userId(userId)
                .userPw(userPw)
                .userName(userName)
                .userAuth(userAuth)
                .appendDate(appendDate)
                .updateDate(updateDate)
                .build();
    }
}