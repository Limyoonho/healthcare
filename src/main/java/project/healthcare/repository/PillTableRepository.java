package project.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.healthcare.entity.PillEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface PillTableRepository extends JpaRepository<PillEntity, Long> {

    List<PillEntity> findAll();
    Optional<PillEntity> findByCategory(String category);
}
