package com.example.accounting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
//show account books present and allows to create new account and do import and export from
    // external files
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FileInit.file_Initializer(getApplicationContext());
        Button open_account_b,import_export_b;
        open_account_b = findViewById(R.id.button3);
        import_export_b = findViewById(R.id.button2);

        ArrayList<String> account_list = ItemList.fetch_array(FileInit.account_file);
        LinearLayout linearLayout = findViewById(R.id.linear);
        linearLayout.setOrientation(LinearLayout.VERTICAL); // set orientation
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        for (String account_each:account_list
             ) {
            Button btn = new Button(this);
            btn.setText(account_each);
            btn.setOnClickListener(this);
            linearLayout.addView(btn, params);
        }

        open_account_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go to open account activity
                Intent i = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(i);
            }
        });

        import_export_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to import export activity
                Intent i = new Intent(MainActivity.this,MainActivity3.class);
                startActivity(i);
            }
        });
    }
    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        StaticData.account_name = b.getText().toString();
        Intent i = new Intent(MainActivity.this,MainActivity5.class);
        startActivity(i);
    }
}