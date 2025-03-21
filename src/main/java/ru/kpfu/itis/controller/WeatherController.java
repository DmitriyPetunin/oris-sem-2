package ru.kpfu.itis.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.model.get.ProductResponse;
import ru.kpfu.itis.model.post.PostEntity;
import ru.kpfu.itis.service.WeatherService;

import java.util.List;

@RestController
public class WeatherController {

    private final WeatherService service;

    public WeatherController(WeatherService webService) {
        this.service = webService;
    }

    @GetMapping("/getAllObjects")
    public List<ProductResponse> get(){
        return service.getAllObjects();
    }

    @PostMapping(value = "/save",consumes = MediaType.APPLICATION_JSON_VALUE)
    public String post(@RequestBody(required = false) PostEntity entity){
        return service.addObject();
    }

    @PutMapping("/update/{id}")
    public String put(@PathVariable("id") String id){
        return service.updateObject(id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id){
        return service.delete(id);
    }

}
