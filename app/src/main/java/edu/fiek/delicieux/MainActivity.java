package edu.fiek.delicieux;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.fiek.delicieux.helpers.LocaleManager;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "App";
    public static LocaleManager localeManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        BottomNavigationView bnv = findViewById(R.id.navigation);
        NavigationUI.setupWithNavController(bnv, navController);


    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }

    @Override
    protected void attachBaseContext(Context base) {
        localeManager = new LocaleManager(base);
        super.attachBaseContext(localeManager.setLocale(base));
        Log.d(TAG, "attachBaseContext");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        localeManager.setLocale(this);
        Log.d(TAG, "onConfigurationChanged: " + newConfig.locale.getLanguage());
    }
}