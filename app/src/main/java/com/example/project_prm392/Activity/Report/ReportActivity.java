package com.example.project_prm392.Activity.Report;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.project_prm392.Activity.Base.BaseActivity;
import com.example.project_prm392.Activity.Base.MainActivity;
import com.example.project_prm392.Activity.helper.DataEncode;
import com.example.project_prm392.databinding.ActivityReportBinding;
import com.example.project_prm392.entities.Report;
import com.google.firebase.database.DatabaseReference;

import java.util.Objects;

public class ReportActivity  extends BaseActivity {
    ActivityReportBinding binding;

    DataEncode dataEncode = new DataEncode();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnReportCf.setEnabled(false);
        binding.tvReportErr1.setVisibility(View.GONE);
        binding.tvReportErr2.setVisibility(View.GONE);
        binding.tvReportErr3.setVisibility(View.GONE);
        setVariable();
        handleTextChange();
        handleButton();
    }

    private void handleButton() {
        binding.btnReportBack.setOnClickListener(v -> startActivity(new Intent(ReportActivity.this, MainActivity.class)));

        binding.btnReportCf.setOnClickListener(v -> {
            //Get data from view
            SharedPreferences preferences = getSharedPreferences("currentStudent", MODE_PRIVATE);
            String report_category = binding.spReportCategory.getSelectedItem().toString().trim();
            String report_transaction_id = binding.edtReportTransactionId.getText().toString().trim();
            String report_title = Objects.requireNonNull(binding.edtReportTitle.getText()).toString().trim();
            String report_description = binding.edtReportDescription.getText().toString().trim();

            //Get current student roll number
            String student_roll_number = preferences.getString("student_roll_number", "");

            //Save data to DB
            DatabaseReference report_reference = database.getReference("Report").child(student_roll_number);
            report_reference.push().setValue(new Report(report_category, report_transaction_id,
                            report_title, report_description,
                            dataEncode.getCurrentTime(), 0))
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Lưu dữ liệu thành công
                            // Hiển thị dialog thông báo
                            showDialogAndNavigateToMainActivity();
                        }  // Xử lý khi lưu dữ liệu không thành công (nếu cần)

                    });
        });
        binding.textView99.setOnClickListener(v -> startActivity(new Intent(ReportActivity.this, ReportListActivity.class)));
    }
    private void showDialogAndNavigateToMainActivity() {
        // Hiển thị dialog thông báo
        AlertDialog.Builder builder = new AlertDialog.Builder(ReportActivity.this);
        builder.setTitle("Success")
                .setMessage("Data saved successfully.")
                .setPositiveButton("OK", (dialog, which) -> {
                    // Chuyển về MainActivity
                    startActivity(new Intent(ReportActivity.this, MainActivity.class));
                    finish(); // Đóng ReportActivity để ngăn người dùng quay lại từ MainActivity
                }).show();
    }

    private void handleTextChange() {
        
    }

    private void setVariable() {
        String[] options = {"Lỗi giao dịch", "Vấn đề khác"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spReportCategory.setAdapter(adapter);
        
    }

}
