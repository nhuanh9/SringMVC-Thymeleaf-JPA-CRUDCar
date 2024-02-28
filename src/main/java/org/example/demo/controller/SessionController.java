package org.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class SessionController {

    @GetMapping("/session/increase")
    public String increaseSessionValue(Model model, HttpSession session) {
        Integer sessionValue = (Integer) session.getAttribute("sessionValue");
        if (sessionValue == null) {
            sessionValue = 0;
        }
        sessionValue++;
        session.setAttribute("sessionValue", sessionValue);
        model.addAttribute("sessionValue", sessionValue);
        return "/sessionView";
    }
}
