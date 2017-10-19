/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master.Localization;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author Ricardo
 */
public class LanguageHandler {

    private final String FILE_TYPE = ".properties";

    private Locale[] supportedLanguages;

    public void test() {
        parseDatabase();
    }

    public void parseDatabase() {
        System.out.println("Current Locale: " + Locale.getDefault());
        ResourceBundle languageBundles = ResourceBundle.getBundle("BuildingBlocks.Master.Localization.Translation_File");
        System.out.println(languageBundles.getString("file"));
        Locale.setDefault(new Locale("pt", "PT"));
        languageBundles = ResourceBundle.getBundle("BuildingBlocks.Master.Localization.Translation_File");
        System.out.println(languageBundles.getString("file"));
        Locale.setDefault(new Locale("en", "US"));
        languageBundles = ResourceBundle.getBundle("BuildingBlocks.Master.Localization.Translation_File");
        System.out.println(languageBundles.getString("file"));
        Locale.setDefault(new Locale("de", "CH"));

        


        //List<String> files = getPropertyFiles("\\");
        //supportedLanguages = new Locale[files.size()-1];
//        for (String filename : files) {
//            try {
//                String path = "\\" + filename;
//                System.out.println(path);
//                BufferedReader reader = new BufferedReader(new FileReader(path));
//                Reader reader = new InputStreamReader(Parser.class.getResourceAsStream(path), "UTF-8");
//            } catch (Exception uee) {
//                System.out.println(uee.getMessage());
//            }
//        }
    }

    private boolean isFileType(File file) {
        String filename = file.getName().toLowerCase();
        return (filename.endsWith(FILE_TYPE));
    }

    private List<String> getPropertyFiles(String directory) {
        List<String> textFiles = new ArrayList<String>();
        File dir = new File(directory);
        for (File file : dir.listFiles()) {
            if (isFileType(file)) {
                textFiles.add(file.getName());
            }
        }
        return textFiles;
    }

}
