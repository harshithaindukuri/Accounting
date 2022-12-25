package com.example.accounting;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

public class TranList {

    public static boolean add_to_tranFile(File filename, Transaction tranName) {
        try {

            FileWriter fileWriter = new FileWriter(filename,true);
            String tranString = tranName.get_TranString();
            fileWriter.append(tranString);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void accumulate(ArrayList<Transaction> fetched_list) {

        for(int i =0;i<StaticData.incomeQty.length;i++){
            StaticData.incomeQty[i] = 0;
            StaticData.incomeAmt[i] = 0;
        }

        for(int i =0;i<StaticData.expenseQty.length;i++){
            StaticData.expenseQty[i] = 0;
            StaticData.expenseAmt[i] = 0;
        }
        int i = 0;
        for (Transaction t:StaticData.fetched_list
             ) {
            if(t.amount >= 0){
                StaticData.incomeQty[i]  =  StaticData.incomeQty[i] + t.quantity;
                StaticData.incomeAmt[i]  =  StaticData.incomeAmt[i] + t.amount;
                i+=1;
            }
            else{
                StaticData.expenseQty[i]  =  StaticData.expenseQty[i] - t.quantity;
                StaticData.expenseAmt[i]  =  StaticData.expenseAmt[i] - t.amount;
                i+=1;
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void fetch_records_byRange(String accountName, LocalDate start_date, LocalDate end_date) {
        for (Transaction tr:StaticData.main_list_read
             ) {
            if(tr.accountName.equals(accountName) && tr.date_entered.isAfter(start_date)
            && tr.date_entered.isBefore(end_date)){
                StaticData.fetched_list.add(tr);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void read_file(File filename){
         //read record by record
        // convert to transaction
        //append to arraylist
        Transaction tr;
        try {
            FileReader fileReader = new FileReader(filename);
            StringBuilder accum = new StringBuilder();
            while (fileReader.ready()) {
                char c = (char)fileReader.read();
                if(c != '\n')
                    accum.append(c);
                else {
                    tr = Transaction.create_transaction(accum.toString());
                    StaticData.main_list_read.add(tr);
                    accum = new StringBuilder();
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }


}

