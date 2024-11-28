package com.BetfairBootcamp.FootballApp.controllers;

import com.BetfairBootcamp.FootballApp.services.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final MatchService matchService;

    public HomeController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("matches", matchService.getAllMatches());
        return "index";
    }
}

