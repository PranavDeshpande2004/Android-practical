package com.example.exp_9;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.*;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;

    String fileName = "data.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // 🔹 INTERNAL SAVE
    public void saveInternal(View v){
        try{
            String data = editText.getText().toString();

            if(data.isEmpty()){
                Toast.makeText(this, "Enter text", Toast.LENGTH_SHORT).show();
                return;
            }

            FileOutputStream fos = openFileOutput(fileName, MODE_PRIVATE);
            fos.write(data.getBytes());
            fos.close();

            Toast.makeText(this, "Saved Internal", Toast.LENGTH_SHORT).show();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // 🔹 INTERNAL READ
    public void readInternal(View v){
        try{
            FileInputStream fin = openFileInput(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fin));

            String line;
            StringBuilder data = new StringBuilder();

            while((line = br.readLine()) != null){
                data.append(line);
            }

            br.close();
            fin.close();

            textView.setText(data.toString());

        } catch (Exception e) {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }
    }

    // 🔹 EXTERNAL SAVE (SAFE METHOD)
    public void saveExternal(View v){
        try{
            String data = editText.getText().toString();

            if(data.isEmpty()){
                Toast.makeText(this, "Enter text", Toast.LENGTH_SHORT).show();
                return;
            }

            File file = new File(getExternalFilesDir(null), fileName);
            FileOutputStream fs = new FileOutputStream(file);

            fs.write(data.getBytes());
            fs.close();

            Toast.makeText(this, "Saved External", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🔹 EXTERNAL READ (SAFE)
    public void readExternal(View v){
        try{
            File file = new File(getExternalFilesDir(null), fileName);

            if(!file.exists()){
                Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
                return;
            }

            BufferedReader bf = new BufferedReader(new FileReader(file));

            String line;
            StringBuilder data = new StringBuilder();

            while((line = bf.readLine()) != null){
                data.append(line);
            }

            bf.close();

            textView.setText(data.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}