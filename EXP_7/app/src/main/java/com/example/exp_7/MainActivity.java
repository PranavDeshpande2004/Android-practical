package com.example.exp_7;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageSlider imageSlider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        imageSlider=findViewById(R.id.imageSlider);

        ArrayList<SlideModel>imagelist=new ArrayList<>();
        imagelist.add(new SlideModel(R.drawable.img1, ScaleTypes.CENTER_CROP));
        imagelist.add(new SlideModel(R.drawable.img2,ScaleTypes.CENTER_CROP));
        imagelist.add(new SlideModel(R.drawable.images,ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(imagelist);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}