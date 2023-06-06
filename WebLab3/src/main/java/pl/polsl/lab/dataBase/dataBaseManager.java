/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.lab.dataBase;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.String;
import java.sql.*;
import pl.polsl.lab.model.Album;
import pl.polsl.lab.model.AlbumLibrary;

/**
 *
 * @author nobodyL
 */
public class dataBaseManager {
    
    
    public AlbumLibrary loadDataFromDataBase () throws SQLException{
        AlbumLibrary al = new AlbumLibrary();
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/lab", "app", "app")) { 
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM MusicLibrary");

            while (rs.next()) {
                String[] temp = {String.valueOf(rs.getInt("id")), rs.getString("Album_Title"),
                rs.getString("Album_Author"), rs.getString("Genre"),
                String.valueOf(rs.getInt("Release_Year")), rs.getString("Language"),
                rs.getString("comm"), rs.getString("holder")
                };
                al.checkAndAddAlbum(temp);
            }
             rs.close();
        }catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
     
      return al;
    }
    
    public void insertData(String[] data, HttpServletResponse response) throws IOException, SQLException {
        
        // make a connection to DB
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/lab", "app", "app")) {
            Statement statement = con.createStatement();
            statement.executeUpdate("INSERT INTO MusicLibrary VALUES "
                    +"(" +data[0] +", '" + data[1] + "','" + data[2] + "', '" + data[3] + "'," + data[4]+ ", '" + data[5]+ "','" + data[6] + "','"+data[7]+ "')");

            //System.out.println("Data inserted");
        } catch (SQLException sqle) {
            //System.err.println(sqle.getMessage());
            PrintWriter out = response.getWriter(); 
            out.println("enter correct values");
        }
    }
    
    public void eraseData(int data, HttpServletResponse response) throws IOException, SQLException{
        
        // make a connection to DB
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/lab", "app", "app")) {
            Statement statement = con.createStatement();
            statement.executeUpdate("DELETE FROM MusicLibrary WHERE id =" + data);
                    
            
            //System.out.println("Data inserted");
        } catch (SQLException sqle) {
            PrintWriter out = response.getWriter(); 
            out.println("it is not an id");
        }
        
       
    }
    
    public void showFromDataBase(HttpServletResponse response)throws IOException, SQLException{
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/lab", "app", "app")) {
            Statement statement = con.createStatement();  
            ResultSet rs = statement.executeQuery("SELECT * FROM MusicLibrary");
      
        PrintWriter out = response.getWriter();
       
                while (rs.next()) {
                out.println("<tr>");
                out.println("<td>");
                out.println(rs.getInt("id"));
                out.println("</td>");
                out.println("<td>");
                out.println(rs.getString("Album_Title"));
                out.println("</td>");            
                out.println("<td>");
                out.println(rs.getString("Album_Author"));
                out.println("</td>");            
                out.println("<td>"); 
                out.println(rs.getString("Genre"));
                out.println("</td>");
                out.println("<td>");
                out.println(rs.getInt("Release_Year"));
                out.println("</td>");
                out.println("<td>");
                out.println(rs.getString("Language"));
                out.println("</td>");
                out.println("<td>");
                out.println(rs.getString("comm"));
                out.println("</td>");
                out.println("<td>");
                out.println(rs.getString("holder"));
                out.println("</td>");
                out.println("</tr>");
                }
            rs.close();
            //out.print("hahahahahahahaha");
            
        }catch (SQLException sqle) {
            //System.err.println(sqle.getMessage());
            PrintWriter out = response.getWriter(); 
            out.println(sqle.getMessage());
        }
    }
    
    public void showFilteredDataBase(String col, String param, HttpServletResponse response)throws IOException, SQLException{
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/lab", "app", "app")) {
            Statement statement = con.createStatement();  
            ResultSet rs = statement.executeQuery("SELECT * FROM MusicLibrary WHERE "+col+" = '"+param+"'");
      
        PrintWriter out = response.getWriter();
       
                while (rs.next()) {
                out.println("<tr>");
                out.println("<td>");
                out.println(rs.getInt("id"));
                out.println("</td>");
                out.println("<td>");
                out.println(rs.getString("Album_Title"));
                out.println("</td>");            
                out.println("<td>");
                out.println(rs.getString("Album_Author"));
                out.println("</td>");            
                out.println("<td>"); 
                out.println(rs.getString("Genre"));
                out.println("</td>");
                out.println("<td>");
                out.println(rs.getInt("Release_Year"));
                out.println("</td>");
                out.println("<td>");
                out.println(rs.getString("Language"));
                out.println("</td>");
                out.println("<td>");
                out.println(rs.getString("comm"));
                out.println("</td>");
                out.println("<td>");
                out.println(rs.getString("holder"));
                out.println("</td>");
                out.println("</tr>");
                }
            rs.close();
            //out.print("hahahahahahahaha");
            
        }catch (SQLException sqle) {
            //System.err.println(sqle.getMessage());
            PrintWriter out = response.getWriter(); 
            out.println(sqle.getMessage());
        }
    }
    
        public static void main(String[] args) throws SQLException {
        dataBaseManager dbm = new dataBaseManager();
        AlbumLibrary al = new AlbumLibrary();
        
        al = dbm.loadDataFromDataBase();    
        System.out.print(al.getAlbumList().size());
        //dbm.eraseData(16);
        System.out.print(al.getAlbumList().size());

        }
}
    