package project.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.healthcare.entity.PillEntity;

import java.util.List;

@Repository
public interface PillRepository extends JpaRepository<PillEntity, Long> {
    PillEntity findById(int id);
    PillEntity findByProduct(Object product);
    List<PillEntity> findByCategory(Object category);
    PillEntity findByCompanyAndProduct(Object company, Object product);
}


