package com.fzuTeachBolg.ask.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fzuTeachBolg.ask.web.model.UserHistory;

/**
 * 
 * @author admin
 *
 */
public interface UserEventRepository extends JpaRepository<UserHistory, String> {

}
