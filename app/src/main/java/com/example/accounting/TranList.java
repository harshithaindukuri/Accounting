package com.example.accounting;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;

public class TranList {

    public static Transaction accum_rec;
    public static ArrayList<Transaction> fetched_records;
    public static ArrayList<Transaction> accumulated_records = new ArrayList<Transaction>();

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
    public static ArrayList<Transaction> accumulate(File filename, String accountName, LocalDate startDate,LocalDate endDate) {

                fetched_records = fetch_records_byRange(filename, accountName,startDate,endDate);
                if (fetched_records == null) {
                    return null;
                } else {
                    for (Transaction fetch_each : fetched_records
                    ) {
                        accum_rec = getRecord(accumulated_records, fetch_each.accountName);
                        if (accum_rec == null) {
                            accumulated_records.add(fetch_each);
                        } else {
                            accum_rec.quantity = accum_rec.quantity + fetch_each.quantity;
                            accum_rec.amount = accum_rec.amount + fetch_each.amount;
                        }
                    }
                }
        return accumulated_records;
    }

    public static ArrayList<Transaction> fetch_records_byRange(File filename, String accountName, LocalDate start_date,LocalDate end_date) {
        return null;
    }

    public static Transaction getRecord(ArrayList<Transaction> accum_records, String accountName) {

        for (Transaction accum_each : accum_records
        ) {
            if (accum_each.accountName.equals(accountName)) {
                return accum_each;
            }
        }
        return null;
    }
}

