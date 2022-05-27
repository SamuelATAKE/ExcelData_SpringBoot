/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdk.shtam.ReadingDataFile.entities;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import jdk.shtam.ReadingDataFile.entities.enumclasses.Pays;
import jdk.shtam.ReadingDataFile.entities.enumclasses.Sexe;

/**
 *
 * @author Samuel
 */
@Entity
public class Touriste {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "nom_touriste", nullable = false)
    private String nomTouriste;

    @NotNull
    @Column(name = "prenom_touriste", nullable = false)
    private String prenomTouriste;

    @NotNull
    @Column(name = "date_naissance_touriste", nullable = false)
    private LocalDate dateNaissanceTouriste;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sexe_touriste", nullable = false)
    private Sexe sexeTouriste;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "nationalite_touriste", nullable = false)
    private Pays nationaliteTouriste;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "pays_residence_touriste", nullable = false)
    private Pays paysResidenceTouriste;

    @NotNull
    @Column(name = "adresse", nullable = false)
    private String adresse;

    @Column(name = "telephone_touriste")
    private String telephoneTouriste;

    @Column(name = "email")
    private String email;

    public Touriste() {
    }

    public Touriste(String nomTouriste, String prenomTouriste, LocalDate dateNaissanceTouriste, Sexe sexeTouriste, Pays nationaliteTouriste, Pays paysResidenceTouriste, String adresse, String telephoneTouriste, String email) {
        this.nomTouriste = nomTouriste;
        this.prenomTouriste = prenomTouriste;
        this.dateNaissanceTouriste = dateNaissanceTouriste;
        this.sexeTouriste = sexeTouriste;
        this.nationaliteTouriste = nationaliteTouriste;
        this.paysResidenceTouriste = paysResidenceTouriste;
        this.adresse = adresse;
        this.telephoneTouriste = telephoneTouriste;
        this.email = email;
    }

    public Touriste(Long id, String nomTouriste, String prenomTouriste, LocalDate dateNaissanceTouriste, Sexe sexeTouriste, Pays nationaliteTouriste, Pays paysResidenceTouriste, String adresse, String telephoneTouriste, String email) {
        this.id = id;
        this.nomTouriste = nomTouriste;
        this.prenomTouriste = prenomTouriste;
        this.dateNaissanceTouriste = dateNaissanceTouriste;
        this.sexeTouriste = sexeTouriste;
        this.nationaliteTouriste = nationaliteTouriste;
        this.paysResidenceTouriste = paysResidenceTouriste;
        this.adresse = adresse;
        this.telephoneTouriste = telephoneTouriste;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomTouriste() {
        return nomTouriste;
    }

    public void setNomTouriste(String nomTouriste) {
        this.nomTouriste = nomTouriste;
    }

    public String getPrenomTouriste() {
        return prenomTouriste;
    }

    public void setPrenomTouriste(String prenomTouriste) {
        this.prenomTouriste = prenomTouriste;
    }

    public LocalDate getDateNaissanceTouriste() {
        return dateNaissanceTouriste;
    }

    public void setDateNaissanceTouriste(LocalDate dateNaissanceTouriste) {
        this.dateNaissanceTouriste = dateNaissanceTouriste;
    }

    public Sexe getSexeTouriste() {
        return sexeTouriste;
    }

    public void setSexeTouriste(Sexe sexeTouriste) {
        this.sexeTouriste = sexeTouriste;
    }

    public Pays getNationaliteTouriste() {
        return nationaliteTouriste;
    }

    public void setNationaliteTouriste(Pays nationaliteTouriste) {
        this.nationaliteTouriste = nationaliteTouriste;
    }

    public Pays getPaysResidenceTouriste() {
        return paysResidenceTouriste;
    }

    public void setPaysResidenceTouriste(Pays paysResidenceTouriste) {
        this.paysResidenceTouriste = paysResidenceTouriste;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephoneTouriste() {
        return telephoneTouriste;
    }

    public void setTelephoneTouriste(String telephoneTouriste) {
        this.telephoneTouriste = telephoneTouriste;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Touriste other = (Touriste) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Touriste{" + "id=" + id + ", nomTouriste=" + nomTouriste + ", prenomTouriste=" + prenomTouriste + ", dateNaissanceTouriste=" + dateNaissanceTouriste + ", sexeTouriste=" + sexeTouriste + ", nationaliteTouriste=" + nationaliteTouriste + ", paysResidenceTouriste=" + paysResidenceTouriste + ", adresse=" + adresse + ", telephoneTouriste=" + telephoneTouriste + ", email=" + email + '}';
    }
    
    
}
