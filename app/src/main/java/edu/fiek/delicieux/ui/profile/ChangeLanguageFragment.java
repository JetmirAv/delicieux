package edu.fiek.delicieux.ui.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import edu.fiek.delicieux.R;


public class ChangeLanguageFragment extends Fragment {

    private static final String TAG = "MainActivity";

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_change_language);
//
//        ArrayList<String> names = new ArrayList<>();
//        names.add("Albanian");
//        names.add("Albanian");
//        names.add("Albanian");
//        names.add("Albanian");
//        names.add("Albanian");
//        names.add("Albanian");
//
//
//    }
//
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_change_language, container, false);
        return view;
    }

}