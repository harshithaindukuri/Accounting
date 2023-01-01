package com.example.accounting;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        TableLayout tableLayout = findViewById(R.id.tableLayout1);
        TextView textView ;
        if(StaticData.category.equals("EX")) {
            for(int i = 0;i<StaticData.expenseItems.length;i++) {
                TableRow tableRow = new TableRow(this);
                tableRow.setId(i);
                textView = new TextView(this);
                textView.setTextAppearance(this,R.style.fields1);
                textView.setText(StaticData.expenseItems[i]);
                tableRow.addView(textView);

                textView = new TextView(this);
                textView.setTextAppearance(this,R.style.fields1);
                textView.setText(String.valueOf(StaticData.expenseQty[i]));
                tableRow.addView(textView);

                textView = new TextView(this);
                textView.setTextAppearance(this,R.style.fields1);
                textView.setText(String.valueOf(StaticData.expenseAmt[i]));
                tableRow.addView(textView);
                tableLayout.addView(tableRow);
            }
        }
        else {

            double qty ,amt, total_amt = 0;
            ArrayList<String> matched_list = new ArrayList<>();

            for (int i = 0; i < StaticData.incomeItems.length; i++) {
                qty = StaticData.incomeQty[i];
                amt = StaticData.incomeAmt[i];
                for(int j = 0;j<StaticData.expenseItems.length;j++){
                    if(StaticData.incomeItems[i].equals(StaticData.expenseItems[j])){
                        qty = StaticData.incomeQty[i] + StaticData.expenseQty[j];
                        amt = StaticData.incomeAmt[i] + StaticData.expenseAmt[j];
                        matched_list.add(StaticData.incomeItems[i]);
                        break;
                    }
                }
                TableRow tableRow = new TableRow(this);
                tableRow.setId(i);
                textView = new TextView(this);
                textView.setTextAppearance(this, R.style.fields1);
                textView.setText(StaticData.incomeItems[i]);
                tableRow.addView(textView);

                textView = new TextView(this);
                textView.setTextAppearance(this, R.style.fields1);
                textView.setText(String.valueOf(qty));
                tableRow.addView(textView);

                textView = new TextView(this);
                textView.setTextAppearance(this, R.style.fields1);
                total_amt = amt + total_amt;
                textView.setText(String.valueOf(amt));
                tableRow.addView(textView);
                tableLayout.addView(tableRow);
            }
            for (int i = 0; i < StaticData.expenseItems.length; i++) {
                qty = StaticData.expenseQty[i];
                amt = StaticData.expenseAmt[i];
                if(!matched_list.contains(StaticData.expenseItems[i])) {
                    TableRow tableRow = new TableRow(this);
                    tableRow.setId(i);
                    textView = new TextView(this);
                    textView.setTextAppearance(this, R.style.fields1);
                    textView.setText(StaticData.expenseItems[i]);
                    tableRow.addView(textView);

                    textView = new TextView(this);
                    textView.setTextAppearance(this, R.style.fields1);
                    textView.setText(String.valueOf(qty));
                    tableRow.addView(textView);

                    textView = new TextView(this);
                    textView.setTextAppearance(this, R.style.fields1);
                    total_amt = amt + total_amt;
                    textView.setText(String.valueOf(amt));
                    tableRow.addView(textView);
                    tableLayout.addView(tableRow);
                }
            }

            TableRow tableRow = new TableRow(this);
            textView = new TextView(this);
            textView.setTextAppearance(this, R.style.fields1);
            textView.setText("  ");
            tableRow.addView(textView);
            tableLayout.addView(tableRow);

            tableRow = new TableRow(this);
            textView = new TextView(this);
            textView.setTextAppearance(this, R.style.fields1);
            String s = "TOTAL";
            textView.setText(s);
            tableRow.addView(textView);

            if(total_amt > 0){
                textView = new TextView(this);
                textView.setTextAppearance(this, R.style.fields1);
                s = "PROFIT";
                textView.setText(s);
                textView.setTextColor(getResources().getColor(R.color.green));
                tableRow.addView(textView);
            }
            else{
                textView = new TextView(this);
                textView.setTextAppearance(this, R.style.fields1);
                s = "LOSS";
                textView.setText(s);
                textView.setTextColor(Color.RED);
                tableRow.addView(textView);
            }
            textView = new TextView(this);
            textView.setTextAppearance(this, R.style.fields1);
            textView.setText(String.valueOf(total_amt));
            tableRow.addView(textView);

            tableLayout.addView(tableRow);

        }
    }
}