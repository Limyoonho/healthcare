package project.healthcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.healthcare.entity.Free;
import project.healthcare.repository.FreeRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class FreeService {
    @Autowired
    private FreeRepository freeRepository;

    @Autowired
    private UserService userService;

    //글 작성 처리
    public void write(Free free){
        String localTime=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        free.setCreate_date(localTime); // 현재 시간을 작성일자로 설정
        free.setView_count(0);
        free.setWrite_user(userService.getCurrentUser().getNickName());
        freeRepository.save(free);
    }

    //문의사항 리스트 처리
    public Page<Free> freeList(Pageable pageable){
        return freeRepository.findAll(pageable);
    }

    public Page<Free> freeSearchList(String searchKeyword, Pageable pageable){
        return freeRepository.findByTitleContaining(searchKeyword, pageable);
    }

    public Free freeId(Integer id) {
        Free free = freeRepository.findById(id).orElse(null);
        return free;
    }
    //글 상세 페이지
    public Free freeView(Integer id){
        Free free = freeRepository.findById(id).orElse(null);
        if (free != null) {
            if (free.getView_count() == null) {
                free.setView_count(0); // 초기 조회수 설정
            } else {
                free.setView_count(free.getView_count() + 1); // 조회수 증가
            }
            freeRepository.save(free); // 변경된 조회수를 저장
        }
        return free;
    }
    //글 삭제
    public void freeUpdate(Integer id){
        freeRepository.deleteById(id);
    }

    public void freeModify(Free free) {
        free.setTitle(free.getTitle());
        free.setContent(free.getContent());
        freeRepository.save(free);
    }
    public void getFreeWriter(Integer id) {
        Free free=freeRepository.findById(id).orElse(null);
        free.setWrite_user(free.getWrite_user());
    }
}