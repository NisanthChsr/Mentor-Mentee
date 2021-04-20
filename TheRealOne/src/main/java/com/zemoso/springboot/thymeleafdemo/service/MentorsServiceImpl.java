package com.zemoso.springboot.thymeleafdemo.service;

//import com.zemoso.springapplication.dao.MentorsRepository;
//import com.zemoso.springapplication.entity.Mentors;
//import com.luv2code.springboot.thymeleafdemo.dao.*;
//import com.luv2code.springboot.thymeleafdemo.entity.*;
import com.zemoso.springboot.thymeleafdemo.dao.MentorsRepository;
import com.zemoso.springboot.thymeleafdemo.entity.Mentors;
import com.zemoso.springboot.thymeleafdemo.exceptionhandler.MentorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class MentorsServiceImpl implements MentorsService {

    private MentorsRepository mentorsRepository;
    HashMap<String,Integer> mentorsList=new HashMap<>();

    @Autowired
    public MentorsServiceImpl(MentorsRepository theMentorsRepository)
    {
        mentorsRepository = theMentorsRepository;
    }

    @Override
    public List<Mentors> findAll() {

        return mentorsRepository.findAll();
    }

    @Override
    public Mentors findById(int theId) {

        Optional<Mentors> result = mentorsRepository.findById(theId);

        Mentors theEmployee = null;

        if (result.isPresent()) {
            theEmployee = result.get();
        }
        else {

            throw new MentorNotFoundException("Did not find employee id - " + theId);
        }

        return theEmployee;
    }

    @Override
    public void save(Mentors theEmployee) {

        mentorsRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {

        mentorsRepository.deleteById(theId);
    }

    @Override
    public int validate(String mentor, String name) {

        if(mentor.equals(name)) {

            List<Mentors> theMentors = mentorsRepository.findAll();
            for (int i = 0; i < theMentors.size(); i++) {
                mentorsList.put(theMentors.get(i).getFirstName(), theMentors.get(i).getId());
            }

            if (mentorsList.containsKey(mentor)) {
                return mentorsList.get(mentor);
            } else {
                new MentorNotFoundException("Enter Valid name");
                return -1;
            }
        }
        else
        {
            new MentorNotFoundException("Enter Valid name");
            return -1;
        }
    }
}
