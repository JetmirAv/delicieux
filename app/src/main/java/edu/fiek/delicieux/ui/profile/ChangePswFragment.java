package edu.fiek.delicieux.ui.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rengwuxian.materialedittext.MaterialEditText;

import edu.fiek.delicieux.MainActivity;
import edu.fiek.delicieux.R;
import edu.fiek.delicieux.SplashScreenActivity;


public class ChangePswFragment extends Fragment {

    ImageView btnBack;


    private MaterialEditText oldPsw, newPsw, confirmPsw;
    private TextView done;
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;


    public ChangePswFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_change_psw, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        oldPsw = view.findViewById(R.id.currentPassword);
        newPsw = view.findViewById(R.id.newPassword);
        confirmPsw = view.findViewById(R.id.confirmPassword);
        done = view.findViewById(R.id.button_done);

        btnBack = view.findViewById(R.id.back_Id);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).popBackStack();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtOldPsw = oldPsw.getText().toString();
                String txtNewPsw = newPsw.getText().toString();
                String txtConfirmPsw = confirmPsw.getText().toString();
                if (txtOldPsw.isEmpty() || txtNewPsw.isEmpty() || txtConfirmPsw.isEmpty()) {
                    Toast.makeText(getContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                } else if (txtNewPsw.length() < 6) {
                    Toast.makeText(getContext(), "The new password length should be more than 6 character on numbers", Toast.LENGTH_SHORT).show();
                } else if (!txtConfirmPsw.equals(txtNewPsw)) {
                    Toast.makeText(getContext(), "Confirm password does not match new password", Toast.LENGTH_SHORT).show();
                } else {
                    changePassword(txtOldPsw, txtNewPsw);
                }
            }
        });

        return view;
    }

    private void changePassword(String txtOldPsw, final String txtNewPsw) {
        AuthCredential credential = EmailAuthProvider.getCredential(firebaseUser.getEmail(), txtOldPsw);
        firebaseUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    firebaseUser.updatePassword(txtNewPsw).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getContext(), "Password changed successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(getContext(), "Wrong credentials", Toast.LENGTH_SHORT).show();
                }
                oldPsw.setText("");
                newPsw.setText("");
                confirmPsw.setText("");
            }
        });
    }
}