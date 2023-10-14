package project.healthcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.healthcare.entity.Help;
import project.healthcare.repository.HelpRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class HelpService {
    @Autowired
    private HelpRepository helpRepository;

    @Autowired
    private UserService userService;

    //글 작성 처리
    public void write(Help help){
        String localTime=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        help.setCreate_date(localTime); // 현재 시간을 작성일자로 설정
        help.setView_count(0);
        help.setWrite_user(userService.getCurrentUser().getNickName());
        helpRepository.save(help);
    }

    //문의사항 리스트 처리
    public Page<Help> helpList(Pageable pageable){
        return helpRepository.findAll(pageable);
    }

    public Page<Help> helpSearchList(String searchKeyword, Pageable pageable){
        return helpRepository.findByTitleContaining(searchKeyword, pageable);
    }

    public Help helpId(Integer id) {
        Help help = helpRepository.findById(id).orElse(null);
        return help;
    }
    //글 상세 페이지
    public Help helpView(Integer id){
        Help help = helpRepository.findById(id).orElse(null);
        if (help != null) {
            if (help.getView_count() == null) {
                help.setView_count(0); // 초기 조회수 설정
            } else {
                help.setView_count(help.getView_count() + 1); // 조회수 증가
            }
                helpRepository.save(help); // 변경된 조회수를 저장
        }
        return help;
    }
    //글 삭제
    public void helpUpdate(Integer id){
        helpRepository.deleteById(id);
    }

    public void helpModify(Help help) {
        help.setTitle(help.getTitle());
        help.setContent(help.getContent());
        helpRepository.save(help);
    }
    public void getHelpWriter(Integer id) {
        Help help=helpRepository.findById(id).orElse(null);
        help.setWrite_user(help.getWrite_user());
    }
}
