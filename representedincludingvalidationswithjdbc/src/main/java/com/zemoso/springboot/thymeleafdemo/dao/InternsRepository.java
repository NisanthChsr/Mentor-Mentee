package com.zemoso.springboot.thymeleafdemo.dao;

//import com.zemoso.springapplication.entity.Interns;

//import com.luv2code.springboot.thymeleafdemo.entity.*;
import com.zemoso.springboot.thymeleafdemo.entity.Interns;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InternsRepository extends JpaRepository<Interns,Integer> {

}
