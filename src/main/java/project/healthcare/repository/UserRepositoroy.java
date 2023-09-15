package project.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.healthcare.domain.User.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepositoroy extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findAll();
    Optional<UserEntity> findByName(String name);
    Optional<UserEntity> findByEmail(String email);
}