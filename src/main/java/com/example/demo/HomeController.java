package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    DirectorRepository directorRepository;

    @Autowired
    MovieRepository movieRepository;

    @RequestMapping("/")
    public String index(Model model){
        //create a director
        model.addAttribute("directors", directorRepository.findAll());
        model.addAttribute("movies", movieRepository.findAll());
        return "index";
    }

    //director****
    @GetMapping("/addDirector")  //Add Director
    public String directorForm(Model model){
        model.addAttribute("director", new Director());
        return "directorform";
    }

    @PostMapping("/processDirector")
    public String processForm(@Valid @ModelAttribute Director director, BindingResult result){
        if(result.hasErrors()){
            return "directorform";
        }
        directorRepository.save(director);
        return "redirect:/";
    }

    //movie****
    @GetMapping("/addMovie")
    public String movieForm(Model model){
        model.addAttribute("movie", new Movie());
        return "movieform";
    }

    @PostMapping("/processMovie")
    public String processMovie(@Valid @ModelAttribute Movie movie, BindingResult result){
        if(result.hasErrors()){
            return "movieform";
        }
        movieRepository.save(movie);
        return "redirect:/";
    }
    //*********
}
