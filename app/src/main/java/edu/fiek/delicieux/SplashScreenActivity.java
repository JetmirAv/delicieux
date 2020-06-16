package edu.fiek.delicieux;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreenActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fAuth = FirebaseAuth.getInstance();
        user = fAuth.getCurrentUser();

        Class startClass = user != null ? MainActivity.class : AuthActivity.class;

        Intent intent = new Intent(this, startClass);
        startActivity(intent);
        finish();
    }
}
