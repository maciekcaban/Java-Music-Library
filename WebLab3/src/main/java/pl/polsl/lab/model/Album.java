package pl.polsl.lab.model;

import java.util.Objects;

/**
 * Album
 * 
 * @version 1.3
 *
 * 17.10.2022 
 * 
 * @author Maciej Caban
 */

/**
 * represents music albums, desribed by 7 params and id
*id is used to recognize one album if there are few copies of one album
*/
public class Album {
    private int akbumId;
    private String albumName;
    private String albumAuthor;
    private String musicGenre;
    private int releaseYear;
    private String language;    
    private String otherComments;
    private String currentHolder;

    /**
     * 
     * @return id of indicated album 
     */
    public int getAlbumId() {
        return akbumId;
    }
    
    
    
    public Album(String[] s){
        int i = Integer.parseInt(s[0]);
       setAkbumId(i);
       setAlbumName(s[1]);
       setAuthorName(s[2]);
       setMusicGenre(s[3]);
       i = Integer.parseInt(s[4]);
       setReleaseYear(i);
       setLanguage(s[5]);
       setOtherComments(s[6]);
       setCurrentHolder(s[7]);
    }

    /**
     * 
     * @return name of indicated album
     */
    public String getAlbumName() {
        return albumName;
    }
    /**
     * 
     * @return author name of indicated album
     */
    public String getAlbumAuthor() {
        return albumAuthor;
    }

    /**
     * 
     * @return music genre of indicated album
     */
    public String getMusicGenre() {
        return musicGenre;
    }

    /**
     * 
     * @return release year of indicated album
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * 
     * @return language of indicated album
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 
     * @return comments to indicated album
     */
    public String getOtherComments() {
        return otherComments;
    }

    /**
     * 
     * @return holder of indicated album
     */
    public String getCurrentHolder() {
        return currentHolder;
    }

    /**
     * 
     * @param akbumId new id for indicated album
     */
    public void setAkbumId(int akbumId) {
        this.akbumId = akbumId;
    }

    /**
     * 
     * @param albumName new name for indicated album
     */
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    /**
     * 
     * @param authorName new author name for indicated album
     */
    public void setAuthorName(String authorName) {
        this.albumAuthor = authorName;
    }

    /**
     * 
     * @param musicGenre new music genre for indicated album
     */
    public void setMusicGenre(String musicGenre) {
        this.musicGenre = musicGenre;
    }

    /**
     * 
     * @param releaseYear new release year for indicated album
     */
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * 
     * @param language new language for indicated album
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 
     * @param otherComments new comments for indicated albumb
     */
    public void setOtherComments(String otherComments) {
        this.otherComments = otherComments;
    }

    /**
     * 
     * @param currentHolder new holder for indicated album
     */
    public void setCurrentHolder(String currentHolder) {
        this.currentHolder = currentHolder;
    }
    
    /**
     *convert album into form: param1;param2;param3;param4;param5;param6;param7;param8 as string
    * this form is used in file which collect albums 
     * @param a album to cenvert 
    * @return album as string
    */
    public String convertAlbumToSaveForm(Album a)
    {
        String finalForm = new String();
        finalForm +=  String.valueOf(a.getAlbumId());
        finalForm =finalForm + ";"+ a.getAlbumName();
        finalForm =finalForm + ";"+ a.getAlbumAuthor();
        finalForm =finalForm + ";"+ a.getMusicGenre();
        finalForm =finalForm + ";"+ String.valueOf(a.getReleaseYear());
        finalForm =finalForm + ";"+ a.getLanguage();
        finalForm =finalForm + ";"+ a.getOtherComments();
        finalForm =finalForm + ";"+ a.getCurrentHolder();
        
        return finalForm;
    }
 
    /**
     * functions use to make easier showing Album 
     * return AlbumName
     * @return AlbumName
     */
    @Override
    public String toString(){
        return getAlbumName();
    }
    
    
    /**
     * function use to compare Album with another object
     * @param obj
     * @return true if second object is Album class and its equal to first
     */
    @Override
     public boolean equals(Object obj) {
         if (this == obj)
             return true;
         if (obj == null)
             return false;
         
         if (getClass() != obj.getClass())
             return false;
         Album other = (Album) obj;
         if(this.getAlbumId()!=other.getAlbumId()){
             return false;
         }
         if(!this.getAlbumName().equals(other.getAlbumName())){
             return false;
         }
         if(!this.getAlbumAuthor().equals(other.getAlbumAuthor())){
             return false;
         }
         if(!this.getCurrentHolder().equals(other.getCurrentHolder())){
             return false;
         }
         if(!this.getLanguage().equals(other.getLanguage())){
             return false;
         }
         if(!this.getMusicGenre().equals(other.getMusicGenre())){
             return false;
         }
         if(!this.getOtherComments().equals(other.getOtherComments())){
             return false;
         }
         if(!this.getMusicGenre().equals(other.getMusicGenre())){
             return false;
         }
         if(this.getReleaseYear()!=other.getReleaseYear()){
             return false;
         }
         
         return true;
     }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.akbumId;
        hash = 83 * hash + Objects.hashCode(this.albumName);
        hash = 83 * hash + Objects.hashCode(this.albumAuthor);
        hash = 83 * hash + Objects.hashCode(this.musicGenre);
        hash = 83 * hash + this.releaseYear;
        hash = 83 * hash + Objects.hashCode(this.language);
        hash = 83 * hash + Objects.hashCode(this.otherComments);
        hash = 83 * hash + Objects.hashCode(this.currentHolder);
        return hash;
    }
}
