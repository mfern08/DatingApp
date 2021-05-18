package com.example.datingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class SecondActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String name;
    private String occupation;
    private String info;
    private String age;
    private FragmentManager fManager;
    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        fManager = getSupportFragmentManager();

        Intent intent = getIntent();
        Bundle b = intent.getExtras();


        if (b != null) {
            if (b.containsKey((Constants.KEY_NAME))) {
                name = b.getString(Constants.KEY_NAME);
            }
            if (b.containsKey((Constants.KEY_AGE))) {
                age = b.getString(Constants.KEY_AGE);
            }
            if (b.containsKey((Constants.KEY_OCC))) {
                occupation = b.getString(Constants.KEY_OCC);
            }
            if (b.containsKey((Constants.KEY_INFO))) {
                info = b.getString(Constants.KEY_INFO);
            }
        }

        ProfileFragment fragment = new ProfileFragment();
        fragment.setAttach(new Attachment(name, age, info, occupation));

        FragmentTransaction transaction = fManager.beginTransaction();
        transaction.add(R.id.fragment_container, fragment, "firstFrag");
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_profile:
                ProfileFragment fragment = new ProfileFragment();
                fragment.setAttach(new Attachment(name, age, info, occupation));

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        fragment).commit();
                break;
            case R.id.nav_match:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MatchesFragment()).commit();
                break;
            case R.id.nav_settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static class Attachment{
        String name;
        String age;
        String info;
        String occupation;

        Attachment(String name, String age, String bio, String occupation){
            this.name = name;
            this.age = age;
            this.info = info;
            this.occupation = occupation;
        }
    }
}