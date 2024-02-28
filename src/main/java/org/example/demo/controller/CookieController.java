package org.example.demo.controller;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.WebUtils;

@Controller
public class CookieController {

    @GetMapping("/cookie/increase")
    public String increaseCookieValue(HttpServletRequest request, HttpServletResponse response, Model model) {
        Cookie cookie = WebUtils.getCookie(request, "cookieValue");
        int cookieValue = 0;
        if (cookie != null) {
            try {
                cookieValue = Integer.parseInt(cookie.getValue()) + 1;
            } catch (NumberFormatException e) {
                cookieValue = 1;
            }
        } else {
            cookieValue = 1;
        }

        Cookie newCookie = new Cookie("cookieValue", String.valueOf(cookieValue));
        newCookie.setMaxAge(15);
        response.addCookie(newCookie);
        model.addAttribute("cookieValue", cookieValue);
        return "/cookieView";
    }
}
