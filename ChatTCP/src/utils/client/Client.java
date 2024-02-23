package utils.client;

import gui.ClientGui;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class Client implements Runnable {
    private String name;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    public Client(String name, Socket socket){
        this.name = name;
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
        ClientGui clientGui = new ClientGui(this.name, this);
        clientGui.setName(this.name + "'s chat");
        while (true){
            try {
                String msgRecieved = "";
                if((msgRecieved = dis.readUTF()).startsWith("ms/")){
                    System.out.println(msgRecieved);
                    msgRecieved = msgRecieved.substring(3);
                    clientGui.addMessage(msgRecieved);
                }
                clientGui.revalidate();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void sendMessage(String msg){
        StringBuilder stringBuilder = new StringBuilder("ms/");
        stringBuilder.append(msg);
        try {
            dos.writeUTF(String.valueOf(stringBuilder));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
