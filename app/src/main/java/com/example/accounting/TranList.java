package com.example.accounting;

import java.io.File;
import java.util.Date;

public class TranList {

    public static int add_to_tranFile(File filename,  Transaction tranName){

        return 0;
    }

    public static Transaction accumulate(File filename, Transaction tranName, int accumCategory){
return null;
    }

    public static Transaction [] fetch_records_byItem(File filename ,String itemName){
        return null;
    }
    public static Transaction [] fetch_records(File filename ,String itemName , String accountName){
        return null;
    }
    public static Transaction [] fetch_records_byAccount(File filename ,String accountName){
        return null;
    }
    public static Transaction [] fetch_records_bydate(File filename , Date start_date, int timePeriod){
        return null;
    }

}
