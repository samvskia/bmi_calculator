package com.company.classes;
import java.sql.SQLException;
import java.util.Scanner;
import net.sf.jasperreports.engine.*;

public class BMICalculator {

    private static int genderId;
    private static int age;
    private static double height;
    private static double mass;
    private static double indexBMI;
    private static double minBMI;
    private static double maxBMI;
    private static String toPDF;
    private static String firstName;
    private static String lastName;

    public BMICalculator(Double minBMI, Double maxBMI){
        this.minBMI = minBMI;
        this.maxBMI = maxBMI;
    }

    public static void startCalculate() throws SQLException, JRException {

        BMICalculator.inputParameters();
        BMICalculator.calculateData();
        DBProcessor.getMinMaxBMI(genderId,age);
        Functions.clearConsole(30);

        //Answer
        if (age > 80 || age<=0){
            System.out.println("The test works only for people 1 - 80 years old!");
        }else if (indexBMI > minBMI && indexBMI < maxBMI){
            System.out.println("Minimal body mass index = " + minBMI);
            System.out.println("Your body mass index !! = " + indexBMI);
            System.out.println("Maximal body mass index = " + maxBMI);
            Functions.clearConsole(2);
            BMICalculator.accessToPrintPDF();
        }else if (indexBMI < minBMI){
            System.out.println("Your body mass index !! = " + indexBMI);
            System.out.println("Minimal body mass index = " + minBMI);
            System.out.println("Maximal body mass index = " + maxBMI);
            Functions.clearConsole(2);
            BMICalculator.accessToPrintPDF();
        }else if (indexBMI > maxBMI){
            System.out.println("Minimal body mass index = " + minBMI);
            System.out.println("Maximal body mass index = " + maxBMI);
            System.out.println("Your body mass index !! = " + indexBMI);
            Functions.clearConsole(2);
            BMICalculator.accessToPrintPDF();
        }else {
            System.out.println("Do you want to test me? ;)");
        }
    }

    private static void calculateData(){
        height = height > 5 ? height/100 : height;
        indexBMI = mass/Math.pow(height,2);
        indexBMI = Math.round(indexBMI*1000);
        indexBMI = indexBMI/1000;

    }

    private static void inputParameters(){
        Scanner input = new Scanner(System.in);
        System.out.println("You are a man or a woman (M/W)?");
        String gender = input.next();
        genderId = (gender.compareToIgnoreCase("m")==0 || gender.compareToIgnoreCase("man")==0) ? 1 : 0;
        System.out.print("Please input your age: ");
        age = input.nextInt();
        System.out.print("Please input your height in centimeter or meter: ");
        height = input.nextDouble();
        System.out.print("Please input your mass in kilograms: ");
        mass = input.nextDouble();
    }

    private static void accessToPrintPDF() throws JRException{
        Scanner inputParamForPDF = new Scanner(System.in);
        System.out.println("Do you want to print the answer (Y/N)?");
        toPDF = inputParamForPDF.next();
        if(toPDF.compareToIgnoreCase("y")==0 || toPDF.compareToIgnoreCase("yes")==0){
            System.out.println("Please input your first name: ");
            firstName = inputParamForPDF.next();
            System.out.println("Please input your last name: ");
            lastName = inputParamForPDF.next();
            PDFGenerator generator = new PDFGenerator(firstName, lastName, age, genderId, mass, height, indexBMI, minBMI, maxBMI);
            generator.createPDF();
        }
    }
}
