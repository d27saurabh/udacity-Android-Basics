package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 13-Jan-17.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int colorItemRSRCid;

    public WordAdapter(Activity activity, ArrayList<Word> words, int color) {
        super(activity, 0, words);
        colorItemRSRCid = color;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Word currentWord = getItem(position);

        TextView textView1 = (TextView) listItem.findViewById(R.id.text1);
        textView1.setText(currentWord.getMiwokTranslation());

        TextView textView2 = (TextView) listItem.findViewById(R.id.text2);
        textView2.setText(currentWord.getDefaultTranslation());

        //set img
        ImageView img = (ImageView) listItem.findViewById(R.id.img);
        if (currentWord.hasImage()) {
            img.setImageResource(currentWord.getIMGrsrcId());
            img.setVisibility(View.VISIBLE); // because the view gets reused from adapter
        }else
            img.setVisibility(View.GONE); // invisible with not taking space in layout

        //text layout set color
        View txtContainer = listItem.findViewById(R.id.textColor);
        int chosenColor = ContextCompat.getColor(getContext(), colorItemRSRCid);
        txtContainer.setBackgroundColor(chosenColor);

        return listItem;
    }
}
