package com.example.sonicochat.database;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.sonicochat.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.sonicochat.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class Registration extends AppCompatActivity {
    TextView loginbut;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseDatabase database;
    FirebaseStorage storage;

    CircleImageView imageView;
    EditText userName, email, password1, password2;
    Button saveButton;
    String imageURL;
    Uri uri;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
//        getSupportActionBar().hide();
        FirebaseApp.initializeApp(Registration.this);
        storageReference = FirebaseStorage.getInstance().getReference();
        imageView = findViewById(R.id.profilerg0);
        userName = findViewById(R.id.rgusername);
        email = findViewById(R.id.rgemail);
        password1 = findViewById(R.id.rgpassword);
        password2 = findViewById(R.id.rgrepassword);
        saveButton = findViewById(R.id.signupbutton);
        loginbut = findViewById(R.id.loginbut);
        storage = FirebaseStorage.getInstance();

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if (o.getResultCode() == MainActivity.RESULT_OK) {
                            Intent data = o.getData();
                            uri = data.getData();
                            imageView.setImageURI(uri);
                            Glide.with(getApplicationContext()).load(uri).into(imageView);
                        } else {
                            Toast.makeText(getBaseContext(), "No image selected", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
        loginbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

    }
    public void saveData() {
        if (uri != null) {
            String namee = userName.getText().toString();
            String emaill = email.getText().toString();
            String Password = password1.getText().toString();
            String cPassword = password2.getText().toString();
            if (TextUtils.isEmpty(namee) || TextUtils.isEmpty(emaill) ||
                    TextUtils.isEmpty(Password) || TextUtils.isEmpty(cPassword)) {
                Toast.makeText(Registration.this, "Please Enter Valid Information", Toast.LENGTH_SHORT).show();
            } else if (!emaill.matches(emailPattern)) {
                email.setError("Type A Valid Email Here");
            } else if (Password.length() < 6) {
                password1.setError("Password Must Be 6 Characters Or More");
            } else if (!Password.equals(cPassword)) {
                password1.setError("The Password Doesn't Match");
            } else {
                storage = FirebaseStorage.getInstance();
                StorageReference storageReference = storage.getReference().child("Android Image").child(uri.getLastPathSegment());
                AlertDialog.Builder builder = new AlertDialog.Builder(Registration.this);
                builder.setCancelable(false);
                builder.setView(R.layout.progres_layout);
                AlertDialog dialog = builder.create();
                dialog.show();

                UploadTask uploadTask = storageReference.putFile(uri);
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        uriTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                imageURL = uri.toString();
                                uploadData();
                                dialog.dismiss();
                                // Navigate to login activity after successful registration
                                Intent intent = new Intent(Registration.this, Login.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Upload Error", e.getMessage());
                        dialog.dismiss();
                        Toast.makeText(Registration.this, "Failed to upload image.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } else {
            Toast.makeText(Registration.this, "Please select an image first.", Toast.LENGTH_SHORT).show();
        }
    }

    public void uploadData() {
        String name = userName.getText().toString();
        String mail = email.getText().toString();
        String pass1 = password1.getText().toString();
        String pass2 = password2.getText().toString();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Users");
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(mail,pass1);

        String key = database.push().getKey();
       // Users userData = new Users(name, mail, pass1, pass2, imageURL);
        Users userData = new Users(imageURL, name, mail,pass1, pass2);
        database.child(key).setValue(userData);
    }
}
