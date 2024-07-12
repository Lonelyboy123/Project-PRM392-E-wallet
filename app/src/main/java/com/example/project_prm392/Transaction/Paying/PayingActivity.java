package com.example.project_prm392.Transaction.Paying;

import android.os.Bundle;

import com.example.project_prm392.Activity.Base.BaseActivity;
import com.example.project_prm392.databinding.ActivityPayingBinding;

public class PayingActivity extends BaseActivity {
    ActivityPayingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPayingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getStudentFee();
        handleButton();
    }

    private void handleButton() {
    }

    private void getStudentFee() {
        
    }
}
