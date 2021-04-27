package com.zemoso.springboot.thymeleafdemo.dao;


import com.zemoso.springboot.thymeleafdemo.entity.Mentors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorsRepository extends JpaRepository<Mentors,Integer> {
}
