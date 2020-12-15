package com.vasu.osfs.screens;

import com.vasu.osfs.services.FeedBack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentScreen extends JFrame {

    JFrame frame;
    JPanel panel;
    JLabel infoLabel;
    JLabel keyStatus;
    JButton enterKeyButton;
    JButton logoutButton;
    String info="Press the button to enter key and provide Feedback";
    String htmlOp="<html><center>";
    String htmlClo="</center></html>";
    String key;

    FeedBack feedBack = new FeedBack();
    SubmitReviewScreen submitReviewScreen = new SubmitReviewScreen();
    //Student Screen Begin
    public void createScreen(){
        frame = new JFrame("Student Home");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(new Dimension(500,400));
        frame.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(400,350));
        panel.setLayout(null);

        fillPanel();
        addElements();
    }

    private void addElements() {
        panel.add(infoLabel);
        panel.add(enterKeyButton);
        panel.add(keyStatus);
        panel.add(logoutButton);
        frame.add(panel);
    }

    private void destroy(){
        frame.dispose();
    }

    private void fillPanel() {
        infoLabel = new JLabel(htmlOp+info+htmlClo);
        infoLabel.setHorizontalAlignment(JLabel.CENTER);
        keyStatus = new JLabel();
        keyStatus.setHorizontalAlignment(JLabel.CENTER);
        enterKeyButton=new JButton("Enter Key");
        logoutButton=new JButton("Logout");
        infoLabel.setBounds(25,25,350,30);
        enterKeyButton.setBounds(100,75,200,40);
        keyStatus.setBounds(25,125,350,100);
        logoutButton.setBounds(100,250,200,40);
        actionsHere();
    }


    //On Button Click
    private void actionsHere()
    {
        enterKeyButton.addActionListener((ae)->keyEntryBox());
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginScren();
                frame.dispose();
            }
        });
    }
    //SScreen End

    //Dialogue Box for Key
    public void keyEntryBox(){
        key = JOptionPane.showInputDialog("Key:");
        checkKey(key);

        //System.out.println("key: "+key);
    }

    //If key Matched

    private void checkKey(String key) {
        String confirmationOk="Key Matched";
        String confirmationError="Wrong Key or provided Q. doesn't exists.";
        try{
        if (feedBack.confirmCode(key)){
            keyStatus.setText(htmlOp+confirmationOk+htmlClo);
            submitReviewScreen.storeKey(key);
            submitReviewScreen.createScreen();
            //Method to show solution screen
            //Thread to delay 2000ms
        }else {
            keyStatus.setText(htmlOp+confirmationError+htmlClo);
            //Nothing more
        }}
        catch (Exception e){
            System.out.println("Something going off"+e.getMessage());
        }
    }
    //End of key Matching

    public void setKeyStatus(){
        keyStatus.setText("Press Enter Key to add a different feedback!");
    }

    public void startScreen(){
        StudentScreen studentScreen = new StudentScreen();
        studentScreen.createScreen();
    }
}
