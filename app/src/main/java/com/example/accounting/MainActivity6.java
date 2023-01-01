package com.example.accounting;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity6 extends AppCompatActivity {
    public  String name_selected ;
    Spinner spinner;
    ToggleButton type_tran;
    ImageButton add_item_button;
    Button add_to_account_bk;
    EditText quantity_et,amount_et;
    ArrayList<String> income_list,expense_list;
    double  multiplier;

//this page is for entering income/expense items and add to the files
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        add_item_button = findViewById(R.id.imageButton2);
        add_to_account_bk = findViewById(R.id.button9);
        quantity_et = findViewById(R.id.editTextNumberDecimal);
        amount_et = findViewById(R.id.editTextNumberDecimal2);
        income_list = ItemList.fetch_array(FileInit.income_file);
        expense_list = ItemList.fetch_array(FileInit.expense_file);
        type_tran = findViewById(R.id.toggleButton2);
        spinner = findViewById(R.id.spinner3);
    }

    @Override
    protected void onStart() {
        super.onStart();

        multiplier = +1;
        activate_name_spinner(income_list);

        type_tran.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                    multiplier = -1;
                    activate_name_spinner(expense_list);
                }
                else
                {
                    multiplier = 1;
                    activate_name_spinner(income_list);
                }
            }
        });

        add_item_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity6.this,MainActivity7.class);
                startActivity(i);
            }
        });

        add_to_account_bk.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                double quantity,amount;
                String temp = quantity_et.getText().toString();
                if(temp.isEmpty()){
                    quantity = 0.0 * multiplier;
                }
                else
                {
                    quantity = Double.parseDouble(temp) * multiplier;
                }

                temp = amount_et.getText().toString();
                if(temp.isEmpty()){
                    Toast.makeText(MainActivity6.this,"AMOUNT MUST BE ENTERED", Toast.LENGTH_SHORT).show();
                }
                else {
                    amount = Double.parseDouble(amount_et.getText().toString()) * multiplier;
                    Transaction new_tran = new Transaction(StaticData.account_name, name_selected, quantity, amount);
                    boolean added = TranList.add_to_tranFile(FileInit.daily_file, new_tran);
                    if (added) {
                        Toast.makeText(MainActivity6.this, "Added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity6.this, "Failed to Add", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        income_list = ItemList.fetch_array(FileInit.income_file);
        expense_list = ItemList.fetch_array(FileInit.expense_file);
        if(type_tran.isChecked()){
            multiplier = -1;
            activate_name_spinner(expense_list);
        }
        else
        {
            multiplier = +1;
            activate_name_spinner(income_list);
        }
        type_tran.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    multiplier = -1;
                    activate_name_spinner(expense_list);
                }
                else
                {
                    multiplier = +1;
                    activate_name_spinner(income_list);
                }
            }
        });

        add_item_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity6.this,MainActivity7.class);
                startActivity(i);
            }
        });

        add_to_account_bk.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                double quantity,amount;
                String temp = quantity_et.getText().toString();
                if(temp.isEmpty()){
                    quantity = 0.0;
                }
                else
                {
                    quantity = Double.parseDouble(temp) * multiplier;
                }

                temp = amount_et.getText().toString();
                if(temp.isEmpty() || Double.parseDouble(amount_et.getText().toString()) == 0){
                    Toast.makeText(MainActivity6.this,"AMOUNT MUST BE ENTERED", Toast.LENGTH_SHORT).show();
                }
                else {
                    amount = Double.parseDouble(amount_et.getText().toString()) * multiplier;
                    Transaction new_tran = new Transaction(StaticData.account_name, name_selected, quantity, amount);
                    boolean added = TranList.add_to_tranFile(FileInit.daily_file, new_tran);
                    if (added) {
                        Toast.makeText(MainActivity6.this, "Added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity6.this, "Failed to Add", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void activate_name_spinner(ArrayList<String> list){
        if(list.isEmpty()){
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            Toast.makeText(MainActivity6.this, "CLICK on + ICON ABOVE TO ADD ITEM", Toast.LENGTH_LONG).show();
        }
        else {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
            {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long arg3)
                {
                    name_selected = parent.getItemAtPosition(position).toString();
                }

                public void onNothingSelected(AdapterView<?> arg0)
                {
                    name_selected = arg0.getItemAtPosition(0).toString();
                }
            });
        }
    }

}