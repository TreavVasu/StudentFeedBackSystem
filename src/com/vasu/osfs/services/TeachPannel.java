package com.vasu.osfs.services;

import com.vasu.osfs.db.ReadWrite;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;



//Unused Class for testing database working without ui check

public class TeachPannel extends ReadWrite{


    Scanner teach = new Scanner(System.in);
    String Question;
    String Key;
    String content;
    public void setQuestionnKeys() throws IOException {
        System.out.print("Enter the Question: ");
        Question = teach.nextLine();
        System.out.print("Generate a Key for Question: ");
        Key = teach.nextLine();
        //Storing Q in file
        content=saveQuestion(Question,Key);
        System.out.print(content);
    }

    public String saveQuestion(String question,String key){
        //Save a file as keyName and Question as data in it.
        writeToFile(key+".feedback",question);
        //Confirmation
        return readFromFile(key+".feedback");
    }

    public void loadQuestion(String key){
        //Read Q file's Q
        //Read all response from Key+R file and add them to get result
        //Avg most likely
        System.out.println(readFromFile(key+".feedback"));

    }

    public String loadQ(String key){
        return readFromFile(key+".feedback");
    }



    public int sumResponse(List<String> response){
        int sum=0;
        for (String ab:response) {
            sum+=Integer.parseInt(ab);
        }
        return sum;
    }


    public List<String> getResponse(String file){
        List<String> resp;
        double numberOfStudent;

        resp=readResp(file+".response");
        int sum = sumResponse(resp);
        numberOfStudent=resp.size();
        System.out.println("Sum: "+sum);
        System.out.println("Average: "+sum/numberOfStudent+"/5");
        return resp;
        }

    //Option 2 of Teacher Menu
    public void viewResponse(){
        List<String> keyList ;
        keyList =dirRead();
        System.out.println("Here's a list of all feedbacks asked:");
        for (String i:keyList) {
            System.out.println("Key: "+i);
        }
        //Load all keys

        String keyChose;
        System.out.print("Please type the key to open response sheet and analysis:");
        keyChose = teach.nextLine();
        //Select the question

        loadQuestion(keyChose);
        //Q loaded

        getResponse(keyChose);
        //Response loaded
    }

    public static void main(String[] args) throws IOException {

        TeachPannel teachPannel = new TeachPannel();
//        teachPannel.setQuestionnKeys();
        teachPannel.viewResponse();
    }
}
