package com.example.playingaudio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    Button button;
    boolean flag = true;
    SeekBar seekBar;
    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.stuff);
        button = findViewById(R.id.button);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(maxVolume);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               // Log.d("Progress changed: ", "" + progress);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            //Метод захвата кнопки пальцем срабатывает как только мы нажимаем на кнопку
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            //Срабатывает как только мы отпускаем кнопку
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void play_pause(View view) {
        /*if (flag) {
            mediaPlayer.start();
            button.setText(R.string.pause);
            flag = false;
        } else {
            mediaPlayer.pause();
            button.setText(R.string.play);
            flag = true;
        }*/
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            button.setText(R.string.play);
        } else {
            mediaPlayer.start();
            button.setText(R.string.pause);
        }
    }
}
