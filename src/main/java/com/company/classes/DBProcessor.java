package com.company.classes;

import java.sql.*;

public class DBProcessor {

    private Connection connection;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Gaugamela331";
    private static final String URL = "jdbc:mysql://localhost:3306/bmic?useSSL=false&serverTimezone=UTC";

    public DBProcessor() throws SQLException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
    }

    public Connection getConnection(String url, String username, String password) throws SQLException {
        if (connection != null) {
            return connection;
        } else {
            connection = DriverManager.getConnection(url, username, password);
            return connection;
        }
    }

    public static void getMinMaxBMI(int gender, int age) throws SQLException {
        DBProcessor db = new DBProcessor();
        Connection conn = db.getConnection(URL, USERNAME, PASSWORD);
        String query = "SELECT minBMI,maxBMI FROM person_char WHERE gender=" + gender + " AND age=" + age;
        Statement statement = conn.createStatement();
        ResultSet resSet = statement.executeQuery(query);

        while (resSet.next()) {
            double minBMI;
            double maxBMI;
            minBMI = resSet.getDouble("minBMI");
            maxBMI = resSet.getDouble("maxBMI");
            BMICalculator minmaxbmi = new BMICalculator(minBMI, maxBMI);
        }

        statement.close();
        conn.close();
    }


}