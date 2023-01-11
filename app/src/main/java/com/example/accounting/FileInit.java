package com.example.accounting;

import static com.example.accounting.StaticData.accounts;
import static com.example.accounting.StaticData.expenseAmt;
import static com.example.accounting.StaticData.expenseItems;
import static com.example.accounting.StaticData.incomeItems;
import static com.example.accounting.StaticData.lastOptimizedDate;
import static com.example.accounting.StaticData.main_list_read;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;

public class FileInit {

    static public File account_file;
    static public File income_file;
    static public File expense_file;
    static public File daily_file;
    static public File history_file;
    public static ArrayList<Transaction> fixedSet = new ArrayList<>();
    public static ArrayList<LocalDate> fixedDates;

    public static void file_Initializer(Context context){
         account_file = new File(context.getFilesDir(),"accounts.txt");
         income_file = new File(context.getFilesDir(),"income_items.txt");
         expense_file = new File(context.getFilesDir(),"expense_items.txt");
         daily_file = new File(context.getFilesDir(),"daily_tran.txt");
        history_file = new File(context.getFilesDir(),"history_tran.txt");

        try {
            account_file.createNewFile();
            income_file.createNewFile();
            expense_file.createNewFile();
            daily_file.createNewFile();
            history_file.createNewFile();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void file_data_holder()
    {
        //create accounts array
        ArrayList<String> fetchArray =  ItemList.fetch_array(FileInit.account_file);
        int i = 0;
        StaticData.accounts = new String[fetchArray.size()];
        for (String s:fetchArray
        ) {
            StaticData.accounts[i++] = s;
        }

        //create incomeItems array
        fetchArray =  ItemList.fetch_array(FileInit.income_file);
        i = 0;
        StaticData.incomeItems = new String[fetchArray.size()];
        for (String s:fetchArray
        ) {
            StaticData.incomeItems[i++] = s;
        }
        //create expenseItems array
        fetchArray =  ItemList.fetch_array(FileInit.expense_file);
        i = 0;
        StaticData.expenseItems = new String[fetchArray.size()];
        for (String s:fetchArray
        ) {
            StaticData.expenseItems[i++] = s;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static int file_optimizer(File file, char criteria){

       int lines = 0;
        prepareFixedSet(LocalDate.parse(lastOptimizedDate),file,criteria);
        for (Transaction t:StaticData.main_list_read
             ) {
            for (Transaction f:fixedSet
                 ) {
                LocalDate end = f.date_entered.plus(8,ChronoUnit.DAYS);
                if(criteria == 'M'){
                    end = f.date_entered.plus(1,ChronoUnit.MONTHS);
                }
                else if(criteria == 'Y'){
                    end = f.date_entered.plus(1,ChronoUnit.YEARS);
                }
                if (f.accountName.equals(t.accountName) && f.itemName.equals(t.itemName) &&
                        t.date_entered.isBefore(end) && (t.date_entered.isAfter(f.date_entered) ||
                        t.date_entered.isEqual(f.date_entered))
                ) {

                    f.quantity = f.quantity + t.quantity;
                    f.amount = f.amount + t.amount;
                    break;
                }
            }
        }
        try {
            FileInit.copy_file(file,FileInit.history_file);
            FileWriter fileWriter = new FileWriter(file,false);
            fileWriter.append("");
            for (Transaction t:fixedSet
                 ) {
                if(t.amount == 0){
                    continue;
                }
                String tranString = t.get_TranString();
                fileWriter.append(tranString);
                lines++;
                fileWriter.flush();
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    return lines;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public  static void prepareFixedSet(LocalDate lastWriteFileDate, File file, char criteria){

        FileInit.file_data_holder();
        prepareDateSet(lastWriteFileDate, LocalDate.now() , criteria);

        for (String account : StaticData.accounts) {
            for (String incomeItem : incomeItems) {
                for (LocalDate date : fixedDates
                ) {
                    fixedSet.add(new Transaction(account, incomeItem, 0, 0, date));
                }
            }
            for (String expenseItem : expenseItems) {
                for (LocalDate date : fixedDates
                ) {
                    fixedSet.add(new Transaction(account, expenseItem, 0, 0, date));
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public  static void prepareDateSet(LocalDate startDate, LocalDate endDate , char criteria){
        LocalDate temp = startDate;
        fixedDates = new ArrayList<>();
        while(temp.isBefore(endDate) || temp.isEqual(endDate)){
            LocalDate local = temp;
            fixedDates.add(temp);
            if(criteria == 'W'){
                temp = local.plus(8,ChronoUnit.DAYS);
            }
            else if(criteria == 'M'){
                temp = local.plus(1,ChronoUnit.MONTHS);
            }
            else if(criteria == 'Y'){
                temp = local.plus(1,ChronoUnit.YEARS);
            }
            else{
                break;

            }
        }
    }

    public static void copy_file(File filesrc, File filedes){
        FileReader fileReader ;
        FileWriter fileWriter ;
        try {
            fileReader = new FileReader(filesrc);
            fileWriter = new FileWriter(filedes,true);
            while (fileReader.ready()) {
                fileWriter.append((char)fileReader.read());
            }
            fileReader.close();
            fileWriter.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
