package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGui extends JFrame {
    private JPanel panel1;
    private JTextArea msgTextArea;
    private JButton sendButton;
    private JLabel nameLabel;
    private JScrollPane msgScrollPane;

    public ClientGui() {
        setTitle("Chat Application");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        msgScrollPane = new JScrollPane();
        msgTextArea = new JTextArea();
        msgTextArea.setEditable(false);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(msgScrollPane);
        panel.add(msgTextArea);
        panel.add(sendButton);

        add(panel);

        setLocationRelativeTo(null);
    }
}

