package com.zemoso.springboot.thymeleafdemo.controller;

import com.zemoso.springboot.thymeleafdemo.converter.InternConverter;
import com.zemoso.springboot.thymeleafdemo.dto.InternDto;
import com.zemoso.springboot.thymeleafdemo.entity.Interns;
import com.zemoso.springboot.thymeleafdemo.exceptionhandler.InternNotFoundException;
import com.zemoso.springboot.thymeleafdemo.service.InternsService;
import com.zemoso.springboot.thymeleafdemo.service.MentorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/interns")
public class InternsController {

    int presentMentor=0;

    @Autowired
    private InternsService internsService;

    @Autowired
    private MentorsService mentorsService;

    @Autowired
    private InternConverter internConverter;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/list")
    public String listInterns(@RequestParam("mentors") String mentors, Model theModel) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            this.presentMentor = mentorsService.validate(mentors,auth.getName());
            List<InternDto> theInterns = internsService.findByMentorId(presentMentor);

            theModel.addAttribute("interns", theInterns);

            return "list-interns";

    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(@RequestParam("mentor") int mentor,Model theModel) {
        this.presentMentor=mentor;
        InternDto theIntern = new InternDto();

        theModel.addAttribute("interns", theIntern);
        return "interns-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("interns") int theId,
                                    Model theModel) {

        InternDto theIntern = internsService.findById(theId);

        if(theIntern.getMentor()==mentorsService.findById(presentMentor)) {
            theModel.addAttribute("interns", theIntern);
        }
        else
        {
            new InternNotFoundException("Intern not Found");
        }

        return "interns-form";
    }


    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("interns") InternDto theIntern, BindingResult theBindingResult) {

        if (theBindingResult.hasErrors()) {
            return "interns-form";
        }
        else {

            Interns intern=internConverter.dtoToEntity(theIntern);
            mentorsService.findById(presentMentor).add(intern);
            theIntern.setMentor(mentorsService.findById(presentMentor));
            internsService.save(theIntern);

            return "redirect:/mentors/list";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("interns") int theId) {

        InternDto theIntern=internsService.findById(theId);
        if(theIntern.getMentor()==mentorsService.findById(presentMentor)) {
            internsService.deleteById(theId);
        }
        else
        {
            new InternNotFoundException("Intern not Found");
        }
        return "list-mentors";

    }

}
