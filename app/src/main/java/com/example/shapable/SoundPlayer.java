package com.example.shapable;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundPlayer {
    private SoundPool soundPool;
    private int cheersSound;
    private int wrongSound;

    public SoundPlayer(Context context){
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
        cheersSound = soundPool.load(context,R.raw.crowdcheer,1);
        wrongSound = soundPool.load(context,R.raw.wrongchoice,1);

    }

    public void playCheerSound(){
        soundPool.play(cheersSound, 1.0f, 1.0f,1,0,1.0f);
    }

    public void playWrongSound(){
        soundPool.play(wrongSound,1.0f,1.0f,1,0,1.0f);
    }

}
