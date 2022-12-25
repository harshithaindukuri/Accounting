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
        TimeSpan ts1 = generate_criteria(StaticData.type_view);
        TranList.fetch_records_byRange(StaticData.account_name,ts1.getStartDate(),ts1.getEndDate());
        TranList.accumulate(StaticData.fetched_list);
        generate_report(StaticData.category);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public TimeSpan generate_criteria(String type){
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
        return tp;
    }

    public void generate_report(String category){

        if(category.equals("EX")){
//PDF write
        }
        else{
//PDF write
        }
    }

}