/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab.dataBase;


import java.sql.*;
import pl.polsl.lab.model.AlbumLibrary;
import pl.polsl.lab.model.Album;

/**
 *
 * @author nobodyL
 */
public class CreateTableApp {
    public void createTables() {

        System.out.print("xDDDDppppppppp");
        // make a connection to DB
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/lab", "app", "app")) {
            Statement statement = con.createStatement();
            statement.executeUpdate("CREATE TABLE MusicLibrary "
                    + "(id INTEGER, Album_Title VARCHAR(50), "
                    + "Album_Author VARCHAR(50), Genre VARCHAR(50), "
                    + "Release_Year INTEGER, Language VARCHAR(50), "
                    + "comm VARCHAR(50), holder VARCHAR(50) )");
            System.out.println("Table created");
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

    public static void main(String[] args) {
        CreateTableApp createTablesApp = new CreateTableApp();
        createTablesApp.createTables();
    }
}
