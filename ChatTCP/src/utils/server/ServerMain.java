package utils.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        int port = 6900;
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("Escuchando por el puerto " + port);
            while (true){
                Socket socket = serverSocket.accept();

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
