package com.example.shapable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;

public class Game extends AppCompatActivity {
    private MyStrtDrggngLstnr mStrtDrg;
    private MyEndDrgLstnr mEndDrg;
    private MediaPlayer mySong;
    private SoundPlayer soundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mStrtDrg = new MyStrtDrggngLstnr();
        mEndDrg = new MyEndDrgLstnr();
        soundPlayer = new SoundPlayer(this);

        mySong =  MediaPlayer.create(Game.this, R.raw.lostjungle);
        mySong.start();


        findViewById(R.id.btn_circle).setOnLongClickListener(mStrtDrg);
        findViewById(R.id.btn_square).setOnLongClickListener(mStrtDrg);
        findViewById(R.id.btn_oval).setOnLongClickListener(mStrtDrg);
        findViewById(R.id.btn_rectangle).setOnLongClickListener(mStrtDrg);

       findViewById(R.id.btn_rectangle_input).setOnDragListener(mEndDrg);
       findViewById(R.id.btn_circle_input).setOnDragListener(mEndDrg);
       findViewById(R.id.btn_oval_input).setOnDragListener(mEndDrg);
       findViewById(R.id.btn_square_input).setOnDragListener(mEndDrg);
    }



    private class MyStrtDrggngLstnr implements View.OnLongClickListener{

        @Override
        public boolean onLongClick(View v) {
            WithDragShadow shadow= new WithDragShadow(v);
            ClipData data= ClipData.newPlainText("","");;
            v.startDrag(data,shadow,v,0);
            return false;
        }
    }

    private class MyEndDrgLstnr implements View.OnDragListener{
        @Override
        public boolean onDrag(View v, DragEvent event) {
            if(event.getAction()==DragEvent.ACTION_DROP){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    v.setBackground(((Button)event.getLocalState()).getBackground());
                }
            }
            return true;
        }
    }

    private class WithDragShadow extends View.DragShadowBuilder {
        public WithDragShadow(View v){ super(v);  }

        @Override public void onDrawShadow(Canvas canvas) { super.onDrawShadow(canvas);}

        @Override
        public void onProvideShadowMetrics(Point outShadowSize, Point outShadowTouchPoint) {
            super.onProvideShadowMetrics(outShadowSize, outShadowTouchPoint);
        }
    }
}