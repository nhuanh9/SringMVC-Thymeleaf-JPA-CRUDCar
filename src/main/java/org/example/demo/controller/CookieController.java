package org.example.demo.controller;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.util.WebUtils;

@Controller
public class CookieController {

    @GetMapping("/cookie/increase")
    public String increaseCookieValue(@CookieValue(name = "cookieValue", defaultValue = "0") String value, HttpServletResponse response, Model model) {
        int cookieValue;
        try {
            // Cố gắng chuyển giá trị cookie từ String sang Integer và tăng nó lên
            cookieValue = Integer.parseInt(value) + 1;
        } catch (NumberFormatException e) {
            // Trong trường hợp giá trị cookie không phải là một số, thiết lập cookieValue bằng 1
            cookieValue = 1;
        }

        // Tạo một cookie mới và gửi nó lại cho client
        Cookie newCookie = new Cookie("cookieValue", String.valueOf(cookieValue));
        newCookie.setMaxAge(15); // Thiết lập thời gian sống của cookie là 15 giây
        response.addCookie(newCookie); // Thêm cookie vào response để gửi về client

        // Thêm giá trị cookie vào model để có thể sử dụng nó trong view
        model.addAttribute("cookieValue", cookieValue);
        return "/cookieView"; // Trả về tên của view để hiển thị
    }

}
