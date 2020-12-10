package com.vasu.osfs.screens;

import com.vasu.osfs.utilities.commonFunction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class LoginScren extends JFrame {

    JLabel proPic;
    JLabel unameLabel;
    JLabel welcomeLabel;
    JButton loginButton;
    JTextField uname;
    JRadioButton jcT,jcS;
    ImageIcon profileImage;

    String welcomeMessage="Hello,Welcome ";
    String htmlOp="<html>";
    String htmlClo="</html>";

    String wMessage=htmlOp+welcomeMessage;
    commonFunction function = new commonFunction();

    LoginScren(){
        this.setTitle("HomeScreen- FeedBack System");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setSize(500,700);
        this.setResizable(true);
        setSize(new Dimension(850,580));
        this.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        //Main Pannel
        JPanel mainConPanel = new JPanel();
        mainConPanel.setPreferredSize(new Dimension(400,500));
        mainConPanel.setLayout(null);
        mainConPanel.setBackground(Color.WHITE);

        mainPanelCreateElements();

        //Display Pannel
        JPanel displayPanel = new JPanel();
        displayPanel.setPreferredSize(new Dimension(400,500));
        displayPanel.setLayout(null);
        displayPanel.setBackground(Color.LIGHT_GRAY);

        //displayPanel contents
        displayPanelCreateElements();
        displayPanel.add(proPic);
        displayPanel.add(welcomeLabel);
        displayPanel.add(loginButton);


        //uname.addActionListener(userTyped());
        uname.addKeyListener(userTyped());
        jcT.addActionListener((l)->clickedTeacherRadio());
        jcS.addActionListener((s)->clickedStudentRadio());
        loginButton.addActionListener((o)->pressStart());


        profileImage= function.createImageIcon("../assets/stud.png","Pro Pic");
        proPic.setIcon(profileImage);
        proPic.setHorizontalAlignment(JLabel.CENTER);

        mainConPanel.add(unameLabel);
        mainConPanel.add(uname);
        mainConPanel.add(jcT);
        mainConPanel.add(jcS);
        this.add(mainConPanel);
        this.add(displayPanel);
    }

    private void clickedTeacherRadio() {
        if (jcS.isSelected()){
            jcT.setEnabled(false);
        }else {
            jcT.setEnabled(true);
            jcS.setEnabled(false);
        }
    }

    private void clickedStudentRadio() {
        if (jcT.isSelected()){
            jcS.setEnabled(false);
        }else {
            jcS.setEnabled(true);
            jcT.setEnabled(false);
        }
    }

    public void disposeFrame(){
        this.dispose();
    }

    private KeyListener userTyped() {
        return new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                welcomeLabel.setText(wMessage+uname.getText()+htmlClo);
            }
        };
    }

    private void mainPanelCreateElements() {
        // mainPannel 400w x 500h
        unameLabel = new JLabel("Username");
        uname = new JTextField();
        jcT = new JRadioButton("Teacher");
        jcS = new JRadioButton("Student");

        unameLabel.setBounds(175,220,100,40);
        uname.setBounds(50,260,300,20);
        jcT.setBounds(50,300,100,20);
        jcS.setBounds(250,300,100,20);
        border();
    }

    private void displayPanelCreateElements() {
        //displayPannel 400w x 500h
        proPic = new JLabel();
        welcomeLabel = new JLabel();
        loginButton = new JButton("Start");
        welcomeLabel.setText(htmlOp+welcomeMessage+htmlClo);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        proPic.setBounds(50,20,300,300);
        welcomeLabel.setBounds(50,350,300,20);
        loginButton.setBounds(150,400,100,20);
    }


    private  void border(){
        uname.setBorder(BorderFactory.createCompoundBorder(
                uname
                        .getBorder(),
                BorderFactory
                        .createEmptyBorder(2,5,2,5)
        ));
        uname.setSize(300,25);
    }


    private void pressStart(){

        if (jcS.isSelected()){
            //Goto student screen
            StudentScreen studentScreen = new StudentScreen();
            studentScreen.createScreen();
            dispose();
        }else if (jcT.isSelected()){
            //Goto teacher screen
            TeacherHome teacherHome =  new TeacherHome();
            teacherHome.createScreen();
            dispose();
        }
    }



}
