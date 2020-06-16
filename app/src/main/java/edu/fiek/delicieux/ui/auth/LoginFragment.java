package edu.fiek.delicieux.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import edu.fiek.delicieux.AuthActivity;
import edu.fiek.delicieux.MainActivity;
import edu.fiek.delicieux.R;

public class LoginFragment extends Fragment {

    private FirebaseAuth mAuth;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        final Context context = getContext();
        mAuth = FirebaseAuth.getInstance();

        TextView forgetPassword = (TextView) view.findViewById(R.id.forget);
        Button btnLogIn = (Button) view.findViewById(R.id.btnLogIn);
        final EditText emailText = (EditText) view.findViewById(R.id.email);
        final EditText passwordText = (EditText) view.findViewById(R.id.password);

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.forgetPasswordFragment);
            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailText.getText().toString().isEmpty() || passwordText.getText().toString().isEmpty()) {
                    Toast.makeText(context, "All fields are ruquired!", Toast.LENGTH_LONG).show();
                    return;
                }

                System.out.println(emailText.getText().toString() + " " + passwordText.getText().toString());

                mAuth.signInWithEmailAndPassword(emailText.getText().toString(), passwordText.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            return;
                        }
                        Intent intent = new Intent(getActivity(), MainActivity.class);
                        startActivity(intent);
                    }
                });

            }
        });

        return view;
    }
}
