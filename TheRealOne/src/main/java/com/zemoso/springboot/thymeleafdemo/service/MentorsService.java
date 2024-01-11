package com.zemoso.springboot.thymeleafdemo.service;


//import com.luv2code.springboot.thymeleafdemo.entity.*;
import com.zemoso.springboot.thymeleafdemo.entity.Mentors;

import java.util.List;


public interface MentorsService {

    List<Mentors> findAll();

    Mentors findById(int theId);

    void save(Mentors theEmployee);

    void deleteById(int theId);

    int validate(String mentor,String name);
}