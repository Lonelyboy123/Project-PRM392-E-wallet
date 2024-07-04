package com.example.project_prm392.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_prm392.R;
import com.example.project_prm392.entities.Banks;

import java.util.ArrayList;

public class BankAdapter extends  RecyclerView.Adapter<BankAdapter.viewholder>{
    ArrayList<Banks> banks;
    Context context;
    @NonNull
    @Override
    public BankAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_bank, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BankAdapter.viewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
