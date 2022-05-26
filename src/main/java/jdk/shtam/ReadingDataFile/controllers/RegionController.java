/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdk.shtam.ReadingDataFile.controllers;

import java.util.List;
import jdk.shtam.ReadingDataFile.entities.Region;
import jdk.shtam.ReadingDataFile.services.RegionService;
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
@RequestMapping(path="/region")
public class RegionController {
    
    @Autowired
    private RegionService service;

    public RegionController(RegionService service) {
        this.service = service;
    }
    
    @GetMapping("/")
    public List<Region> getRegions() {
        return service.getRegions();
    }
    
    @GetMapping("/{id}")
    public Region findRegionById(@PathVariable Long id) {
        return service.findRegionById(id);
    }
    
    @PostMapping
    public Region addRegion(@RequestBody Region region) {
        return service.createRegion(region);
    }
}
