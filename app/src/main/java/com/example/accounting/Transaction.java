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
}
