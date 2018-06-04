package com.example.dami.anif;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import com.example.dami.anif.Data.DishContract.DishEntry;
import com.example.dami.anif.Data.DishCursorAdapter;
import com.example.dami.anif.Data.DishDbHelper;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DishDbHelper mDbHelper = new DishDbHelper(getApplicationContext());
                SQLiteDatabase db = mDbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(DishEntry.COLUMN_NAME, "Toto");
                values.put(DishEntry.COLUMN_KCL, 12);
                values.put(DishEntry.COLUMN_GRAMS, 100);
                values.put(DishEntry.COLUMN_PROTEINS, 7);
                values.put(DishEntry.COLUMN_CARBOHYDRATES, 7);
                values.put(DishEntry.COLUMN_FATS, 7);
                values.put(DishEntry.COLUMN_IS_FAVORITE, 0);
                values.put(DishEntry.COLUMN_IS_IN_DAY, 0);
                db.insert(DishEntry.TABLE_NAME, null, values);

                values = new ContentValues();
                values.put(DishEntry.COLUMN_NAME, "Toto");
                values.put(DishEntry.COLUMN_KCL, 12);
                values.put(DishEntry.COLUMN_GRAMS, 100);
                values.put(DishEntry.COLUMN_PROTEINS, 7);
                values.put(DishEntry.COLUMN_CARBOHYDRATES, 7);
                values.put(DishEntry.COLUMN_FATS, 7);
                values.put(DishEntry.COLUMN_IS_FAVORITE, 1);
                values.put(DishEntry.COLUMN_IS_IN_DAY, 0);
                db.insert(DishEntry.TABLE_NAME, null, values);
                Snackbar.make(view, "Test Item Added", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_calorie_calculator) {
            Intent icc = new Intent(this, CalorieCalculatorActivity.class);
            startActivity(icc);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
}