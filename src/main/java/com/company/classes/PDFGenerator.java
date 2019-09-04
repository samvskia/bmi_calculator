package com.company.classes;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class PDFGenerator {

    private static String firstName;
    private static String lastName;
    private static int age;
    private static String gender;
    private static double mass;
    private static double height;
    private static double indexBMI;
    private static double minBMI;
    private static double maxBMI;
    private static String templateName = "D:/projects/bmi_calculator/projectData/jasper_templates/bmi_parameters.jrxml";
    private static String uploadDirection = "C:/Users/itmsuk01/Downloads";
    private static String fileName = "BMIReport";
    private static int i = 1;

    public PDFGenerator(String firstName, String lastName, int age, int genderId, double mass, double height, double indexBMI, double minBMI, double maxBMI){

        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = (genderId == 1) ? "Man" : "Woman";
        this.mass = mass;
        this.height = height;
        this.indexBMI = indexBMI;
        this.minBMI = minBMI;
        this.maxBMI = maxBMI;
    }

    public String getFirstName(){
        return firstName;
    }

    public static void createPDF(){

        try{
            //Compile jrxml file.
            JasperReport jasperReport = JasperCompileManager.compileReport(templateName);

            //Parameters for report
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("firstName", firstName);
            parameters.put("lastName", lastName);
            parameters.put("age", age);
            parameters.put("gender", gender);
            parameters.put("mass", mass);
            parameters.put("height", height);
            parameters.put("indexBMI", indexBMI);
            parameters.put("minBMI", minBMI);
            parameters.put("maxBMI", maxBMI);

            // DataSource
            // This is simple example, no database.
            // then using empty datasource.
            JRDataSource dataSource = new JREmptyDataSource();

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);


            // Make sure the output directory exists.
            File outDir = new File(uploadDirection);
            outDir.mkdirs();

            // Export to PDF.
            while ((new File(uploadDirection + "/" + fileName + i + ".pdf")).exists()){
                i++;
            }
            fileName = fileName + i;
            JasperExportManager.exportReportToPdfFile(jasperPrint, uploadDirection + "/" + fileName + ".pdf");

        }catch(JRException e){
            e.printStackTrace();
        }

        System.out.println("The PDF document has downloaded!");
    }
}
