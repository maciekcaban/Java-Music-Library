/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.pols.lab.services;

import customException.UnableToReadFileException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.polsl.lab.dataBase.dataBaseManager;
//import pl.polsl.lab.model.Person;
//import pl.polsl.lab.model.Persons;
import pl.polsl.lab.model.Album;
import pl.polsl.lab.model.AlbumLibrary;

/** 
 * Controls web version of music library
 * data 29.11.2022
 * @version 1.1
 * @author Maciej Caban
 */
@WebServlet("/persons")
public class PersonsServlet extends HttpServlet {

    //private Persons persons;
    private AlbumLibrary al;
    private dataBaseManager dBM;
    //private Connection con;
     /**
     * initotialize album library
     */
    @Override
    public void init() {
        /*
        try {
            al = new AlbumLibrary("C:/Users/nobodyL/Desktop/Library.txt");
            //  super.init();
        } catch (UnableToReadFileException | FileNotFoundException ex) {
            Logger.getLogger(showLibraryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
         
        dBM = new dataBaseManager();
        //con = new Connection();
        
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/lab", "app", "app")) {
            Statement statement = con.createStatement();
            statement.executeUpdate("CREATE TABLE MusicLibrary "
                    + "(id INTEGER, Album_Title VARCHAR(50), "
                    + "Album_Author VARCHAR(50), Genre VARCHAR(50), "
                    + "Release_Year INTEGER, Language VARCHAR(50), "
                    + "comm VARCHAR(50), holder VARCHAR(50) )");
            System.out.println("Table created");
            
          
            // al = dBM.loadDataFromDataBase();

        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
        
        try {
            al = dBM.loadDataFromDataBase();
        } catch (SQLException ex) {
            Logger.getLogger(PersonsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     

   
    /**
     * show library (whem there is no params to filter)
     * show filter library if there are params to filter 
     * @param request servlet request (params to filter)
     * @param response servlet response (used to show library)
     * @throws IOException if an I/O error occurs
     */
    protected void showFiltered(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
        String firstName = request.getParameter("firstName");       
        String secondName = request.getParameter("secondName");
        boolean showAll = firstName == null || firstName.length() == 0 || secondName == null || secondName.length() == 0;
        
        
        PrintWriter out = response.getWriter();        
        if(showAll==true){
            dBM.showFromDataBase(response);
        }else{
            dBM.showFilteredDataBase(firstName, secondName, response);
        }
        
        
        /*
        //out.println(al.getAlbumList().size());
        //for(Person person : persons.getData()){
        if(showAll)
        {
            for(Album album : al.getAlbumList()){
                out.println("<tr>");
                out.println("<td>");
                out.println(album.getAlbumId());
                out.println("</td>");
                out.println("<td>");
                out.println(album.getAlbumName());
                out.println("</td>");            
                out.println("<td>");
                out.println(album.getAlbumAuthor());
                out.println("</td>");            
                out.println("<td>"); 
                out.println(album.getMusicGenre());
                out.println("</td>");
                out.println("<td>");
                out.println(album.getReleaseYear());
                out.println("</td>");            
                out.println("<td>");
                out.println(album.getLanguage());
                out.println("</td>");
                out.println("<td>");
                out.println(album.getOtherComments());
                out.println("</td>");            
                out.println("<td>");
                out.println(album.getCurrentHolder());
                out.println("</td>");
                out.println("</tr>");
            }
        } else{
            for(Album album : al.getAlbumListWithFilter(secondName, firstName)){
                out.println("<tr>");
                out.println("<td>");
                out.println(album.getAlbumId());
                out.println("</td>");
                out.println("<td>");
                out.println(album.getAlbumName());
                out.println("</td>");            
                out.println("<td>");
                out.println(album.getAlbumAuthor());
                out.println("</td>");            
                out.println("<td>"); 
                out.println(album.getMusicGenre());
                out.println("</td>");
                out.println("<td>");
                out.println(album.getReleaseYear());
                out.println("</td>");            
                out.println("<td>");
                out.println(album.getLanguage());
                out.println("</td>");
                out.println("<td>");
                out.println(album.getOtherComments());
                out.println("</td>");            
                out.println("<td>");
                out.println(album.getCurrentHolder());
                out.println("</td>");
                out.println("</tr>");
            }
        }*/
    }
    
    /**
     * get params, create album and add it to library
    * @param request servlet request (params of album)
    * @param response servlet response (information how operation went)
    * @throws IOException if an I/O error occurs 
    */
    protected void addAlbum(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{  
        String ar2 = request.getParameter("addName");       
        String ar3 = request.getParameter("addAuthor");
        String ar4 = request.getParameter("addGenre");       
        String ar5 = request.getParameter("addYear");
        String ar6 = request.getParameter("addLan");       
        String ar7 = request.getParameter("addComm");
        String ar8 = request.getParameter("addHolder");  
        //System.out.print(ar8);
        
        
        int id = al.getLastAlbumId();
        id++;
        String allArgs = Integer.toString(id);
        allArgs += ";";
        allArgs += ar2;
        allArgs += ";";
        allArgs += ar3;
        allArgs += ";";
        allArgs += ar4;
        allArgs += ";";
        allArgs += ar5;
        allArgs += ";";
        allArgs += ar6;
        allArgs += ";";
        allArgs += ar7;
        allArgs += ";";
        allArgs += ar8;
        
        String[] allArgsInParts = allArgs.split(";");
        PrintWriter out = response.getWriter(); 
        
        dBM.insertData(allArgsInParts, response);
        
        if(al.checkAndAddAlbum(allArgsInParts)){
            out.print("album was added");
            showFiltered(request, response);
        }else{
            //response.sendError(response.SC_BAD_REQUEST, "You should give two parameters!");
            //out.print("enter correct values");
        }
        
        
    }
    
    
    /**
     * removing album with specyfic if
     * @param request (id of album)
     * @param response (information abohowut operation went)
     * @throws IOException 
     */
    protected void removeAlbum(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException{
        String arg = request.getParameter("removeId"); 
        PrintWriter out = response.getWriter(); 
        int id;
        
        try{
            id = Integer.parseInt(arg);
        }catch(NumberFormatException e){
            out.print("Enter corret ID");
            //out.print(arg);
            return;
        }
        
        dBM.eraseData(id, response);
        if(al.removeAlbum(id)){
        //out.print("album was removed");
            showFiltered(request, response);
        }else{
            out.print("there is no album with this id");
        }
    }
    
    
    
    /**
     * save album on user PC
     * @param request (path to saving place)
     * @param response (information how operation went)
     * @throws ServletException
     * @throws IOException 
     */
    protected void saveLibrary(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String path = request.getParameter("pathToSave"); 
        PrintWriter out = response.getWriter(); 
    
        //out.print(path);
        if(path != null || path.length()==0){
        al.SaveAlbumsToFile(path);
        }else{
            out.print("enter correct path");
        }
    }    
    
    
    /**
     * load library from user PC
     * @param request (path to library)
     * @param response (information how operation went)
     * @throws ServletException
     * @throws IOException 
     */
    protected void loadLibrary(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String path = request.getParameter("pathToLoad"); 
        PrintWriter out = response.getWriter(); 
        //out.print(path.length());

        try{
           al.loadLibraryFromFile(path);
           out.print("Library is loaded"); 
        }catch (UnableToReadFileException | FileNotFoundException e ){
            out.print("Enter correct path to library(file not found exception)");
            //response.sendError(response.SC_BAD_REQUEST, "Enter path first!!!!");
        }
     
    }
    
     /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request  (params from website)
     * @param response servlet response (used to write back information to website)
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
         String condition = request.getParameter("func");
         
         //PrintWriter out = response.getWriter();        
         //out.println(condition);
        
        switch(condition){
            case "showF":
                    showFiltered(request, response);
                    break;
            case "add":
                    addAlbum(request, response);
                    break;
            case "remove":
                    removeAlbum(request, response);
                    break; 
            case "save":
                    saveLibrary(request, response);
                    break; 
            case "load":
                    loadLibrary(request, response);
                    break; 
        } 
        
    }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PersonsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PersonsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
