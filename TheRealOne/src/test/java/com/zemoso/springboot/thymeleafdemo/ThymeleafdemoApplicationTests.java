package com.zemoso.springboot.thymeleafdemo;

import com.zemoso.springboot.thymeleafdemo.converter.InternConverter;
import com.zemoso.springboot.thymeleafdemo.dao.InternsRepository;
import com.zemoso.springboot.thymeleafdemo.dao.MentorsRepository;
import com.zemoso.springboot.thymeleafdemo.dto.InternDto;
import com.zemoso.springboot.thymeleafdemo.entity.Interns;
import com.zemoso.springboot.thymeleafdemo.entity.Mentors;
import com.zemoso.springboot.thymeleafdemo.service.InternsService;
import com.zemoso.springboot.thymeleafdemo.service.MentorsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThymeleafdemoApplicationTests{

    @Autowired
    private MentorsService mentorsService;

    @Autowired
    private InternsService internsService;

    @MockBean
    private MentorsRepository mentorsRepository;

    @MockBean
    private InternsRepository internsRepository;

    @Autowired
    private InternConverter internConverter;
    @Test
    public void getMentorsTest()
    {
        when(mentorsRepository.findAll()).thenReturn(Stream.of(new Mentors(10,"Mentor1Firstname","Mentor1LastName"),new Mentors(11,"Mentor2Firstname","Mentor2LastName")).collect(Collectors.toList()));
        assertEquals(2,mentorsService.findAll().size());
    }

    @Test
    public void findByMentorId(){
        Mentors theMentor=new Mentors(12,"Mentor2FirstName","Mentor3LastName");

        Interns theIntern1=new Interns();
        theIntern1.setId(50);
        theIntern1.setFirstName("Intern1FirstName");
        theIntern1.setLastName("Intern2LastName");
        theIntern1.setMentor(theMentor);

        Interns theIntern2=new Interns();
        theIntern2.setId(51);
        theIntern2.setFirstName("Intern2FirstName");
        theIntern2.setLastName("Intern2LastName");
        theIntern2.setMentor(theMentor);

        ArrayList<Interns> internsList=new ArrayList<>();
        internsList.add(theIntern1);
        internsList.add(theIntern2);

        when(internsRepository.findInternsByMentorId(theMentor.getId()+"")).thenReturn(internsList);

        List<InternDto> internDtos=internsService.findInternsByMentorId(theMentor.getId());

        assertEquals(internsList.size(),internDtos.size());

    }

}