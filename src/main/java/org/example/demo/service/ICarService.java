package org.example.demo.service;

import org.example.demo.model.Brand;
import org.example.demo.model.Car;

import java.util.List;

public interface ICarService extends IGeneralService<Car>{
    List<Car> findAllByNameContaining(String string);
}
