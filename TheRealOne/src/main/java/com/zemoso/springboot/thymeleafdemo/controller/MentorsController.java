package com.zemoso.springboot.thymeleafdemo.controller;

import com.zemoso.springboot.thymeleafdemo.entity.Mentors;
import com.zemoso.springboot.thymeleafdemo.service.MentorsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/mentors")
public class MentorsController {

    private MentorsService mentorsService;

    public MentorsController(MentorsService theMentorsService) {
        mentorsService = theMentorsService;
    }


    @GetMapping("/list")
    public String listMentors(Model theModel) {

        List<Mentors> theMentors = mentorsService.findAll();

        theModel.addAttribute("mentors", theMentors);

        return "list-mentors";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        Mentors theMentor = new Mentors();

        theModel.addAttribute("mentors", theMentor);

        return "mentors-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId,
                                    Model theModel) {

        Mentors theMentor = mentorsService.findById(theId);

        theModel.addAttribute("mentors", theMentor);

        return "mentors-form";
    }


    @PostMapping("/save")
    public String saveMentor(@ModelAttribute("mentors") Mentors theMentor) {

        mentorsService.save(theMentor);

        return "redirect:/mentors/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId) {

        mentorsService.deleteById(theId);

        return "redirect:/mentors/list";

    }

}
