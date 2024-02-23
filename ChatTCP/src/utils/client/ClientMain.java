package utils.client;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMain {
    public static void main(String[] args) {
        int port = 6900;
        Socket socket = null;
        
        try{
            socket = new Socket("localhost", port);
            System.out.println("Conectado a " + port);

            //Login Form
            String name = JOptionPane.showInputDialog(null, "Choose your username", "Log In", JOptionPane.PLAIN_MESSAGE);

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            sendOffer(name, dataOutputStream);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            //Configure to only read of/ headers
            String answer = "";
            while((answer = dataInputStream.readUTF()).contains("of/")){
                JOptionPane.showMessageDialog(null, answer.substring(3));
                //Name Check
                if(!answer.equals("of/Name already in use")){
                    Client client = new Client(name, socket);
                    Thread thread = new Thread(client);
                    thread.start();
                }else{
                    System.exit(-1);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendOffer(String userName, DataOutputStream dos){
        StringBuilder offerString = new StringBuilder("of/");
        offerString.append(userName);
        try {
            dos.writeUTF(String.valueOf(offerString));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
