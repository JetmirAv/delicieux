package edu.fiek.delicieux.ui.profile;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import edu.fiek.delicieux.models.User;

public class ProfileViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

    MutableLiveData<User> user = new MutableLiveData<>();

    public MutableLiveData<User> getUser() {
        return user;
    }

    public LiveData<User> getCurrentUser() {

        if (user.getValue() == null)

            FirebaseDatabase.getInstance().getReference().child("Users").child(currentUser.getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    System.out.println("ASFASFASFASF: " + snapshot.child("mobile").getValue());
                    User val = snapshot.getValue(User.class);
                    val.setEmail(currentUser.getEmail());

                    user.setValue(val);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        return user;
    }
}