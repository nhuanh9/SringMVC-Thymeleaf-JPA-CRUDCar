package org.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* org.example.demo.controller.*.*(..))")
    public void logBeforeAllMethods(JoinPoint joinPoint) {
        System.out.println("Before method: " + joinPoint.getSignature().getName());
    }

    @After("execution(* org.example.demo.controller.*.*(..))")
    public void logAfterAllMethods(JoinPoint joinPoint) {
        System.out.println("After method: " + joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "execution(public * org.example.demo.controller.*.*(..))", throwing = "e")
    public ModelAndView logMethod(Exception e) {
        System.out.println("[CMS] co loi xay ra: " + e.getMessage());
        ModelAndView modelAndView = new ModelAndView("redirect:/cars");
        return modelAndView;
    }

    @AfterThrowing(pointcut = "execution(public * org.example.demo.controller.*.*(..))", throwing = "e")
    public void logClass(JoinPoint joinPoint, Exception e) { // Advice
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        System.out.printf("[CMS] co loi xay ra: %s.%s%s: %s%n", className, method, args, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e, Model model) {
        ModelAndView modelAndView = new ModelAndView("redirect:/cars");
        return modelAndView;
    }
}