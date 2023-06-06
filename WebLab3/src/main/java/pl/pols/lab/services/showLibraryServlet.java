/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
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
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.polsl.lab.model.Album;
import pl.polsl.lab.model.AlbumLibrary;
 
/**
 *
 * @author nobodyL
 */
@WebServlet("/showLibrary")
public class showLibraryServlet extends HttpServlet {

    
    private AlbumLibrary al;
    /*
    @Override
    public void init() {
        try {
            al = new AlbumLibrary("C:/Users/nobodyL/Desktop/Library.txt");
            //  super.init();
        } catch (UnableToReadFileException ex) {
            Logger.getLogger(showLibraryServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(showLibraryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    @Override
    public void init() {
        al = new AlbumLibrary();
        final String param2 = "1;heroes;sabaton;heavy metal;2014;English;-;-";
        final String param3 = "4;blessed and possesed;powerwolf;power metal;2015;English;-;-";
        final String param5 = "7;war to end all wars;sabaton;heavy metal;2022;English;-;-";
        String[] p2 = param2.split(";");
        String[] p3 = param3.split(";");
        String[] p5 = param5.split(";");
        al.checkAndAddAlbum(p2);
        al.checkAndAddAlbum(p3);
        al.checkAndAddAlbum(p5);
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       //response.setContentType("text/html;charset=UTF-8");
        
        
        PrintWriter out = response.getWriter();        
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
        processRequest(request, response);
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
        processRequest(request, response);
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
