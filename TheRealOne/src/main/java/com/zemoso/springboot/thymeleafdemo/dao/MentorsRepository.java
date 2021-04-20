package com.zemoso.springboot.thymeleafdemo.dao;

//import com.zemoso.springapplication.entity.Mentors;
//import com.luv2code.springboot.thymeleafdemo.entity.*;
import com.zemoso.springboot.thymeleafdemo.entity.Mentors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorsRepository extends JpaRepository<Mentors,Integer> {
}
