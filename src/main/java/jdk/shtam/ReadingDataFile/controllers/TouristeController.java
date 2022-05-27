/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdk.shtam.ReadingDataFile.controllers;

import java.util.List;
import jdk.shtam.ReadingDataFile.entities.Touriste;
import jdk.shtam.ReadingDataFile.services.TouristeService;
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
@RequestMapping("/touriste")
public class TouristeController {
    
    @Autowired
    private TouristeService service;
    
    @GetMapping
    public List<Touriste> getTouristes() {
        return service.getTouristes();
    }
    
    @GetMapping("/{id}")
    public Touriste findTouristeById(@PathVariable Long id) {
        return service.findTouristeById(id);
    }
    
    @PostMapping
    public Touriste addTouriste(@RequestBody Touriste touriste) {
        return service.createTouriste(touriste);
    }
}
