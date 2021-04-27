package com.zemoso.springboot.thymeleafdemo.service;

import com.zemoso.springboot.thymeleafdemo.converter.InternConverter;
import com.zemoso.springboot.thymeleafdemo.dao.InternsRepository;
import com.zemoso.springboot.thymeleafdemo.dto.InternDto;
import com.zemoso.springboot.thymeleafdemo.entity.Interns;
import com.zemoso.springboot.thymeleafdemo.exceptionhandler.InternNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InternsServiceImpl implements InternsService{

    @Autowired
    private InternsRepository internsRepository;

    @Autowired
    private MentorsService mentorsService;

    @Autowired
    private InternConverter internConverter;

    @Override
    public List<Interns> findAll(int theId) {

        return internsRepository.findAll();
    }
    @Override
    public List<InternDto> findInternsByMentorId(int id) {

        List<Interns> internsList=internsRepository.findInternsByMentorId(String.valueOf(id));

        List<InternDto> internDtoList=new ArrayList<>();

        for(int i=0;i<internsList.size();i++)
        {
            internDtoList.add(internConverter.entityToDto(internsList.get(i)));
        }

        return internDtoList;
    }
    @Override
    public InternDto findById(int theId) {

        Optional<Interns> result =internsRepository.findById(theId);

        Interns theIntern = null;

        if (result.isPresent()) {
            theIntern = result.get();
        }
        else {
            throw new InternNotFoundException("Did not find employee id - " + theId);
        }

        return internConverter.entityToDto(theIntern);
    }

    @Override
    public void save(InternDto theIntern, int mentorId) {
        Interns intern=internConverter.dtoToEntity(theIntern);
        mentorsService.findById(mentorId).add(intern);
        theIntern.setMentor(mentorsService.findById(mentorId));

        internsRepository.save(internConverter.dtoToEntity(theIntern));
    }

    @Override
    public void deleteById(int theId) {
        Optional<Interns> result =internsRepository.findById(theId);

        if (result.isPresent()) {
            internsRepository.deleteById(theId);
        }
        else {
            throw new InternNotFoundException("Did not find employee id - " + theId);
        }

    }

}
