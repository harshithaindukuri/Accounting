package com.example.accounting;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity9 extends AppCompatActivity {
// view reports from mainactivity5

    Spinner spinner_expense , spinner_pl;
    String expense_selected,pl_selected;
    Button exp_b , pl_b;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        // create main list from daily file
        TranList.read_file(FileInit.daily_file);
        //create incomeItems array
        ArrayList<String> fetchArray =  ItemList.fetch_array(FileInit.income_file);
        int i = 0;
        for (String s:fetchArray
             ) {
            StaticData.incomeItems[i++] = s;
        }
       //create expenseItems array
        fetchArray =  ItemList.fetch_array(FileInit.expense_file);
        i = 0;
        for (String s:fetchArray
        ) {
            StaticData.expenseItems[i++] = s;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        spinner_expense = findViewById(R.id.spinner7);
        spinner_expense.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                expense_selected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                expense_selected = parent.getItemAtPosition(0).toString();
            }
        });
        spinner_pl = findViewById(R.id.spinner8);
        spinner_pl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pl_selected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                pl_selected = parent.getItemAtPosition(0).toString();
            }
        });
        exp_b = findViewById(R.id.button11);
        pl_b = findViewById(R.id.button12);

        exp_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticData.type_view = expense_selected;
                StaticData.category = "EX";
                Toast.makeText(MainActivity9.this, StaticData.type_view, Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity9.this,MainActivity10.class);
                startActivity(i);
            }
        });

        pl_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaticData.type_view = pl_selected;
                StaticData.category = "PL";
                Toast.makeText(MainActivity9.this, StaticData.type_view, Toast.LENGTH_LONG).show();
                Intent i = new Intent(MainActivity9.this,MainActivity10.class);
                startActivity(i);
            }
        });


    }
}