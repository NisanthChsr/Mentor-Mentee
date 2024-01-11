package com.zemoso.springboot.thymeleafdemo.converter;

import com.zemoso.springboot.thymeleafdemo.dto.InternDto;
import com.zemoso.springboot.thymeleafdemo.entity.Interns;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InternConverter {

    public InternDto entityToDto(Interns intern){
        InternDto dto=new InternDto();
        dto.setId(intern.getId());
        dto.setFirstName(intern.getFirstName());
        dto.setLastName(intern.getLastName());
        dto.setMentor(intern.getMentor());
        dto.setMarks(intern.getMarks());
        return dto;
    }

    public Interns dtoToEntity(InternDto internDtos){
        Interns student=new Interns();
        student.setId(internDtos.getId());
        student.setFirstName(internDtos.getFirstName());
        student.setLastName(internDtos.getLastName());
        student.setMentor(internDtos.getMentor());
        student.setMarks(internDtos.getMarks());
        return student;
    }

    public List<InternDto> entityToDto(List<Interns> interns){

        return interns.stream().map(x->entityToDto(x)).collect(Collectors.toList());
    }

    public List<Interns> dotToEntity(List<InternDto> internDtos){

        return internDtos.stream().map(x->dtoToEntity(x)).collect(Collectors.toList());
    }
}