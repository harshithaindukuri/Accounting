package com.example.accounting;

import static com.example.accounting.StaticData.expenseAmt;
import static com.example.accounting.StaticData.expenseItems;
import static com.example.accounting.StaticData.expenseQty;
import static com.example.accounting.StaticData.fetched_list;
import static com.example.accounting.StaticData.incomeAmt;
import static com.example.accounting.StaticData.incomeQty;
import static com.example.accounting.StaticData.main_list_read;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity9 extends AppCompatActivity {
// view reports from mainactivity5
    TimeSpan tp;

    Spinner spinner_expense , spinner_pl;
    String expense_selected,pl_selected;
    Button exp_b , pl_b;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        FileInit.file_data_holder();
    }

    @Override
    protected void onStart() {
        super.onStart();
        spinner_expense = findViewById(R.id.spinner7);
        spinner_pl = findViewById(R.id.spinner8);
        exp_b = findViewById(R.id.button11);
        pl_b = findViewById(R.id.button12);

    }

    @Override
    protected void onResume() {
        super.onResume();
        spinner_expense.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                expense_selected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                expense_selected = parent.getItemAtPosition(0).toString();
            }
        });
        spinner_pl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pl_selected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                pl_selected = parent.getItemAtPosition(0).toString();
            }
        });


        exp_b.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                StaticData.type_view = expense_selected;
                StaticData.category = "EX";
                TimeSpan ts1 = generate_criteria(StaticData.type_view);
                fetched_list = new ArrayList<Transaction>();
                TranList.fetch_records_byRange(StaticData.account_name,ts1.getStartDate(),ts1.getEndDate());
                TranList.accumulate();
                // generate_report(StaticData.category);
                Intent i = new Intent(MainActivity9.this,MainActivity8.class);
                startActivity(i);
            }
        });

        pl_b.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                StaticData.type_view = pl_selected;
                StaticData.category = "PL";

                TimeSpan ts1 = generate_criteria(StaticData.type_view);

                fetched_list = new ArrayList<Transaction>();
                TranList.fetch_records_byRange(StaticData.account_name,ts1.getStartDate(),ts1.getEndDate());
                TranList.accumulate();
                // generate_report(StaticData.category);
                Intent i = new Intent(MainActivity9.this,MainActivity8.class);
                startActivity(i);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public TimeSpan generate_criteria(String type){
        long sub = 0;
        LocalDate startDate,endDate;
        YearMonth yearMonth = YearMonth.of( LocalDate.now().getYear(),LocalDate.now().getMonth());
        String day;
        switch (type){
            case "DISPLAY TODAY" : tp = new TimeSpan(LocalDate.now(),LocalDate.now()); break;
            case "DISPLAY THIS WEEK" :
                day = LocalDate.now().getDayOfWeek().toString();
                for(int i = 0;i<7;i++){
                    if(StaticData.days[i].equals(day)){
                        sub = StaticData.values[i];
                        break;
                    }
                }
                tp = new TimeSpan(LocalDate.now().minusDays(sub),LocalDate.now());
                break;
            case "DISPLAY LAST WEEK":
                day = LocalDate.now().getDayOfWeek().toString();
                for(int i = 0;i<7;i++){
                    if(StaticData.days[i].equals(day)){
                        sub = StaticData.values[i];
                        break;
                    }
                }
                tp = new TimeSpan(LocalDate.now().minusDays(sub + 7),LocalDate.now().minusDays(sub));
                break;
            case "DISPLAY THIS MONTH":
                startDate = yearMonth.atDay( 1 );
                endDate = yearMonth.atEndOfMonth();
                tp = new TimeSpan(startDate,endDate);
                break;
            case "DISPLAY LAST MONTH":
                YearMonth yearMonth1 = yearMonth.minusMonths(1);
                startDate = yearMonth1.atDay( 1 );
                endDate = yearMonth1.atEndOfMonth();
                tp = new TimeSpan(startDate,endDate);
                break;
            case "LAST 3 MONTHS":
                YearMonth yearMonth2 = yearMonth.minusMonths(2);
                startDate = yearMonth2.atDay( 1 );
                endDate = yearMonth.atEndOfMonth();
                tp = new TimeSpan(startDate,endDate);
                break;
            case "LAST 6 MONTHS":
                YearMonth yearMonth3 = yearMonth.minusMonths(5);
                startDate = yearMonth3.atDay( 1 );
                endDate = yearMonth.atEndOfMonth();
                tp = new TimeSpan(startDate,endDate);
                break;
            case "DISPLAY 1 YEAR":
                yearMonth = YearMonth.of( LocalDate.now().getYear(),1);
                tp = new TimeSpan(yearMonth.atDay(1),LocalDate.now());
                break;

            case "DISPLAY ALL DATA":
                yearMonth = YearMonth.of( LocalDate.now().getYear(),1);
                YearMonth yearMonth4 = yearMonth.minusYears(5);
                tp = new TimeSpan(yearMonth4.atDay(1),LocalDate.now());
                break;
        }
        return tp;
    }

    public void generate_report(String category){
        int pageHeight = 1120;
        int pagewidth = 792;
        if(category.equals("EX")){
//PDF write
            // creating an object variable
            // for our PDF document.
            PdfDocument pdfDocument = new PdfDocument();

            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.accounting_logo);
            Bitmap scaledbmp = Bitmap.createScaledBitmap(bmp, 140, 140, false);
            // two variables for paint "paint" is used
            // for drawing shapes and we will use "title"
            // for adding text in our PDF file.
            Paint paint = new Paint();
            Paint title = new Paint();

            // we are adding page info to our PDF file
            // in which we will be passing our pageWidth,
            // pageHeight and number of pages and after that
            // we are calling it to create our PDF.
            PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(pagewidth, pageHeight, 1).create();

            // below line is used for setting
            // start page for our PDF file.
            PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);

            // creating a variable for canvas
            // from our page of PDF.
            Canvas canvas = myPage.getCanvas();

            // below line is used to draw our image on our PDF file.
            // the first parameter of our drawbitmap method is
            // our bitmap
            // second parameter is position from left
            // third parameter is position from top and last
            // one is our variable for paint.
            canvas.drawBitmap(scaledbmp, 56, 40, paint);

            // below line is used for adding typeface for
            // our text which we will be adding in our PDF file.
            title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

            // below line is used for setting text size
            // which we will be displaying in our PDF file.
            title.setTextSize(15);

            // below line is sued for setting color
            // of our text inside our PDF file.
            title.setColor(ContextCompat.getColor(this, R.color.purple_200));

            // below line is used to draw text in our PDF file.
            // the first parameter is our text, second parameter
            // is position from start, third parameter is position from top
            // and then we are passing our variable of paint which is title.
            canvas.drawText("EXPENSE REPORT.", 209, 100, title);
            canvas.drawText("ACCOUNT NAME,ITEM NAME,QUANTITY,AMOUNT,DATE ENTERED", 209, 80, title);

            // similarly we are creating another text and in this
            // we are aligning this text to center of our PDF file.
            title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            title.setColor(ContextCompat.getColor(this, R.color.purple_200));
            title.setTextSize(15);

            // below line is used for setting
            // our text to center of PDF.
            title.setTextAlign(Paint.Align.CENTER);

            canvas.drawText("This is sample document which we have created.", 396, 560, title);

            // after adding all attributes to our
            // PDF file we will be finishing our page.
            pdfDocument.finishPage(myPage);

            // below line is used to set the name of
            // our PDF file and its path.
          //*  File appSpecificExternalDir = new File(this.getExternalFilesDir(null), "GFG.pdf");
            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "accounting");
            file.mkdir();


            try {
                // after creating a file name we will
                // write our PDF file to that location.
                File file1 = new File(file.getAbsolutePath(),"example.pdf");
                file1.createNewFile();
                pdfDocument.writeTo(new FileOutputStream(file1));

                // below line is to print toast message
                // on completion of PDF generation.
                Toast.makeText(MainActivity9.this, "PDF file generated successfully.", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                // below line is used
                // to handle error
                e.printStackTrace();
            }
            // after storing our pdf to that
            // location we are closing our PDF file.
            pdfDocument.close();
        }
        else{
//PDF write
        }
    }


}