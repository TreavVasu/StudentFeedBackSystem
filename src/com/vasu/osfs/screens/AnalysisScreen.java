package com.vasu.osfs.screens;

import com.vasu.osfs.services.TeachPannel;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.List;

public class AnalysisScreen {



    JFrame frame;
    JPanel panel;
    JLabel qLabel;
    JLabel keyLabel;
    JLabel avgLabel;
    JLabel stats;
    Font fontDefault;
    TeachPannel tp = new TeachPannel();
    String htmlop="<html><p align=center>";
    String htmlclo="</p></html>";
    String lineBreak="<br>";


    //Constructs the Screen
    public void createScreen(){
        frame = new JFrame("Response Analysis Screen");
        frame.setVisible(true);
        //frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(new Dimension(500,400));
        frame.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(400,350));
        panel.setLayout(null);


        fillPanel();
        placeElement();
        addElement();
    }

    private void addElement() {
        panel.add(keyLabel);
        panel.add(qLabel);
        panel.add(avgLabel);
        panel.add(stats);
        frame.add(panel);
        frame.pack();
    }

    private void placeElement() {
        //400w x 350h
        keyLabel.setBounds(25,25,350,30);
        qLabel.setBounds(25,75,350,75);
        avgLabel.setBounds(25,150,350,30);
        stats.setBounds(25,160,350,150);

        keyLabel.setHorizontalAlignment(JLabel.CENTER);
        qLabel.setHorizontalAlignment(JLabel.CENTER);
        avgLabel.setHorizontalAlignment(JLabel.CENTER);
        stats.setHorizontalAlignment(JLabel.CENTER);
        fontDefault = new Font("Roboto Light",Font.BOLD,15);

        keyLabel.setFont(new Font("Roboto Light",Font.BOLD,20));
        qLabel.setFont(new Font("Roboto Light",Font.BOLD,20));
        avgLabel.setFont(new Font("Roboto Light",Font.BOLD,20));
        stats.setFont(fontDefault);


    }

    //Get number of people for what number of response
    public void analysis(List<String> resp){

        int one=0,two=0,thee=0,four=0,five=0;

        for (String c:resp) {
            switch (c){
                case "1":
                    one++;
                    break;
                case "2":
                    two++;
                    break;
                case "3":
                    thee++;
                    break;
                case "4":
                    four++;
                    break;
                default:
                    five++;
                    break;
            }
        }
        stats.setText(
                        htmlop+
                "Students Rated One   : "+one +lineBreak+
                "Students Rated two   : "+two+lineBreak+
                "Students Rated three : "+thee+lineBreak+
                "Students Rated four  : "+four+lineBreak+
                "Students Rated five  : "+five
                        +htmlclo
        );
    }

    private void fillPanel(){
        qLabel      =   new JLabel("Question Label here?");
        keyLabel    =   new JLabel("Key Label here !");
        avgLabel    =   new JLabel("Average will show here.");
        stats       =   new JLabel("Stats Mentioned here !");
    }

    //Analysis
    public void doAnal(String key){
        try {
            qLabel.setText(htmlop+"Question asked:"+lineBreak+tp.loadQ(key)+htmlclo);
            keyLabel.setText(htmlop+"Key: "+key+htmlclo);
            List<String> response = tp.getResponse(key);

            //DecimalFormat df = new DecimalFormat("#.000");
            double sum = tp.sumResponse(response);
            BigDecimal bd = new BigDecimal(sum/response.size());
            bd=bd.round(new MathContext(3));
            double rounded = bd.doubleValue();
            sum = Math.round(sum/response.size());

            avgLabel.setText(htmlop+"Average: "+rounded+"/5"+htmlclo);
            analysis(response);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
