package com.example.project_prm392.Activity.Transaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_prm392.Activity.Base.MainActivity;
import com.example.project_prm392.Activity.Base.PinIntroActivity;
import com.example.project_prm392.Security.PINActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.example.project_prm392.databinding.ActivityTopUpBinding;
import com.example.project_prm392.Activity.Base.BaseActivity;
import com.example.project_prm392.Activity.Payment.ListPaymentMethodActivity;
public class TopUpActivity extends AppCompatActivity {
    private ActivityTopUpBinding binding;
    BaseActivity baseActivity = new BaseActivity();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTopUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setVariable();
    }
    private void setVariable() {
        binding.btnBack.setOnClickListener(v -> {
            startActivity(new Intent(TopUpActivity.this, MainActivity.class));
            finish();
        });

        binding.btnPay.setEnabled(false);
        binding.edtAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                binding.textView100.setVisibility(View.GONE);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String amountString = charSequence.toString().trim();
                if (!amountString.isEmpty()) {
                    int amount = Integer.parseInt(amountString);
                    if (amount >= 50000000) {
                        binding.textView100.setVisibility(View.VISIBLE);
                        binding.textView100.setText("Số tiền giao dịch không vượt quá 50000000");
                    } else {
                        binding.textView100.setVisibility(View.GONE);
                    }
                } else {
                    binding.textView100.setVisibility(View.GONE);
                }
                binding.btnPay.setEnabled(!amountString.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        binding.btnPay.setOnClickListener(v -> {
            SharedPreferences preferences = getSharedPreferences("currentStudent", MODE_PRIVATE);
            String student_roll_number = preferences.getString("student_roll_number", "");
            String studentPIN = preferences.getString("student_PIN", "");

            if (studentPIN.isEmpty()) {
                startActivity(new Intent(TopUpActivity.this, PinIntroActivity.class));
            } else {
                String amountText = binding.edtAmount.getText().toString().trim();

                if (!amountText.isEmpty()) {
                    int amount_top_up = Integer.parseInt(amountText);

                    // Calculate total transaction amount today
                    DatabaseReference reference = baseActivity.database.getReference("Transaction").child(student_roll_number);

                    Query query = reference.orderByChild("time").startAt(baseActivity.dataEncode.getTodayDateString());

                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int totalTransactionAmountToday = 0;

                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                String from = dataSnapshot.child("from").getValue(String.class);
                                String to = dataSnapshot.child("to").getValue(String.class);
                                String category = dataSnapshot.child("category").getValue(String.class);
                                int transactionAmount = dataSnapshot.child("amount").getValue(Integer.class);

                                // Check if the transaction is related to the current student
                                if (from.equals(student_roll_number) || to.equals(student_roll_number) && !category.equals("Nhận tiền từ ví khác")) {
                                    totalTransactionAmountToday += transactionAmount;
                                }
                            }
                            // Check transaction limit
                            if (totalTransactionAmountToday + amount_top_up <= 100000000) {
                                // Proceed with the transaction
                                if (amount_top_up <= 50000000) {
                                    Intent intent = new Intent(TopUpActivity.this, PINActivity.class);
                                    intent.putExtra("transaction_amount", amount_top_up);
                                    intent.putExtra("transaction_type", 1); // type 1: Nạp tiền vào ví
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(TopUpActivity.this, "Số tiền top-up không được vượt quá 50.000.000 VNĐ trên 1 giao dịch", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(TopUpActivity.this, "Hạn mức giao dịch ngày hôm nay đã vượt quá 100.000.000 VNĐ", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Handle onCancelled event if needed
                        }
                    });
                } else {
                    Toast.makeText(TopUpActivity.this, "Vui lòng nhập số tiền top-up", Toast.LENGTH_SHORT).show();
                }
            }
        });


        binding.btnPaymentMethod.setOnClickListener(v -> startActivity(new Intent(TopUpActivity.this, ListPaymentMethodActivity.class)));
    }

}
