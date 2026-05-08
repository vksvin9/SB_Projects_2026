package com.vin.controller;

import com.vin.repository.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LeaderboardController {

    private final ResultRepository
            resultRepository;

    @GetMapping("/leaderboard")
    public String leaderboard(
            Model model) {

        model.addAttribute(
                "leaders",
                resultRepository
                        .findTop10ByOrderByScoreDesc());

        return "leaderboard";
    }
}