/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package customException;

/**
 *  @version 1.0
 * 
 * 01.11.2022
 * 
 * @author nobodyL
 */


public class UnableToReadFileException extends Exception{
    /**
     * used while checking if program can read file
     * @param errorMessage text which will be send to user
     */
    public UnableToReadFileException(String errorMessage) {  
    super(errorMessage);  
    }  
    
 } 

