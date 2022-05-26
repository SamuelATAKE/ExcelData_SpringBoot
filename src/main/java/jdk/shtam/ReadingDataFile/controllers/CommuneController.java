/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdk.shtam.ReadingDataFile.controllers;

import java.util.List;
import jdk.shtam.ReadingDataFile.entities.Commune;
import jdk.shtam.ReadingDataFile.services.CommuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Samuel
 */
@RestController
@RequestMapping("/commune")
public class CommuneController {
    
    @Autowired
    private CommuneService service;
    
    @GetMapping("/")
    public List<Commune> getCommunes() {
        return service.getCommunes();
    }
    
    @GetMapping("/{id}")
    public Commune findCommuneById(@PathVariable Long id) {
        return service.findCommuneById(id);
    }
    
    @PostMapping
    public Commune addCommune(@RequestBody Commune commune) {
        return service.createCommune(commune);
    }
}
