package com.zemoso.springboot.thymeleafdemo.service;

import com.zemoso.springboot.thymeleafdemo.dto.InternDto;
import com.zemoso.springboot.thymeleafdemo.entity.Interns;

import java.util.List;

public interface InternsService {
    void deleteById(int theId);
    void save(InternDto theIntern,int mentorId);
    List<Interns> findAll(int theId);
    InternDto findById(int theId);
    List<InternDto> findInternsByMentorId(int id);
}
