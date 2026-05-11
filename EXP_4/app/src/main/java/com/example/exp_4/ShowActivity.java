package com.example.exp_4;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity{
    TextView tvname,tvsubject,tvgender,tvQualification;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        tvname=findViewById(R.id.tvname);
        tvsubject=findViewById(R.id.tvsubject);
        tvgender=findViewById(R.id.tvgender);
        tvQualification=findViewById(R.id.tvQualification);

        SharedPreferences sp=getSharedPreferences("Data",MODE_PRIVATE);

        tvname.setText("Name="+sp.getString("name",""));
        tvsubject.setText("Subject : " + sp.getString("subject",""));
        tvgender.setText("gender="+sp.getString("gender",""));
        tvQualification.setText("Qualification : " + sp.getString("qualification",""));



    }
}
