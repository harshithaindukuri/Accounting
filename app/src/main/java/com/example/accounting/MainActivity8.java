package com.example.accounting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

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
            for (int i = 0; i < StaticData.incomeItems.length; i++) {
                TableRow tableRow = new TableRow(this);
                tableRow.setId(i);
                textView = new TextView(this);
                textView.setTextAppearance(this, R.style.fields1);
                textView.setText(StaticData.incomeItems[i]);
                tableRow.addView(textView);

                textView = new TextView(this);
                textView.setTextAppearance(this, R.style.fields1);
                textView.setText(String.valueOf(StaticData.incomeQty[i]));
                tableRow.addView(textView);

                textView = new TextView(this);
                textView.setTextAppearance(this, R.style.fields1);
                textView.setText(String.valueOf(StaticData.incomeAmt[i]));
                tableRow.addView(textView);

                tableLayout.addView(tableRow);
            }
        }
    }
}