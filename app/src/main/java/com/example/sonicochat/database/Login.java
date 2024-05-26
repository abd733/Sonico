package com.example.sonicochat.database;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sonicochat.MainActivity2;
import com.example.sonicochat.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login extends AppCompatActivity {
    TextView logsignup, forgetPasswordTv;
    ImageView googleImageView, facebookImageView;
    Button button;
    LoginButton facebookLoginButton;
    GoogleSignInClient googleSignInClient;
    private static final int RC_SIGN_IN = 1;
    EditText email, password;
    CallbackManager callbackManager;

    FirebaseAuth firebaseAuth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog; // Change ProgressDialog import to android.app.ProgressDialog
    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            if(o.getResultCode() == RESULT_OK) {
                Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(o.getData());
                try {
                    GoogleSignInAccount signInAccount = accountTask.getResult(ApiException.class);
                    AuthCredential authCredential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(), null);
                    firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                firebaseAuth = FirebaseAuth.getInstance();
                                // Navigate to main activity upon successful login
                                Intent intent = new Intent(Login.this, MainActivity2.class);
                                startActivity(intent);
                                finish(); //
                            } else {
                                Toast.makeText(Login.this, "Filed to sign in" + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        }
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logging in..."); // Set progress dialog message
        progressDialog.setCancelable(false);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logging in..."); // Set progress dialog message
        progressDialog.setCancelable(false); // Set progress dialog non-cancelable
        //getSupportActionBar().hide();
        button = findViewById(R.id.logbutton);
        email = findViewById(R.id.editTexLogEmail);
        password = findViewById(R.id.editTextLogPassword);
        logsignup = findViewById(R.id.logsignup);
//        googleImageView = findViewById(R.id.google_login);
        facebookImageView = findViewById(R.id.facebook_login);
        forgetPasswordTv = findViewById(R.id.forget_password);
        facebookLoginButton = findViewById(R.id.facebook_btn);
        // Initialize callbackManager
        callbackManager = CallbackManager.Factory.create();
        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(Login.this,options);
        firebaseAuth = FirebaseAuth.getInstance();
        ImageView signInButton = findViewById(R.id.google_login);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = googleSignInClient.getSignInIntent();
                activityResultLauncher.launch(intent);
            }
        });
        facebookImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Set read permissions
                facebookLoginButton.setReadPermissions("email", "public_profile");
                // Register callback
                facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // Handle successful login
                        handleFacebookAccessToken(loginResult.getAccessToken());
                        progressDialog.setMessage("Se connecter...");
                        progressDialog.show();
                        startActivity(new Intent(Login.this, MainActivity2.class));
                        Toast.makeText(getBaseContext(), "Vous avez été connecté avec succès", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancel() {
                        // Handle cancelation
                    }

                    @Override
                    public void onError(@NonNull FacebookException e) {
                        // Handle error
                    }
                });
            }
        });

        logsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Registration.class);
                startActivity(intent);
            }
        });

        forgetPasswordTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRecoverPasswordDialog();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement your login logic here
                userLogin();
            }
        });
    }

    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(Login.this, MainActivity2.class));
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                progressDialog.setMessage("Se connecter...");
                progressDialog.show();
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException ignored) {
                Toast.makeText(getBaseContext(), ignored.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                Toast.makeText(this, "Vous avez été connecté avec succès", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            // Update UI or navigate to next activity
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void userLogin() {
        String Email = email.getText().toString().trim();
        String pass = password.getText().toString().trim();

        // Check if email and password are empty
        if (TextUtils.isEmpty(Email) || TextUtils.isEmpty(pass)) {
            Toast.makeText(Login.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if email is valid
        if (!Email.matches(emailPattern)) {
            email.setError("Please enter a valid email address");
            return;
        }

        // Check password length
        if (pass.length() < 6) {
            password.setError("Password should be at least 6 characters long");
            return;
        }
        // Show progress dialog
        progressDialog.setMessage("Se connecter...");
        progressDialog.show();

        // Sign in with Firebase authentication
        firebaseAuth.signInWithEmailAndPassword(Email, pass).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                // Login successful, navigate to main activity
                Intent intent = new Intent(Login.this, MainActivity2.class);
                startActivity(intent);
                Toast.makeText(this, "Vous avez été connecté avec succès", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Erreur dans l'e-mail ou le mot de passe", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }
    private void showRecoverPasswordDialog() {
        LinearLayout linearLayout = new LinearLayout(this);
        final EditText emailEt = new EditText(this);
        emailEt.setHint("Enter your email");
        emailEt.setMinEms(10);
        emailEt.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        linearLayout.addView(emailEt);
        linearLayout.setPadding(10, 10, 10, 10);

        new MaterialAlertDialogBuilder(this, R.style.Base_Theme_SonicoChat)
                .setTitle("Change Password")
                .setMessage("After confirmation, you will receive a message on your email to change your password")
                .setView(linearLayout)
                .setPositiveButton("OK",((dialog, i) -> {
                    String emailEditText = emailEt.getText().toString().trim();
                    if (TextUtils.isEmpty(emailEditText) || !emailEditText.matches(emailPattern)) {
                        Toast.makeText(Login.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    sendPasswordResetEmail(emailEditText);
                }))
                .setNegativeButton("Cancel", null)
                .show();
    }
    private void sendPasswordResetEmail(String email) {
        progressDialog.setMessage("Sending confirmation message...");
        progressDialog.show();

        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    progressDialog.dismiss();
                    if (task.isSuccessful()) {
                        Toast.makeText(Login.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Login.this, "Failed to send password reset email", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    progressDialog.dismiss();
                    Toast.makeText(Login.this, "Failed to send password reset email", Toast.LENGTH_SHORT).show();
                });
    }
}


