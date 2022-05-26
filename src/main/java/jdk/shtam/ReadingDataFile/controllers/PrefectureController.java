/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdk.shtam.ReadingDataFile.controllers;

import java.util.List;
import jdk.shtam.ReadingDataFile.entities.Prefecture;
import jdk.shtam.ReadingDataFile.services.PrefectureService;
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
@RequestMapping("/prefecture")
public class PrefectureController {
    
    @Autowired
    private PrefectureService service;

    public PrefectureController(PrefectureService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Prefecture> getPrefectures() {
        return service.getPrefectures();
    }
    
    @GetMapping("/{id}")
    public Prefecture findPrefectureById(@PathVariable Long id) {
        return service.findPrefectureById(id);
    }
    
    @PostMapping
    public Prefecture addPrefecture(@RequestBody Prefecture prefecture) {
        return service.createPrefecture(prefecture);
    }
}
