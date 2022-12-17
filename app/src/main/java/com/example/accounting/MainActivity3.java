package com.example.accounting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity3 extends AppCompatActivity {
//does provide import and export options
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
                Intent i = new Intent(MainActivity3.this,MainActivity4.class);
                startActivity(i);
            }
        });

        export_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //perform export of app-specific files to external storage
                // this will have wite - permission to external storage , so other apps can utilize
            }
        });
    }
}