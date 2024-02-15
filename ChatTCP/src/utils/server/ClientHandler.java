package utils.server;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket socket;
    private InputStreamReader isr;
    private OutputStreamWriter osr;
    public ClientHandler(Socket socket){
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
        BufferedReader bufferedReader = new BufferedReader(isr);
        try {
            //Recieve username
            String clientName = bufferedReader.readLine();

            //Username check
            if(ServerMain.checkClients(clientName)){
                ServerMain.addClient(clientName);
                osr.write(clientName + " Joined the chat");
            }else{
                osr.write("Name already in use");
                socket.close();
                System.exit(0);
            }

            //Chat
            while (true){
                String msg = bufferedReader.readLine();
                ServerMain.sendMessage(clientName, msg);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Gives format to then display in JScrollPane
    public void messageFormatter(String clientName, String msg){
        try {
            osr.write(clientName + " : " + msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
