package com.matchingscoring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MatchingController {

    @RequestMapping("/matching")
    public String matching() {

        return "matching";
    }
}
