package com.example.accounting;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;

public class MainActivity5 extends AppCompatActivity {
    Button new_tran, view_report;
    ScrollView scrollView;
    TableLayout tableLayout;
    TextView display_tran;
    TableRow tableRow;
    TextView account_tv;
    //this page shows options for entering transactions and view reports
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onStart() {
        super.onStart();
        account_tv = findViewById(R.id.textView9);
        CharSequence account_text = StaticData.account_name;
        account_tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        account_tv.setText(account_text);

        new_tran = findViewById(R.id.button6);
        view_report = findViewById(R.id.button7);
        scrollView = findViewById(R.id.scrollView2);
        tableLayout = new TableLayout(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();
        scrollView.removeAllViews();
        scrollView.addView(tableLayout);
        display_tran = new TextView(this);
        String heading = "  " + LocalDate.now().toString() + " --- Today's Transactions";
        display_tran.setText(heading);
        display_tran.setTextAppearance(R.style.heading2);
        tableRow = new TableRow(this);
        tableRow.addView(display_tran);
        tableLayout.addView(tableRow);

        for (Transaction t:StaticData.main_list_read
        ){
            if(t.date_entered.equals(LocalDate.now()) && t.accountName.equals(StaticData.account_name)) {
                display_tran = new TextView(this);
                display_tran.setText(t.displayable_string());
                display_tran.setTextAppearance(R.style.fields1);
                tableRow = new TableRow(this);
                tableRow.addView(display_tran);
                tableLayout.addView(tableRow);
            }
        }


        new_tran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this will go to new page to enter tran
                Intent i = new Intent(MainActivity5.this,MainActivity6.class);
                startActivity(i);
            }
        });

        view_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this will view reports
                Intent i = new Intent(MainActivity5.this,MainActivity9.class);
                startActivity(i);
            }
        });
    }
}