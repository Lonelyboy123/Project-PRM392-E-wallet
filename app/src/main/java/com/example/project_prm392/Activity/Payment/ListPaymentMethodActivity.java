package com.example.project_prm392.Activity.Payment;

import com.example.project_prm392.Activity.Base.BaseActivity;
import com.example.project_prm392.Adapter.BankAdapter;
import com.example.project_prm392.entities.Banks;

import java.util.ArrayList;

public class ListPaymentMethodActivity extends BaseActivity {
    ActivityListPaymentMethodBinding binding;
    private ArrayList<Banks> banksList;
    private BankAdapter bankAdapter;
}
