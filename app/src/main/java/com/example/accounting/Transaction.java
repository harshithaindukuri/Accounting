package com.example.accounting;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;

public class Transaction {
    public String accountName;
    public String itemName;
    public double quantity;
    public double amount;
    public LocalDate date_entered ;

    @RequiresApi(api = Build.VERSION_CODES.O)
    Transaction(String accountName, String itemName, double quantity, double amount){
        this.accountName = accountName;
        this.itemName = itemName;
        this.quantity = quantity;
        this.amount = amount;
        this.date_entered = LocalDate.now();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    Transaction(String accountName, String itemName, double quantity, double amount, LocalDate date_entered){
        this.accountName = accountName;
        this.itemName = itemName;
        this.quantity = quantity;
        this.amount = amount;
        this.date_entered = date_entered;
    }


    public String get_TranString(){

        return this.accountName +
                ',' +
                this.itemName +
                ',' +
                this.quantity +
                ',' +
                this.amount +
                ',' +
                this.date_entered + '\n';  }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Transaction create_transaction(String tran){
       String [] tokens = tran.split(",",5);
         String accountName = tokens[0];
         String itemName = tokens[1];
         double quantity = Double.parseDouble(tokens[2]);
         double amount = Double.parseDouble(tokens[3]);
         LocalDate date_entered = LocalDate.parse(tokens[4]);
        return new Transaction(accountName,itemName,quantity,amount,date_entered);
    }
}
