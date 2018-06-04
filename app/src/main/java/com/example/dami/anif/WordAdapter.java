package com.example.dami.anif;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dami on 07.06.2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    public WordAdapter(Context context, ArrayList<Word> words) {
        super(context, 0, words);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Word word = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.all_item, parent, false);
        }
        // Lookup view for data population
        TextView wKcl = (TextView) convertView.findViewById(R.id.kcl);
        TextView wProteins = (TextView) convertView.findViewById(R.id.proteins);
        TextView wCarbons = (TextView) convertView.findViewById(R.id.carbons);
        TextView wFats = (TextView) convertView.findViewById(R.id.fats);
        // Populate the data into the template view using the data object
        wKcl.setText(word.getStringKcl());
        wProteins.setText(word.getStringProteins());
        wCarbons.setText(word.getStringCarbons());
        wFats.setText(word.getStringFats());
        // Return the completed view to render on screen
        return convertView;
    }

}