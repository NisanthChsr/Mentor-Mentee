package com.zemoso.springboot.thymeleafdemo.dao;


import com.zemoso.springboot.thymeleafdemo.entity.Interns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface InternsRepository extends JpaRepository<Interns,Integer> {
    @Query(value="select * from interns where interns.mentor_id=?1",nativeQuery = true)
    List<Interns> findByMentorId(String mentorId);
}
