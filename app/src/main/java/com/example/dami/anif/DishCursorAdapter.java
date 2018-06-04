package com.example.dami.anif.Data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dami.anif.R;
import com.example.dami.anif.Data.DishContract.DishEntry;
import android.widget.CursorAdapter;
/**
 * Created by dami on 11.06.2017.
 */

public class DishCursorAdapter extends CursorAdapter {
    public DishCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.all_item, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // Find fields to populate in inflated template
        TextView mName=(TextView) view.findViewById(R.id.name);
        TextView mKcl = (TextView) view.findViewById(R.id.kcl);
        TextView mGrams  = (TextView) view.findViewById(R.id.grams);
        TextView mProteins = (TextView) view.findViewById(R.id.proteins);
        TextView mCarbons = (TextView) view.findViewById(R.id.carbons);
        TextView mFats = (TextView) view.findViewById(R.id.fats);
        TextView mIsFavorite = (TextView) view.findViewById(R.id.isFavorites);

        // Extract properties from cursor
        String name = cursor.getString(cursor.getColumnIndexOrThrow(DishEntry.COLUMN_NAME));
        int kcl = cursor.getInt(cursor.getColumnIndexOrThrow(DishEntry.COLUMN_KCL));
        int grams = cursor.getInt(cursor.getColumnIndexOrThrow(DishEntry.COLUMN_GRAMS));
        int proteins = cursor.getInt(cursor.getColumnIndexOrThrow(DishEntry.COLUMN_PROTEINS));
        int carbohydrates = cursor.getInt(cursor.getColumnIndexOrThrow(DishEntry.COLUMN_CARBOHYDRATES));
        int fats = cursor.getInt(cursor.getColumnIndexOrThrow(DishEntry.COLUMN_FATS));
        int isFavorites = cursor.getInt(cursor.getColumnIndexOrThrow(DishEntry.COLUMN_IS_FAVORITE));//TODO rzutować na bool

        // Populate fields with extracted properties
        mName.setText(name);
        mKcl.setText(String.valueOf(kcl));//error
        mGrams.setText(String.valueOf(grams));
        mProteins.setText(String.valueOf(proteins));
        mCarbons.setText(String.valueOf(carbohydrates));
        mFats.setText(String.valueOf(fats));
        mIsFavorite.setText(String.valueOf(isFavorites));
    }
}