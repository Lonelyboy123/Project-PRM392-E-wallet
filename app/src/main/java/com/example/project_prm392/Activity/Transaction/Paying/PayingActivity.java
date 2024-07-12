package com.example.project_prm392.Activity.Transaction.Paying;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
        binding.btnPayingBack.setOnClickListener(v -> finish());
        binding.btnPayingTuition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("currentStudent", MODE_PRIVATE);
                int tuition = preferences.getInt("semester_fee", 0);
                if (tuition != 0){
                    Intent intent = new Intent(PayingActivity.this, SemesterFeeActivity.class);
                    intent.putExtra("fee_type", "semester_fee");
                    startActivity(intent);
                }else{
                    Toast.makeText(PayingActivity.this, "Bạn không nợ học phí kỳ này.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.btnPayingDomFee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("currentStudent", MODE_PRIVATE);
                Long tuition = preferences.getLong("additional_dormitory_f`ee", 0);
                if (tuition != 0) {
                    Intent intent = new Intent(PayingActivity.this, SemesterFeeActivity.class);
                    intent.putExtra("fee_type", "additional_dormitory_fee");
                    startActivity(intent);
                } else {
                    Toast.makeText(PayingActivity.this, "Bạn không nợ học phí kỳ này.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getStudentFee() {
        
    }
}
