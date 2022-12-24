package com.example.accounting;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;


import java.time.LocalDate;

import java.time.YearMonth;


public class MainActivity10 extends AppCompatActivity {
    TimeSpan tp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onStart() {
        super.onStart();
        if(StaticData.category.equals("EX")){
            generate_expense_report();
        }
        else{
            generate_pl_report();
        }
        generate_criteria(StaticData.type_view,StaticData.category);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void generate_criteria(String type, String category){
        long sub = 0;
        LocalDate firstOfMonth,lastOfMonth;
        YearMonth yearMonth = YearMonth.of( LocalDate.now().getYear(),LocalDate.now().getMonth());
        String day;
        switch (type){
            case "DISPLAY TODAY" : tp = new TimeSpan(LocalDate.now(),LocalDate.now()); break;
            case "DISPLAY THIS WEEK" :
                day = LocalDate.now().getDayOfWeek().toString();
                for(int i = 0;i<7;i++){
                    if(StaticData.days[i].equals(day)){
                        sub = StaticData.values[i];
                    }
                }
                tp = new TimeSpan(LocalDate.now(),LocalDate.now().minusDays(sub));
                                      break;
            case "DISPLAY LAST WEEK":
                day = LocalDate.now().getDayOfWeek().toString();
                for(int i = 0;i<7;i++){
                    if(StaticData.days[i].equals(day)){
                        sub = StaticData.values[i];
                    }
                }
                tp = new TimeSpan(LocalDate.now().minusDays(sub),LocalDate.now().minusDays(sub + 7));
                break;
            case "DISPLAY THIS MONTH":
                 firstOfMonth = yearMonth.atDay( 1 );
                 lastOfMonth = yearMonth.atEndOfMonth();
                tp = new TimeSpan(firstOfMonth,lastOfMonth);
                break;
            case "DISPLAY LAST MONTH":
                YearMonth yearMonth1 = yearMonth.minusMonths(1);
                firstOfMonth = yearMonth1.atDay( 1 );
                lastOfMonth = yearMonth1.atEndOfMonth();
                tp = new TimeSpan(firstOfMonth,lastOfMonth);
                break;
            case "LAST 3 MONTHS":
                YearMonth yearMonth2 = yearMonth.minusMonths(3);
                firstOfMonth = yearMonth2.atDay( 1 );
                lastOfMonth = yearMonth2.atEndOfMonth();
                tp = new TimeSpan(firstOfMonth,lastOfMonth);
                break;
            case "LAST 6 MONTHS":
                YearMonth yearMonth3 = yearMonth.minusMonths(6);
                firstOfMonth = yearMonth3.atDay( 1 );
                lastOfMonth = yearMonth3.atEndOfMonth();
                tp = new TimeSpan(firstOfMonth,lastOfMonth);
                break;
            case "DISPLAY 1 YEAR":
                yearMonth = YearMonth.of( LocalDate.now().getYear(),1);
                tp = new TimeSpan(yearMonth.atDay(1),LocalDate.now());
                break;

            case "DISPLAY ALL DATA":
                yearMonth = YearMonth.of( LocalDate.now().getYear(),1);
                YearMonth yearMonth4 = yearMonth.minusYears(5);
                tp = new TimeSpan(yearMonth4.atDay(1),LocalDate.now());
                break;
        }
    }

    public void generate_expense_report(){

    }

    public void generate_pl_report(){

    }
}