package com.budzko.cookbook.spring.springboot.crud.app.controller;

import com.budzko.cookbook.spring.springboot.crud.app.dao.model.Developer;
import com.budzko.cookbook.spring.springboot.crud.app.service.DeveloperService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/developer")
public class CrudAppController {
    private DeveloperService developerService;

    public CrudAppController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping("/getById/{id}")
    public Developer findDeveloperById(@PathVariable Long id) {
        return developerService.findDeveloperById(id);
    }

    @PostMapping("/createDeveloper")
    public Developer createDeveloperById(@RequestBody Developer developer) {
        return developerService.createDeveloper(developer);
    }

    @PutMapping("/updateDeveloper")
    public Developer updateDeveloperById(@RequestBody Developer developer) {
        return developerService.updateDeveloper(developer);
    }
}
