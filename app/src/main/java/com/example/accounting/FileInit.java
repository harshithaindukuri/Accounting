package com.example.accounting;

import android.content.Context;

import java.io.File;
import java.io.IOException;

public class FileInit {

    static public File account_file;
    static public File income_file;
    static public File expense_file;
    static public File daily_file;
    static public File weekly_file;
    static public File monthly_file;
    static public File yearly_file;


    public static void file_Initializer(Context context){
         account_file = new File(context.getFilesDir(),"accounts.txt");
         income_file = new File(context.getFilesDir(),"income_items.txt");
         expense_file = new File(context.getFilesDir(),"expense_items.txt");
         daily_file = new File(context.getFilesDir(),"daily_tran.txt");
        weekly_file = new File(context.getFilesDir(),"weekly_tran.txt");
        monthly_file = new File(context.getFilesDir(),"monthly_tran.txt");
        yearly_file = new File(context.getFilesDir(),"yearly_tran.txt");

        try {
            account_file.createNewFile();
            income_file.createNewFile();
            expense_file.createNewFile();
            daily_file.createNewFile();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


}
