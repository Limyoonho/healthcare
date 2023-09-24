package project.healthcare.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Entity
@Data
@Table(name="tb_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="USER_NO")
    private int userNo;

    @Column(name="USER_ID")
    private String userId;

    @Column(length=32,name="USER_PW")
    private String userPw;

    @Column(name="USER_NAME")
    private String userName;

    @Column(name="USER_AUTH")
    private String userAuth;

    @Column(name="APPEND_DATE")
    private String appendDate;

    @Column(name="UPDATE_DATE")
    private String updateDate;

    @Builder
    public UserEntity(
            int userNo,
            String userId,
            String userPw,
            String userName,
            String userAuth,
            String appendDate,
            String updateDate) {
        this.userNo = userNo;
        this.userId = userId;
        this.userPw = userPw;
        this.userName = userName;
        this.userAuth = userAuth;
        this.appendDate = appendDate;
        this.updateDate = updateDate;
    }
}