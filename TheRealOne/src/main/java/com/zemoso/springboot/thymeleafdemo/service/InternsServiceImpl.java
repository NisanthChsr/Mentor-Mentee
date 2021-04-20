package com.zemoso.springboot.thymeleafdemo.service;

//import com.luv2code.springboot.thymeleafdemo.dao.*;
//import com.luv2code.springboot.thymeleafdemo.entity.*;
import com.zemoso.springboot.thymeleafdemo.converter.InternConverter;
import com.zemoso.springboot.thymeleafdemo.dao.InternsRepository;
import com.zemoso.springboot.thymeleafdemo.dto.InternDto;
import com.zemoso.springboot.thymeleafdemo.entity.Interns;
import com.zemoso.springboot.thymeleafdemo.exceptionhandler.InternNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InternsServiceImpl implements InternsService{

    @Autowired
    private InternsRepository internsRepository;

    @Autowired
    private InternConverter internConverter;

    @Override
    public List<Interns> findAll(int theId) {

        return internsRepository.findAll();
    }
    @Override
    public List<InternDto> findByMentorId(int id) {

        List<Interns> internsList=internsRepository.findByMentorId(String.valueOf(id));
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

        Interns theEmployee = null;

        if (result.isPresent()) {
            theEmployee = result.get();
        }
        else {
            throw new InternNotFoundException("Did not find employee id - " + theId);
        }

        return internConverter.entityToDto(theEmployee);
    }

    @Override
    public void save(InternDto theIntern) {

        internsRepository.save(internConverter.dtoToEntity(theIntern));
    }

    @Override
    public void deleteById(int theId) {

        internsRepository.deleteById(theId);
    }


}
