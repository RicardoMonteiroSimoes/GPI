/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package old.BuildingBlocks.Master;

import old.BuildingBlocks.Master.ContactPoint.Datatype;
import BuildingBlocks.Master.util.CreationUtil;
import BuildingBlocks.Master.util.Dialogs;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Ricardo
 */
public abstract class TimerBlock extends LogicBlock {

    private double delayTime = 1.0;
    private ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

    public TimerBlock(String sName, String blockSubName) {
        super(sName, blockSubName, CreationUtil.createInput(Datatype.BOOLEAN), false, CreationUtil.createOutput(Datatype.BOOLEAN), Type.TIMER);
        createAdditionalOptionsDialog();
    }

    protected abstract void setOutputAfterTimer();


    protected void setTime(double dTime) {
        this.delayTime = dTime;
    }

    protected double getTime() {
        return delayTime;
    }

    protected void startTimer() {

        final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.schedule(new Runnable() {
            @Override
            public void run() {
                setOutputAfterTimer();
            }
        }, (long) delayTime, TimeUnit.SECONDS);
    }

    protected void cancelTimer() {
        
    }

    @Override
    public void update(Observable o, Object arg) {
        try{
            Logic();
        } catch (NullPointerException npe){
            System.out.println(npe.getMessage());
        }
    }

    private void createAdditionalOptionsDialog() {
        Group modeChangeGroup = new Group();
        Label pressMode = new Label("Zeitverzögerung:");
        TextField time = new TextField();
        time.setText(String.valueOf(getTime()));
        time.setEditable(true);

        Button changeMode = new Button("Übernehmen");
        changeMode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    setTime(Double.valueOf(time.getText()));
                } catch (Exception e) {
                    Dialogs.alertDialog("Fehler", "Eingabefehler", "Keine Funktion für folgenden Wert gefunden: " + time.getText());
                }
            }
        });
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        grid.add(pressMode, 0, 0);
        grid.add(time, 0, 1);
        grid.add(changeMode, 1, 1);
        modeChangeGroup.getChildren().add(grid);
        addDialogFunction(modeChangeGroup);
    }

}
