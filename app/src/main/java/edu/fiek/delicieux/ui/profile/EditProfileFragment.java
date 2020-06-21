package edu.fiek.delicieux.ui.profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import edu.fiek.delicieux.R;

public class EditProfileFragment extends Fragment {
    ImageView mImageView;
    StorageReference storageReference;
    Button mChooseBtn;
    FirebaseAuth fAuth;
    FirebaseDatabase fStore;
    Uri newImageUri;
    TextView username, email, phoneNumber, button_done;
    View view;
    FirebaseUser user;
    DatabaseReference databaseReference;


    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        fStore = FirebaseDatabase.getInstance();
        user = fAuth.getCurrentUser();
        databaseReference = fStore.getReference().child("Users").child(user.getUid());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        button_done = view.findViewById(R.id.button_done);
        mImageView = view.findViewById(R.id.image_view);
        mChooseBtn = view.findViewById(R.id.choose_image_btn);
        username = view.findViewById(R.id.username);
        email = view.findViewById(R.id.email);
        phoneNumber = view.findViewById(R.id.phoneNumber);


        button_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });

        mChooseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent, 1000);

            }
        });

        return view;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == IMAGE_PICK_CODE) {

            mImageView.setImageURI(data.getData());
            newImageUri = data.getData();
//            uploadImageToFirebase(data.getData());
        }
    }

    private void uploadImageToFirebase(final Uri imageUri) {
        // uplaod image to firebase storage

        System.out.println("user: " + fAuth.getCurrentUser());

//        String extension = imageUri.toString().substring(imageUri.toString().lastIndexOf("."));

        final StorageReference fileRef = storageReference.child("avatars/" + fAuth.getCurrentUser().getUid() + ".jpeg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(mImageView);
                        newImageUri = uri;
                        updateProfileAction();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "Failed.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateProfile() {
        if (newImageUri != null) {
            uploadImageToFirebase(newImageUri);
        } else {
            updateProfileAction();
        }
    }

    private void updateProfileAction() {
        DatabaseReference dbRef = fStore.getReference();
        dbRef = dbRef.child("Users").child(fAuth.getCurrentUser().getUid());

        Toast.makeText(getContext(), "phase 1", Toast.LENGTH_LONG).show();

        HashMap<String, Object> values = new HashMap<>();
        values.put("mobile", phoneNumber.getText().toString());
        values.put("username", username.getText().toString());

        Toast.makeText(getContext(), "phase 2 ", Toast.LENGTH_LONG).show();

        if (newImageUri != null) {
            values.put("avatar", newImageUri.toString());
            System.out.println("newImageUri: " + newImageUri);
//            dbRef.child("avatar").setValue(newImageUri);
        }

        dbRef.updateChildren(values);


        if (!email.getText().equals(fAuth.getCurrentUser().getEmail()) && !email.getText().toString().isEmpty()) {
            fAuth.getCurrentUser().updateEmail(email.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getContext(), "Email changed", Toast.LENGTH_LONG).show();
                }
            });
        }

        Toast.makeText(getContext(), "bravo", Toast.LENGTH_LONG).show();


    }


}