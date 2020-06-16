package edu.fiek.delicieux.ui.auth;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import edu.fiek.delicieux.R;

public class AuthFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        System.out.println("Testing this mf");

        final View flater = inflater.inflate(R.layout.fragment_auth, container, false);

        final Context context = getContext();

        TextView signIn = (TextView) flater.findViewById(R.id.sign_in);
        Button signUp = (Button) flater.findViewById(R.id.sign_up);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.loginFragment);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.registerFragment);
            }
        });


        return flater;

    }

}