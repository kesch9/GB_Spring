package com.geekbrains.spring.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CalcController {
    @GetMapping("/calc_form")
    public String showClacForm(@RequestParam(name = "var_a", required = false) Integer a, @RequestParam(name = "var_b", required = false) Integer b, @RequestParam(name = "result", required = false) Integer result) {
        return "calc_form";
    }

    @GetMapping("/calc_result")
    public String sum(@RequestParam(name = "var_a") Integer a, @RequestParam(name = "var_b") Integer b) {
        Integer result = a + b;
        return "redirect:/" + String.format("calc_form?var_a=%d&var_b=%d&result=%d", a, b, result);
    }
}
