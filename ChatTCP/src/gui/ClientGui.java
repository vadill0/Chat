package gui;

import utils.client.Client;
import utils.server.ClientHandler;
import utils.server.ServerMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGui extends JFrame {
    private String clientName;
    private Client client;
    private JPanel panel1;
    private JButton sendButton;
    private JScrollPane msgScrollPane;
    private JTextArea msgTextArea;
    private JScrollPane textScrollPanel;

    public ClientGui(String clientName, Client client) {
        setSize(420, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        add(panel1);
        setLocationRelativeTo(null);
        this.clientName = clientName;
        this.client = client;

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String msg = msgTextArea.getText();
                client.sendMessage(msg);
                msgTextArea.setText("");
            }
        });
    }

    public void addMessage(String messageText) {
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel messageLabel = new JLabel(messageText);


        messagePanel.add(messageLabel);


        msgScrollPane.setViewportView(messagePanel);

        msgScrollPane.revalidate();
        msgScrollPane.getVerticalScrollBar().setValue(msgScrollPane.getVerticalScrollBar().getMaximum());
    }
}
