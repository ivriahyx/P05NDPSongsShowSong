package com.example.a16023018.p05_ndpsongsshowsong;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSingers, etYear;
    RadioGroup rg;
    Button btnInsert, btnShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = (EditText)findViewById(R.id.etTitle);
        etSingers = (EditText)findViewById(R.id.etSingers);
        etYear = (EditText)findViewById(R.id.etYear);

        btnInsert = (Button)findViewById(R.id.btnInsert);
        btnShow = (Button)findViewById(R.id.btnShow);
        rg = (RadioGroup)findViewById(R.id.radioGroup);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedButtonId = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)findViewById(selectedButtonId);
                int stars = Integer.parseInt(rb.getText().toString());

                String data = etTitle.getText().toString();
                String singers = etSingers.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                DBHelper dbh = new DBHelper(MainActivity.this);
                dbh.insertNote(data,singers,year,stars);
                dbh.close();
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
/*
                if (row_affected != -1){
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
 */
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });

    }
}
