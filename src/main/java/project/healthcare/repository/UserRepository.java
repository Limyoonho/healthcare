package project.healthcare.Repository;

import project.healthcare.Entity.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    public UserEntity findByUserId(String username);
}