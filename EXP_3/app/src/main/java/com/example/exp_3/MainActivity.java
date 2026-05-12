package com.example.exp_3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button register,loginpage;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        register=findViewById(R.id.register);
        loginpage=findViewById(R.id.loginpage);

        db=new DBHelper(this);

        register.setOnClickListener(v->{
            db.insert(
                    username.getText().toString(),
                    password.getText().toString()
            );
            Toast.makeText(this,"Registered",Toast.LENGTH_SHORT).show();

        });

        loginpage.setOnClickListener(v->{
            startActivity(new Intent(this,LoginActivity.class));
        });

    }
}