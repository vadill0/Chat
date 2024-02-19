package utils.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerMain {
    private static final HashMap<String, ClientHandler> CLIENTS = new HashMap<>();
    public static void main(String[] args) {
        int port = 6900;
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("Escuchando por el puerto " + port);
            while (true){
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Name check method
    public static synchronized boolean checkClients(String user, ClientHandler clientHandler){
        if(!CLIENTS.containsKey(user)){ CLIENTS.put(user, clientHandler);return true;}
        return false;
    }

    public static synchronized void addClient(String clientName, ClientHandler clientHandler){
        CLIENTS.put(clientName, clientHandler);
    }

    public static synchronized void delClient(String clientName){
        CLIENTS.remove(clientName);
    }

    //Server's broadcast message
    public static synchronized void sendMessage(String clientName, String msg){
        for (ClientHandler clientHandler : CLIENTS.values()){
            clientHandler.messageFormatter(clientName, msg);
        }
    }
}
