package com.vasu.osfs.services;

import java.util.Scanner;


//Unused Class for testing

public class Home  {


    public void Screen(){
        //doSingIn();
        //String designation = (identity==1)?"Teacher":"Student";
        //System.out.println(">"+name+"\t"+designation);

//        switch (identity){
//            case 1:
//                TeacherOptions();
//                break;
//            case 2:
//                StudentOptions();
//                break;
//            default:
//                StudentOptions();
//        }

    }

    int Task;

    public void TeacherOptions(){
        System.out.println("Select Task:");
        System.out.println("1.Post Question");
        System.out.println("2.View Response");
        Scanner TaskSelect = new Scanner(System.in);
        Task = TaskSelect.nextInt();

        System.out.println("Task: "+Task);

    }
    public void StudentOptions(){
        System.out.println("Select Task:");
        System.out.println("1.Enter Code for Feedback: ");
        System.out.println("2.Exit");
        Scanner TaskSel = new Scanner(System.in);
        Task = TaskSel.nextInt();

        System.out.println("Task: "+Task);

    }



}
