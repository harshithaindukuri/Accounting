package com.example.accounting;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity5 extends AppCompatActivity {
//this page shows options for entering transactions and view reports
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        Button new_tran, view_report;
        TextView account_tv = findViewById(R.id.textView9);
        CharSequence account_text = StaticData.account_name;
        account_tv.setText(account_text);

        new_tran = findViewById(R.id.button6);
        view_report = findViewById(R.id.button7);

        new_tran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this will go to new page to enter tran
                Toast.makeText(MainActivity5.this, StaticData.account_name, Toast.LENGTH_LONG).show();
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