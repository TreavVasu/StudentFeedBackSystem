package com.vasu.osfs.services;

import com.vasu.osfs.db.ReadWrite;

import java.util.List;
import java.util.Scanner;

public class FeedBack extends ReadWrite {

    String OTCode;
    int Resp;
    String sub;

    //Takes code
    public void EnterCode(){
        System.out.print("Enter Key to Give FeedBack:");
        Scanner Code = new Scanner(System.in);
        OTCode = Code.nextLine();
        System.out.println("Your Exam Code OTC is :"+OTCode);

        if (confirmCode(OTCode)){
            System.out.println("OK! This FeedBack is available to give in :)");
            //Show Question Execute Here and Ask to respond
            int response = submitResponse(OTCode);

            System.out.println("Your Response: "+response);
            System.out.print("Confirm to submit Y/n: ");
            sub = Code.nextLine();

            if (sub!="n"){
                storeResponse(OTCode,response);

                System.out.println("Yeah! Response Accepted");
                System.out.println("Exit!");
            }else {
                System.out.println("Exit!");
            }
        }else {
            System.out.println("Sorry!Wrong Code or FeedBack doesn't exists :(");
            //Show Exit Screen / Home Screen
        }
    }

    //Matches it.
    public boolean confirmCode(String code){
        //Confirm if there is a file named as Key or Code
        //If not return True or flase
        List<String> keysAvail;
        keysAvail = dirRead();
        for (String key:keysAvail) {
            System.out.println("key:"+key);
            if (code.contains(key)){
                System.out.println("Match Found");
                return true;
            }
        }
        System.out.println("Match Not Found");
        return false;

    }


    public String getQuestion(String key){
        //JDBC OR File implementation here to change
        //Can use SQL or
        //Can use Key as fileQ name
        //return  Q to put in display
        return readFromFile(key);
    }

    public void storeResponse(String key,int Response){
        //Key.R as filename and Response Stored
        String response =Response+"`";
        writeToFile(key+".response",response);
    }

    public int submitResponse(String OTCode){
        System.out.println(getQuestion(OTCode+".feedback"));
        System.out.print("Enter from 1-5:");
        Scanner resp = new Scanner(System.in);
        Resp = resp.nextInt();
        while (Resp>5 && Resp<0){
            System.out.println("Enter Valid Response:");
            Resp = resp.nextInt();
        }
        return Resp;
    }


    public static void main(String[] args) {
    FeedBack feedBack = new FeedBack();
    feedBack.EnterCode();
    }

}
