package utils.server;

import java.awt.*;
import java.util.ArrayList;

public class Server implements Runnable{
    private final ArrayList <String> CLIENTS = new ArrayList<>();

    public Server(){
        
    }
    @Override
    public void run() {
        while (true){

        }
    }

    public boolean checkClients(String user){
        if(!CLIENTS.contains(user)){ CLIENTS.add(user);return true;}
        return false;
    }
}
