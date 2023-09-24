package project.healthcare.Repository;

import project.healthcare.Entity.SurveyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyTableRepository extends JpaRepository<SurveyEntity, Long> {
   // public SurveyEntity save(SurveyEntity surveyEntity);
}
