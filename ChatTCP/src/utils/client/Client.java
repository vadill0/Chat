package utils.client;

import gui.ClientGui;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client implements Runnable {
    private String name;
    private Socket socket;
    private InputStreamReader isr;
    private OutputStreamWriter osr;
    public Client(String name, Socket socket){
        this.name = name;
        this.socket = socket;
        try {
            isr = new InputStreamReader(socket.getInputStream());
            osr = new OutputStreamWriter(socket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void run() {
        ClientGui clientGui = new ClientGui(this.name);
        clientGui.setName(this.name + "'s chat");

    }
}
