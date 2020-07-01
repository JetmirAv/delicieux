package edu.fiek.delicieux.ui.profile;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import edu.fiek.delicieux.AuthActivity;
import edu.fiek.delicieux.MainActivity;
import edu.fiek.delicieux.R;
import edu.fiek.delicieux.SplashScreenActivity;
import edu.fiek.delicieux.models.User;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;

    private FirebaseAuth fAuth;

    TextView email;
    TextView username;
    TextView mobile;

    ImageView avatar;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        fAuth = FirebaseAuth.getInstance();

        ImageView settings = view.findViewById(R.id.settings);
        Button editProfileBtn = view.findViewById(R.id.edit_profile_btn);
        email = view.findViewById(R.id.emailProfile);
        username = view.findViewById(R.id.usernameProfile);
        mobile = view.findViewById(R.id.mobileProfile);
        avatar = view.findViewById(R.id.avatar);


        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.editProfileFragment);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.settingsFragment);
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();

        mViewModel.getCurrentUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                email.setText(user.getEmail());
                username.setText(user.getUsername());
                mobile.setText(user.getMobile());
                Glide.with(getContext()).load(user.getAvatar()).into(avatar);

            }
        });
    }
}