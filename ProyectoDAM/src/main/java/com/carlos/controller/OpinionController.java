package com.carlos.controller;

import javax.validation.Valid;

import com.carlos.model.Opinion;
import com.carlos.service.IServiceOpinion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/opinion")
public class OpinionController {
    
    @Autowired
    private IServiceOpinion opinionService;

    @PostMapping("/addOpinion")
    public ModelAndView addOpinion(@Valid @ModelAttribute Opinion opinion, BindingResult bindingResult, Authentication auth) {
        ModelAndView mav = new ModelAndView();
        
        opinionService.add(opinion);
        mav.setViewName("redirect:/libro/ver/" + opinion.getLibro().getIsbn());
        return mav;
    }
}
