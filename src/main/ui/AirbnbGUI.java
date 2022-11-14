package ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AirbnbGUI extends JFrame implements ActionListener {


    private JTextArea field;

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public AirbnbGUI() {
        super("Airbnb Manager");

        //
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        content.setBorder(padding);

        //
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 4, 25, 50));

        JButton adminBtn = new JButton("Admin");
        JButton customerBtn = new JButton("Customer");
        JButton saveBtn = new JButton("Save Properties");
        JButton loadBtn = new JButton("Load Properties");
        JButton quitBtn = new JButton("Exit");
        JButton loginBtn = new JButton("Login");
        JButton addBtn = new JButton("Add Airbnb");
        JButton removeBtn = new JButton("Remove Airbnb");
        JButton backBtn1 = new JButton("Main Menu");
        JButton backBtn2 = new JButton("Main Menu");
        JButton reserveBtn = new JButton("Make Reservation");
        JButton cancelBtn = new JButton("Cancel Reservations");
        JButton reservationInfoBtn1 = new JButton("Display Reservation Info");
        JButton reservationInfoBtn2 = new JButton("Display Reservation Info");
        JButton fillerBtn1 = new JButton();
        JButton fillerBtn2 = new JButton();
        fillerBtn1.setEnabled(false);
        fillerBtn1.setVisible(false);
        fillerBtn2.setEnabled(false);
        fillerBtn2.setVisible(false);

        buttonsPanel.add(fillerBtn1);
        buttonsPanel.add(adminBtn);
        buttonsPanel.add(customerBtn);
        buttonsPanel.add(fillerBtn2);
        buttonsPanel.add(addBtn);
        buttonsPanel.add(removeBtn);
        buttonsPanel.add(reservationInfoBtn1);
        buttonsPanel.add(backBtn1);
        buttonsPanel.add(reserveBtn);
        buttonsPanel.add(cancelBtn);
        buttonsPanel.add(reservationInfoBtn2);
        buttonsPanel.add(backBtn2);

        //
        adminBtn.setActionCommand("myButton");
        adminBtn.addActionListener(this); // Sets "this" object as an action listener for btn
        // so that when the btn is clicked,
        // this.actionPerformed(ActionEvent e) will be called.
        // You could also set a different object, if you wanted
        // a different object to respond to the button click

        customerBtn.setActionCommand("myButton");
        customerBtn.addActionListener(this);

        //
        JPanel sideButtons = new JPanel();
        sideButtons.setLayout(new FlowLayout());
        Box sideBox = Box.createVerticalBox();
        sideBox.add(saveBtn);
        sideBox.add(Box.createRigidArea(new Dimension(0,10)));
        sideBox.add(loadBtn);
        sideBox.add(Box.createRigidArea(new Dimension(0,10)));
        sideBox.add(quitBtn);
        sideButtons.add(sideBox);

        //
        Box loginBox = Box.createHorizontalBox();
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new FlowLayout());
        JTextField nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(200,30));

        //
        Box vertBox = Box.createVerticalBox();
        loginBox.add(nameField);
        loginBox.add(loginBtn);
        vertBox.add(buttonsPanel);
        vertBox.add(Box.createRigidArea(new Dimension(0,10)));
        vertBox.add(loginBox);
        loginPanel.add(vertBox);

        //
        field = new JTextArea();
        field.setPreferredSize(new Dimension(1500,50));
        field.setEditable(false);

        //
        content.add(field, BorderLayout.SOUTH);
        content.add(loginPanel, BorderLayout.NORTH);
        content.add(sideButtons, BorderLayout.EAST);

        //
        setContentPane(content);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1500,1000));
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
