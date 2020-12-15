package com.vasu.osfs.screens;

import com.vasu.osfs.db.ReadWrite;
import com.vasu.osfs.services.TeachPannel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class EnterQuestionScreen {

    JFrame frame;
    JPanel panel;
    JLabel keyAsk;
    JLabel questionLabel;
    JLabel wordCountLabel;
    JTextField keyAskField;
    JTextArea questionArea;
    JButton submitQButton;
    JButton exitButton;
    String wc ="Word Count: ";
    String htmlOp="<html><center>";
    String htmlClo="</center></html>";
    Border border;

    TeachPannel teachPannel = new TeachPannel();

    //Constructs the Screen
    public void createScreen(){

        frame = new JFrame("Question Screen");
        frame.setVisible(true);
        //frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(new Dimension(500,600));
        frame.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(400,550));
        panel.setLayout(null);

        fillPanel();
        placeElements();
        elementProp();
        addElements();
        frame.pack();
    }

    private void elementProp() {
        border = BorderFactory.createLineBorder(Color.orange,3);
        keyAskField.setBorder(border);
        questionArea.setBorder(border);
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        questionArea.setLineWrap(true);
        questionArea.addKeyListener(wordCounter());
        questionArea.setFont(new Font("Monospace Regular",Font.PLAIN,25));
        keyAskField.setFont(new Font("Monospace Regular",Font.BOLD,12));

    }

    //Counts the word
    private KeyListener wordCounter() {
        return new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                wordCountLabel.setText(htmlOp+wc+questionArea.getText().length()+"/184"+htmlClo);

                if (questionArea.getText().length()>184)
                    JOptionPane.showMessageDialog(null,"Limit Reached please shorten it!");

            }
        };
    }

    private void fillPanel() {
        keyAsk = new JLabel("Pease provide a suitable key for access: ");
        questionLabel = new JLabel("Enter your feedback question here:");
        wordCountLabel = new JLabel();
        keyAskField = new JTextField();
        questionArea = new JTextArea();
        submitQButton = new JButton("Submit");
        exitButton = new JButton("Exit");
        submitQButton.addActionListener((m)->bAction());
        //To close the screen
        exitButton.addActionListener((exittt)->frame.dispose());
    }

    //Execute and Check details on Button Press
    private void bAction(){
        ReadWrite rw = new ReadWrite();
        //teachPannel.saveQuestion(questionArea.getText(),key);
        if (keyAskField.getText().length()==0 || questionArea.getText().length()<10){
            JOptionPane.showMessageDialog(null,"Please fill the details properly!");
        }else {
            rw.writeToFile(keyAskField.getText()+".feedback",questionArea.getText());
            JOptionPane.showMessageDialog(null,"Entry of Question Successful :)");
            questionArea.setText("");
            keyAskField.setText("");
            wordCountLabel.setText("");
        }


    }

    private void placeElements() {
        //400x550

        keyAsk.setBounds(25,25,350,20);
        keyAskField.setBounds(25,55,350,30);
        questionLabel.setBounds(25,125,350,20);
        questionArea.setBounds(25,170,350,240);
        wordCountLabel.setBounds(25,420,200,10);
        submitQButton.setBounds(50,480,100,30);
        exitButton.setBounds(250,480,100,30);

    }

    private void addElements() {
        panel.add(keyAsk);
        panel.add(keyAskField);
        panel.add(questionLabel);
        panel.add(questionArea);
        panel.add(wordCountLabel);
        panel.add(submitQButton);
        panel.add(exitButton);
        frame.add(panel);

    }



}
