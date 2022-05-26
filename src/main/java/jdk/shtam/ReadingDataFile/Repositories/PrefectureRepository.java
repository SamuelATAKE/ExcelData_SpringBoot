/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdk.shtam.ReadingDataFile.Repositories;

import jdk.shtam.ReadingDataFile.entities.Prefecture;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Samuel
 */
public interface PrefectureRepository extends JpaRepository<Prefecture, Long> {
    
}
