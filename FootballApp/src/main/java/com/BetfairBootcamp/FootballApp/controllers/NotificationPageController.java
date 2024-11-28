package com.BetfairBootcamp.FootballApp.controllers;

import com.BetfairBootcamp.FootballApp.services.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationPageController {

    private final MatchService matchService;

    @GetMapping
    public String getNotificationsPage(Model model) {
        List<String> notifications = List.of(
                "A new participant joined your match!",
                "Match is now full!"
        );
        model.addAttribute("notifications", notifications);
        return "notifications"; // Refers to notifications.html
    }
}

