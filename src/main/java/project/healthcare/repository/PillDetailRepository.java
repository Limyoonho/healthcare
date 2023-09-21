package project.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.healthcare.entity.PillBasic;
import project.healthcare.entity.PillDetail;

import java.util.List;

@Repository
public interface PillDetailRepository extends JpaRepository<PillDetail, Long> {

    public PillDetail save(PillDetail pillDetail);

}


