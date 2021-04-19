package com.zemoso.springboot.thymeleafdemo.service;


//import com.luv2code.springboot.thymeleafdemo.entity.*;
import com.zemoso.springboot.thymeleafdemo.entity.Mentors;

import java.util.List;


public interface MentorsService {

    public List<Mentors> findAll();

    public Mentors findById(int theId);

    public void save(Mentors theEmployee);

    public void deleteById(int theId);

    public int validate(String mentor,String name);
}