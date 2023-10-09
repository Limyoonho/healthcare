package project.healthcare.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.healthcare.dto.PillDto;
import project.healthcare.Entity.PillBasic;
import project.healthcare.Repository.PillBasicRepository;

import java.util.List;

@Service
public class PillBasicService extends PillDto {

    @Autowired
    private PillBasicRepository pillBasicRepository;

    public PillBasic save(PillBasic pillBasic){ return pillBasicRepository.save(pillBasic);}

    public List<PillBasic> getAllPillBasics() {
        return pillBasicRepository.findAll(); // 데이터베이스에서 모든 PillBasic 엔티티 검색
    }
}