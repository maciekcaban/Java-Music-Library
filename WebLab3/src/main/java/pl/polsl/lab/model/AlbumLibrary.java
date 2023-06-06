package pl.polsl.lab.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import customException.UnableToReadFileException;
import java.util.Objects;


/**
 * AlbumLibrary
 * 
 * @version 1.5
 *
 * 01.11.2022 
 * 
 * @author Maciej Caban
 */

/**
 * model class
*contains data about albums in library 
*contains function to manipulate them
*/
public class AlbumLibrary {     
    //collect albums in  list, to easy maeage
    private List<Album> AlbumList = new ArrayList<>();
    
    /**
    * create album with them and return new album 
    * @param s array of album parameter 
    */
    private void AddAlbumToList(String[] s){
       Album album = new Album(s);
       this.AlbumList.add(album );
    }
    
    public AlbumLibrary(){
    }
    
    /**
     * checking if every parameter if album is correct
     * if its true add new album to list
     * @param s
     * @return true if it went correctly
     */
    public boolean checkAndAddAlbum(String[] s) {
        if(s == null){
            return false;
        }
        
        if(s.length != 8){
            return false;
        }
        
        try {
            int d = Integer.parseInt(s[0]);
            int e = Integer.parseInt(s[4]);
            if(d<=0) {
                return false;
            }
            
            Calendar calendar = new GregorianCalendar(Locale.ITALY);
            
            if(e>calendar.get(Calendar.YEAR))
            {
                return false;
                
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
        
        for(int i=1;i<4;i++){
            if(s[i].length()<2){
                return false;
            }
        }
          
        for(int i=5;i<8;i++){
            if(s[i].length()<1){
                return false;
            }
        }
        AddAlbumToList(s);
        return true;
    }

    
    
    
    /**
     * constuct list and load albums to it from file
     * @param path location of file with saved albums
     * @throws customException.UnableToReadFileException inform user about problem with file - program cant read it 
     * @throws FileNotFoundException 
     */
    
    
    public AlbumLibrary(String path) throws UnableToReadFileException, FileNotFoundException  {
        //path = "C:/Users/nobodyL/Desktop/Library17.txt";
        File file = new File(path);

        if(file.canRead()!=true){
            throw new UnableToReadFileException("Unable to read file with library");
        }                          
        
        Scanner s = new Scanner(new FileReader(path));
                while (s.hasNext()) {     
                    String s2 =s.nextLine();                  //temp string, reading one line from file

                    String sInParts[] = s2.split(";");   //split line into array of params
                    if(checkAndAddAlbum(sInParts))                //force to check if params are correct
                    {
                    }
            }
          
    
        AlbumList.sort((Album a1, Album a2) -> a1.getAlbumAuthor().compareTo(a2.getAlbumAuthor()));
    }
 
    
    /**
    *gave AlbumList in to file
    *use convertAlbumToSaveForm (from class Album) func to prepare save form for albums
     * @param path  location where albums will be saved
    */
    public void SaveAlbumsToFile(String path){
        
        File fout = new File(path);
       
        try (FileWriter fileWriter = new FileWriter(fout);
         BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){
            for (Album str : AlbumList) {                
                bufferedWriter.write(str.convertAlbumToSaveForm(str));
                bufferedWriter.newLine();
            }
            } catch (FileNotFoundException e) {
                System.out.println("Unable to open file, file not found.");
            } catch (IOException e) {
                System.out.println("Unable to write to file." + fout.getName());
            }
    }
    
    public void loadLibraryFromFile(String path) throws UnableToReadFileException, FileNotFoundException{
         File file = new File(path);

        if(file.canRead()!=true){
            throw new UnableToReadFileException("Unable to read file with library");
        }                          
        
        Scanner s = new Scanner(new FileReader(path));
                while (s.hasNext()) {     
                    String s2 =s.nextLine();                  //temp string, reading one line from file

                    String sInParts[] = s2.split(";");   //split line into array of params
                    if(checkAndAddAlbum(sInParts))                //force to check if params are correct
                    {
                    }
            }
          
    
        AlbumList.sort((Album a1, Album a2) -> a1.getAlbumAuthor().compareTo(a2.getAlbumAuthor()));
        
    }
    /**
     * return album list
     * @return album list
     */
    public List<Album> getAlbumList() {
        return AlbumList;
    }
    
    /**
     * get param - string with param  which is the filtered criterion
    * get paramPos - its value determine on which param func will filter
    * paramPos values means: 1-albumName,   2-creatorName,  3-musicGenre,   4-releaseDate,  5-language,   6-comments,   other value - currentHolder
    * @param paramPos feature by which program filter
    * @param param value of this feature
    * 
    * @return new list of albums 
    */
     public List<Album> getAlbumListWithFilter(String param, String paramPos) {
        List<Album> AlbumListWithFilter = new ArrayList<>();
        
        for (Album AlbumList1 : AlbumList) {
            switch(paramPos) {
                    case "1":          //filter by albumName
                        {
                             if(param.equals(AlbumList1.getAlbumName()));{
                                AlbumListWithFilter.add(AlbumList1);
                                
                             }
                        }
                      break;
                    case "2":          //filter by creatornName  
                    {
                            
                             if(param.equals(AlbumList1.getAlbumAuthor())){
                                AlbumListWithFilter.add(AlbumList1);
                                
                             }
                    }  
                      break;
                      case "3":        //filter by music genre
                        {
                             if(param.equals(AlbumList1.getMusicGenre())){
                                AlbumListWithFilter.add(AlbumList1);
                             }
                        }
                      break;
                    case "4":          //filter by release year
                        {
                            String p = String.valueOf(AlbumList1.getReleaseYear());
                             if(param.equals(p)){
                                AlbumListWithFilter.add(AlbumList1);
                             }
                        }
                      break;
                      case "5":        //filter by    language
                        {
                             if(param.equals(AlbumList1.getLanguage())){
                                AlbumListWithFilter.add(AlbumList1);
                             }
                        }
                      break;
                    case "6":          //filter by other comment
                      {
                             if(param.equals(AlbumList1.getOtherComments())){
                                AlbumListWithFilter.add(AlbumList1);
                             }
                        }
                      break;
                    default:         //filter by curent holder
                      {
                             if(param.equals(AlbumList1.getCurrentHolder())){
                                AlbumListWithFilter.add(AlbumList1);
                             }
                        }
                  }
        }

        return AlbumListWithFilter;
    }
    
    /**
     * return id of last album in list
     * @return id of last album in list
     */
    public int getLastAlbumId()
    {
      if(AlbumList.isEmpty()){
          //System.out.print(AlbumList.size());
          return 0;
      }
        
      int i = AlbumList.get(AlbumList.size()-1).getAlbumId();  
      return i;
    }
    
    /**
     * removing album from list with given id and return true if removing gone correct
     * @param id id of album which will be removed
    *@return true if album romoving went correct or false if there is no album with this id 
    */
    public boolean removeAlbum(int id){
        for(int i=0;i<AlbumList.size();i++){
            if(AlbumList.get(i).getAlbumId()==id){
                AlbumList.remove(i);
                return true;
            }
        }
        return false;
    }
    
    /**
     * creat map with albums grouped by authors
     * @return map with albums grouped by authors
     */
    public Map<String, List<Album>> getAlbumsGrupedByAuthors (){
        Map<String, List<Album>> albumsByAutors = AlbumList.stream().
                collect(Collectors.groupingBy (c-> c.getAlbumAuthor()));
        
        
        return albumsByAutors;
    } 
    
    /**
     * function use to compare AlbumLibrary with another object
     * @param obj
     * @return true if second object is AlbumLibrary class and its equal to first
     */
 @Override
     public boolean equals(Object obj) {
         if (this == obj)
             return true;
         if (obj == null)
             return false;
         
         if (getClass() != obj.getClass())
             return false;
         AlbumLibrary other = (AlbumLibrary) obj;
         if(!this.getAlbumList().equals(other.getAlbumList())){
         return false;
         }
        return true;
     }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.AlbumList);
        return hash;
    }

}

