package com.geekbrains.spring.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TempController {
    @GetMapping("/reqdemo")
    @ResponseBody
    public String demoRequestParam(String title, Integer value) {
        return "Привет";
    }
}
