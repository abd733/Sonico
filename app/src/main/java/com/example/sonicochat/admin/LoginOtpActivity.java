package com.example.sonicochat.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sonicochat.R;
import com.example.sonicochat.admin.utils.AndroidUtils;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class LoginOtpActivity extends AppCompatActivity {
    String phoneNumber;
    String verificationCode;
    PhoneAuthProvider.ForceResendingToken resendingToken;
    Button next;
    EditText otpInput;
    ProgressBar progressBar2;
    TextView resendOtpTextView;
    Long timeOutSeconds = 60L;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public void sendOtp(String phoneNumber, boolean isResend){
        startResendTimer();
        setInProgress(true);
        PhoneAuthOptions.Builder builder = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(timeOutSeconds, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                            signIn(phoneAuthCredential);
                            setInProgress(false);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        String errorMessage = e.getMessage();
                        if (errorMessage.contains("We have blocked all requests from this device")) {
                            AndroidUtils.showToast(getBaseContext(), "Requests blocked due to unusual activity. Please try again later.");
                        } else {
                            AndroidUtils.showToast(getBaseContext(), "OTP Verification failed: " + errorMessage);
                        }
                        Log.d("verification", errorMessage);
                        setInProgress(false);
                    }


                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        verificationCode = s;
                        resendingToken = forceResendingToken;
                        AndroidUtils.showToast(getBaseContext(),"OTP sent successfully");
                        setInProgress(false);
                    }
                });
        if (isResend){
            PhoneAuthProvider.verifyPhoneNumber(builder.setForceResendingToken(resendingToken).build());
        }else {
            PhoneAuthProvider.verifyPhoneNumber(builder.build());
        }
    }

    public void setInProgress(boolean inProgress){
        if (inProgress){
            progressBar2.setVisibility(View.VISIBLE);
            next.setVisibility(View.GONE);
        }else {
            progressBar2.setVisibility(View.GONE);
            next.setVisibility(View.VISIBLE);
        }
    }

    public void signIn(PhoneAuthCredential phoneAuthCredential){
        //login and go to next activity
        setInProgress(true);
        mAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(this, task -> {
            setInProgress(false);
                    if (task.isSuccessful()) {
                        // Sign in success, navigate to main activity
                        Intent intent = new Intent(LoginOtpActivity.this, LoginUserNameActivity.class);
                        intent.putExtra("phone",phoneNumber);
                        startActivity(intent);
                        finish();
                    } else {
                        // Sign in failed, display a message
                        AndroidUtils.showToast(getBaseContext(), "Sign-in failed: ");
                    }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_otp);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        next = findViewById(R.id.btn_next);
        otpInput =findViewById(R.id.login_otp);
        progressBar2 = findViewById(R.id.login_progress_bar2);
        resendOtpTextView = findViewById(R.id.resend_otp_textView);

        phoneNumber = getIntent().getExtras().getString("phone");
        sendOtp(phoneNumber,false);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String entredOtp = otpInput.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode,entredOtp);
                signIn(credential);
                setInProgress(true);
            }
        });
        resendOtpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOtp(phoneNumber,true);
            }
        });


        //Toast.makeText(getBaseContext(),phoneNumber,Toast.LENGTH_SHORT).show();
        /*Map<String,String> data = new HashMap<>();
        FirebaseFirestore.getInstance().collection("test").add(data);*/

    }
    @SuppressLint("DiscouragedApi")
    void startResendTimer(){
        resendOtpTextView.setEnabled(false);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeOutSeconds --;
                resendOtpTextView.setText("Resend Otp in "+timeOutSeconds+" seconds");
                if (timeOutSeconds <=0){
                    timeOutSeconds = 60L;
                    timer.cancel();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            resendOtpTextView.setEnabled(true);
                        }
                    });
                }
            }
        },0,1000);
    }
}