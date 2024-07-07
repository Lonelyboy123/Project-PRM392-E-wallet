package com.example.project_prm392.Activity.Report;

import com.example.project_prm392.Activity.Base.BaseActivity;
import com.example.project_prm392.Adapter.ReportAdapter;
import com.example.project_prm392.databinding.ActivityReportListBinding;
import com.example.project_prm392.entities.Report;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;


public class ReportListActivity extends BaseActivity {
    ActivityReportListBinding binding;
    private ReportAdapter adapter;
    private List<Report> reportList = new ArrayList<>();
    private DatabaseReference reportRef;
}
