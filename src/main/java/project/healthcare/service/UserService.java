package project.healthcare.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.healthcare.entity.PillEntity;
import project.healthcare.entity.UserEntity;
import project.healthcare.repository.UserRepository;
import project.healthcare.dto.UserDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public void saveUser(UserDTO userDTO) {
        userDTO.setUserPw(passwordEncoder.encode(userDTO.getUserPw()));
        userDTO.setUserAuth("USER");
        String localTime=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        userDTO.setAppendDate(localTime);
        userDTO.setUpdateDate(localTime);
        userRepository.save(userDTO.toEntity());
    }

    public UserDTO getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            String username = userDetails.getUsername();

            UserEntity userEntity = userRepository.findByUserId(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(userEntity.getUserId());
            userDTO.setUName(userEntity.getUName());
            userDTO.setUserPw(userEntity.getUserPw());
            userDTO.setUserAuth(userEntity.getUserAuth());
            userDTO.setAppendDate(userEntity.getAppendDate());
            userDTO.setUpdateDate(userEntity.getUpdateDate());
            userDTO.setNickName(userEntity.getNickName());

            return userDTO;
        }
        return null;
    }

    public void updateUsername(String username, String newUsername) {
        UserEntity userEntity = userRepository.findByUserId(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        String localTime=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        userEntity.setUpdateDate(localTime);
        userEntity.setUName(newUsername);
        userRepository.save(userEntity);
    }

    public void updatePassword(String username, String newPassword) {
        UserEntity userEntity = userRepository.findByUserId(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userEntity.setUserPw(passwordEncoder.encode(newPassword));
        String localTime=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        userEntity.setUpdateDate(localTime);
        userRepository.save(userEntity);
    }

    public void updateNickname(String username, String newNickname) {
        UserEntity userEntity=userRepository.findByUserId(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userEntity.setNickName(newNickname);
        String localTime=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        userEntity.setUpdateDate(localTime);
        userRepository.save(userEntity);
    }
    public boolean isUserIdDuplicate(String userId) {
        // 이메일(ID) 중복 확인 로직
        UserEntity existingUser = userRepository.findByUserId(userId).orElse(null);
        return existingUser != null;
    }
    public boolean isNickNameDuplicate(String nickName) {
        // 닉네임 중복 확인 로직
        UserEntity existingUser = userRepository.findByNickName(nickName).orElse(null);
        return existingUser != null;
    }
}
