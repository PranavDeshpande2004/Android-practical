package com.example.exp_4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Spinner spinner;
    RadioGroup radioGroup;
    RadioButton rbmale,rbfemale;
    CheckBox cbgraduate, cbpostgraduate;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        editText=findViewById(R.id.editText);
        spinner=findViewById(R.id.spinner);
        radioGroup=findViewById(R.id.radioGroup);
        rbmale=findViewById(R.id.rbmale);
        rbfemale=findViewById(R.id.rbfemale);
        cbgraduate=findViewById(R.id.cbgraduate);
        cbpostgraduate=findViewById(R.id.cbpostgraduate);
        button=findViewById(R.id.button);

        String sub[]={"JAVA","C++","PYTHON","SQL"};

        ArrayAdapter<String> adapter=new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                sub
        );
        spinner.setAdapter(adapter);

        //btnevent

        button.setOnClickListener(v->{
            String name=editText.getText().toString();
            String subject=spinner.getSelectedItem().toString();
            String gender="";
            if(rbmale.isChecked()){
                gender="Male";
            }
            else{
                gender="Female";
            }
            String qualification="";
            if(cbgraduate.isChecked()){
                qualification="Graduate ";
            }
            if(cbpostgraduate.isChecked()){
                qualification="PostGraduate";
            }

            SharedPreferences sp=getSharedPreferences("Data",MODE_PRIVATE);

            SharedPreferences.Editor editor=sp.edit();

            editor.putString("name",name);
            editor.putString("subject",subject);
            editor.putString("gender",gender);
            editor.putString("qualification",qualification);
            editor.apply();

            Intent i=new Intent(MainActivity.this,ShowActivity.class);
            startActivity(i);


        });




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}