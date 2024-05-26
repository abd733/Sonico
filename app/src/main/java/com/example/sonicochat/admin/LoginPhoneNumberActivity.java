package com.example.sonicochat.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sonicochat.R;
import com.hbb20.CountryCodePicker;

public class LoginPhoneNumberActivity extends AppCompatActivity {
    CountryCodePicker countryCodePicker;
    EditText phoneInput;
    Button sendOtpButton;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_phone_number);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        countryCodePicker = findViewById(R.id.login_countrycode);
        phoneInput = findViewById(R.id.login_mobile_number);
        sendOtpButton = findViewById(R.id.login_send_otp_button);
        progressBar = findViewById(R.id.login_progress_bar);
        progressBar.setVisibility(View.GONE);

        // Set the default country to Morocco (MA)
        countryCodePicker.setDefaultCountryUsingNameCode("MA");
        countryCodePicker.resetToDefaultCountry();
        countryCodePicker.registerCarrierNumberEditText(phoneInput);

        sendOtpButton.setOnClickListener(v -> {
            String fullNumber = countryCodePicker.getFullNumber();
            if (!isValidMoroccanNumber(fullNumber)) {
                phoneInput.setError("Phone number not valid");
                return;
            }
            Intent intent = new Intent(LoginPhoneNumberActivity.this, LoginOtpActivity.class);
            intent.putExtra("phone", countryCodePicker.getFullNumberWithPlus());
            startActivity(intent);
        });
    }

    private boolean isValidMoroccanNumber(String number) {
        // Check if the number starts with +2127 or +2126
        if (number.startsWith("2127") || number.startsWith("2126")) {
            return number.length() == 12; // +2127XXXXXXXX or +2126XXXXXXXX (12 digits)
        }
        return false;
    }
}
