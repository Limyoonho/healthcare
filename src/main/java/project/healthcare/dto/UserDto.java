package project.healthcare.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.healthcare.domain.User.UserEntity;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String password;
    private String name;
    private String email;
    @Builder.Default
    private LocalDateTime createT = LocalDateTime.now();

    public static UserDto toUserDto(UserEntity userEntity){
        UserDto userDto = new UserDto();

        userDto.setId(userEntity.getId());
        userDto.setPassword(userEntity.getPassword());
        userDto.setName(userEntity.getName());
        userDto.setEmail(userEntity.getEmail());

        return userDto;
    }
}
