package project.healthcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.healthcare.entity.Help;
import project.healthcare.repository.HelpRepository;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class HelpService {
    @Autowired
    private HelpRepository helpRepository;

    //글 작성 처리
    public void write(Help help){
        helpRepository.save(help);
    }

    //문의사항 리스트 처리
    public Page<Help> helpList(Pageable pageable){
        return helpRepository.findAll(pageable);
    }

    public Page<Help> helpSearchList(String searchKeyword, Pageable pageable){
        return helpRepository.findByTitleContaining(searchKeyword, pageable);
    }

    //글 상세 페이지
    public Help helpView(Integer id){
       return helpRepository.findById(id).get();
    }
    //글 삭제
    public void helpUpdate(Integer id){
        helpRepository.deleteById(id);
    }



}
