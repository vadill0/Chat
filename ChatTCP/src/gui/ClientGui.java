package gui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ClientGui extends JFrame{
    private JPanel panel1;
    private JButton sendButton;
    private JLabel nameLabel;
    private JScrollPane msgScrollPane;
    private JTextArea msgTextArea;
    private JScrollPane textScrollPanel;

    public ClientGui() {
        setTitle("Chat");
        setSize(420, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);


        add(panel1);
        setLocationRelativeTo(null);
    }
}
