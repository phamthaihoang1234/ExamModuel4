package com.example.demo.city.Controller;


import com.example.demo.city.model.City;
import com.example.demo.city.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
public class CityController {

    @Autowired
    CityRepository cityRepository;


    @GetMapping("/")
    public String showPage(Model model, @RequestParam("searchName") Optional<String> name, @PageableDefault(value =3) Pageable pageable) {
        String searchName = name.orElse("");
        model.addAttribute("data", cityRepository.findAllByNameContaining(searchName,pageable));
        return "index";
    }

    @PostMapping("/save")
    public String save(City city, Model model) {

        cityRepository.save(city);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteCountry(long id) {
//        List<Country> countries = new ArrayList<>();
//        countries = countryRepository.findAll();
//        for (long i=countries.size(); i>= (id+1); countries.size() ){
//            countryRepository.getOne(i).setId(i-1);
//        }
        cityRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/findOne")
    @ResponseBody
    public City findOne(Long id) {
        return (City) cityRepository.findById(id).get();
    }
}
