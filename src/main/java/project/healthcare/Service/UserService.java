package project.healthcare.Service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.healthcare.repository.UserRepository;
import project.healthcare.dto.UserDTO;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public String saveUser(UserDTO userDTO) {
        userDTO.setUserPw(passwordEncoder.encode(userDTO.getUserPw()));
        userDTO.setUserAuth("USER");
//        userDTO.setAppendDate(localTime);
//        userDTO.setUpdateDate(localTime);
        userRepository.save(userDTO.toEntity());
        return userDTO.getUserId();
    }
}