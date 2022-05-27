/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdk.shtam.ReadingDataFile.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import jdk.shtam.ReadingDataFile.dataexport.TouristeExcelDataExport;
import jdk.shtam.ReadingDataFile.dataexport.TouristeExcelReporter;
import jdk.shtam.ReadingDataFile.entities.Commune;
import jdk.shtam.ReadingDataFile.entities.Prefecture;
import jdk.shtam.ReadingDataFile.entities.Region;
import jdk.shtam.ReadingDataFile.entities.Touriste;
import jdk.shtam.ReadingDataFile.services.CommuneService;
import jdk.shtam.ReadingDataFile.services.PrefectureService;
import jdk.shtam.ReadingDataFile.services.RegionService;
import jdk.shtam.ReadingDataFile.services.TouristeService;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
   
   @Autowired
   private TouristeService touristeService;
   
   int i =0;
   
   @GetMapping
   public String welcome() {
       return "Bienvenu dans notre application";
   }
   
   @GetMapping("/read")
   public String[][] readingFile() {
       //Déclaration du tableau de String à deux dimensions qui va contenir les données du fichier Excel
       String[][] datas = new String[120][4];
       try {
            // InputStream input = new FileInputStream("data.xlsx");
            // A ce qu'il parâit, l'utilisation de File dans ce cas est plus approprié que FileInputStream
            File input = new File("datas.xlsx");
            OPCPackage pkg = OPCPackage.open(input);
            // POIFSFileSystem fileSystem = new POIFSFileSystem(pkg);
            XSSFWorkbook book = new XSSFWorkbook(pkg);
            XSSFSheet sheet = book.getSheetAt(0);
            
            Iterator rows = sheet.rowIterator(); 
            
            //System.out.println("First try");
            int x = 0;
            int y = 0;
            // Parcours des lignes
            while(rows.hasNext()) {
                
                XSSFRow row = (XSSFRow) rows.next();
                
                Iterator cells = row.cellIterator();
                y = 0;
                
                // Parcours des colonnes par lignes : les cellules
                while (cells.hasNext()) {
                    //System.out.println("Second while");
                    XSSFCell cell = (XSSFCell) cells.next();
                    
                    if(XSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                        datas[x][y] = String.valueOf(cell.getNumericCellValue());
                    } else if(XSSFCell.CELL_TYPE_STRING == cell.getCellType()) {
                        datas[x][y] = cell.getStringCellValue();
                    }
                   y++; 
                }
                
                System.out.println("    ---     ");
                i++;
                x++;
                
            }
            return datas;
       } catch (Exception e) {
           System.out.println("Erreur lors de la lecture du fichier");
            System.out.println(e);
       }
       
       return datas;
   } 
   
   @GetMapping("/insert")
   public String insertingInDatabase() {
       // Récupération du tableau de données
       String[][] datas = readingFile();
       for(int x =0; x<119; x++) {
           // Les deux premières lignes du fichier constituent l'entête, donc on les élimine avec un "if index>1"
           if(x>1) {
               // La première colonne d'index 0 correspond à la région
                Region region = new Region(datas[x][0]);
                // System.out.println("Region exists? : "+regionService.ifExists(region));

                // Je vérifie si la région n'existe pas déjà pour ne pas créer de doublon puisque j'ai "unmerge cells" avant de créer une nouvelle
                if(!regionService.ifExists(region)) {
                    regionService.createRegion(region);
                    System.out.println("Created");
                 }
                // Je récupère la région par son nom (premierè colonne du tableau) avant la création de la préfecture
                Region pRegion = regionService.findRegionByName(datas[x][0]);

                // Initialisation de la Préfecture
                Prefecture prefecture = new Prefecture(datas[x][1], pRegion);

                // Je vérifie si la préfecture n'existe pas déjà, avant d'en ajouter une autre.
                if(!prefectureService.ifExists(prefecture)) {
                    prefectureService.createPrefecture(prefecture);
                    System.out.println("Prefecture created");
                }
                
                // Je récupère la préfecture par son nom (étant la 2ème colonne du tableau, donc d'index 2)
                Prefecture cPrefecture = prefectureService.findPrefectureByName(datas[x][1]);
                Commune commune = new Commune(datas[x][3], cPrefecture);
                
                // Création de commune
                communeService.createCommune(commune);
           }
       }
       
       return "Done";
   }
   
   @GetMapping("/exportTouristeToExcel")
   public ModelAndView exportTouristeToExcel() {
       ModelAndView mav = new ModelAndView();
       mav.setView(new TouristeExcelDataExport());
       //TouristeExcelDataExport tede = new TouristeExcelDataExport();
       
       List<Touriste> touristes = touristeService.getTouristes();
       
       mav.addObject("touristes", touristes);
       
       return mav;
   }
   
   @GetMapping("/touristes/export")
   public void exportTouriste(HttpServletResponse response) throws Exception {
       response.setContentType("application/octet-stream");
       
       String headerKey = "Content-Disposition";
       String headerValue = "attachement; filename=touristes.xlsx";
       
       response.setHeader(headerKey, headerValue);
       
       List<Touriste> touristes = touristeService.getTouristes();
       
       TouristeExcelReporter touristeExporter =  new TouristeExcelReporter(touristes);
       
       touristeExporter.export(response);
   }
   
}
