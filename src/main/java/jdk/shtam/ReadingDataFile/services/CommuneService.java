/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdk.shtam.ReadingDataFile.services;

import java.util.List;
import jdk.shtam.ReadingDataFile.Repositories.CommuneRepository;
import jdk.shtam.ReadingDataFile.entities.Commune;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Samuel
 */
@Service
public class CommuneService {
    
    @Autowired
    private CommuneRepository repository;

    public CommuneService(CommuneRepository repository) {
        this.repository = repository;
    }
    
    public List<Commune> getCommunes() {
        return repository.findAll();
    }
    
    public Commune findCommuneById(Long id) {
        return repository.getById(id);
    }
    
    public Commune createCommune(Commune commune) {
        return repository.save(commune);
    }
    
}
