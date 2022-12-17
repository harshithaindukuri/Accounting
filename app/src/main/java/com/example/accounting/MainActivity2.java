package com.example.accounting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity2 extends AppCompatActivity {
//open new account book page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        EditText open_account_name = findViewById(R.id.accountName);

        Button add_account = findViewById(R.id.button);
        add_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account_book_name = open_account_name.getText().toString();
                        int response = ItemList.add_to_file(FileInit.account_file,account_book_name);
                        switch (response) {
                            case 0: Toast.makeText(MainActivity2.this, "ACCOUNT CREATED", Toast.LENGTH_LONG).show();break;
                            case 1: Toast.makeText(MainActivity2.this, "ENTERED NAME IS EMPTY", Toast.LENGTH_LONG).show();break;
                            case 2: Toast.makeText(MainActivity2.this, "ACCOUNT ALREADY EXIST", Toast.LENGTH_LONG).show();break;
                        }
            }
        });
    }
}