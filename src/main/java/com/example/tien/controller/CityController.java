package com.example.tien.controller;

import com.example.tien.model.City;
import com.example.tien.model.Country;
import com.example.tien.service.ICityService;
import com.example.tien.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller

public class CityController {
    @Autowired
    private ICityService cityService;
    @Autowired
    private ICountryService countryService;

    @ModelAttribute("countries")
    public Iterable<Country> getAll(){
        return countryService.findAll();
    }
    @GetMapping("/")
    public ModelAndView showCity(@RequestParam("regex") Optional<String> regex, @SortDefault(sort = {"id"}) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("index");
        Page<City> cities;
        if (regex.isPresent()) {
            cities = cityService.findAllByName(regex.get(), pageable);
        } else {
            cities = cityService.findAll(pageable);
        }
        modelAndView.addObject("regex", regex.orElse(""));
        modelAndView.addObject("cities", cities);
        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewCity(@PathVariable("id") Long id){
        ModelAndView modelAndView = new ModelAndView("view");
        City city = cityService.findCityById(id);
        modelAndView.addObject("city", city);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteCity(@PathVariable("id") Long id, @SortDefault(sort = {"id"}) Pageable pageable){
        cityService.delete(id);
        ModelAndView modelAndView = new ModelAndView("index");
        Page<City> cities = cityService.findAll(pageable);
        modelAndView.addObject("cities", cities);
        modelAndView.addObject("success", "Xóa thành công!");
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createCity(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("city", new City());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView saveCity(@Valid @ModelAttribute("city") City city, BindingResult bindingResult,
                                 @SortDefault(sort = {"id"}) Pageable pageable){
        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("create");
            modelAndView.addObject("city", city);
            return modelAndView;
        }
        cityService.save(city);
        Page<City> cities = cityService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("cities", cities);
        modelAndView.addObject("success", "Tạo thành công!");
        return modelAndView;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView editCity(@PathVariable("id") Long id){
        City city = cityService.findCityById(id);
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("city", city);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView updateCity(@Valid @ModelAttribute("city") City city, BindingResult bindingResult,
                                   @SortDefault(sort = {"id"}) Pageable pageable){
        if(bindingResult.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("edit");
            modelAndView.addObject("city", city);
            return modelAndView;
        }
        cityService.save(city);
        Page<City> cities = cityService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("cities", cities);
        modelAndView.addObject("success", "Cập nhật thành công!");
        return modelAndView;
    }
}
