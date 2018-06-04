package com.example.dami.anif;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

import java.util.Timer;

import static android.R.attr.checked;
import static android.R.attr.maxHeight;
import static com.example.dami.anif.R.id.button_calorie_calculator_activity;
import static com.example.dami.anif.R.id.harris_benedict;
import static com.example.dami.anif.R.id.katch_mc_cardle;
import static com.example.dami.anif.R.id.mifflin_st_jeor;
import static com.example.dami.anif.R.id.weight_be_healthy;
import static com.example.dami.anif.R.id.weight_gain_weight;
import static com.example.dami.anif.R.id.weight_lose_weight;

public class CalorieCalculatorActivity extends AppCompatActivity {
    private final int MAN=0;//options for spinner_sex
    private final int WOMEN=1;
    private TextView mBmrKclTextView;
    private TextView mKclTextView;
    private EditText mAgeEditText;
    private EditText mHeightEditText;
    private EditText mWeightEditText;
    private EditText mWeightDifferenceEditText;
    private Spinner mSpinnerSex;
    private Spinner mSpinnerActivity;
    private Button mCalculateButton;
    private RadioButton mLoseWeightRadioButton;
    private RadioButton mMantainWeightRadioButton;
    private RadioButton mGainWeightRadioButton;
    private SharedPreferences mSharedPref;
    private RadioButton mHarrisBenedictRadioButton;
    private RadioButton mMifflinStJeorRadioButton;
    private RadioButton mKatchMcCardleRadioButton;
    private int mKcl;
    private int mBmrKcl;
    private int mAge;
    private int mWeight;
    private int mHeight;
    private double mMultiplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_calculator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //creating object
        mSpinnerSex = (Spinner) findViewById(R.id.sex_spinner);
        mSpinnerActivity = (Spinner) findViewById(R.id.activity_spinner);
        mAgeEditText =(EditText) findViewById(R.id.age_edit_text);
        mHeightEditText=(EditText) findViewById(R.id.height_edit_text);
        mWeightEditText=(EditText) findViewById(R.id.weight_edit_text);
        mWeightDifferenceEditText=(EditText) findViewById(R.id.weight_difference_edit_text);
        mCalculateButton = (Button) findViewById(button_calorie_calculator_activity);
        mLoseWeightRadioButton = (RadioButton) findViewById(weight_lose_weight);
        mMantainWeightRadioButton = (RadioButton) findViewById(weight_be_healthy);
        mGainWeightRadioButton = (RadioButton) findViewById(weight_gain_weight);
        mHarrisBenedictRadioButton = (RadioButton) findViewById(harris_benedict);
        mMifflinStJeorRadioButton = (RadioButton) findViewById(mifflin_st_jeor);
        mKatchMcCardleRadioButton = (RadioButton) findViewById(katch_mc_cardle);
        mBmrKclTextView=(TextView) findViewById(R.id.bmr_kcl_text_view);
        mKclTextView=(TextView) findViewById(R.id.kcl_text_view);

        //spinners

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sex_spinner, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mSpinnerSex.setAdapter(adapter);

        //Create a ArrayAdapter for activity_spinner
        ArrayAdapter<CharSequence> activityAdapter= ArrayAdapter.createFromResource(this, R.array.activity_spinner, android.R.layout.simple_spinner_item);
        activityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerActivity.setAdapter(activityAdapter);

        //setting preff
        mSharedPref = this.getPreferences(Context.MODE_PRIVATE);
        //mStringKcl=mKcl.getText().toString();

        //reading shared Preference
        mKcl=mSharedPref.getInt(String.valueOf(R.string.kcl), 0);
        mBmrKcl=mSharedPref.getInt(String.valueOf(R.string.bmr_kcl), 0);
        mAgeEditText.setText(mSharedPref.getString(String.valueOf(R.string.age), "0"));
        mHeightEditText.setText(mSharedPref.getString(String.valueOf(R.string.height), "0"));
        mWeightEditText.setText(mSharedPref.getString(String.valueOf(R.string.weight), "0"));
        mWeightDifferenceEditText.setText(mSharedPref.getString(String.valueOf(R.string.weight_difference), "0"));
        mSpinnerSex.setSelection(mSharedPref.getInt(String.valueOf(R.string.sex), 0));
        mSpinnerActivity.setSelection(mSharedPref.getInt(String.valueOf(R.string.activity), 0));
        mLoseWeightRadioButton.setChecked(mSharedPref.getBoolean(String.valueOf(R.string.lose_weight), false));
        mMantainWeightRadioButton.setChecked(mSharedPref.getBoolean(String.valueOf(R.string.healthy_weight), true));
        mGainWeightRadioButton.setChecked(mSharedPref.getBoolean(String.valueOf(R.string.gain_weight), false));
        mHarrisBenedictRadioButton.setChecked(mSharedPref.getBoolean(String.valueOf(R.string.harris_benedict), false));
        mMifflinStJeorRadioButton.setChecked(mSharedPref.getBoolean(String.valueOf(R.string.mifflin_st_jeor), true));
        mKatchMcCardleRadioButton.setChecked(mSharedPref.getBoolean(String.valueOf(R.string.katch_mc_cardle), false));

        //wyświetla zapotrzebowaniek BMR i kcl
        showResults(mBmrKcl, mKcl);

        //listener for button
        mCalculateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                confirm();
                finish();
            }
        });

        RadioGroup radioGrouFormula=(RadioGroup) findViewById(R.id.formula_radio_group);
        radioGrouFormula.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                showBmrKcl();
            }
        });

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.weight_radio_group);
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                showKcl();
            }
        });

        //listeners for editText
        mAgeEditText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                calculate();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        mHeightEditText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                calculate();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        mWeightEditText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                calculate();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        mWeightDifferenceEditText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                calculate();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
    }


    void initVariables(){
        mBmrKcl=0;
        mMultiplier=0;

        Log.v("CalorieCal", "start");
        if(mAgeEditText.getText().toString().matches("")){
            mAge=0;
        } else {
            mAge=Integer.parseInt(mAgeEditText.getText().toString());
        }

        if(mHeightEditText.getText().toString().matches("")){
            mHeight=0;
        } else {
            mHeight=Integer.parseInt(mHeightEditText.getText().toString());
        }

        if(mWeightEditText.getText().toString().matches("")){
            mWeight=0;
        } else {
            mWeight=Integer.parseInt(mWeightEditText.getText().toString());
        }

        Log.v("CalorieCal", "end");
    }

    boolean calculate(){
        initVariables();

        calculateBmrKcl();
        calculateKcl();

        showResults();

        return true;
    }
    boolean showResults(int bmrKcl, int kcl){
        showBmrKcl(bmrKcl);
        showKcl(kcl);

        return true;
    }

    boolean showBmrKcl(int bmrKcl){
        initVariables();
        calculateBmrKcl();
        mBmrKclTextView.setText(Integer.toString(mBmrKcl)+"  [Kcl]");

        return true;
    }

    boolean showKcl(int kcl){
        if(mLoseWeightRadioButton.isChecked())
            mKclTextView.setText(Integer.toString(kcl-500)+"  [Kcl]");
        else if(mMantainWeightRadioButton.isChecked())
            mKclTextView.setText(Integer.toString(kcl)+"  [Kcl]");
        else if(mGainWeightRadioButton.isChecked())
            mKclTextView.setText(Integer.toString(kcl+500)+"  [Kcl]");

        return true;
    }

    boolean showBmrKcl(){
        initVariables();
        calculateBmrKcl();
        mBmrKclTextView.setText(Integer.toString(mBmrKcl)+"  [Kcl]");

        return true;
    }

    boolean showKcl(){
        if(mLoseWeightRadioButton.isChecked())
            mKclTextView.setText(Integer.toString(mKcl-500)+"  [Kcl]");
        else if(mMantainWeightRadioButton.isChecked())
            mKclTextView.setText(Integer.toString(mKcl)+"  [Kcl]");
        else if(mGainWeightRadioButton.isChecked())
            mKclTextView.setText(Integer.toString(mKcl+500)+"  [Kcl]");

        return true;
    }

    boolean showResults(){
        showBmrKcl();
        showKcl();

        return true;
    }

    //masa ciała x 24 x współczynnik aktywności - wartość dla płci
    //0 gdy jesteś mężczyzną
    //150 gdy jesteś kobietą
    boolean calculateKcl(){
        switch(mSpinnerActivity.getSelectedItemPosition()){
            case 0:
                mMultiplier=1.2;
                break;
            case 1:
                mMultiplier=1.4;
                break;
            case 2:
                mMultiplier=1.6;
                break;
            case 3:
                mMultiplier=1.75;
                break;
            case 4:
                mMultiplier=2.0;
                break;
        }

        mKcl=(int)(mWeight*24*mMultiplier-((mSpinnerSex.getSelectedItemPosition()==MAN)? 0: 150));
        return true;
    }

    //oblicza zapotrzebowanie kcl i wyswietla
    boolean calculateBmrKcl() {
        if(mHarrisBenedictRadioButton.isChecked()){
            if(mSpinnerSex.getSelectedItemPosition()==MAN)
                mBmrKcl=harrisBenedictForMan(mWeight, mHeight, mAge);

            else if(mSpinnerSex.getSelectedItemPosition()==WOMEN)
                mBmrKcl=harrisBenedictForWoman(mWeight, mHeight, mAge);
        }
        else if(mMifflinStJeorRadioButton.isChecked()){
            if(mSpinnerSex.getSelectedItemPosition()==MAN)
                mBmrKcl=mifflinstStJeorForMan(mWeight, mHeight, mAge);

            else if(mSpinnerSex.getSelectedItemPosition()==WOMEN)
                mBmrKcl=mifflinstStJeorForWoman(mWeight, mHeight, mAge);
        }
        else if(mKatchMcCardleRadioButton.isChecked()){
            if(mSpinnerSex.getSelectedItemPosition()==MAN)
                mBmrKcl=katchMcCardleForMan(mWeight, mHeight, mAge);

            else if(mSpinnerSex.getSelectedItemPosition()==WOMEN)
                mBmrKcl=katchMcCardleForWoman(mWeight, mHeight, mAge);
        }
        return true;
    }

    //Harris-Benedict
    //Mężczyźni: 66.5 + (13.75 * waga) + (5.003 * wzrost) - (6.775 * wiek)
    int harrisBenedictForMan(int weight, int height, int age){
        return (int)(66.5+(13.75*weight)+(5.003*height)-(6.775*age));
    }

    //Harris-Benedict
    //Kobiety: 655.1 + (9.563 * waga) + (1.85 * wzrost)-(4.676 * wiek)
    int harrisBenedictForWoman(int weight, int height, int age){
        return (int)(655.1+(9.563*weight)+(1.85*height)-(4.676*age));
    }

    //Mifflin-St Jeor
    //Mężczyźni: 10 * waga + 6,25 * wzrost - 5 * wiek + 5
    int mifflinstStJeorForMan(int weight, int height, int age){
        return (int)((10*weight)+(6.25*height)-(5*age)+5);
    }

    //Mifflin-St Jeor
    //Kobiety: 10 * waga + 6,25 * wzrost - 5 * wiek - 161
    int mifflinstStJeorForWoman(int weight, int height, int age){
        return (int)((10*weight)+(6.25*height)-(5*age)-161);
    }

    //Katch-McCardle
    //Mężczyźni i kobiety: 21,6 * Waga bez tłuszczu + 370
    //gdzie Waga bez tłuszczu = Waga - (procentowa zawartość tkanki tłuszczowej * Waga)
    int katchMcCardleForMan(int weight, int height, int age){
        return (int)(66.5+(13.75*weight)+(5.003*height)-(6.775*age));
    }

    //Katch-McCardle
    //Mężczyźni i kobiety: 21,6 * Waga bez tłuszczu + 370
    //gdzie Waga bez tłuszczu = Waga - (procentowa zawartość tkanki tłuszczowej * Waga)
    int katchMcCardleForWoman(int weight, int height, int age){
        return (int)(655.1+(9.563*weight)+(1.85*height)-(4.676*age));
    }

    void confirm(){
        //obliczanie zapotrzebowania Kcl
        calculate();

        // zapisywanie
        SharedPreferences.Editor editor=mSharedPref.edit();
        editor.putInt(String.valueOf(R.string.kcl), mKcl);
        editor.putInt(String.valueOf(R.string.bmr_kcl), mBmrKcl);
        editor.putString(String.valueOf(R.string.age), mAgeEditText.getText().toString());
        editor.putString(String.valueOf(R.string.height), mHeightEditText.getText().toString());
        editor.putString(String.valueOf(R.string.weight), mWeightEditText.getText().toString());
        editor.putString(String.valueOf(R.string.weight_difference), mWeightDifferenceEditText.getText().toString());
        editor.putInt(String.valueOf(R.string.sex), mSpinnerSex.getSelectedItemPosition());
        editor.putInt(String.valueOf(R.string.activity), mSpinnerActivity.getSelectedItemPosition());
        editor.putBoolean(String.valueOf(R.string.lose_weight), mLoseWeightRadioButton.isChecked());
        editor.putBoolean(String.valueOf(R.string.healthy_weight), mMantainWeightRadioButton.isChecked());
        editor.putBoolean(String.valueOf(R.string.gain_weight), mGainWeightRadioButton.isChecked());
        editor.putBoolean(String.valueOf(R.string.harris_benedict), mHarrisBenedictRadioButton.isChecked());
        editor.putBoolean(String.valueOf(R.string.mifflin_st_jeor), mMifflinStJeorRadioButton.isChecked());
        editor.putBoolean(String.valueOf(R.string.katch_mc_cardle), mKatchMcCardleRadioButton.isChecked());
        editor.commit();

    }

}