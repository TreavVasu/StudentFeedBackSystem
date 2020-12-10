package com.vasu.osfs.screens;

import com.vasu.osfs.db.ReadWrite;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class AvailableResponseScreen {

    JFrame frame;
    JPanel panel;
    JTree  tree;
    JScrollPane jScrollPane;
    JLabel infoLabel;
    JButton loader;
    String[] choices;
    AnalysisScreen analysisScreen= new AnalysisScreen();


    public void createScreen(){
        frame = new JFrame("Available Response Screen");
        frame.setVisible(true);
        //frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(new Dimension(500,400));
        frame.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(400,350));
        panel.setLayout(new FlowLayout());
        fillTree();
        fillPanel();
        functionsDone();
        addElement();
    }


    private void fillTree(){
        ReadWrite rd = new ReadWrite();
        choices = rd.dirRead().toArray(new String[0]);
        System.out.println("Fill Tree :Working!");
    }


    private void fillPanel() {

        infoLabel=new JLabel("Please select Q. to view Analysis: ");
        tree = new JTree(choices);
        jScrollPane = new JScrollPane(tree);
        jScrollPane.setPreferredSize(new Dimension(350,275));
        loader = new JButton("Load");

    }

    private void functionsDone(){
        loader.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    TreePath[] paths = tree.getSelectionPaths();
                    for (TreePath path:paths) {
                        System.out.println("Selected Node :"+path.getLastPathComponent());
                        analysisScreen.createScreen();
                        analysisScreen.doAnal(path.getLastPathComponent().toString());
                    }
                }catch (Exception err){
                    System.out.println(err.getMessage()+": Error! Select kar bhai::");
                }
            }
        });
    }



    private void addElement() {
        panel.add(infoLabel);
        panel.add(jScrollPane);
        panel.add(loader);
        frame.add(panel);
        frame.pack();
    }

//    public static void main(String[] args) {
//        AvailableResponseScreen avrs = new AvailableResponseScreen();
//        avrs.createScreen();
//    }

}
