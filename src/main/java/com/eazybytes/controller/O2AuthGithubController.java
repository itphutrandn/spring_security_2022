package com.eazybytes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/github")
public class O2AuthGithubController {

    @GetMapping
    public String main() {
        return "index";
    }
}
