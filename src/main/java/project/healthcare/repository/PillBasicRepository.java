package project.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.healthcare.entity.PillBasic;

@Repository
public interface PillBasicRepository extends JpaRepository<PillBasic, Long> {

    public PillBasic save(PillBasic pillBasic);
}


