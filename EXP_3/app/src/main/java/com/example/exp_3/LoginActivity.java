package com.example.exp_3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText lname,lpass;
    Button login;
    DBHelper db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        lname=findViewById(R.id.lname);
        lpass=findViewById(R.id.lpass);
        login=findViewById(R.id.login);

        db=new DBHelper(this);

        login.setOnClickListener(c->{
            boolean check=db.check(
                    lname.getText().toString(),
                    lpass.getText().toString()
            );

            if(check){
                startActivity(new Intent(this,HomeActivity.class));
            }
            else{
                Toast.makeText(this,"Invalid name and password",Toast.LENGTH_SHORT).show();
            }
        });




    }
}
