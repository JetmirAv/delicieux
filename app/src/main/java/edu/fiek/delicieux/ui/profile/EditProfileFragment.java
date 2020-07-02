package edu.fiek.delicieux.ui.profile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import edu.fiek.delicieux.R;
import edu.fiek.delicieux.models.User;


public class EditProfileFragment extends Fragment {


    ProfileViewModel mViewModel;
    private int mDialogType;
    ImageView btnBack;
    ImageView mImageView;
    StorageReference storageReference;
    //    Button mChooseBtn;
    Button mCameraBtn;
    FirebaseAuth fAuth;
    FirebaseDatabase fStore;
    Bitmap newBitmapObj;
    Uri newImageUrl;
    TextView username, email, phoneNumber, button_done;
    View view;
    FirebaseUser user;
    DatabaseReference databaseReference;


    private static final int IMAGE_PICK_CODE = 1000;
    public static final int CAMERA_REQUEST_CODE = 1001;


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
//        mChooseBtn = view.findViewById(R.id.choose_image_btn);
        mCameraBtn = view.findViewById(R.id.upload_from_cam);
        username = view.findViewById(R.id.username);
        email = view.findViewById(R.id.email);
        phoneNumber = view.findViewById(R.id.phoneNumber);
        btnBack = view.findViewById(R.id.back_Id);


        button_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(v).popBackStack();
            }
        });

        mCameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage(getContext());

            }
        });

        return view;
    }


    private void uploadImageToFirebase() {
        // uplaod image to firebase storage
        final StorageReference fileRef = storageReference.child("avatars/" + fAuth.getCurrentUser().getUid() + ".jpeg");
        UploadTask task = null;


        if (newBitmapObj != null) {

            Log.e("Error", "UPLOADING!!");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            newBitmapObj.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray();
            task = fileRef.putBytes(data);
        }

        if (task != null) {
            task.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Log.e("Error", "UPLOADED!!: " + uri);

                            Glide.with(getContext()).load(uri).into(mImageView);
                            newImageUrl = uri;
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
        } else {
            updateProfileAction();
        }

    }

    private void updateProfile() {
        if (newBitmapObj != null) {
            uploadImageToFirebase();
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

        if (newImageUrl != null)
            values.put("avatar", newImageUrl.toString());

        Toast.makeText(getContext(), "phase 2 ", Toast.LENGTH_LONG).show();

        dbRef.updateChildren(values);


        Log.e("Error", email.getText().toString());
        Log.e("Error", fAuth.getCurrentUser().getEmail().toString());
        Log.e("Error", fAuth.getCurrentUser().getEmail().toString().equals(email.getText().toString()) ? "Njejt" : "Ndryshe");

        if (!fAuth.getCurrentUser().getEmail().equals(email.getText().toString()) && email.getText().toString().length() > 0) {

            Log.e("TEASF", "ASSAFJASFJASFJASFJASJFAS");

            fAuth.getCurrentUser().updateEmail(email.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getContext(), "Email changed", Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("Fail: ", e.getMessage());
                }
            });
        }

        Toast.makeText(getContext(), "bravo", Toast.LENGTH_LONG).show();


    }

    private void selectImage(Context context) {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);//one can be replaced with any action code

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != 0) {
            Uri uri;

            switch (requestCode) {
                case 0:
                    if (resultCode == -1 && data != null) {
                        Log.e("Error", "Vec po testoj");
                        newBitmapObj = (Bitmap) data.getExtras().get("data");
                    }

                    break;
                case 1:
                    if (resultCode == -1 && data != null) {
//                        Glide.with(getContext()).load(user.getAvatar()).into(mImageView);
                        try {
                            newBitmapObj = MediaStore.Images.Media.getBitmap(this.getContext().getContentResolver(), data.getData());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);


    }

    @Override
    public void onResume() {
        super.onResume();

        mViewModel.getCurrentUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {

                System.out.println("ASFASF: " + user.getUsername());
                System.out.println("ASFASF: " + user.getMobile());
                System.out.println("ASFASF: " + user.getAvatar());

                email.setText(user.getEmail());
                username.setText(user.getUsername());
                Glide.with(getContext()).load(newBitmapObj != null ? newBitmapObj : user.getAvatar()).skipMemoryCache(true).into(mImageView);
                phoneNumber.setText(user.getMobile());

            }
        });
    }
}