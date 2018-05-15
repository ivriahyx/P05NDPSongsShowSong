package com.example.a16023018.p05_ndpsongsshowsong;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EditActivity extends AppCompatActivity {

    EditText etIDEdit, etTitleEdit, etSingersEdit, etYearEdit;
    Button btnUpdate, btnDelete, btnCancel;
    RadioGroup rg;
    Song data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        etIDEdit = (EditText)findViewById(R.id.etIdEdit);
        etTitleEdit = (EditText)findViewById(R.id.etTitleEdit);
        etSingersEdit = (EditText)findViewById(R.id.etSingersEdit);
        etYearEdit = (EditText)findViewById(R.id.etYearEdit);

        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        btnCancel = (Button)findViewById(R.id.btnCancel);

        rg = (RadioGroup)findViewById(R.id.radioGroup);

            Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        etIDEdit.setText(data.getId()+"");

        etTitleEdit.setText(data.getTitle());
        etSingersEdit.setText(data.getSingers());
        etYearEdit.setText(data.getYear()+"");

        final int starsSelected = data.getStars();
        if(starsSelected == 5){
            rg.check(R.id.radioButton5);
        }else if(starsSelected == 4){
            rg.check(R.id.radioButton4);
        }else if(starsSelected == 3){
            rg.check(R.id.radioButton3);
        }else if(starsSelected == 2){
            rg.check(R.id.radioButton5);
        }else {
            rg.check(R.id.radioButton1);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                DBHelper dbh = new DBHelper(EditActivity.this);
                data.setTitle(etTitleEdit.getText().toString());
                data.setSingers(etSingersEdit.getText().toString());
                data.setYear(Integer.parseInt(etYearEdit.getText().toString()));

                int selectedButtonId = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)findViewById(selectedButtonId);
                int stars = Integer.parseInt(rb.getText().toString());
                data.setStars(stars);

                dbh.updateSong(data);
                dbh.close();
                finish();
                setResult(RESULT_OK, intent);

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteSong(data.getId());
                dbh.close();
                finish();
                setResult(RESULT_OK, intent);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            btnUpdate.performClick();
        }
    }
}
