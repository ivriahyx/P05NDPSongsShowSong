package com.example.a16023018.p05_ndpsongsshowsong;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class SongArrayAdapter extends ArrayAdapter<Song> {
    private Context context;
    private ArrayList<Song> songs;
    //int resource;
    private ImageView iv1, iv2, iv3, iv4, iv5, ivLogo;
    private TextView tvYear, tvTitle, tvSingers;

    public SongArrayAdapter(Context context, int resource, ArrayList<Song> songs) {
        super(context, resource, songs);
        this.songs=songs;
        // Store Context object as we would need to use it later
        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.activity_listview, parent, false);

        //Match the UI components with Java variables
        tvYear = (TextView)rowView.findViewById(R.id.tvYear);
        tvSingers = (TextView)rowView.findViewById(R.id.tvSingers);
        tvTitle = (TextView)rowView.findViewById(R.id.tvTitle);

        iv1=(ImageView)rowView.findViewById(R.id.imageView1star);
        iv2=(ImageView)rowView.findViewById(R.id.imageView2star);
        iv3=(ImageView)rowView.findViewById(R.id.imageView3star);
        iv4=(ImageView)rowView.findViewById(R.id.imageView4star);
        iv5=(ImageView)rowView.findViewById(R.id.imageView5star);

        Song current = songs.get(position);

        tvYear.setText(current.getYear()+"");
        tvSingers.setText(current.getSingers());
        tvTitle.setText(current.getTitle());
        int stars = current.getStars();

        //Check if the property for starts >= 5, if so, "light" up the stars

		switch (current.getStars()){
            case 5:iv5.setImageResource(android.R.drawable.btn_star_big_on);
            case 4:iv4.setImageResource(android.R.drawable.btn_star_big_on);
            case 3:iv3.setImageResource(android.R.drawable.btn_star_big_on);
            case 2:iv2.setImageResource(android.R.drawable.btn_star_big_on);
            case 1:iv1.setImageResource(android.R.drawable.btn_star_big_on);
            default:;
        }

        return rowView;
    }
}
