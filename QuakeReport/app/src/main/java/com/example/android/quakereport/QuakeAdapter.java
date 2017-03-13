package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
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
        // current EarthQuake
        Quake quake = getItem(position);

        //------------------
        // MAGNITUDE format "0.0" and set
        TextView mag = (TextView) quakeItem.findViewById(R.id.magnitude);
        mag.setText(formatMagnitude(quake.GetMag()));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) mag.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(quake.GetMag());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // ------------------
        // LOCATION text split
        String wholePlace = quake.GetPlace();
        String primaryLocation;
        String locationOffset;
        if (wholePlace.contains(LOCATION_SEPARATOR)) {
            String[] parts = wholePlace.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = wholePlace;
        }
        TextView place = (TextView) quakeItem.findViewById(R.id.place);
        TextView placeOffset = (TextView) quakeItem.findViewById(R.id.locationOffset);
        placeOffset.setText(locationOffset);
        place.setText(primaryLocation);

        // -------------------
        // get TIME from Long - milisecs
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

    private String formatMagnitude(double magnitude) {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }

    private int getMagnitudeColor (double magnitude){
        int magnitudeColorResourceId;
        int magFloor = (int) Math.floor(magnitude);
        switch (magFloor){
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
