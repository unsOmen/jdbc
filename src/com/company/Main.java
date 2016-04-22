package com.company;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.*;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== TEST JDBC ===");
        Connection con = null;
        String url = "jdbc:oracle:thin:@localhost:1521:xe";

        String user_name = "system";
        String user_pass = "123";

        try {
            Locale.setDefault(Locale.ENGLISH); // без этого может быть ошибка  при подключении
            DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
            con = DriverManager.getConnection(url, user_name, user_pass);
            System.out.println("Соединение установлено");

            //Для использования SQL запросов существуют 3 типа объектов:
            //1.Statement: используется для простых случаев без параметров
            Statement statement = null;
            statement = con.createStatement();
            //Выполним запрос
            ResultSet result1 = statement.executeQuery(
                    "SELECT * FROM HR.EMPLOYEES");
            //result это указатель на первую строку с выборки
            //чтобы вывести данные мы будем использовать
            //метод next() , с помощью которого переходим к следующему элементу
            System.out.println("Выводим statement");
            while (result1.next()) {
                System.out.println(result1.getRow() + "\t" + result1.getInt("EMPLOYEE_ID")
                        + "\t" + result1.getString("FIRST_NAME") + "\t" + result1.getString("LAST_NAME"));
            }
        } catch (Exception ex) {
            //выводим наиболее значимые сообщения
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
