package ch.rs.logiceditor.view.master.loader;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class LoadingScreenVariables {

    @FXML
    protected Text loadingText;
    @FXML
    protected ProgressBar progressBar;
    @FXML
    protected AnchorPane masterPane = new AnchorPane();

}
