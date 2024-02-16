package utils.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerMain {
    private static final ArrayList <String> CLIENTS = new ArrayList<>();
    private static final ArrayList <ClientHandler> HANDLERS = new ArrayList<>();
    public static void main(String[] args) {
        int port = 6900;
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("Escuchando por el puerto " + port);
            while (true){
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                HANDLERS.add(clientHandler);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Name check method
    public static synchronized boolean checkClients(String user){
        if(!CLIENTS.contains(user)){ CLIENTS.add(user);return true;}
        return false;
    }

    public static synchronized void addClient(String clientName){
        CLIENTS.add(clientName);
    }

    public static synchronized void delClient(String clientName){
        CLIENTS.remove(clientName);
    }

    //Server's broadcast message
    public static synchronized void sendMessage(String clientName, String msg){
        for (ClientHandler clientHandler : HANDLERS){
            clientHandler.messageFormatter(clientName, msg);
        }
    }
}
