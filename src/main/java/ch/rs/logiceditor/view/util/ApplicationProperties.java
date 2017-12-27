package ch.rs.logiceditor.view.util;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ApplicationProperties {

    private final String APP_FOLDER_NAME = "\\.GPI";
    private final String PROP_FILE_NAME = "\\AppSettings";
    private final String PROP_FILE_ENDING = ".properties";

    private final String PROPERTY_PATH_BLOCKS = "pathToBlocks";
    private final String PROPERTY_USERNAME = "username";
    private Path pathToProperties;
    private File propertiesFile;

    private Path pathToBlocks;
    private String username;

    public ApplicationErrorEnum getProperties() {
        pathToProperties = Paths.get(System.getProperty("user.home") + APP_FOLDER_NAME + PROP_FILE_NAME + PROP_FILE_ENDING);
        propertiesFile = new File(pathToProperties.toUri());
        if (propertiesFile.exists() && !propertiesFile.isDirectory()) {
            if (loadProperties()) {
                return ApplicationErrorEnum.OK;
            } else {
                return ApplicationErrorEnum.WRONG_DATA;
            }
        } else {
            if (createAppDirectory() && createPropertiesFile()) {
                return ApplicationErrorEnum.NEW_FILE;
            } else {
                return ApplicationErrorEnum.ERROR;
            }
        }
    }

    private boolean createAppDirectory() {
        File dir = new File(System.getProperty("user.home") + APP_FOLDER_NAME);
        if (dir.exists()) {
            return true;
        } else {
            return dir.mkdirs();
        }
    }

    private boolean createPropertiesFile() {
        Properties properties = new Properties();
        properties.setProperty(PROPERTY_PATH_BLOCKS, "");
        properties.setProperty(PROPERTY_USERNAME, "");
        propertiesFile = new File(pathToProperties.toUri());
        try {
            FileOutputStream fileOut = new FileOutputStream(propertiesFile);
            properties.store(fileOut, "Properties for GPI.jar");
            fileOut.close();
        } catch (IOException e) {
            return false;
        }
        try {
            return propertiesFile.createNewFile();
        } catch (IOException e) {
            return false;
        }
    }

    public Path getPathToBlocks() {
        return pathToBlocks;
    }

    public boolean setPathToBlocks(Path path){
        if(path == null){
            return false;
        }
        pathToBlocks = path;
        return true;
    }

    public String getUsername(){
        return username;
    }

    public boolean setUsername(String username){
        if(username == null){
            return false;
        }
        this.username = username;
        return true;
    }


    private boolean loadProperties() {
        Properties properties = new Properties();
        try {
            InputStream inputFile = new FileInputStream(pathToProperties.toString());
            properties.load(inputFile);
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
        pathToBlocks = Paths.get(properties.getProperty(PROPERTY_PATH_BLOCKS));
        username = properties.getProperty(PROPERTY_USERNAME);
        return true;
    }


}
