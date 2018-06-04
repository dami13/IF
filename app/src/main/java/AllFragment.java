package com.example.dami.anif;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dami.anif.Data.DishCursorAdapter;
import com.example.dami.anif.Data.DishDbHelper;
import com.example.dami.anif.Data.DishContract.DishEntry;

import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

import static com.example.dami.anif.R.id.remove_from_favorites;

/**
 * Created by dami on 07.06.2017.
 */

public class AllFragment extends Fragment {
    //private View rootView;
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private DishDbHelper mDbHelper;
    private SQLiteDatabase db;
    public ListView listView;

    public AllFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_all, container, false);

        DishDbHelper mDbHelper = new DishDbHelper(getContext());
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create and/or open a database to read from it
        db = mDbHelper.getReadableDatabase();
        // Perform this raw SQL query "SELECT * FROM pets"
        // to get a Cursor that contains all rows from the pets table.
        Cursor cursor = db.rawQuery("SELECT * FROM " + DishEntry.TABLE_NAME, null);
        final ListView listView = (ListView) rootView.findViewById(R.id.list_view);

        DishCursorAdapter todoAdapter = new DishCursorAdapter(getActivity(), cursor);
        // Attach cursor adapter to the ListView
        listView.setAdapter(todoAdapter);
        registerForContextMenu(listView);

        //listeners
        listView.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DishDbHelper DbHelper = new DishDbHelper(getContext());
                SQLiteDatabase db = DbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(DishEntry.COLUMN_IS_IN_DAY, 1);//TODO upgrade

                db.update(DishEntry.TABLE_NAME, values, "_id=" + (position + 1), null);

                Snackbar.make(rootView, "Item " + position + " added to DAY", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }
        });

        return rootView;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.fragment_all_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int itemPosition = info.position;

        switch (item.getItemId()) {
            case R.id.add_to_favorites:
                mDbHelper = new DishDbHelper(getContext());
                db = mDbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(DishEntry.COLUMN_IS_FAVORITE, 1);//TODO upgrade

                db.update(DishEntry.TABLE_NAME, values, "_id=" + (itemPosition + 1), null);
                Toast.makeText(getContext(), "Item " + (itemPosition + 1) + " removed from DAY", Toast.LENGTH_SHORT).show();
                break;
            case R.id.edit:
                // use temp item
                break;
        }
        return super.onContextItemSelected(item);
    }
}
