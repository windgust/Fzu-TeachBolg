package com.ukefu.ask.service.repository;

import com.ukefu.ask.web.model.Work;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<Work,String> {
    Work getById(String id);
    Page<Work> findByStartTimeAfterAndAndCloseTimeBefore(long startTime,long closeTime,Pageable pageable);
    Page<Work> findAll(Pageable pageable);
    Page<Work> findByUserNameLike(String userName,Pageable pageable);
    Page<Work> findByTypeLikeAndUseridLike(String type,String userid,Pageable pageable);
}
