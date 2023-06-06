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
public class InsertDataFromFileApp {
    public void insertData() {
        int idd = 6;
        String title = "Sabaton";
        String name = "War to end all wars";
        String genre = "heavy metal";
        int year = 2020;
        String lan = "English";
        String hol = "-";
        
        // make a connection to DB
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/lab", "app", "app")) {
            Statement statement = con.createStatement();
            statement.executeUpdate("INSERT INTO MusicLibrary VALUES "
                    +"(" +idd +", '" + title + "','" + name + "', '" + genre + "'," + year+ ", '" + lan+ "','" + hol + "','"+ hol+ "')");
            //statement.executeUpdate("INSERT INTO MusicLibrary VALUES (2, 'Kowalski', 'Wojciech', 3.0)");
            //statement.executeUpdate("INSERT INTO MusicLibrary VALUES (3, 'Mickiewicz', 'Adam', 4.5)");
            //statement.executeUpdate("INSERT INTO MusicLibrary VALUES (4, 'Kotek', 'Ludwik', 5.0)");
            //System.out.println("Data inserted");
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

    public static void main(String[] args) {
        InsertDataFromFileApp insertDataApp = new InsertDataFromFileApp();
        insertDataApp.insertData();
    }
}
