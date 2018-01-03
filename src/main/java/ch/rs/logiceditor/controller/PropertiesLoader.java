package ch.rs.logiceditor.controller;



import ch.rs.logiceditor.controller.interfaces.LoadingScreenInterface;

import java.io.*;
import java.util.Properties;

public class PropertiesLoader {

    private final String propertiesFileName = "appSettings.properties";
    private final String appName = "GPI";
    private final String propertiesFolder = "Settings";
    private final String[] properties = {"username", "pathToBlocks"};
    private Properties props = new Properties();
    private String propertiesPath;

    public void startLoading(LoadingScreenInterface loadingScreen) throws IOException{
        System.out.println("initializing loading screen");

        propertiesPath = getPropertiesPath() + "\\" + appName + "\\" + propertiesFolder;

        loadingScreen.setLoadingText("Checking directory...");
        checkForDirectory(propertiesPath);
        loadingScreen.addToProgressBar(0.1);

        loadingScreen.setLoadingText("Checking for properties file...");
        File file = checkForFile(propertiesFileName);
        loadingScreen.addToProgressBar(0.1);
        loadingScreen.setLoadingText("Loading properties...");

        if(file != null){
            InputStream input = new FileInputStream(file);
            props.load(input);
            for(String s : properties){
                loadingScreen.setLoadingText("checking " + s + "...");
                if(props.getProperty(s).equals("")){
                    props.setProperty(s,
                            loadingScreen.createDialog("Missing Propertie",
                                    "Please insert the missing propertie for " + s, s));
                }

                loadingScreen.addToProgressBar(0.05);
            }
            saveProperties(file);


        } else {
            System.out.println("File is null what");
        }


    }

    private void saveProperties(File file) throws FileNotFoundException, IOException{
        OutputStream out = new FileOutputStream(file);
        props.store(out, "Settings for the GPI, only change if you know what you're doing!");
    }

    private void checkForDirectory(String path){
        File appDirectory = new File((path));
        if(!appDirectory.isDirectory()){
            appDirectory.mkdirs();
        }
    }


    private File checkForFile(String filename) throws IOException{
        File propertiesFile = new File (propertiesPath + "\\"  + filename);
        if(propertiesFile.exists()){
            return propertiesFile;
        } else {
            try {
                propertiesFile.createNewFile();
                Properties prop = new Properties();
                for(String s : properties){
                    prop.setProperty(s, "");
                }
                OutputStream out = new FileOutputStream(propertiesFile);
                prop.store(out, "Settings for the GPI, only change if you know what you're doing!");
                return propertiesFile;
            } catch (IOException e){
                throw new IOException("Cannot create new File! " + e.getLocalizedMessage());
            }
        }
    }

    private String getPropertiesPath(){
        return System.getProperty("user.home");
    }

    public String getPropertie(String propertieName){
        return props.getProperty(propertieName);
    }

}
