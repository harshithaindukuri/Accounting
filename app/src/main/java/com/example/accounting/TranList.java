package com.example.accounting;

import static com.example.accounting.StaticData.main_list_read;

import android.os.Build;
import android.widget.Toast;

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
            main_list_read.add(tranName);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void accumulate() {
        StaticData.incomeQty = new double[StaticData.incomeItems.length];
        StaticData.incomeAmt = new double[StaticData.incomeItems.length];
        StaticData.expenseQty = new double[StaticData.expenseItems.length];
        StaticData.expenseAmt = new double[StaticData.expenseItems.length];

        for(int i =0;i<StaticData.incomeItems.length;i++){
            StaticData.incomeQty[i] = 0.0;
            StaticData.incomeAmt[i] = 0.0;
        }

        for(int i =0;i<StaticData.expenseItems.length;i++){
            StaticData.expenseQty[i] = 0.0;
            StaticData.expenseAmt[i] = 0.0;
        }
        int i ;
        for (Transaction t:StaticData.fetched_list
             ) {
            if(t.amount > 0){
                i = getIndex(t.itemName,'i');
                StaticData.incomeQty[i]  =  StaticData.incomeQty[i] + t.quantity;
                StaticData.incomeAmt[i]  =  StaticData.incomeAmt[i] + t.amount;
            }
            else{
                i = getIndex(t.itemName,'e');
                StaticData.expenseQty[i]  =  StaticData.expenseQty[i] + t.quantity;
                StaticData.expenseAmt[i]  =  StaticData.expenseAmt[i] + t.amount;
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void fetch_records_byRange(String accountName, LocalDate start_date, LocalDate end_date) {
        for (Transaction tr: main_list_read
             ) {
            if(tr.accountName.equals(accountName)
                    &&
              (tr.date_entered.isAfter(start_date) || tr.date_entered.isEqual(start_date))
                    &&
              (tr.date_entered.isBefore(end_date) || tr.date_entered.isEqual(end_date))) {
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
                    main_list_read.add(tr);
                    accum = new StringBuilder();
                }
            }
            fileReader.close();
        } catch(IOException e){
            e.printStackTrace();
        }

    }
public static int getIndex(String itemName, char listType){

        switch (listType){
            case 'i':
                for(int i = 0;i<StaticData.incomeItems.length;i++){
                  if(itemName.equals(StaticData.incomeItems[i])){
                   return i;
                  }
                }
                break;

            case 'e':
                for(int i = 0;i<StaticData.expenseItems.length;i++){
                    if(itemName.equals(StaticData.expenseItems[i])){
                        return i;
                    }
                }break;
        }
    return 0;
}
}

