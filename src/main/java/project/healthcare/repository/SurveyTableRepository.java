package project.healthcare.repository;

import project.healthcare.entity.SurveyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyTableRepository extends JpaRepository<SurveyEntity, Long> {
     SurveyEntity findByNameAndEmail(Object name, Object email);
}
