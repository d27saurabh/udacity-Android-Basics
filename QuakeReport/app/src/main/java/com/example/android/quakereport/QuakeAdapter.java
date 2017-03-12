package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Admin on 12-Mar-17.
 */
public class QuakeAdapter extends ArrayAdapter<Quake> {

    public QuakeAdapter(Activity activity, ArrayList<Quake> quakes) {
        super(activity, 0, quakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View quakeItem = convertView;
        if (quakeItem == null) {
            quakeItem = LayoutInflater.from(getContext()).inflate(
                    R.layout.quake_item, parent, false);
        }
        Quake quake = getItem(position);

        TextView mag = (TextView) quakeItem.findViewById(R.id.magnitude);
        mag.setText(quake.GetMag());

        TextView place = (TextView) quakeItem.findViewById(R.id.place);
        place.setText(quake.GetPlace());

        TextView date = (TextView) quakeItem.findViewById(R.id.date);
        date.setText(quake.GetDate());


        return quakeItem;
    }
}
