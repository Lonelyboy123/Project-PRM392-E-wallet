package com.example.project_prm392.Activity.Payment;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;

import com.example.project_prm392.Activity.Base.BaseActivity;
import com.example.project_prm392.Adapter.BankAdapter;
import com.example.project_prm392.databinding.ActivityListPaymentMethodBinding;
import com.example.project_prm392.entities.Banks;

import java.util.ArrayList;

public class ListPaymentMethodActivity extends BaseActivity {
    ActivityListPaymentMethodBinding binding;
    private ArrayList<Banks> banksList;
    private BankAdapter bankAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListPaymentMethodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        banksList = new ArrayList<>();
        bankAdapter = new BankAdapter(this, banksList);

        binding.rvBank.setLayoutManager(new GridLayoutManager(this, 3));

        binding.rvBank.setAdapter(bankAdapter);
//
//        // Lấy danh sách ngân hàng từ API
//        fetchBankList();
//        handleButton();
    }
}
