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
import java.util.Optional;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author Ricardo
 */
public abstract class NetworkBlock extends LogicBlock{
    
    private int port;
    
    public NetworkBlock(){
        super("Server In", "Receives Commands on a Port", CreationUtil.createOutput(Datatype.STRING), Type.NETWORK);
        launchConfigurationDialog();
    }
    
    private void launchConfigurationDialog(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Server receiving Port");
        dialog.setHeaderText("Port");
        dialog.setContentText("Insert the Port the Server is supposed to receive on:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try{
                    port = Integer.valueOf(result.get());
                    
            } catch (NumberFormatException nfe){
                Dialogs.alertDialog("Error", "Inputerror", result.get() + " is not a valid input!");
            }
        }
    }

    
}

    
        
    

