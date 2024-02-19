package utils.server;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    public ClientHandler(Socket socket){
        this.socket = socket;
        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void run() {
        try {
            //Recieve username
            String clientName = dis.readUTF();
            //Username check
            if(ServerMain.checkClients(clientName, this)){
                ServerMain.addClient(clientName, this);
                dos.writeUTF(clientName + " Joined the chat");
            }else{
                dos.writeUTF("Name already in use");
                socket.close();

            }

            //Chat
            while (true){
                String msg = dis.readUTF();
                ServerMain.sendMessage(clientName, msg);
            }
        } catch (IOException e) {
            System.err.println("Client disconnected");
        }
    }

    //Gives format to then display in JScrollPane
    public void messageFormatter(String clientName, String msg){
        try {
            dos.writeUTF(clientName + " : " + msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
