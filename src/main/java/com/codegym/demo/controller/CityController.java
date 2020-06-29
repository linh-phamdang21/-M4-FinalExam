package com.codegym.demo.controller;

import com.codegym.demo.model.City;
import com.codegym.demo.model.Country;
import com.codegym.demo.service.city.ICityService;
import com.codegym.demo.service.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class CityController {

    @Autowired
    private ICityService cityService;

    @Autowired
    private ICountryService countryService;

    @ModelAttribute("countries")
    public Iterable<Country> countries() {
        return countryService.getAll();
    }

    @GetMapping("/city/list")
    public ModelAndView listProduct(@RequestParam("s")Optional<String> s,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "4") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<City> cities;
        if (s.isPresent()){
            cities = cityService.findAllByNameContaining(s.get(), pageable);
        } else {
            cities = cityService.findAll(pageable);
        }
        ModelAndView modelAndView = new ModelAndView("city/cityList");
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("/city/create")
    public ModelAndView showCreateForm(){
        City city = new City();
        ModelAndView modelAndView = new ModelAndView("/city/create");
        modelAndView.addObject("city",city);
        modelAndView.addObject("countries", countries());
        return modelAndView;
    }

    @PostMapping("/city/create")
    public ModelAndView createProduct(@ModelAttribute("city") City city){
        City newProduct = new City(city.getName(), city.getArea(),
                 city.getPopulation(), city.getGDP(), city.getDescription(), city.getCountry());
        cityService.save(newProduct);
        ModelAndView modelAndView = new ModelAndView("/city/create");
        modelAndView.addObject("message", "Add new city successfully!");
        modelAndView.addObject("city", newProduct);
        return modelAndView;
    }

    @GetMapping("/city/edit/{id}")
    public ModelAndView showEditForm(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("city/edit");
        Optional<City> city = cityService.findById(id);
        if (city.isPresent()){
            City editProduct = city.get();
            modelAndView.addObject("city", city);

        } else {
            modelAndView.addObject("city", new City());
        }
        return modelAndView;
    }

    @PostMapping("/city/edit")
    public ModelAndView editProduct(@ModelAttribute("city") City city){
        ModelAndView modelAndView = new ModelAndView("city/edit");
        if (city.getId() != null){
            modelAndView.addObject("message", "Edit product successfully!");
        } else {
            modelAndView.addObject("message", "Edit error");
        }
        cityService.save(city);
        return modelAndView;
    }

    @GetMapping("/city/delete/{id}")
    public ModelAndView deleteProduct(@PathVariable("id") Long id){
        cityService.remove(id);
        return new ModelAndView("redirect:/city/list");
    }

    @GetMapping("/city/detail/{id}")
    public ModelAndView showDetailForm(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("city/cityDetail");
        Optional<City> city = cityService.findById(id);
        if (city.isPresent()){
            City cityDetail = city.get();
            modelAndView.addObject("cityDetail", cityDetail);

        } else {
            modelAndView.addObject("cityDetail", new City());
        }
        return modelAndView;
    }
}
