package com.example.project_prm392.Activity.Base;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project_prm392.*;
import com.example.project_prm392.entities.Student;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView studentInfoTextView;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        studentInfoTextView = findViewById(R.id.studentInfoTextView);
        databaseReference = FirebaseDatabase.getInstance().getReference("Student");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Đọc thông tin sinh viên từ Firebase
                String studentInfo = "";
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Student student = snapshot.getValue(Student.class);
                    if (student != null) {
                        studentInfo += "Roll Number: " + student.getStudent_roll_number() + "\n";
                        studentInfo += "Name: " + student.getStudent_name() + "\n";
                        studentInfo += "Email: " + student.getStudent_email() + "\n";
                        studentInfo += "Phone: " + student.getStudent_phone() + "\n";

                        studentInfo += "Password: " + student.getStudent_password() + "\n";
                        studentInfo += "PIN: " + student.getStudent_PIN() + "\n";
                        studentInfo += "Status: " + student.getStatus() + "\n\n";
                    }
                }
                // Hiển thị thông tin sinh viên lên TextView
                studentInfoTextView.setText(studentInfo);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý khi có lỗi xảy ra
            }

        });
    }
}