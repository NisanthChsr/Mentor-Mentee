package com.zemoso.springboot.thymeleafdemo.controller;


//import com.luv2code.springboot.thymeleafdemo.entity.*;
import com.zemoso.springboot.thymeleafdemo.entity.Interns;
import com.zemoso.springboot.thymeleafdemo.exceptionhandler.NumberFormatExceptionHandler;
//import com.luv2code.springboot.thymeleafdemo.service.*;
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

    int presentMentor;
    @Autowired
    private InternsService internsService;

    @Autowired
    private MentorsService mentorsService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/list")
    public String listInterns(@RequestParam("mentors") String mentors, Model theModel) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            this.presentMentor = mentorsService.validate(mentors,auth.getName());
            List<Interns> theInterns = mentorsService.findById(presentMentor).getTheInterns();

            theModel.addAttribute("interns", theInterns);

            return "list-interns";

    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(@RequestParam("mentor") int mentor,Model theModel) {
        this.presentMentor=mentor;
        Interns theIntern = new Interns();
        //theIntern.setId(count++);

        theModel.addAttribute("interns", theIntern);
        return "interns-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("interns") int theId,
                                    Model theModel) {

        Interns theIntern = internsService.findById(theId);

        theModel.addAttribute("interns", theIntern);

        return "interns-form";
    }


    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("interns") Interns theIntern, BindingResult theBindingResult) {

        if (theBindingResult.hasErrors()) {
            return "interns-form";
        }
        else {

            mentorsService.findById(presentMentor).add(theIntern);

            internsService.save(theIntern);

            return "redirect:/mentors/list";
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("interns") int theId) {

        internsService.deleteById(theId);

        return "list-mentors";

    }

}
