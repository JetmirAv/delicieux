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


//    private MaterialEditText oldPsw,newPsw,confirmPsw;
//    private Button done;
//    private FirebaseUser firebaseUser;
//    private FirebaseAuth firebaseAuth;
//
//
//    public ChangePswFragment() {
//        // Required empty public constructor
//    }
//

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_change_psw, container, false);


        btnBack = view.findViewById(R.id.back_Id);



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).popBackStack();
            }
        });
        return view;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.fragment_change_psw);
//        getSupportActionBar().setTitle("Register");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ChangePswFragment.this,MainActivity.class));
//            }
//        });
//        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        oldPsw = findViewById(R.id.currentPassword);
//        newPsw = findViewById(R.id.newPassword);
//        confirmPsw = findViewById(R.id.confirmPassword);
//        changePsw = findViewById(R.id.resetPassword);
//        progressBar = findViewById(R.id.progressBar);
//        changePsw.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String txtOldPsw = oldPsw.getText().toString();
//                String txtNewPsw = newPsw.getText().toString();
//                String txtConfirmPsw = confirmPsw.getText().toString();
//                if (txtOldPsw.isEmpty() || txtNewPsw.isEmpty() || txtConfirmPsw.isEmpty()){
//                    Toast.makeText(ChangePswFragment.this, "All fields are required", Toast.LENGTH_SHORT).show();
//                }else if(txtNewPsw.length() <6){
//                    Toast.makeText(ChangePswFragment.this, "The new password length should be more than 6 character on numbers", Toast.LENGTH_SHORT).show();
//
//                }else if(! txtConfirmPsw.equals(txtNewPsw)){
//                    Toast.makeText(ChangePswFragment.this, "Confirm password does not match new password", Toast.LENGTH_SHORT).show();
//                }else{
//                    changePassword(txtOldPsw,txtNewPsw);
//                }
//            }
//
//        });
//    }
//
//    private void changePassword(String txtOldPsw, final String txtNewPsw) {
//        progressBar.setVisibility(View.VISIBLE);
//        AuthCredential credential = EmailAuthProvider.getCredential(firebaseUser.getEmail(),txtOldPsw);
//        firebaseUser.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()){
//                    firebaseUser.updatePassword(txtNewPsw).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()){
//                                firebaseAuth.signOut();
//                                Intent intent = new Intent(ChangePswFragment.this, MainActivity.class);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                startActivity(intent);
//                            }else{
//                                Toast.makeText(ChangePswFragment.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                }else {
//                    progressBar.setVisibility(View.GONE);
//                    Toast.makeText(ChangePswFragment.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//    }
}