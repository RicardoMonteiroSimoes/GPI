package ch.rs.logiceditor.controller.interfaces;

public interface LoadingScreenInterface {

    /**Loading Screen section**/

    /**
     * Shows the Loading screen to the user. This enables the application to construct the whole GUI
     * before any is seen by the user.
     */
    public void initLoadingScreen();

    /**
     * Text to be display during the loading screen
     * @param text The text you want to show the user.
     */
    public void setLoadingText(String text);

    /**
     * Adds a certain value to the {@link javafx.scene.control.ProgressBar}.
     * Take care to not get to 1.0 total value to soon!
     * @param value double value between 0.0 and 1.0
     */
    public void addToProgressBar(double value);

    /**
     * function to manually close the loading screen.
     */
    public void closeLoadingScreen();

    /**
     * In case the properties are missing OR wrong, this method can be used to make the user insert the correct values.
     * Implementation of this is open to anyone. Please make sure that your methodic works for the
     * properties defined here: {@link ch.rs.logiceditor.controller.PropertiesLoader}
     * @param title Title of the dialog
     * @param message Ttile message to give the user more information about the missing property
     * @param propertieName The name of the property itself.
     * @return returns the userinput as a String, which gets inserted into the property automaticly.
     */
    public String createDialog(String title, String message, String propertieName);

    /**Loading Screen section end**/

}
