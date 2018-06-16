package com.ukefu.ask.service.repository;

import com.ukefu.ask.web.model.WorkFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkFileRepository extends JpaRepository<WorkFile,String> {
    Page<WorkFile> findAll(Pageable pageable);
    Page<WorkFile> findByTitleLike(String title, Pageable pageable);
}
