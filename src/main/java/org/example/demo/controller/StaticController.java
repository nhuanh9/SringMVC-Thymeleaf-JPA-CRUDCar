package org.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticController {

    private static int staticValue = 0;

    @GetMapping("/static/increase")
    public String increaseStaticValue(Model model) {
        staticValue++;
        model.addAttribute("staticValue", staticValue);
        return "/staticView";
    }
}