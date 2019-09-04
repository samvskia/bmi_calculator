package com.company;

import com.company.classes.BMICalculator;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        try {
            BMICalculator.startCalculate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        }

    }

}
