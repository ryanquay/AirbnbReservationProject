package ui;

import model.Airbnb;
import model.Event;
import model.EventLog;
import model.Properties;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

//Airbnb application with a gui
public class AirbnbGUI extends JFrame implements ActionListener {

    private JTextArea propertiesField;
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
    private JButton saveBtn;
    private JButton loadBtn;
    private JButton quitBtn;
    private JButton fillerBtn1;
    private JButton fillerBtn2;
    private JPanel rightSideButtons;
    private Box loginBox;
    private JTextField checkInField;
    private JTextField checkOutField;
    private JPanel content;
    private JPanel currentCalendar;
    private String loginName;
    private JTextField nameField;
    private JTextField propertyNameField;
    private JPanel calendarPanel;
    private JPanel buttonsPanel;
    private JPanel imagePanel;
    private JPanel northPanel;
    private JPanel westPanel;
    private Box areaBox;

    private static final String JSON_STORE = "./data/properties.json";  //Location to store saved data file
    private JsonWriter jsonWriter; //Writer
    private JsonReader jsonReader; //Reader

    //MODIFIES: this
    //EFFECTS: Initializes json writer and reader
    private void initializeJson() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    //MODIFIES: this
    //EFFECTS: Adds an initial airbnb to property list
    private void initializePropertyList() {
        //Add 1 airbnb to start
        propertyList = new Properties();
        propertyList.addProperties(new Airbnb("House1"));
    }

    //MODIFIES: this
    //EFFECTS: Sets up the main panel where different components will be added to
    private void createMainPanel() {
        content = new JPanel();
        content.setLayout(new BorderLayout());
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        content.setBorder(padding);
    }

    //MODIFIES: this
    //EFFECTS: Initialize panel that will contain all the menu buttons
    private void initializeButtonPanel() {
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 4, 25, 50));
    }

    //MODIFIES: this
    //EFFECTS: Initializes all the menu buttons
    private void initializeMenuButtons() {
        adminBtn = new JButton("Admin");
        customerBtn = new JButton("Customer");
        saveBtn = new JButton("Save Properties");
        loadBtn = new JButton("Load Properties");
        quitBtn = new JButton("Exit");
        loginBtn = new JButton("Login");
        addBtn = new JButton("Add Airbnb");
        removeBtn = new JButton("Remove Airbnb");
        backBtn1 = new JButton("Main Menu");
        backBtn2 = new JButton("Main Menu");
        reserveBtn = new JButton("Make Reservation");
        cancelBtn = new JButton("Cancel Reservations");
        reservationInfoBtn1 = new JButton("Display Reservation Info");
        reservationInfoBtn2 = new JButton("Display Reservation Info");
        fillerBtn1 = new JButton();
        fillerBtn2 = new JButton();
        fillerBtn1.setEnabled(false);
        fillerBtn1.setVisible(false);
        fillerBtn2.setEnabled(false);
        fillerBtn2.setVisible(false);
    }

    //MODIFIES: this
    //EFFECTS: Adds menu buttons to button panel
    private void addMenuButtons() {
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
    }

    //MODIFIES: this
    //EFFECTS: Sets certain buttons' visibility
    private void setButtonVisibility() {
        addBtn.setVisible(false);
        removeBtn.setVisible(false);
        backBtn1.setVisible(false);
        reservationInfoBtn1.setVisible(false);

        reserveBtn.setVisible(false);
        cancelBtn.setVisible(false);
        reservationInfoBtn2.setVisible(false);
        backBtn2.setVisible(false);
    }

    //MODIFIES: this
    //EFFECTS: Sets action commands to buttons
    private void setActionCommands() {
        adminBtn.setActionCommand("admin");
        customerBtn.setActionCommand("customer");
        backBtn1.setActionCommand("back");
        backBtn2.setActionCommand("back");
        loginBtn.setActionCommand("login");
        addBtn.setActionCommand("add");
        removeBtn.setActionCommand("remove");
        reservationInfoBtn1.setActionCommand("seeInfo");
        reservationInfoBtn2.setActionCommand("seeInfo");
        reserveBtn.setActionCommand("reserve");
        cancelBtn.setActionCommand("cancel");
        quitBtn.setActionCommand("quit");
        saveBtn.setActionCommand("save");
        loadBtn.setActionCommand("load");
    }

    //MODIFIES: this
    //EFFECTS: Adds buttons to action listener
    private void addActionListeners() {
        adminBtn.addActionListener(this);
        customerBtn.addActionListener(this);
        backBtn1.addActionListener(this);
        backBtn2.addActionListener(this);
        loginBtn.addActionListener(this);
        addBtn.addActionListener(this);
        removeBtn.addActionListener(this);
        reservationInfoBtn1.addActionListener(this);
        reservationInfoBtn2.addActionListener(this);
        reserveBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        quitBtn.addActionListener(this);
        saveBtn.addActionListener(this);
        loadBtn.addActionListener(this);
    }

    //MODIFIES: this
    //EFFECTS: Creates a panel for the components on the right
    private void createRightPanel() {
        //Create panel for the save,load, quit buttons
        rightSideButtons = new JPanel();
        rightSideButtons.setLayout(new FlowLayout());
        Box sideBox = Box.createVerticalBox();
        sideBox.add(saveBtn);
        sideBox.add(Box.createRigidArea(new Dimension(0, 10)));
        sideBox.add(loadBtn);
        sideBox.add(Box.createRigidArea(new Dimension(0, 10)));
        sideBox.add(quitBtn);
        rightSideButtons.add(sideBox);
    }

    //MODIFIES: this
    //EFFECTS: Create panel to hold an image png
    private void createImagePanel() {
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("./data/AirbnbLogo.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        Image scaledImage = myPicture.getScaledInstance(250, 75, Image.SCALE_DEFAULT);
        JLabel picLabel = new JLabel(new ImageIcon(scaledImage));

        imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        imagePanel.add(picLabel);
    }

    //MODIFIES: this
    //EFFECTS: Creates login area
    private void createLogin() {
        loginBox = Box.createHorizontalBox();
        nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(200, 30));
        loginBox.setVisible(false);
        loginBox.add(nameField);
        loginBox.add(loginBtn);
    }

    //MODIFIES: this
    //EFFECTS: Create upper panel that holds, image/button panel, and login box
    private void createUpperPanel() {
        northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        Box vertBox = Box.createVerticalBox();
        vertBox.add(imagePanel);
        vertBox.add(buttonsPanel);
        vertBox.add(loginBox);
        northPanel.add(vertBox);
    }

    //MODIFIES: this
    //EFFECTS: Creates field that will display properties at all times
    private void createPropertiesField() {
        JLabel propertiesLabel = new JLabel("List of available Airbnbs");
        propertiesField = new JTextArea();
        propertiesField.setPreferredSize(new Dimension(1500, 50));
        propertiesField.setEditable(false);
        propertiesField.setText(propertyList.seeAllProperties().toString());
        propertiesField.setAlignmentX(Component.LEFT_ALIGNMENT);
        areaBox = Box.createVerticalBox();
        areaBox.add(propertiesLabel);
        areaBox.add(propertiesField);
    }

    //MODIFIES: this
    //EFFECTS: Create panel that will hold components on the left side
    private void createLeftPanel() {
        //Panel to add components on the left side
        westPanel = new JPanel();
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
    }

    //MODIFIES: this
    //EFFECTS: Creates a calendar panel that is initially hidden
    private void createCalendarPanel() {
        calendarPanel = makeCalendar(propertyList.getProperties().get(0));
        currentCalendar = calendarPanel;
        currentCalendar.setVisible(false);
    }

    //MODIFIES: this
    //EFFECTS: Adds all panels to main content panel in their desired locations
    private void combinePanels() {
        content.add(currentCalendar, BorderLayout.CENTER);
        content.add(areaBox, BorderLayout.SOUTH);
        content.add(northPanel, BorderLayout.NORTH);
        content.add(rightSideButtons, BorderLayout.EAST);
        content.add(westPanel, BorderLayout.WEST);
    }

    //MODIFIES: this
    //EFFECTS: Initializes the JFrame
    private void initializeJFrame() {
        setContentPane(content);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1150, 850));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    /*
     * MODIFIES: this
     * EFFECTS: Sets up a GUI with many components, which include panels, buttons, etc
     */
    public AirbnbGUI() {
        super("Airbnb GUI");
        initializeJson();
        initializePropertyList();
        createMainPanel();
        initializeButtonPanel();
        initializeMenuButtons();
        addMenuButtons();
        setButtonVisibility();
        setActionCommands();
        addActionListeners();
        createRightPanel();
        createImagePanel();
        createLogin();
        createUpperPanel();
        createPropertiesField();
        createLeftPanel();
        createCalendarPanel();
        combinePanels();
        initializeJFrame();
    }

    /*
     * MODIFIES: this
     * EFFECTS: Takes an Airbnb as an argument, and creates a calendar-like
     * format using its data. Returns it as a JPanel.
     */
    private JPanel makeCalendar(Airbnb airbnb) {
        JPanel calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(5, 7, 25, 25));
        for (int i = 0; i <= 30; i++) {
            JTextArea date = new JTextArea();
            date.setEditable(false);
            if (airbnb.getReservations().get(i) == null) {
                date.setText("Available");
            } else {
                date.setText(airbnb.getReservations().get(i));
            }
            Box dateBox = Box.createVerticalBox();
            JLabel dateNumber = new JLabel(String.valueOf(i + 1));
            dateBox.add(dateNumber);
            dateBox.add(date);
            calendarPanel.add(dateBox);
        }
        return calendarPanel;
    }

    /*
     * MODIFIES: this
     * EFFECTS: Executes different procedures depending on the button clicked*/
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("admin")) { //Options for admin revealed
            addBtn.setVisible(true);
            removeBtn.setVisible(true);
            backBtn1.setVisible(true);
            reservationInfoBtn1.setVisible(true);
            adminBtn.setVisible(false);
            customerBtn.setVisible(false);

        } else if (e.getActionCommand().equals("customer")) { //Login appears
            adminBtn.setVisible(false);
            customerBtn.setVisible(false);
            loginBox.setVisible(true);
            nameField.setVisible(true);
            nameField.setText("");
            loginBtn.setVisible(true);

        } else if (e.getActionCommand().equals("back")) { //Goes back to initial startup screen
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

        } else if (e.getActionCommand().equals("login")) { //Shows options available for customer
            loginBtn.setVisible(false);
            reserveBtn.setVisible(true);
            cancelBtn.setVisible(true);
            reservationInfoBtn2.setVisible(true);
            backBtn2.setVisible(true);
            nameField.setVisible(false);
            loginName = nameField.getText();

        } else if (e.getActionCommand().equals("quit")) { //Quit application
            Iterator<Event> events = EventLog.getInstance().iterator();
            System.out.println("Event Log:");
            while (events.hasNext()) {
                System.out.println(events.next());
            }
            System.exit(0);

        } else if (e.getActionCommand().equals("add")) { //Adds Airbnb
            propertyList.addProperties(new Airbnb(propertyNameField.getText()));
            propertiesField.setText(propertyList.seeAllProperties().toString());

        } else if (e.getActionCommand().equals("remove")) {
            propertyList.removeProperties(propertyNameField.getText());
            propertiesField.setText(propertyList.seeAllProperties().toString());

        } else if (e.getActionCommand().equals("seeInfo")) { //Displays information of specific Airbnb
            if (propertyList.airbnbExists(propertyNameField.getText())) {
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
            }

        } else if (e.getActionCommand().equals("reserve")) { //Reserves dates in a specific Airbnb
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
                boolean valid = chosenAirbnb.makeReservation(loginName, checkIn, checkOut);
                if (valid) {
                    content.remove(currentCalendar);
                    calendarPanel = makeCalendar(chosenAirbnb);
                    currentCalendar = calendarPanel;
                    content.add(currentCalendar, BorderLayout.CENTER);
                    content.revalidate();
                    content.repaint();
                }
            }

        } else if (e.getActionCommand().equals("cancel")) { //Cancels reservations for a customer in a specific Airbnb
            Airbnb chosenAirbnb = null;
            if (propertyList.airbnbExists(propertyNameField.getText())) {
                for (int i = 0; i < propertyList.getProperties().size(); i++) {
                    if (propertyList.getProperties().get(i).getAirbnbName().equals(propertyNameField.getText())) {
                        chosenAirbnb = propertyList.getProperties().get(i);
                        break;
                    }
                }
                chosenAirbnb.cancelReservation(loginName);
                content.remove(currentCalendar);
                calendarPanel = makeCalendar(chosenAirbnb);
                currentCalendar = calendarPanel;
                content.add(currentCalendar, BorderLayout.CENTER);
                content.revalidate();
                content.repaint();
            }

        } else if (e.getActionCommand().equals("save")) { //Saves data to file
            try {
                jsonWriter.open();
                jsonWriter.write(propertyList);
                jsonWriter.close();
                System.out.println("Saved properties to " + JSON_STORE);
            } catch (FileNotFoundException f) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        } else if (e.getActionCommand().equals("load")) { //Loads data from file
            try {
                propertyList = jsonReader.read();
                System.out.println("Loaded from " + JSON_STORE);
            } catch (IOException f) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
            propertiesField.setText(propertyList.seeAllProperties().toString());
        }
    }
}
// Sources used:
// Provided links in edX
//https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html
//https://stackoverflow.com/questions/2501861/how-can-i-remove-a-jpanel-from-a-jframe
