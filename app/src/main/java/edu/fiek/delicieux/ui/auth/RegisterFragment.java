package edu.fiek.delicieux.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import edu.fiek.delicieux.MainActivity;
import edu.fiek.delicieux.R;

public class RegisterFragment extends Fragment {

    private FirebaseAuth firebaseAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        System.out.println("Register");

        final EditText emailText = (EditText) view.findViewById(R.id.email);
        final EditText passwordText = (EditText) view.findViewById(R.id.password);
        final EditText usernameText = (EditText) view.findViewById(R.id.username);
        final EditText mobileText = (EditText) view.findViewById(R.id.mobile);

        Button btnRegister = (Button) view.findViewById(R.id.sign_up);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("Knej 1");
                if (emailText.getText().toString().isEmpty() || passwordText.getText().toString().isEmpty() || mobileText.getText().toString().isEmpty() || usernameText.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "All fields are required!", Toast.LENGTH_LONG).show();
                    return;
                }
                System.out.println("Knej 2");

                firebaseAuth.createUserWithEmailAndPassword(emailText.getText().toString(), passwordText.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            return;
                        }
                        System.out.println("Knej 3");

                        final FirebaseUser user = firebaseAuth.getCurrentUser();
                        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());

                        HashMap<String, String> additionalData = new HashMap<>();
                        additionalData.put("mobile", mobileText.getText().toString());
                        additionalData.put("username", usernameText.getText().toString());

                        dbRef.setValue(additionalData).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    user.delete();
                                    return;
                                }
                                System.out.println("Knej 4");

                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                startActivity(intent);
                            }
                        });

                    }
                });

            }
        });

        return view;
    }
}
