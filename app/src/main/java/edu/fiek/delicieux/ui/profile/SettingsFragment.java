package edu.fiek.delicieux.ui.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import edu.fiek.delicieux.R;
import edu.fiek.delicieux.SplashScreenActivity;

public class SettingsFragment extends Fragment {

    ImageView btnBack;
    TextView Password;
    TextView Language;
    TextView LogOut;
    FirebaseAuth fAuth;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_settings, container, false);

        btnBack = view.findViewById(R.id.backbtn);
        Password = view.findViewById(R.id.passId);
        Language = view.findViewById(R.id.langId);
        LogOut = view.findViewById(R.id.logout);
        fAuth = FirebaseAuth.getInstance();


        Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.changePswFragment);
            }
        });
        Language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.changeLanguageFragment);
            }
        });
        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fAuth.signOut();
                Intent intent = new Intent(getContext(), SplashScreenActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).popBackStack();
            }
        });
        return view;


    }
}
