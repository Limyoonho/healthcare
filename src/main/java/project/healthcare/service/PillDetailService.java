package project.healthcare.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.healthcare.dto.PillDto;
import project.healthcare.Entity.PillBasic;
import project.healthcare.Entity.PillDetail;
import project.healthcare.Repository.PillBasicRepository;
import project.healthcare.Repository.PillDetailRepository;

import java.util.List;

@Service
public class PillDetailService extends PillDto {

    @Autowired
    private PillDetailRepository pillDetailRepository;

    public PillDetail save(PillDetail pillDetail){ return pillDetailRepository.save(pillDetail);}

    public List<PillDetail> getAllDetails(){
        return pillDetailRepository.findAll();
    }
}