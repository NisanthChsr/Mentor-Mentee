package com.zemoso.springboot.thymeleafdemo.service;

//import com.luv2code.springboot.thymeleafdemo.dao.*;
//import com.luv2code.springboot.thymeleafdemo.entity.*;
import com.zemoso.springboot.thymeleafdemo.dao.InternsRepository;
import com.zemoso.springboot.thymeleafdemo.entity.Interns;
import com.zemoso.springboot.thymeleafdemo.exceptionhandler.InternNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class InternsServiceImpl implements InternsService{

    @Autowired
    private InternsRepository internsRepository;

    @Override
    public List<Interns> findAll(int theId) {

        return internsRepository.findAll();
    }

    @Override
    public Interns findById(int theId) {

        Optional<Interns> result =internsRepository.findById(theId);

        Interns theEmployee = null;

        if (result.isPresent()) {
            theEmployee = result.get();
        }
        else {
            throw new InternNotFoundException("Did not find employee id - " + theId);
        }

        return theEmployee;
    }

    @Override
    public void save(Interns theIntern) {

        internsRepository.save(theIntern);
    }

    @Override
    public void deleteById(int theId) {

        internsRepository.deleteById(theId);
    }


}
