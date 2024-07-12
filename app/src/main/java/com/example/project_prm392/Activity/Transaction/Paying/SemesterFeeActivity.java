package com.example.project_prm392.Activity.Transaction.Paying;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import com.example.project_prm392.Activity.Base.BaseActivity;
import com.example.project_prm392.databinding.ActivityTransferBinding;

public class SemesterFeeActivity extends BaseActivity {
    ActivityTransferBinding binding;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTransferBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvTransferErrAmount.setVisibility(View.GONE);
        binding.tvTransferErrMssv.setVisibility(View.GONE);
        binding.btnTransferContinue.setEnabled(false);
        getBalance();
        handleError();
        handleButton();
    }

    private void handleButton() {

    }

    private void handleError() {

    }

    private void getBalance() {

    }
}
