package com.vasu.osfs.db;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class ReadWrite {


    //Function to append to file or create new multiple usage
    public void writeToFile(String fileName,String content){

        // Convert the string to a
        // byte array.
        String s = content+"\n";
        byte data[] = s.getBytes();
        Path p = Paths.get("./"+fileName);

        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(p, CREATE, APPEND))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }

    }

    //Use for reading Q
    public String readFromFile(String fileName){
        String Question="";
        Path file = Path.of("./"+fileName);
        try (InputStream in = Files.newInputStream(file);
             BufferedReader reader =
                     new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                Question+=line;
                Question+="\n";
                //System.out.println(line);
            }
        } catch (IOException x) {
            System.err.println(x);
        }

        return Question;
    }

    //To avoid double processing the output function to read contents of dir
    public List<String> dirRead(){
        List<String> keyList=new ArrayList<String>();
        int i=0;
        Path dir = Path.of("./");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path file: stream) {
                if (file.getFileName().toString().endsWith("feedback")){
                    System.out.println(file.getFileName());
                    //keysArray[i]=file.getFileName().toString();
                    keyList.add(file.getFileName().toString().replace(".feedback",""));
                    i++;
                }else {

                }

            }
        } catch (IOException | DirectoryIteratorException x) {
            // IOException can never be thrown by the iteration.
            // In this snippet, it can only be thrown by newDirectoryStream.
            System.err.println(x);
        }
        return keyList;
    }

    //To fetch output in List from dir
    public List<String> readResp(String fileName){
        String Question="";
        Path file = Path.of("./"+fileName);
        try (InputStream in = Files.newInputStream(file);
             BufferedReader reader =
                     new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                Question+=line;
                //System.out.println(line);
            }

        } catch (IOException x) {
            System.err.println(x);
        }

        List<String> abc= Arrays.asList(Question.split("`"));
        System.out.println(abc);
        return abc;
    }

}
