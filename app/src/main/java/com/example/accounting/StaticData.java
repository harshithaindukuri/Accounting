package com.example.accounting;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class StaticData {
    public static String account_name;
    public static String type_view;
    public static String category;
    public static int [] values = {0,1,2,3,4,5,6};
    public static String [] days =
            {"SUNDAY","MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY"};
    public static ArrayList<Transaction> main_list_read;
    public static ArrayList<Transaction> fetched_list;
    public static String [] accounts;
    public static String [] incomeItems;
    public static String [] expenseItems;
    public static double [] incomeQty;
    public static double [] incomeAmt;
    public static double [] expenseQty;
    public static double [] expenseAmt;
    public static String lastOptimizedDate;



}
