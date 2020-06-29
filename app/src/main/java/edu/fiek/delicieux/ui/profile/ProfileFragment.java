package edu.fiek.delicieux.ui.profile;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

import edu.fiek.delicieux.AuthActivity;
import edu.fiek.delicieux.MainActivity;
import edu.fiek.delicieux.R;
import edu.fiek.delicieux.SplashScreenActivity;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    private FirebaseAuth fAuth;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        fAuth = FirebaseAuth.getInstance();

        ImageView settings = view.findViewById(R.id.settings);
        Button btnLogOut = (Button) view.findViewById(R.id.sign_out);
        Button btnSettings = (Button) view.findViewById(R.id.settingsID);
        Button btnEditProfile = (Button) view.findViewById(R.id.btnEditProfile);


        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.settingsFragment);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fAuth.signOut();
                Intent intent = new Intent(getActivity(), AuthActivity.class);
                startActivity(intent);
            }
        });
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.editProfileFragment);
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
    }

}