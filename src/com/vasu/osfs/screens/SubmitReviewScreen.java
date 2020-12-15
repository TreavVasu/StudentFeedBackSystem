package com.vasu.osfs.screens;

import com.vasu.osfs.services.FeedBack;
import com.vasu.osfs.utilities.MyThread;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class SubmitReviewScreen {
    JFrame frame;
    JPanel panel;
    JLabel qLabel;
    JLabel instructionLabel;
    JSlider slider;
    JButton submitButton;
    JTextField experinceTF;
    String htmlOp="<html><center>";
    String htmlClo="</center></html>";
    String key;

    MyThread mt = new MyThread("Slowing down");
    FeedBack feedBack = new FeedBack();

    //Constructs the Screen
    public void createScreen(){
        frame = new JFrame("Student Home");
        frame.setVisible(true);
        //frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(new Dimension(500,400));
        frame.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(400,350));
        panel.setLayout(null);


        fillPanel();
        sliderProp();
        rateAction();
        addElements();
    }

    private void sliderProp() {
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setPaintTrack(true);
    }

    private void addElements() {
        panel.add(qLabel);
        panel.add(slider);
        panel.add(instructionLabel);
        panel.add(experinceTF);
        panel.add(submitButton);
        frame.add(panel);
    }

    private void fillPanel() {
        qLabel=new JLabel();
        instructionLabel = new JLabel("Rate value from 0-5: ");
        slider=new JSlider(JSlider.HORIZONTAL,0,5,3);
        experinceTF =new JTextField("3");
        submitButton=new JButton("Submit");

        qLabel.setBounds(25,25,350,100);
        slider.setBounds(25,100,350,100);
        instructionLabel.setBounds(25,220,250,30);
        experinceTF.setBounds(280,220,50,30);
        submitButton.setBounds(100,300,200,40);
        qLabel.setText(
                htmlOp
                +getQuestion(key)
                +htmlClo
        );
    }

    //Load Q function
    public String getQuestion(String key){
        return feedBack.getQuestion(key+".feedback");
    }

    //store for review storage
    public void storeKey(String code){
        key=code;
    }


    public void rateAction(){
        experinceTF.addKeyListener(changeSlider());
        slider.addChangeListener(changeTF());
        submitButton.addActionListener((ae->taskDone()));
    }

    //TaskDone Method
    private void taskDone() {
        JOptionPane.showMessageDialog(
                null,
                "\t    Yeah! FeedBack Done.",
                "Status",
                JOptionPane.PLAIN_MESSAGE);
        feedBack.storeResponse(key,slider.getValue());
        frame.dispose();

    }

    //Slider here
    private ChangeListener changeTF() {
        return new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                experinceTF.setText(""+slider.getValue());
            }
        };
    }

    //Handles slider and input irregularity
    private KeyListener changeSlider() {
        return new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                //mt.run(500);
                if (experinceTF.getText().isEmpty()){
                    System.out.println("wait...");
                }else {
                    int k;
                    try {
                        k = Integer.parseInt(experinceTF.getText());
                        slider.setValue(k);
                    }catch (Exception a){
                        System.out.println("Let user correct his mistake...");
                    }

                }
            }
        };
    }

    private void destroy(){
        frame.dispose();
    }

//    public static void main(String[] args) {
//        SubmitReviewScreen srs = new SubmitReviewScreen();
//        srs.createScreen();
//    }
}
