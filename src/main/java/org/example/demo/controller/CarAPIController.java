package org.example.demo.controller;

import org.example.demo.model.Car;
import org.example.demo.service.IBrandService;
import org.example.demo.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarAPIController {
    @Autowired
    ICarService carService;
    @Autowired
    IBrandService brandService;
    @GetMapping // api tim kiem toan bo danh sach
    public ResponseEntity findAll() {
        List<Car> list = (List<Car>) carService.findAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Car car) {
        carService.save(car);
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable Long id) {
        carService.remove(id);
        return new ResponseEntity("OK", HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity findById (@PathVariable Long id) {
        return new ResponseEntity(carService.findById(id).get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity  edit(@PathVariable Long id,@RequestBody Car car) {
        car.setId(id);
        carService.save(car);
        return new ResponseEntity("OK", HttpStatus.OK);
    }

    @GetMapping("/findByName")
    public ResponseEntity findByName (@RequestParam String name) {
        return new ResponseEntity(carService.findAllByNameContaining(name), HttpStatus.OK);
    }

}
