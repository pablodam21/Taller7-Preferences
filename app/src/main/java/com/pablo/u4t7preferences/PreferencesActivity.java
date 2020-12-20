package com.pablo.u4t7preferences;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class PreferencesActivity extends AppCompatActivity {

    private TextView textInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        setUI();
    }
    @SuppressLint("SetTextI18n")
    public void setUI(){
        textInfo = findViewById(R.id.tvInfo);


        Bundle bundle = this.getIntent().getExtras();
        String playerName = bundle.getString("PlayerName");
        int level = bundle.getInt("Level");
        int score = bundle.getInt("Socre");
        int color = bundle.getInt("Color");
        int difficulty = bundle.getInt("Difficulty");

        textInfo.setText("PlayerName: " + playerName+"\n"+
                        "Level: "+level+"\n"+
                        "Score: "+score+"\n"+
                        "Color: "+color+"\n"+
                        "Difficulty"+difficulty);

    }
}