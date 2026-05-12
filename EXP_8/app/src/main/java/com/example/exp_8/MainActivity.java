package com.example.exp_8;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnprev,btnplay,btnnext;
    SeekBar seekBar;

    MediaPlayer mediaPlayer;
    int song[]={R.raw.song1,R.raw.song2};

    int currentsong=0;

    Handler handler=new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btnprev=findViewById(R.id.btnprev);
        btnplay=findViewById(R.id.btnplay);
        btnnext=findViewById(R.id.btnnext);
        seekBar=findViewById(R.id.seekBar);

        mediaPlayer=MediaPlayer.create(this,song[currentsong]);

        seekBar.setMax(mediaPlayer.getDuration());

        btnplay.setOnClickListener(v->{
            if(mediaPlayer.isPlaying()){
                mediaPlayer.pause();
                btnplay.setText("Play");
            }
            else{
                mediaPlayer.start();
                btnplay.setText("pause");
                updateSeekBar();

            }
        });

        btnnext.setOnClickListener(v->{
            mediaPlayer.stop();
            mediaPlayer.release();
            currentsong++;

            if(currentsong==song.length){
                currentsong=0;
            }
            mediaPlayer=MediaPlayer.create(this,song[currentsong]);
            seekBar.setMax(mediaPlayer.getDuration());

            seekBar.setProgress(0);

            mediaPlayer.start();
            btnplay.setText("Pause");

            updateSeekBar();

        });

        btnprev.setOnClickListener(v->{
            mediaPlayer.stop();
            mediaPlayer.release();
            currentsong--;
            if(currentsong<0){
                currentsong=song.length-1;
            }
            mediaPlayer=MediaPlayer.create(this,song[currentsong]);
            seekBar.setMax(mediaPlayer.getDuration());

            seekBar.setProgress(0);
            mediaPlayer.start();

            btnplay.setText("Pause");

            updateSeekBar();
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mediaPlayer.seekTo(progress);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });






        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void  updateSeekBar(){
        seekBar.setProgress(mediaPlayer.getCurrentPosition());

        if(mediaPlayer.isPlaying()){
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    updateSeekBar();
                }
            };
            handler.postDelayed(runnable,1000);
        }
    }
}