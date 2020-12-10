package com.vasu.osfs.utilities;

import javax.swing.*;
import java.util.List;

public class MyThread extends Thread{
    public MyThread(String name){
        super(name);
    }

    public void run(int milliseconds){
        try{
            System.out.println(this.getName()+" Delaying and freezing screen");
            //System.out.println(this.getName()+":" +" Thread ready to sleep ");
            Thread.sleep(milliseconds);
        }catch (InterruptedException e){
            System.out.println(this.getName()+" Interrupted");
        }

        System.out.println(this.getName()+" Exiting & Changing Screen");
    }


}
