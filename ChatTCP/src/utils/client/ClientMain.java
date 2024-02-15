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

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write(name);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String answer = bufferedReader.readLine();
            JOptionPane.showMessageDialog(null, answer);
            //Name Check
            if(!answer.equals("Name already in use")){
                Client client = new Client(name, socket);
                Thread thread = new Thread(client);
                thread.start();
            }else{
             System.exit(-1);
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
