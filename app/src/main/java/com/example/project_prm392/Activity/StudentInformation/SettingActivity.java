package com.example.project_prm392.Activity.StudentInformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project_prm392.Activity.Payment.ListPaymentMethodActivity;
import com.example.project_prm392.databinding.ActivitySettingBinding;

public class SettingActivity extends AppCompatActivity {
    ActivitySettingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        handleButton();
    }

    private void handleButton() {
        binding.btnToChangePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, ListPaymentMethodActivity.class));
            }
        });
    }
}
