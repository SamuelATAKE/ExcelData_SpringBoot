/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdk.shtam.ReadingDataFile.services;

import java.util.List;
import jdk.shtam.ReadingDataFile.Repositories.PrefectureRepository;
import jdk.shtam.ReadingDataFile.entities.Prefecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Samuel
 */
@Service
public class PrefectureService {
    
    @Autowired
    private PrefectureRepository repository;

    public PrefectureService(PrefectureRepository repository) {
        this.repository = repository;
    }
    
    public List<Prefecture> getPrefectures() {
        return repository.findAll();
    }
    
    public Prefecture findPrefectureById(Long id) {
        return repository.getById(id);
    }
    
    public Prefecture findPrefectureByName(String nom) {
        Prefecture foundPrefecture = new Prefecture();
        for(Prefecture p : repository.findAll()) {
            if(p.getNom() == nom) {
                foundPrefecture = p;
                break;
            }
        }
        
        return foundPrefecture;
    }
    
    public Prefecture createPrefecture(Prefecture prefecture) {
        return repository.save(prefecture);
    }
    
    public Boolean ifExists(Prefecture prefecture) {
        for(Prefecture p : repository.findAll()) {
            if(p.getNom() == prefecture.getNom()) {
                return true;
            }
        }
        
        return false;
    }
}
