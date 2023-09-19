package project.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.healthcare.entity.PillEntity;

public interface PillRepository extends JpaRepository<PillEntity, Long> {
}
