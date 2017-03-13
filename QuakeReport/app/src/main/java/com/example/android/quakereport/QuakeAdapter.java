package com.example.android.quakereport;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by Admin on 12-Mar-17.
 */
public class QuakeAdapter extends ArrayAdapter<Quake> {

    private static final String LOCATION_SEPARATOR = " of ";

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


        // Location text split
        String wholePlace = quake.GetPlace();
        String primaryLocation;
        String locationOffset;
        if (wholePlace.contains(LOCATION_SEPARATOR)){
            String[] parts = wholePlace.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        }else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = wholePlace;
        }
        TextView place = (TextView) quakeItem.findViewById(R.id.place);
        TextView placeOffset = (TextView) quakeItem.findViewById(R.id.locationOffset);
        placeOffset.setText(locationOffset);
        place.setText(primaryLocation);

        // get time from Long - milisecs
        Date dateObject = new Date(quake.GetDate());
        //format it
        String formattedDate = formatDate(dateObject);
        // set it
        TextView date = (TextView) quakeItem.findViewById(R.id.date);
        date.setText(formattedDate);

        Date timeObject = new Date(quake.GetDate());
        TextView time = (TextView) quakeItem.findViewById(R.id.time);
        String formattedTime = formatTime(timeObject);
        time.setText(formattedTime);

        return quakeItem;
    }
    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
