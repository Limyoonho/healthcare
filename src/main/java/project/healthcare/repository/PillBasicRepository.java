package project.healthcare.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.healthcare.Entity.PillBasic;

@Repository
public interface PillBasicRepository extends JpaRepository<PillBasic, Long> {

    public PillBasic save(PillBasic pillBasic);
}