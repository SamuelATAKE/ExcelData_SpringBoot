/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdk.shtam.ReadingDataFile.entities;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author Samuel
 */
@Entity
public class Commune {
    
    @Id
    @SequenceGenerator(
            name = "communeSequence",
            sequenceName = "communeSequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "communeSequence"
    )
    private Long id;
    private String nom;
    @ManyToOne
    private Prefecture prefecture;

    public Commune() {
    }

    public Commune(String nom, Prefecture prefecture) {
        this.nom = nom;
        this.prefecture = prefecture;
    }

    public Commune(Long id, String nom, Prefecture prefecture) {
        this.id = id;
        this.nom = nom;
        this.prefecture = prefecture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Prefecture getPrefecture() {
        return prefecture;
    }

    public void setPrefecture(Prefecture prefecture) {
        this.prefecture = prefecture;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Commune other = (Commune) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Commune{" + "id=" + id + ", nom=" + nom + ", prefecture=" + prefecture + '}';
    }
   
}
