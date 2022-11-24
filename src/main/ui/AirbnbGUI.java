package ui;

import model.Airbnb;
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
    private Box loginBox;
    private JTextField checkInField;
    private JTextField checkOutField;
    JPanel content;
    JPanel currentCalendar;
    String loginName;
    JTextField nameField;
    JTextField propertyNameField;
    JPanel calendarPanel;

    private static final String JSON_STORE = "./data/properties.json";  //Location to store saved data file
    private JsonWriter jsonWriter; //Writer
    private JsonReader jsonReader; //Reader

    public static void main(String[] args) {
        new AirbnbGUI();
    }

    /*
     * MODIFIES: this
     * EFFECTS: Sets up a JFrame with many components, which include panels, buttons, etc
     */
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public AirbnbGUI() {
        super("Airbnb GUI");

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        //Add 1 airbnb to start
        propertyList = new Properties();
        propertyList.addProperties(new Airbnb("House1"));

        //Main content panel
        content = new JPanel();
        content.setLayout(new BorderLayout());
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        content.setBorder(padding);

        //Panel that will contain the main buttons
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

        //Add action listeners to required buttons
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

        cancelBtn.setActionCommand("cancel");
        cancelBtn.addActionListener(this);

        quitBtn.setActionCommand("quit");
        quitBtn.addActionListener(this);

        saveBtn.setActionCommand("save");
        saveBtn.addActionListener(this);

        loadBtn.setActionCommand("load");
        loadBtn.addActionListener(this);

        //Create panel for the save,load, quit buttons
        JPanel rightSideButtons = new JPanel();
        rightSideButtons.setLayout(new FlowLayout());
        Box sideBox = Box.createVerticalBox();
        sideBox.add(saveBtn);
        sideBox.add(Box.createRigidArea(new Dimension(0, 10)));
        sideBox.add(loadBtn);
        sideBox.add(Box.createRigidArea(new Dimension(0, 10)));
        sideBox.add(quitBtn);
        rightSideButtons.add(sideBox);

        //Create panel to hold an image png
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("./data/AirbnbLogo.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        Image scaledImage = myPicture.getScaledInstance(250,75,Image.SCALE_DEFAULT);
        JLabel picLabel = new JLabel(new ImageIcon(scaledImage));

        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        imagePanel.add(picLabel);
        //Create HBox for login
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        loginBox = Box.createHorizontalBox();
        nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(200, 30));
        loginBox.setVisible(false);
        loginBox.add(nameField);
        loginBox.add(loginBtn);

        //VBox and Panel to hold buttons and login and image
        Box vertBox = Box.createVerticalBox();
        vertBox.add(imagePanel);
        vertBox.add(buttonsPanel);
        vertBox.add(loginBox);
        northPanel.add(vertBox);

        //Area at bottom to display properties at all times
        JLabel propertiesLabel = new JLabel("List of available Airbnbs");
        propertiesField = new JTextArea();
        propertiesField.setPreferredSize(new Dimension(1500, 50));
        propertiesField.setEditable(false);
        propertiesField.setText(propertyList.seeAllProperties().toString());
        propertiesField.setAlignmentX(Component.LEFT_ALIGNMENT);
        Box areaBox = Box.createVerticalBox();
        areaBox.add(propertiesLabel);
        areaBox.add(propertiesField);

        //Panel to add components on the left side
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

        //Create initial calendar panel
        calendarPanel = makeCalendar(propertyList.getProperties().get(0));
        currentCalendar = calendarPanel;
        currentCalendar.setVisible(false);

        //Add all panels in required locations main content panel
        content.add(currentCalendar, BorderLayout.CENTER);
        content.add(areaBox, BorderLayout.SOUTH);
        content.add(northPanel, BorderLayout.NORTH);
        content.add(rightSideButtons, BorderLayout.EAST);
        content.add(westPanel, BorderLayout.WEST);

        //
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
