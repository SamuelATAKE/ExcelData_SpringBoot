/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdk.shtam.ReadingDataFile.dataexport;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdk.shtam.ReadingDataFile.entities.Touriste;
import jdk.shtam.ReadingDataFile.services.TouristeService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

/**
 *
 * @author Samuel
 */
public class TouristeExcelDataExport extends AbstractXlsxView {
    
    @Autowired
    private TouristeService touristeService;
            
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        // Déclaration du nom du fichier excel    
        response.addHeader("Content-Disposition", "attachment;fileName=TouristeData.xlsx");
        
        // Récupération des données
        List<Touriste> touristes = touristeService.getTouristes();
        
        //Création de la feuille 
        Sheet sheet = workbook.createSheet("Touristes");
        
        // creating row0 as a header
       Row row0 = sheet.createRow(0);
       row0.createCell(0).setCellValue("N°");
       row0.createCell(1).setCellValue("Nom");
       row0.createCell(2).setCellValue("Prenom");
       row0.createCell(3).setCellValue("Date de naissance");
       row0.createCell(4).setCellValue("Sexe");
       row0.createCell(5).setCellValue("Nationalite");
       row0.createCell(6).setCellValue("Pays résidence");
       row0.createCell(7).setCellValue("Adresse");
       row0.createCell(8).setCellValue("Téléphone");
       row0.createCell(9).setCellValue("Adresse mail");
       
       // create row1 onwards from List<T>
       int rowNum = 1;
       for(Touriste touriste : touristes) {
           Row row = sheet.createRow(rowNum++);
           row.createCell(0).setCellValue(touriste.getId());
           row.createCell(1).setCellValue(touriste.getNomTouriste());
           row.createCell(2).setCellValue(touriste.getPrenomTouriste());
           row.createCell(3).setCellValue(touriste.getDateNaissanceTouriste().toString());
           row.createCell(4).setCellValue(touriste.getSexeTouriste().toString());
           row.createCell(5).setCellValue(touriste.getNationaliteTouriste().toString());
           row.createCell(6).setCellValue(touriste.getPaysResidenceTouriste().toString());
           row.createCell(7).setCellValue(touriste.getAdresse());
           row.createCell(8).setCellValue(touriste.getTelephoneTouriste());
           row.createCell(9).setCellValue(touriste.getEmail());
       }
       
        
    }
    
}
