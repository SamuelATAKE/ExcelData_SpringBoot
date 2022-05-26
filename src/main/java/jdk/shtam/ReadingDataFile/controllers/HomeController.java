/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdk.shtam.ReadingDataFile.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import jdk.shtam.ReadingDataFile.entities.Commune;
import jdk.shtam.ReadingDataFile.entities.Prefecture;
import jdk.shtam.ReadingDataFile.entities.Region;
import jdk.shtam.ReadingDataFile.services.CommuneService;
import jdk.shtam.ReadingDataFile.services.PrefectureService;
import jdk.shtam.ReadingDataFile.services.RegionService;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Samuel
 */
@RestController
@RequestMapping("/home")
public class HomeController {
   
   @Autowired
   private RegionService regionService;
   
   @Autowired
   private PrefectureService prefectureService;
   
   @Autowired
   private CommuneService communeService;
   
   ArrayList<String> values = new ArrayList<String>();
   
   int i =0;
   
   @GetMapping
   public String welcome() {
       return "Bienvenu dans notre application";
   }
   
   @GetMapping("/read")
   public String[][] readingFile() {
       String[][] datas = new String[120][4];
       try {
          // InputStream input = new FileInputStream("data.xlsx");
            File input = new File("datas.xlsx");
            OPCPackage pkg = OPCPackage.open(input);
            // POIFSFileSystem fileSystem = new POIFSFileSystem(pkg);
            XSSFWorkbook book = new XSSFWorkbook(pkg);
            XSSFSheet sheet = book.getSheetAt(0);
            
            Iterator rows = sheet.rowIterator(); 
            
            //System.out.println("First try");
            int x = 0;
            int y = 0;
            while(rows.hasNext()) {
                
                //System.out.println("First while");
                values.clear();
                
                XSSFRow row = (XSSFRow) rows.next();
                
                Iterator cells = row.cellIterator();
                y = 0;
                while (cells.hasNext()) {
                    //System.out.println("Second while");
                    XSSFCell cell = (XSSFCell) cells.next();
                    
                    if(XSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                        values.add(String.valueOf(cell.getNumericCellValue()) );
                        datas[x][y] = String.valueOf(cell.getNumericCellValue());
                    } else if(XSSFCell.CELL_TYPE_STRING == cell.getCellType()) {
                        values.add(cell.getStringCellValue());
                        datas[x][y] = cell.getStringCellValue();
                    }
                   y++; 
                }
                
                
                /*System.out.println("Value: "+i);
                System.out.println(values);
                // int j = 0;
                String [] tab = { "value" };
                int j = 0;
                if(i>1){
                    System.out.println("Over 1");
                    for(String value : values) {
                        tab[j] = value;
                        System.out.println(value); 
                        j++;
                    }
                    
                    Region region = new Region(tab[0]);
                    System.out.println("Region exists? : "+regionService.ifExists(region));
                    Region pRegion = new Region();

                    if(!regionService.ifExists(region)) {
                        regionService.createRegion(region);
                        System.out.println("Created");
                     }

                    pRegion = regionService.findRegionByName(tab[0]);

                    Prefecture prefecture = new Prefecture(tab[1], pRegion);

                    if(!prefectureService.ifExists(prefecture)) {
                        prefectureService.createPrefecture(prefecture);
                        System.out.println("Prefecture created");
                    }
                    
                    j = 0;
                }*/
                
                
                System.out.println("    ---     ");
                i++;
                x++;
                
            }
            return datas;
       } catch (Exception e) {
           System.out.println("Impossible de lire le fichier");
            System.out.println(e);
       }
       
       return datas;
   } 
   
   @GetMapping("/insert")
   public String insertingInDatabase() {
       String[][] datas = readingFile();
       for(int x =0; x<119; x++) {
           if(x>1) {
                Region region = new Region(datas[x][0]);
                System.out.println("Region exists? : "+regionService.ifExists(region));
                Region pRegion = new Region();

                if(!regionService.ifExists(region)) {
                    regionService.createRegion(region);
                    System.out.println("Created");
                 }

                pRegion = regionService.findRegionByName(datas[x][0]);

                Prefecture prefecture = new Prefecture(datas[x][1], pRegion);

                if(!prefectureService.ifExists(prefecture)) {
                    prefectureService.createPrefecture(prefecture);
                    System.out.println("Prefecture created");
                }
                
                Prefecture cPrefecture = prefectureService.findPrefectureByName(datas[x][1]);
                Commune commune = new Commune(datas[x][3], cPrefecture);
                
                communeService.createCommune(commune);
           }
       }
       
       return "Done";
   }
   
}
