package com.example.a16023018.p05_ndpsongsshowsong;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Song> al;
    ArrayAdapter aa;
    Button btnShow5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lv = (ListView) this.findViewById(R.id.listv);
        btnShow5 = (Button)this.findViewById(R.id.btnShowSongs5);
        al = new ArrayList<Song>();
        aa = new SongArrayAdapter(this,R.layout.activity_listview,al);

        // Create the DBHelper object, passing in the
        // activity's Context
        DBHelper db = new DBHelper(SecondActivity.this);

        // Insert a task
        ArrayList<Song> data = db.getAllSongs();
        db.close();

        String txt = "";
        for (int i = 0; i < data.size(); i++) {
            Log.d("Database Content", i +". "+data.get(i).getId()+" "+data.get(i).getTitle()+" "+data.get(i).getSingers()+" "+data.get(i).getYear()+" "+data.get(i).getStars());
            al.add(new Song(data.get(i).getId(),data.get(i).getTitle(),data.get(i).getSingers(),data.get(i).getYear(),data.get(i).getStars()));
        }
        lv.setAdapter(aa);


        //Log.d("Arraylist",""+al.get(1).getId()+" "+al.get(1).getTitle()+" "+al.get(1).getSingers()+" "+al.get(1).getYear()+" "+al.get(1).getStars());

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SecondActivity.this,
                        EditActivity.class);
                Song song = al.get(position);
                int itemId = song.getId();
                String title = song.getTitle();
                String singers = song.getSingers();
                int year = song.getYear();
                int stars = song.getStars();

                Song target = new Song(itemId, title, singers, year, stars);
                i.putExtra("data",target);
                startActivityForResult(i, 9);

            }
        });

        btnShow5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                al.clear();
                al.addAll(dbh.getAllSongs(5));
                dbh.close();

                String txt = "";
                for (int i = 0; i< al.size(); i++){
                    Song tmp = al.get(i);

                }
                aa.notifyDataSetChanged();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            btnShow5.performClick();
        }
    }
}
