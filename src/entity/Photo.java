package entity;

import java.io.File;

public class Photo {
    /**
     * Required: the File is a .gif, .jpeg., .jpg, or .png.
     * @param image
     */
    private File image;
    public Photo(File image){
        this.image = image;
    }
    public File getImage(){
        return this.image;
    }
    public void setImage(File image){this.image = image;}

}
