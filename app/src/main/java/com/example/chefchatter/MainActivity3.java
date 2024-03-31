package com.example.chefchatter;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import java.util.Calendar;
import android.os.Bundle;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener{
    private Button btnSelectDate;
    private Button btnRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btnSelectDate = findViewById(R.id.btnDateNaissanceMain3);
        btnRetour = findViewById(R.id.btnRetourMain3);
        btnRetour.setOnClickListener(this);

        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity3.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int selectedYear, int selectedMonth, int selectedDay) {
                                // Use the selected date (Note: Month is 0-indexed)
                                String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                            }
                        }, year, month, day);

                datePickerDialog.show();
            }
        });

    }


    @Override
    public void onClick(View v) {
        if(v == btnRetour){
            finish();
        }
    }
}