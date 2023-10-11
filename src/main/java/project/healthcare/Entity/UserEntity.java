package project.healthcare.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
@Entity(name="tb_user")
@Data
public class UserEntity implements UserDetails{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="USER_NO")
    private int userNo;

    @Column(name="USER_ID")
    private String userId;

    @Column(length=2000,name="USER_PW")
    private String userPw;

    @Column(name="USER_NAME")
    private String uName;

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
            String uName,
            String userAuth,
            String appendDate,
            String updateDate) {
        this.userNo = userNo;
        this.userId = userId;
        this.userPw = userPw;
        this.uName = uName;
        this.userAuth = userAuth;
        this.appendDate = appendDate;
        this.updateDate = updateDate;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors=new ArrayList<>();
        collectors.add(()->{
            return "ROLE_USER";
        });
        return collectors;
    }
    @Override
    public String getPassword() {
        return this.userPw;
    }
    @Override
    public String getUsername() {
        return this.userId;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}