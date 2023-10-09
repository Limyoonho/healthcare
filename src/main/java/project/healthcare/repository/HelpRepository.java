package project.healthcare.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.healthcare.Entity.Help;

@Repository
public interface HelpRepository extends JpaRepository<Help,Integer> {
    Page<Help> findByTitleContaining(String searchKeyword, Pageable pageable);
}
