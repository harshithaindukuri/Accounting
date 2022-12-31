package com.example.accounting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ItemList {
    public static int add_to_file(File filename,String itemName){
        if(itemName.isEmpty()){
            return 1;
        }
        boolean itemFound = check_item(filename,itemName);
        if(itemFound){
            return 2;
        }
        else if(itemName.contains(",")){
            return 3;
        }
        try{
            FileWriter writer = new FileWriter(filename,true);
            writer.append(itemName);
            writer.append(',');
            writer.flush();
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
     return 0;
    }

    public static ArrayList<String> fetch_array(File filename){
        ArrayList<String> array_items = new ArrayList<String>();

        try {
            FileReader fileReader = new FileReader(filename);
            StringBuilder accum = new StringBuilder();
            while (fileReader.ready()) {
                char c = (char)fileReader.read();
                if(c != ',')
                accum.append(c);
                else {
                        array_items.add(accum.toString());
                        accum = new StringBuilder();
                    }
                }
            fileReader.close();
            } catch(IOException e){
            e.printStackTrace();
        }
        return array_items;
    }

    public static void copy_file(File filesrc, File filedes){
        FileReader fileReader ;
        FileWriter fileWriter ;
        try {
             fileReader = new FileReader(filesrc);
             fileWriter = new FileWriter(filedes);
            while (fileReader.ready()) {
                fileWriter.append((char)fileReader.read());
            }

            fileReader.close();
            fileWriter.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
        public static boolean check_item(File filename , String itemName){
        ArrayList<String> item_array;
        boolean item_found = false;
        item_array = fetch_array(filename);
        for (String item_single:item_array
             ) {
            if(itemName.equals(item_single)){
               item_found = true;
               break;
            }
        }
        return item_found;
    }
}
