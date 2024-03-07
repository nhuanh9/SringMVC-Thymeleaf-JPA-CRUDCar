package org.example.demo.controller;

import org.example.demo.model.Car;
import org.example.demo.service.IBrandService;
import org.example.demo.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/cars")
public class CarController {
    @Autowired
    ICarService carService;
    @Autowired
    IBrandService brandService;
    @GetMapping
    public ModelAndView showHomePage(){
        ModelAndView modelAndView = new ModelAndView("/list");
        modelAndView.addObject("cars", carService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("car", new Car());
        modelAndView.addObject("listBrand", brandService.findAll());
        return modelAndView;
    }

    @PostMapping("/save") // join point
    public ModelAndView create(@Valid Car car, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception();
//            ModelAndView modelAndView = new ModelAndView("/create");
//            modelAndView.addObject("car", new Car());
//            modelAndView.addObject("listBrand", brandService.findAll());
//            modelAndView.addObject("listErr", bindingResult.getAllErrors());
//            return modelAndView;
        }
        carService.save(car);
        ModelAndView modelAndView = new ModelAndView("redirect:/cars");
        return modelAndView;
    }
    @PostMapping("/edit")
    public ModelAndView edit(@Valid Car car, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("/edit");
            modelAndView.addObject("car", carService.findById(car.getId()).get());
            modelAndView.addObject("listBrand", brandService.findAll());
            modelAndView.addObject("listErr", bindingResult.getAllErrors());
            return modelAndView;
        }
        carService.save(car);
        ModelAndView modelAndView = new ModelAndView("redirect:/cars");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("car", carService.findById(id).get());
        modelAndView.addObject("listBrand", brandService.findAll());
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Long id){
        carService.remove(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/cars");
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam String searchText){
        ModelAndView modelAndView = new ModelAndView("/result");
        modelAndView.addObject("cars", carService.findAllByNameContaining(searchText));
        return modelAndView;
    }

    @GetMapping("/details/{id}")
    public ModelAndView showDetails(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/details");
        modelAndView.addObject("car", carService.findById(id).get());
        return modelAndView;
    }
}
