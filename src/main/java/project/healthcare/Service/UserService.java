package project.healthcare.Service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.healthcare.Repository.UserRepository;
import project.healthcare.dto.UserDTO;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public void saveUser(UserDTO userDTO) {
        userDTO.setUserPw(passwordEncoder.encode(userDTO.getUserPw()));
        userDTO.setUserAuth("USER");
//        userDTO.setAppendDate(localTime);
//        userDTO.setUpdateDate(localTime);
        userRepository.save(userDTO.toEntity());
    }
}