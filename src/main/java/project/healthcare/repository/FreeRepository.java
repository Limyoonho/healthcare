package project.healthcare.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.healthcare.entity.Free;

@Repository
public interface FreeRepository extends JpaRepository<Free,Integer> {
    Page<Free> findByTitleContaining(String searchKeyword, Pageable pageable);
}
