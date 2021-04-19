package com.zemoso.springboot.thymeleafdemo.service;

//import com.zemoso.springapplication.entity.Interns;

//import com.luv2code.springboot.thymeleafdemo.entity.*;
import com.zemoso.springboot.thymeleafdemo.entity.Interns;

import java.util.List;

public interface InternsService {
    public void deleteById(int theId);
    public void save(Interns theIntern);
    public List<Interns> findAll(int theId);
    public Interns findById(int theId);
    ;
}
