package ui;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {


    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public MenuPanel() {

        setLayout(new GridLayout(3, 4, 25, 50));

        JButton adminBtn = new JButton("Admin");
        JButton customerBtn = new JButton("Customer");


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

        add(fillerBtn1);
        add(adminBtn);
        add(customerBtn);
        add(fillerBtn2);
        add(addBtn);
        add(removeBtn);
        add(reservationInfoBtn1);
        add(backBtn1);
        add(reserveBtn);
        add(cancelBtn);
        add(reservationInfoBtn2);
        add(backBtn2);








    }
}
