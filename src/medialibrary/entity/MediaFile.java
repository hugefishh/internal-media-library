package medialibrary.entity;

import java.io.File;
import java.io.Serializable;

/**
 * Created by Hugefish on 13.12.2015.
 */
public class MediaFile implements Serializable{
    private static final long serialVersionUID = 40L;
    private File file;
    private int year;
    private long size;
    private String name;

    public MediaFile(int year, long size, String name, String file) {
        this.year = year;
        this.size = size;
        this.name = name;
        this.file = new File(file);
    }

    public MediaFile(){
        this.year = 0;
        this.size = 0;
        this.name = null;
        this.file = null;
    }

    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public long getSize() {
        return size;
    }
    public void setSize(long size) {
        this.size = size;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
