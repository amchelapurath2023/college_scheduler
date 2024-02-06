package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.myapplication.ClassFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        // Set the default fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new ClassFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    if (item.getItemId() == R.id.nav_todo) {
                        selectedFragment = new ToDoFragment();
                    } else if (item.getItemId() == R.id.nav_class) {
                        selectedFragment = new ClassFragment();
                    } else if (item.getItemId() == R.id.nav_exam) {
                        selectedFragment = new ExamFragment();
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}
