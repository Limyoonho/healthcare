package project.healthcare.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.healthcare.Entity.PillBasic;
import project.healthcare.Entity.PillDetail;

import java.util.List;

@Repository
public interface PillDetailRepository extends JpaRepository<PillDetail, Long> {

    public PillDetail save(PillDetail pillDetail);

}


