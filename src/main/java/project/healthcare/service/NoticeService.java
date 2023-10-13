package project.healthcare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.healthcare.entity.Notice;
import project.healthcare.repository.NoticeRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private UserService userService;

    //글 작성 처리
    public void write(Notice notice){
        String localTime=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        notice.setCreate_date(localTime); // 현재 시간을 작성일자로 설정
        notice.setView_count(0);
        notice.setWrite_user(userService.getCurrentUser().getNickName());
        noticeRepository.save(notice);
    }

    //문의사항 리스트 처리
    public Page<Notice> noticeList(Pageable pageable){
        return noticeRepository.findAll(pageable);
    }

    public Page<Notice> noticeSearchList(String searchKeyword, Pageable pageable){
        return noticeRepository.findByTitleContaining(searchKeyword, pageable);
    }

    public Notice noticeId(Integer id) {
        Notice notice = noticeRepository.findById(id).orElse(null);
        return notice;
    }
    //글 상세 페이지
    public Notice noticeView(Integer id){
        Notice notice = noticeRepository.findById(id).orElse(null);
        if (notice != null) {
            if (notice.getView_count() == null) {
                notice.setView_count(0); // 초기 조회수 설정
            } else {
                notice.setView_count(notice.getView_count() + 1); // 조회수 증가
            }
            noticeRepository.save(notice); // 변경된 조회수를 저장
        }
        return notice;
    }
    //글 삭제
    public void noticeUpdate(Integer id){
        noticeRepository.deleteById(id);
    }

    public void noticeModify(Notice notice) {
        notice.setTitle(notice.getTitle());
        notice.setContent(notice.getContent());
        noticeRepository.save(notice);
    }
    public void getNoticeWriter(Integer id) {
        Notice notice=noticeRepository.findById(id).orElse(null);
        notice.setWrite_user(notice.getWrite_user());
    }
}