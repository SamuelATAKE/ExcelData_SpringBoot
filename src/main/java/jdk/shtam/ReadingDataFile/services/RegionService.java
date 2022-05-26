/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdk.shtam.ReadingDataFile.services;

import java.util.List;
import jdk.shtam.ReadingDataFile.Repositories.RegionRepository;
import jdk.shtam.ReadingDataFile.entities.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Samuel
 */
@Service
public class RegionService {
    
    @Autowired
    private RegionRepository repository;

    public RegionService(RegionRepository repository) {
        this.repository = repository;
    }
    
    public List<Region> getRegions() {
        return repository.findAll();
    }
    
    public Region findRegionById(Long id) {
        return repository.getById(id);
    }
    
    public Region findRegionByName(String nom) {
        Region foundRegion = new Region();
        for(Region r : repository.findAll()) {
            if(r.getNom() == nom) {
                foundRegion = r;
                break;
            } 
        }
        
        return foundRegion;
    }
    
    public Region createRegion(Region region) {
        return repository.save(region);
    }
    
    public boolean ifExists(Region region) {
        for(Region r : repository.findAll()) {
            if(r.getNom() == region.getNom()) {
                return true;
            }
        }
        return false;
    }
}
