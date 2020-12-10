package com.vasu.osfs.screens;

import com.vasu.osfs.utilities.commonFunction;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class TeacherHome{

    JFrame frame;
    JPanel panel;
    JLabel optionLabel;
    JLabel iconFeedBack;
    JLabel iconResponse;
    JLabel logoutLabel;
    JButton postQuestionButton;
    JButton viewResponseButton;
    commonFunction function = new commonFunction();
    EnterQuestionScreen eqs = new EnterQuestionScreen();
    AvailableResponseScreen ars = new AvailableResponseScreen();

    public void TeachHome(){
        frame = new JFrame("Teacher Home");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(new Dimension(500,400));
        frame.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(400,350));
        panel.setLayout(null);

        fillpanel();
        panel.add(logoutLabel);
        panel.add(optionLabel);
        panel.add(iconFeedBack);
        panel.add(postQuestionButton);
        panel.add(iconResponse);
        panel.add(viewResponseButton);
        frame.add(panel);
        //frame.pack();
    }

    ImageIcon feedback,response;
    private void fillpanel() {
        //400x550
        feedback= function.createImageIcon("../assets/classrooms.png","");
        response= function.createImageIcon("../assets/student.png","");
        iconFeedBack =new JLabel();
        iconResponse =new JLabel();
        iconResponse.setIcon(response);
        iconFeedBack.setIcon(feedback);
        optionLabel = new JLabel("Select any option to begin");
        postQuestionButton = new JButton("Post FeedBack Question");
        viewResponseButton = new JButton("View Previous Response");
        logoutLabel = new JLabel("Logout");

        postQuestionButton.addActionListener((o)->postQ());
        viewResponseButton.addActionListener((k)->viewResp());

        logoutLabel.setBounds(300,10,100,20);
        optionLabel.setBounds(100,50,200,20);
        iconFeedBack.setBounds(168,80,64,64);
        postQuestionButton.setBounds(50,160,300,40);
        iconResponse.setBounds(168,200,64,64);
        viewResponseButton.setBounds(50,275,300,40);
        actions();
    }

    //Logout Action
    public void actions(){
        logoutLabel.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new LoginScren();
                frame.dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                new LoginScren();
                frame.dispose();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
    }

    private void viewResp() {
        ars.createScreen();
        //frame.dispose();
    }

    private void postQ() {
        eqs.createScreen();
        //frame.dispose();
    }

    public void createScreen(){
        TeacherHome teacherHome = new TeacherHome();
        teacherHome.TeachHome();
    }
}
