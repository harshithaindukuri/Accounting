package com.example.accounting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.File;

public class MainActivity7 extends AppCompatActivity {
    public EditText item_name;
    public Spinner spinner;
    ToggleButton type_tran;
    String  unit_selected , add_name;
    Button add_name_button;
    File goToFile = FileInit.income_file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
    }

    @Override
    protected void onStart() {
        super.onStart();
        item_name = findViewById(R.id.editTextTextPersonName2);
        type_tran = findViewById(R.id.toggleButton);
    }

    @Override
    protected void onResume() {
        super.onResume();
        type_tran.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    goToFile = FileInit.expense_file;
                }
                else
                {
                    goToFile = FileInit.income_file;
                }
            }
        });
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long arg3)
            {
                unit_selected = parent.getItemAtPosition(position).toString();
            }

            public void onNothingSelected(AdapterView<?> arg0)
            {
                unit_selected = arg0.getItemAtPosition(3).toString();
            }
        });

        add_name_button = findViewById(R.id.button10);
        add_name_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = item_name.getText().toString();
                if(name.isEmpty()){
                    Toast.makeText(MainActivity7.this, "PLEASE ENTER ITEM" + name, Toast.LENGTH_LONG).show();
                }
                else {
                    add_name = name.toUpperCase() + '-' + unit_selected;
                    int response = ItemList.add_to_file(goToFile, add_name);
                    switch (response) {
                        case 0:
                            Toast.makeText(MainActivity7.this, "ITEM-UNIT CREATED" + name, Toast.LENGTH_LONG).show();
                            break;
                        case 2:
                            Toast.makeText(MainActivity7.this, "ALREADY EXIST", Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            }
        });
    }
}