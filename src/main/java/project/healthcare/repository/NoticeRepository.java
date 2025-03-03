package project.healthcare.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.healthcare.entity.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice,Integer> {
    Page<Notice> findByTitleContaining(String searchKeyword, Pageable pageable);
}
