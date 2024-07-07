package com.example.project_prm392.Activity.StudentInformation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.project_prm392.Activity.Authentication.LoginActivity;
import com.example.project_prm392.Activity.Base.BaseActivity;
import com.example.project_prm392.Activity.Base.MainActivity;
import com.example.project_prm392.Activity.Report.ReportActivity;
import com.example.project_prm392.databinding.ActivityUserBinding;

public class UserActivity extends BaseActivity {
    ActivityUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        handleButton();
    }
    private void handleButton() {
        //Handle "Hồ sơ"
        binding.btnProfile.setOnClickListener(v -> {
            startActivity(new Intent(UserActivity.this, ProfileActivity.class));
            finish();
        });

        //Handle "Cài đặt"
        binding.btnSetting.setOnClickListener(v -> {
            startActivity(new Intent(UserActivity.this, SettingActivity.class));
            finish();
        });

        //Handle "Đăng xuất"
        binding.btnLogout.setOnClickListener(v -> {
            //Xóa thông tin người dùng đang đăng nhập trong preferences
            SharedPreferences preferences = getSharedPreferences("currentStudent", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
            //Chuyển đến màn hình đăng nhập
            Intent intent = new Intent(UserActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        binding.btnUserHome.setOnClickListener(v -> startActivity(new Intent(UserActivity.this, MainActivity.class)));
        binding.btnUserReport.setOnClickListener(v -> startActivity(new Intent(UserActivity.this, ReportActivity.class)));
    }
}
