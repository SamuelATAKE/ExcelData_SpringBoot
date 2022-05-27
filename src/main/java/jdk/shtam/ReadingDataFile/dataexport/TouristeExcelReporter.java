/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdk.shtam.ReadingDataFile.dataexport;

import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import jdk.shtam.ReadingDataFile.entities.Touriste;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Samuel
 */
public class TouristeExcelReporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Touriste> touristes;

    public TouristeExcelReporter(List<Touriste> touristes) {
        this.touristes = touristes;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Touristes");
        
    }
    
    private void writeHeaderRow() {
        Row row = sheet.createRow(0);
        
        row.createCell(0).setCellValue("N°");
        row.createCell(1).setCellValue("Nom");
        row.createCell(2).setCellValue("Prenom");
        row.createCell(3).setCellValue("Date de naissance");
        row.createCell(4).setCellValue("Sexe");
        row.createCell(5).setCellValue("Nationalite");
        row.createCell(6).setCellValue("Pays résidence");
        row.createCell(7).setCellValue("Adresse");
        row.createCell(8).setCellValue("Téléphone");
        row.createCell(9).setCellValue("Adresse mail");
        
    }
    
    private void writeDataRows() {
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
    
    public void export(HttpServletResponse response) throws Exception {
        writeHeaderRow();
        writeDataRows();
        
        ServletOutputStream output = response.getOutputStream();
        workbook.write(output);
        workbook.close();
        output.close();
    }
}
