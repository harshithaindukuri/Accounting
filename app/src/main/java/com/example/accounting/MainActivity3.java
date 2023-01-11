package com.example.accounting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity3 extends AppCompatActivity {
//does provide import and export options
File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "accounting");
    File daily_file = new File(file.getAbsolutePath(),"daily_file_ext.txt");
    File history_file = new File(file.getAbsolutePath(),"history_file_ext.txt");
    File income_file = new File(file.getAbsolutePath(),"income_file_ext.txt");
    File expense_file = new File(file.getAbsolutePath(),"expense_file_ext.txt");
    File account_file = new File(file.getAbsolutePath(),"account_file_ext.txt");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Button import_button, export_button;

        import_button = findViewById(R.id.button4);
        export_button = findViewById(R.id.button5);

        import_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // this will override app-specfic files from external storage
                if (!income_file.exists() &&
                        !expense_file.exists() &&
                        !account_file.exists() &&
                        !daily_file.exists() &&
                        !history_file.exists())
                {
                    Toast.makeText(MainActivity3.this, "DATA NOT FOUND", Toast.LENGTH_LONG).show();
                }
                else {
                    FileInit.copy_file(income_file,FileInit.income_file);
                    FileInit.copy_file(expense_file,FileInit.expense_file);
                    FileInit.copy_file(account_file,FileInit.account_file);
                    FileInit.copy_file(daily_file,FileInit.daily_file);
                    FileInit.copy_file(history_file,FileInit.history_file);
                    Toast.makeText(MainActivity3.this, "DATA IMPORTED SUCCESSFUL", Toast.LENGTH_LONG).show();
                }
            }
        });

        export_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //perform export of app-specific files to external storage
                // this will have wite - permission to external storage , so other apps can utilize
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "accounting");
                file.mkdir();
                try {
                    income_file.createNewFile();
                    expense_file.createNewFile();
                    account_file.createNewFile();
                    daily_file.createNewFile();
                    history_file.createNewFile();


                    FileInit.copy_file(FileInit.income_file,income_file);
                    FileInit.copy_file(FileInit.expense_file,expense_file);
                    FileInit.copy_file(FileInit.account_file,account_file);
                    FileInit.copy_file(FileInit.daily_file,daily_file);
                    FileInit.copy_file(FileInit.history_file,history_file);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity3.this, "DATA EXPORTED SUCCESSFUL", Toast.LENGTH_LONG).show();
            }
        });
    }
}