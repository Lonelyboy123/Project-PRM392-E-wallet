package com.example.project_prm392.Activity.StudentInformation;

import android.os.Bundle;

import com.example.project_prm392.Activity.Base.BaseActivity;
import com.example.project_prm392.databinding.ActivityUserBinding;

public class UserActivity extends BaseActivity {
    ActivityUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
