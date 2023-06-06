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
public class SelectDataApp {

    public void selectData() {

        // make a connection to DB
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/lab", "app", "app")) {
            Statement statement = con.createStatement();
            //ResultSet rs = statement.executeQuery("SELECT * FROM MusicLibrary");
            ResultSet rs = statement.executeQuery("SELECT * FROM MusicLibrary WHERE Album_Title = 'Sabaton'");
            // Przeglądamy otrzymane wyniki
            System.out.printf("|%-5s|%-30s|%-20s|%-20s|%-5s|%-20s|%-20s|%-20s|\n", "id", "Album_Title", "Album_Author", "Genre","Release_Year","Language","comm", "holder");
            System.out.println("-----------------------------------");
            while (rs.next()) {
                /*
                System.out.printf("|%-5s", rs.getInt("id"));
                System.out.printf("|%-30s", rs.getString("Album_Title"));
                System.out.printf("|%-20s", rs.getString("Album_Author"));
                System.out.printf("|%-20s", rs.getString("Genre"));
                System.out.printf("|%-5s", rs.getInt("Release_Year"));
                System.out.printf("|%-20s", rs.getString("Language"));
                System.out.printf("|%-20s", rs.getString("comm"));
                System.out.printf("|%-20s|\n", rs.getString("holder"));
                */
                System.out.print(rs.getInt("id"));
                System.out.print(";");
                System.out.print(rs.getString("Album_Title"));
                System.out.print(";");
                System.out.print(rs.getString("Album_Author"));
                System.out.print(";");
                System.out.print(rs.getString("Genre"));
                System.out.print(";");
                System.out.print(rs.getInt("Release_Year"));
                System.out.print(";");
                System.out.print(rs.getString("Language"));
                System.out.print(";");
                System.out.print(rs.getString("comm"));
                System.out.print(";");
                System.out.print(rs.getString("holder"));
                System.out.println();
            }
            System.out.println("");
            System.out.println("-----------------------------------");
            rs.close();
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

    public static void main(String[] args) {
        SelectDataApp selectDataApp = new SelectDataApp();
        selectDataApp.selectData();
    }
}
