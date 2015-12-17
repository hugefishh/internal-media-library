package medialibrary.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import medialibrary.entity.FilmFile;
import medialibrary.logic.MediaFilesManager;

import java.io.File;
import java.io.IOException;

public class Controller {
    private final static String FILE_PATH = "collection";

    @FXML
    private TableView<FilmFile>  movieTableView;
    @FXML
    private TableColumn<FilmFile, String> movieNameTableColoumn;
    @FXML
    private TableColumn<FilmFile, String> movieSizeTableColoumn;
    @FXML
    private TableColumn<FilmFile, String> movieVideoQualityTableColoumn;
    @FXML
    private TableColumn<FilmFile, Integer> movieYearTableColoumn;
    @FXML
    private TableColumn<FilmFile, String> movieAudioQualityTableColoumn;
    @FXML
    private TableColumn<FilmFile, String> movieResolutionTableColoumn;
    @FXML
    private TableColumn<FilmFile, String> moviePathTableColoumn;
    @FXML
    private TextField movieFilepathTextField;
    @FXML
    private TextField movieNameTextField;
    @FXML
    private TextField movieYearTextField;
    @FXML
    private TextField movieWightTextField;
    @FXML
    private TextField movieHeightTextField;

    @FXML
    public void initialize(){
        movieNameTableColoumn.setCellValueFactory(new PropertyValueFactory<FilmFile, String>("name"));
        movieYearTableColoumn.setCellValueFactory(new PropertyValueFactory<FilmFile, Integer>("year"));
        movieSizeTableColoumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FilmFile, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<FilmFile, String> p) {
                return new SimpleStringProperty(p.getValue().getFile().length()/1024/1024 + "MB");
            }
        });
        movieResolutionTableColoumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FilmFile, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<FilmFile, String> p) {
                FilmFile film = p.getValue();
                return new SimpleStringProperty(film.getVideoHeighSize() + "x" + film.getVideoWightSize());
            }
        });
        movieVideoQualityTableColoumn.setCellValueFactory(new PropertyValueFactory<FilmFile, String>("quality"));
        movieAudioQualityTableColoumn.setCellValueFactory(new PropertyValueFactory<FilmFile, String>("audioType"));
        moviePathTableColoumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<FilmFile, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<FilmFile, String> p) {
                FilmFile film = p.getValue();
                String path = "";
                try {
                    path = film.getFile().getCanonicalPath();
                } catch (IOException e) {
                    //Logging log4j
                    System.out.println("Error getting filepath");
                }
                return new SimpleStringProperty(path);
            }
        });

        movieTableView.setItems(MediaFilesManager.getInstance().getFilmFilesList());
    }

    @FXML
    public void addFilePressed(ActionEvent event){
        FilmFile film = new FilmFile();
        MediaFilesManager mediaFilesManager = MediaFilesManager.getInstance();

        try {
            String filepath = movieFilepathTextField.getText();
            film.setFile(new File(filepath));
            film.setName(movieNameTextField.getText());

            String text = movieWightTextField.getText();
            System.out.println(text);
            System.out.println(Integer.valueOf(text));
            film.setVideoWightSize(Integer.valueOf(movieWightTextField.getText()));
            film.setVideoHeighSize(Integer.valueOf(movieHeightTextField.getText()));
            film.setYear(Integer.valueOf(movieYearTextField.getText()));

            mediaFilesManager.getFilmFilesList().add(film);
            mediaFilesManager.saveToFile(FILE_PATH);
        } catch (NumberFormatException e) {
            //Error parsing window
            System.out.println("Error parsing window call");
        } catch (IOException e) {
            //Logger log4j
            System.out.println("Error saving to file: " + FILE_PATH);
        }
        movieTableView.setItems(mediaFilesManager.getFilmFilesList());
    }

    @FXML
    public void onMovieBrowsePressed(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose video files");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Video Files", "*.avi", "*.mp4", "*.mkv"),
                new ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showOpenDialog(((Node)event.getTarget()).getScene().getWindow());
        if (selectedFile != null) {
            movieFilepathTextField.setText(selectedFile.getAbsolutePath());
        }
    }

}
