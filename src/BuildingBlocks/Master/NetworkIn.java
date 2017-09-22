/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Master;

import java.util.ArrayList;
import BuildingBlocks.Master.BlockGraphic.Type;
import BuildingBlocks.Master.ContactPoint.Datatype;
import BuildingBlocks.Master.util.CreationUtil;
import BuildingBlocks.Master.util.Dialogs;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Observable;
import java.util.Optional;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author Ricardo
 */
public class NetworkIn extends LogicBlock {

    private int port;

    public NetworkIn () {
        super("Server In", "Receives Commands on a Port", CreationUtil.createOutput(Datatype.STRING), Type.NETWORK);
        launchConfigurationDialog();
    }
    
    public NetworkIn(boolean isGUI){
        super("Server In", "Receives Commands on a Port", CreationUtil.createOutput(Datatype.STRING), Type.NETWORK);
    }

    private void launchConfigurationDialog () {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Server receiving Port");
        dialog.setHeaderText("Port");
        dialog.setContentText("Insert the Port the Server is supposed to receive on:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                port = Integer.valueOf(result.get());
                ServerSocketReceive ssR = new ServerSocketReceive(port);
                ssR.addObserver(this);
                System.out.println("Trying to run socket server");
                sc_pgi.SC_PGI.executor.execute(ssR.run());
                new Thread(ssR.run());
                
            } catch (NumberFormatException nfe) {
                Dialogs.alertDialog("Error", "Inputerror", result.get() + " is not a valid input!");
            }
        }
    }

    @Override
    public void update (Observable o, Object arg) {
        try {
            ServerSocketReceive receiveSocketTemp = (ServerSocketReceive) o;
            String socketMessage = (String) arg;
            setOutput(socketMessage);
        } catch (Exception npe) {
            System.out.println("Something went wrong @ Logic " + npe.getMessage());
        }
    }

    @Override
    protected void Logic () {
        
    }
}
