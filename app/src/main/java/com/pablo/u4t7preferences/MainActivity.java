package com.pablo.u4t7preferences;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import java.util.prefs.BackingStoreException;
import java.util.prefs.NodeChangeListener;
import java.util.prefs.PreferenceChangeListener;
import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {

    private final String MYPREFS = "MyPrefs";
    private final String MYDEFAULTPREFS = "MyDefaultPrefs";
    private EditText etPlayerName;
    private Spinner spinnerLevel;
    private EditText etScore;
    private Button btQuit;
    private ConstraintLayout constraintLayout;
    private Spinner spinnerColor;
    private RadioGroup rgGeneral;
    private Bundle bundle;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setUI();
    }

    private void setUI(){
        etPlayerName = findViewById(R.id.etPlayerName);

        spinnerLevel = findViewById(R.id.spinnerLevel);
        ArrayAdapter<CharSequence> sppinerLevelAdapter = ArrayAdapter.createFromResource(this,R.array.levels,android.R.layout.simple_spinner_item);

        sppinerLevelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevel.setAdapter(sppinerLevelAdapter);

        etScore= findViewById(R.id.etScore);
        checkBox= findViewById(R.id.checkBox1);
        constraintLayout = findViewById(R.id.constrait);
        spinnerColor = findViewById(R.id.spinnerColor);
        ArrayAdapter<CharSequence> sppinerColorAdapter = ArrayAdapter.createFromResource(this,R.array.colors,android.R.layout.simple_spinner_item);

        spinnerColor.setAdapter(sppinerColorAdapter);
        spinnerColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String color = spinnerColor.getSelectedItem().toString();
                background(color);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                background("White");
            }
        });
        rgGeneral= findViewById(R.id.rgGeneral);

        btQuit = findViewById(R.id.btQuit);
        btQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences myPreferences = getSharedPreferences(MYPREFS,MODE_PRIVATE);

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);

        SharedPreferences sharedDefaultPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editorDefaultPreferences = myPreferences.edit();
        editorDefaultPreferences.putString("PlayerName", etPlayerName.getText().toString());
        editorDefaultPreferences.putInt("Level", spinnerLevel.getSelectedItemPosition());
        editorDefaultPreferences.putInt("Score", Integer.parseInt(etScore.getText().toString()));
        editorDefaultPreferences.putInt("Color",spinnerColor.getSelectedItemPosition());
        editorDefaultPreferences.putInt("Difficulty",rgGeneral.getCheckedRadioButtonId());
        editorDefaultPreferences.putBoolean("ActiveSound",checkBox.isChecked());
        editorDefaultPreferences.commit();



    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences myPreferences = getSharedPreferences(MYPREFS, MODE_PRIVATE);

        etPlayerName.setText(myPreferences.getString("PlayerName", "unknown"));
        spinnerLevel.setSelection(myPreferences.getInt("Level", 0));
        etScore.setText(String.valueOf(myPreferences.getInt("Score", 0)));
        spinnerColor.setSelection(myPreferences.getInt("Color", 0));
        rgGeneral.check(myPreferences.getInt("Difficulty", 0));
        checkBox.setChecked(myPreferences.getBoolean("ActiveSound",true));

    }

    private void background(String color){
        switch (color){
            case "Orange":
                constraintLayout.setBackgroundColor(Color.rgb(255,200,0));
                break;
            case "Yellow":
                constraintLayout.setBackgroundColor(Color.rgb(255,255,0));
                break;
            case "White":
                constraintLayout.setBackgroundColor(Color.rgb(255,255,255));
                break;
            case "Pink":
                constraintLayout.setBackgroundColor(Color.rgb(255,175,175));
                break;
            case "Blue":
                constraintLayout.setBackgroundColor(Color.rgb(3,252,236));
        }

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        etPlayerName.setText(savedInstanceState.getString("PlayerName"));
        etScore.setText(savedInstanceState.getString("Score"));
        spinnerLevel.setSelection(savedInstanceState.getInt("Level"));
        spinnerColor.setSelection(savedInstanceState.getInt("Color"));
        checkBox.setChecked(savedInstanceState.getBoolean("SondMusic"));



    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("PlayerName",etPlayerName.getText().toString());
        outState.putString("Score",etScore.getText().toString());
        outState.putInt("Level",spinnerLevel.getSelectedItemPosition());
        outState.putInt("Color",spinnerColor.getSelectedItemPosition());
        outState.putBoolean("SondMusic",checkBox.isChecked());


    }

}