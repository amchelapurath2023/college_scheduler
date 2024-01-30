package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {

    SwitchCompat push;

    boolean switchState;
    SharedPreferences preferences;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        switchState = preferences.getBoolean("push", false);
        push = this.findViewById(R.id.push);
        push.setChecked(switchState);
        push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchState = !switchState;
                push.setChecked(switchState);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("push", switchState);
                editor.apply();
            }
        });
    }
}