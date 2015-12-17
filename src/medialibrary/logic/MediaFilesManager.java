package medialibrary.logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import medialibrary.entity.AudioFile;
import medialibrary.entity.FilmFile;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Hugefish on 17.12.2015.
 */
public class MediaFilesManager implements Serializable{
    private final static MediaFilesManager INSTANCE = new MediaFilesManager();
    private static final long serialVersionUID = 42L;

    private ObservableList<FilmFile> filmFilesList;
    private ObservableList<AudioFile> audioFilesList;

    public static MediaFilesManager getInstance(){
        return INSTANCE;
    }

    private MediaFilesManager(){
        filmFilesList = FXCollections.observableArrayList();
        audioFilesList = FXCollections.observableArrayList();
    }

    public ObservableList<FilmFile> getFilmFilesList(){
        return filmFilesList;
    }
    public ObservableList<AudioFile> getAudioFilesList(){
        return audioFilesList;
    }
    public void setFilmFilesList(ObservableList<FilmFile> filmFilesList) {
        this.filmFilesList = filmFilesList;
    }
    public void setAudioFilesList(ObservableList<AudioFile> audioFilesList) {
        this.audioFilesList = audioFilesList;
    }

    public void saveToFile(String filepath) throws IOException {
        FileOutputStream fos = new FileOutputStream(filepath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(new ArrayList<FilmFile>(filmFilesList));
        oos.writeObject(new ArrayList<>(audioFilesList));
        oos.close();
    }
    public void loadFromFile(String filepath) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filepath);
        ObjectInputStream oin = new ObjectInputStream(fis);

        Object obj = oin.readObject();
        if (obj instanceof ArrayList){
            System.out.println("Class casting 1 +");
            ObservableList<FilmFile> list1 = FXCollections.observableList((ArrayList<FilmFile>) obj);
            filmFilesList = list1;
        }
        obj = oin.readObject();
        if (obj instanceof ArrayList){
            System.out.println("Class casting 2 +");
            ObservableList<AudioFile> list = FXCollections.observableList((ArrayList<AudioFile>) obj);
            audioFilesList = list;
        }

        oin.close();
    }
}
