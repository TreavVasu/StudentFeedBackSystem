package com.vasu.osfs.screens;

import com.vasu.osfs.utilities.MyThread;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JFrame {

    //Constructs the Screen
    public SplashScreen() {

        ImageIcon teach = createImageIcon("../assets/classrooms.png", "");
        ImageIcon stud = createImageIcon("../assets/student.png", "");
        //3d positioning components
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setBackground(Color.BLACK);
        label.setBounds(50, 50, 200, 200);

        String WelcomeText = "<p align=center>Welcome to " +
                "Feedback System<br>" +
                "<br>Made by Vasu" +
                "<br>" + "19115097 CSE" +
                "</p>";
        JLabel label2 = new JLabel();
        label2.setOpaque(true);
        label2.setBackground(new Color(182, 25, 30, 242));
        label2.setText("<html>" + WelcomeText + "</html>");
        label2.setForeground(Color.white);
        label2.setHorizontalAlignment(JLabel.CENTER);
        label2.setBounds(100, 100, 200, 200);

        JLabel label3 = new JLabel();
        label3.setOpaque(true);
        label3.setBackground(Color.WHITE);
        label3.setBounds(150, 150, 200, 200);


        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 450, 500);


        layeredPane.add(label, Integer.valueOf(0));
        layeredPane.add(label2, Integer.valueOf(2));
        layeredPane.add(label3, Integer.valueOf(1));
        this.getContentPane().setBackground(new Color(120, 10, 150));
        this.setTitle("Student Feedback System");
        this.add(layeredPane);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(450, 500));
        this.setVisible(true);


        ExitnOpen();
    }

    //Pauses the Thread for next activity to continue
    private void ExitnOpen() {
        MyThread mt = new MyThread("ScreenThread");
        mt.run(2000);
        new LoginScren();
        dispose();
    }

    //Function for getting Image fails in Ubuntu by Standard Method
    //Taken from docs.java.com
    private ImageIcon createImageIcon(String s, String description) {
        java.net.URL imgURL = getClass().getResource(s);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + s);
            return null;
        }
    }

}
    


