/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BuildingBlocks.Blocks;

import BuildingBlocks.Master.ContactPoint;
import BuildingBlocks.Master.LogicBlock;
import BuildingBlocks.Master.Network.ServerPacket;
import BuildingBlocks.Master.Network.ServerSend;
import BuildingBlocks.Master.util.CreationUtil;

/**
 *
 * @author Ricardo
 */
public class NetworkOut extends LogicBlock{
    
    public NetworkOut(){
        super("Server Out", "Sends Packets to IP:Port", CreationUtil.createInput("Input", ContactPoint.Datatype.SERVERPACKET), Type.NETWORK);
    }

    @Override
    protected void Logic() {
        try{
            ServerPacket packetToSend = (ServerPacket) getInput();
            if(!packetToSend.isEmpty()){
                new Thread(new ServerSend(packetToSend)).start();
            }
        } catch ( Exception e ){
            System.out.println(e.getMessage());
        }
    }
    
}
