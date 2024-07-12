package com.example.project_prm392.Activity.Transaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.project_prm392.Activity.Base.BaseActivity;
import com.example.project_prm392.Activity.Base.MainActivity;
import com.example.project_prm392.Security.PINActivity;
import com.example.project_prm392.databinding.ActivityTransferBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class TransferActivity extends BaseActivity {
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

    private void handleError() {
    }

    private void getBalance() {
        
    }

    private void handleButton() {
        binding.btnTransferBack.setOnClickListener(v -> {
            startActivity(new Intent(TransferActivity.this, MainActivity.class));
            finish();
        });

        binding.btnTransferContinue.setOnClickListener(v -> {
            String transferTo = binding.edtTransferTo.getText().toString().trim();
            String transferAmountStr = binding.edtTransferAmount.getText().toString().trim();
            if (!TextUtils.isEmpty(transferAmountStr)) {
                long transferAmount = Long.parseLong(transferAmountStr);
                long currentBalance = Long.parseLong(binding.tvTransferCurrentBalance.getText().toString().replaceAll("[^0-9]", ""));
                if (transferAmount <= currentBalance) {
                    progressDialog = new ProgressDialog(TransferActivity.this);
                    progressDialog.setMessage("Đang kiểm tra MSSV...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    checkRollNumberExist(transferTo);
                } else {
                    Toast.makeText(TransferActivity.this, "Số tiền chuyển đi không thể vượt quá số dư hiện tại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void checkRollNumberExist(String rollNumber) {
        DatabaseReference reference = database.getReference("Student");
        Query query = reference.orderByChild("student_roll_number").equalTo(rollNumber);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressDialog.dismiss();
                if (snapshot.exists()) {
                    int transferAmount = Integer.parseInt(binding.edtTransferAmount.getText().toString().trim());
                    Intent intent = new Intent(TransferActivity.this, PINActivity.class);
                    intent.putExtra("transaction_amount", transferAmount);
                    intent.putExtra("transfer_to", rollNumber);
                    intent.putExtra("transaction_type", 2); // Loại giao dịch 2: Chuyển tiền đến ví khác
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(TransferActivity.this, "Mã số sinh viên không tồn tại. Xin vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                progressDialog.dismiss();
                Toast.makeText(TransferActivity.this, "Đã xảy ra lỗi khi kiểm tra MSSV", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
