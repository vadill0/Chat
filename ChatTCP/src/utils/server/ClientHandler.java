package utils.server;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private String userName;
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
            while (true){
                String recievedString = dis.readUTF();
                if(recievedString.contains("of/")){
                    //Username check
                    recievedString = recievedString.substring(3);
                    if(ServerMain.checkClients(recievedString, this)){
                        this.userName = recievedString;
                        ServerMain.addClient(this.userName, this);
                        dos.writeUTF("of/" + this.userName + " joined the chat");
                        ServerMain.greetingBroadcast(this.userName);
                    }else{
                        dos.writeUTF("of/Name already in use");
                        socket.close();

                    }
                }else if(recievedString.contains("ms/")){
                    //Chat
                    recievedString = recievedString.substring(3);
                    System.out.println(recievedString);
                    ServerMain.sendBroadcast(this.userName, recievedString);
                }
            }
        } catch (IOException e) {
            ServerMain.delClient(this.userName);
            System.err.println("Client disconnected");
        }
    }

    //Gives format to then display in JScrollPane
    public void messageFormatter(String clientName, String msg){
        try {
            dos.writeUTF("ms/" + clientName + ": " + msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Joined notification
    public void joinedMessage(String clientName){
        try {
            dos.writeUTF("ms/" + clientName + " joined the chat");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
