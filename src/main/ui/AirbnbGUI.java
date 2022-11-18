package ui;

import model.Airbnb;
import model.Properties;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AirbnbGUI extends JFrame implements ActionListener {

    private JTextArea propertiesField;
    private ArrayList<JTextArea> dates = new ArrayList<>();
    private Properties propertyList;
    private JButton adminBtn;
    private JButton customerBtn;
    private JButton addBtn;
    private JButton removeBtn;
    private JButton backBtn1;
    private JButton reservationInfoBtn1;
    private JButton backBtn2;
    private JButton reserveBtn;
    private JButton cancelBtn;
    private JButton reservationInfoBtn2;
    private JButton loginBtn;
    private Box loginBox;
    private JTextField checkInField;
    private JTextField checkOutField;
    JPanel content;
    JPanel currentCalendar;
    String loginName;
    JTextField nameField;
    JTextField propertyNameField;

    JPanel calendarPanel;

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public AirbnbGUI() {
        super("Airbnb Manager");
        //
        propertyList = new Properties();
        propertyList.addProperties(new Airbnb("House1"));


        //
        content = new JPanel();
        content.setLayout(new BorderLayout());
        Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        content.setBorder(padding);

        //
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 4, 25, 50));

        adminBtn = new JButton("Admin");
        customerBtn = new JButton("Customer");
        JButton saveBtn = new JButton("Save Properties");
        JButton loadBtn = new JButton("Load Properties");
        JButton quitBtn = new JButton("Exit");
        loginBtn = new JButton("Login");
        addBtn = new JButton("Add Airbnb");
        removeBtn = new JButton("Remove Airbnb");
        backBtn1 = new JButton("Main Menu");
        backBtn2 = new JButton("Main Menu");
        reserveBtn = new JButton("Make Reservation");
        cancelBtn = new JButton("Cancel Reservations");
        reservationInfoBtn1 = new JButton("Display Reservation Info");
        reservationInfoBtn2 = new JButton("Display Reservation Info");
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

        addBtn.setVisible(false);
        removeBtn.setVisible(false);
        backBtn1.setVisible(false);
        reservationInfoBtn1.setVisible(false);

        reserveBtn.setVisible(false);
        cancelBtn.setVisible(false);
        reservationInfoBtn2.setVisible(false);
        backBtn2.setVisible(false);

        //
        adminBtn.setActionCommand("admin");
        adminBtn.addActionListener(this);

        customerBtn.setActionCommand("customer");
        customerBtn.addActionListener(this);

        backBtn1.setActionCommand("back");
        backBtn1.addActionListener(this);
        backBtn2.setActionCommand("back");
        backBtn2.addActionListener(this);

        loginBtn.setActionCommand("login");
        loginBtn.addActionListener(this);

        addBtn.setActionCommand("add");
        addBtn.addActionListener(this);

        removeBtn.setActionCommand("remove");
        removeBtn.addActionListener(this);

        reservationInfoBtn1.setActionCommand("seeInfo");
        reservationInfoBtn1.addActionListener(this);

        reservationInfoBtn2.setActionCommand("seeInfo");
        reservationInfoBtn2.addActionListener(this);

        reserveBtn.setActionCommand("reserve");
        reserveBtn.addActionListener(this);

        quitBtn.setActionCommand("quit");
        quitBtn.addActionListener(this);

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
        loginBox = Box.createHorizontalBox();
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new FlowLayout());
        nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(200, 30));

        loginBox.setVisible(false);

        //
        Box vertBox = Box.createVerticalBox();
        loginBox.add(nameField);
        loginBox.add(loginBtn);
        vertBox.add(buttonsPanel);
        vertBox.add(Box.createRigidArea(new Dimension(0, 10)));
        vertBox.add(loginBox);
        loginPanel.add(vertBox);

        //
        JLabel propertiesLabel = new JLabel("List of available properties");
        propertiesField = new JTextArea();
        propertiesField.setPreferredSize(new Dimension(1500, 50));
        propertiesField.setEditable(false);
        propertiesField.setText(propertyList.seeAllProperties().toString());
        propertiesField.setAlignmentX(Component.LEFT_ALIGNMENT);
        Box areaBox = Box.createVerticalBox();
        areaBox.add(propertiesLabel);
        areaBox.add(propertiesField);
        //
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new FlowLayout());
        Box westBox = Box.createVerticalBox();
        JLabel addLabel = new JLabel("Property Name");
        propertyNameField = new JTextField();

        JLabel checkInLabel = new JLabel("Check in date");
        JLabel checkOutLabel = new JLabel("Check out date");
        checkInField = new JTextField();
        checkOutField = new JTextField();

        westBox.add(addLabel);
        westBox.add(propertyNameField);
        westBox.add(Box.createRigidArea(new Dimension(0, 10)));
        westBox.add(checkInLabel);
        westBox.add(checkInField);
        westBox.add(checkOutLabel);
        westBox.add(checkOutField);

        westPanel.add(westBox);


        calendarPanel = makeCalendar(propertyList.getProperties().get(0));
        currentCalendar = calendarPanel;
        currentCalendar.setVisible(false);
        //
        content.add(currentCalendar, BorderLayout.CENTER);
        content.add(areaBox, BorderLayout.SOUTH);
        content.add(loginPanel, BorderLayout.NORTH);
        content.add(sideButtons, BorderLayout.EAST);
        content.add(westPanel, BorderLayout.WEST);

        //

        setContentPane(content);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setPreferredSize(new Dimension(1000, 800));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

    }


    private JPanel makeCalendar(Airbnb airbnb) {
        JPanel calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(5, 7, 25, 25));
        for (int i = 0; i <= 30; i++) {
            JTextArea date = new JTextArea();
            date.setEditable(false);
            if (airbnb.getReservations().get(i) == null) {
                date.setText("Available");
            } else {
                date.setText(loginName);
            }
            Box dateBox = Box.createVerticalBox();
            JLabel dateNumber = new JLabel(String.valueOf(i + 1));
            dateBox.add(dateNumber);
            dateBox.add(date);
            calendarPanel.add(dateBox);
            dates.add(date);
        }
        return calendarPanel;
    }


    public static void main(String[] args) {
        new AirbnbGUI();
    }

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("admin")) {
            addBtn.setVisible(true);
            removeBtn.setVisible(true);
            backBtn1.setVisible(true);
            reservationInfoBtn1.setVisible(true);
            adminBtn.setVisible(false);
            customerBtn.setVisible(false);
        } else if (e.getActionCommand().equals("customer")) {
            adminBtn.setVisible(false);
            customerBtn.setVisible(false);
            loginBox.setVisible(true);
            loginBtn.setVisible(true);
        } else if (e.getActionCommand().equals("back")) {
            adminBtn.setVisible(true);
            customerBtn.setVisible(true);
            addBtn.setVisible(false);
            removeBtn.setVisible(false);
            backBtn1.setVisible(false);
            reservationInfoBtn1.setVisible(false);
            reserveBtn.setVisible(false);
            cancelBtn.setVisible(false);
            reservationInfoBtn2.setVisible(false);
            backBtn2.setVisible(false);
            loginBox.setVisible(false);
        } else if (e.getActionCommand().equals("login")) {

            loginBtn.setVisible(false);
            reserveBtn.setVisible(true);
            cancelBtn.setVisible(true);
            reservationInfoBtn2.setVisible(true);
            backBtn2.setVisible(true);
            nameField.setVisible(false);

            loginName = nameField.getText();
        } else if (e.getActionCommand().equals("quit")) {
            System.exit(0);
        } else if (e.getActionCommand().equals("add")) {
            propertyList.addProperties(new Airbnb(propertyNameField.getText()));
            propertiesField.setText(propertyList.seeAllProperties().toString());
        } else if (e.getActionCommand().equals("remove")) {
            propertyList.removeProperties(propertyNameField.getText());
            propertiesField.setText(propertyList.seeAllProperties().toString());
        } else if (e.getActionCommand().equals("seeInfo")) {
            for (int i = 0; i < propertyList.getProperties().size(); i++) {
                if (propertyList.getProperties().get(i).getAirbnbName().equals(propertyNameField.getText())) {
                    content.remove(currentCalendar);
                    calendarPanel = makeCalendar(propertyList.getProperties().get(i));
                    currentCalendar = calendarPanel;
                    content.add(currentCalendar, BorderLayout.CENTER);
                    content.revalidate();
                    content.repaint();
                }
            }
        } else if (e.getActionCommand().equals("reserve")) {
            Airbnb chosenAirbnb = null;
            if (propertyList.airbnbExists(propertyNameField.getText())) {
                for (int i = 0; i < propertyList.getProperties().size(); i++) {
                    if (propertyList.getProperties().get(i).getAirbnbName().equals(propertyNameField.getText())) {
                        chosenAirbnb = propertyList.getProperties().get(i);
                        break;
                    }
                }
                int checkIn = Integer.parseInt(checkInField.getText());
                int checkOut = Integer.parseInt(checkOutField.getText());
                chosenAirbnb.makeReservation(loginName, checkIn, checkOut);
            }

        }
    }
}
//https://stackoverflow.com/questions/2501861/how-can-i-remove-a-jpanel-from-a-jframe
