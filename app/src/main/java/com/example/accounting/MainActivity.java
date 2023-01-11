package com.example.accounting;

import static com.example.accounting.StaticData.accounts;
import static com.example.accounting.StaticData.lastOptimizedDate;
import static com.example.accounting.StaticData.main_list_read;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.File;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int lines;
    SharedPreferences sharedPreferences;
    Button open_account_b, import_export_b;

    //show account books present and allows to create new account and do import and export from
    // external files
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FileInit.file_Initializer(getApplicationContext());

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        lastOptimizedDate = sharedPreferences.getString("commit_date", LocalDate.now().toString());

        boolean is_first_run = sharedPreferences.getBoolean("is_first_run", true);
        if (is_first_run) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("commit_date", LocalDate.now().toString());
            editor.putBoolean("is_first_run", false);
            editor.apply();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onStart() {
        super.onStart();
        // create main list from daily file

        main_list_read = new ArrayList<Transaction>();
        lines = TranList.read_file(FileInit.daily_file);
        FileInit.file_data_holder();
        if (lines > 100) {
            lines = FileInit.file_optimizer(FileInit.daily_file, 'W');
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("commit_date", LocalDate.now().toString());
            editor.apply();
            if (lines > 1000) {
                lines = FileInit.file_optimizer(FileInit.daily_file, 'M');
                editor.putString("commit_date", LocalDate.now().toString());
                editor.apply();
                if (lines > 10000) {
                    lines = FileInit.file_optimizer(FileInit.daily_file, 'Y');
                    editor.putString("commit_date", LocalDate.now().toString());
                    editor.apply();
                }
            }
            main_list_read = new ArrayList<Transaction>();
            lines = TranList.read_file(FileInit.daily_file);
        }

        LinearLayout linearLayout = findViewById(R.id.linear);
        linearLayout.setOrientation(LinearLayout.VERTICAL); // set orientation
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.removeAllViews();
        for (String account_each : accounts
        ) {
            Button btn = new Button(this);
            btn.setText(account_each);
            btn.setOnClickListener(this);
            linearLayout.addView(btn, params);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();

        open_account_b = findViewById(R.id.button3);
        import_export_b = findViewById(R.id.button2);
        open_account_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to open account activity
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(i);
            }
        });

        import_export_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to import export activity
                Intent i = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        StaticData.account_name = b.getText().toString();
        Intent i = new Intent(MainActivity.this, MainActivity5.class);
        startActivity(i);
    }
}
