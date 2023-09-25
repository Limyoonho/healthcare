package project.healthcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.healthcare.dto.PillDto;
import project.healthcare.entity.PillBasic;
import project.healthcare.entity.PillDetail;
import project.healthcare.repository.PillBasicRepository;
import project.healthcare.repository.PillDetailRepository;

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
