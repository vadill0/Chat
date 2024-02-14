package utils.client;

import gui.ClientGui;
import utils.server.Server;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientMain {
    public static void main(String[] args) {
        int port = 6900;
        Socket socket = null;
        
        try{
            socket = new Socket("localhost", port);
            System.out.println("Conectado a " + port);

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Formulario de entrada
        //String name = JOptionPane.showInputDialog(null, "Choose your username", "Log In", JOptionPane.PLAIN_MESSAGE);

        //ClientGui clientGui = new ClientGui();

    }
}
