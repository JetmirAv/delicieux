package edu.fiek.delicieux.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import edu.fiek.delicieux.MainActivity;
import edu.fiek.delicieux.R;


public class ChangeLanguageFragment extends Fragment {

    ImageView btnBack;
    TextView albtxt;
    TextView engtxt;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_change_language, container, false);

        btnBack = view.findViewById(R.id.backbtn);
        albtxt = view.findViewById(R.id.albId);
        engtxt = view.findViewById(R.id.engId);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).popBackStack();
            }
        });
        albtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewLocale("al");

            }
        });

        engtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewLocale("en");

            }
        });
        return view;
    }

    public void setLocale(String lang) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(lang.toLowerCase()));
        res.updateConfiguration(conf, dm);
    }


    private boolean setNewLocale(String language) {
        MainActivity.localeManager.setNewLocale(getContext(), language);

        Intent i = new Intent(getContext(), MainActivity.class);
        startActivity(i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));

        Toast.makeText(getContext(), "Language changed successfully", Toast.LENGTH_SHORT).show();
        return true;
    }

}