/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdk.shtam.ReadingDataFile.services;

import java.util.List;
import jdk.shtam.ReadingDataFile.Repositories.TouristeRepository;
import jdk.shtam.ReadingDataFile.entities.Touriste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Samuel
 */
@Service
public class TouristeService {
    
    @Autowired
    private TouristeRepository repository;

    public TouristeService(TouristeRepository repository) {
        this.repository = repository;
    }
    
    public List<Touriste> getTouristes() {
        return repository.findAll();
    }
    
    public Touriste findTouristeById(Long id) {
        return repository.getById(id);
    }
    
    public Touriste createTouriste(Touriste touriste) {
        return repository.save(touriste);
    }
    
}
