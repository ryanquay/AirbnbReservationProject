package ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AirbnbGUI extends JFrame implements ActionListener {


    private JTextArea field;
    private MenuPanel mp;

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public AirbnbGUI() {
        super("Airbnb Manager");

        //
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        content.setBorder(padding);

        //

        JButton saveBtn = new JButton("Save Properties");
        JButton loadBtn = new JButton("Load Properties");
        JButton quitBtn = new JButton("Exit");
        JButton loginBtn = new JButton("Login");

        //
        JPanel sideButtons = new JPanel();
        sideButtons.setLayout(new FlowLayout());
        Box sideBox = Box.createVerticalBox();
        sideBox.add(saveBtn);
        sideBox.add(Box.createRigidArea(new Dimension(0, 10)));
        sideBox.add(loadBtn);
        sideBox.add(Box.createRigidArea(new Dimension(0, 10)));
        sideBox.add(quitBtn);
        sideButtons.add(sideBox);



        //
        field = new JTextArea();
        field.setPreferredSize(new Dimension(1500, 50));
        field.setEditable(false);
        mp = new MenuPanel();


        Box loginBox = Box.createHorizontalBox();
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new FlowLayout());
        JTextField nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(200,30));

        //
        Box vertBox = Box.createVerticalBox();
        loginBox.add(nameField);
        loginBox.add(loginBtn);
        vertBox.add(mp);
        vertBox.add(Box.createRigidArea(new Dimension(0,10)));
        vertBox.add(loginBox);
        loginPanel.add(vertBox);
        //

        content.add(field, BorderLayout.SOUTH);
        content.add(loginPanel, BorderLayout.NORTH);
        content.add(sideButtons, BorderLayout.EAST);

        //
        setContentPane(content);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1500, 1000));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

    }

    public static void main(String[] args) {
        new AirbnbGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myButton")) {
            field.setText("Hello");
        }
    }


}
