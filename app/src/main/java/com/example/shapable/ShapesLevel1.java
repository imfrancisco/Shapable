package com.example.shapable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Build;

import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


public class ShapesLevel1 extends AppCompatActivity {
    private MyStrtDrggngLstnr mStrtDrg;
    private MyEndDrgLstnr mEndDrg;
    private MediaPlayer mySong;
    private SoundPlayer soundPlayer;
    private Button home,reset,play;
    private TextView tap,score;
    private static int mscore=0;

    private SharedPreferences sharedPreferences_shapes_Level1;

    private static final String SHARED_PREFERENCE_NAME_SHAPE_LEVEL1 ="sharedPreferenceShapesLevel1";
    private static final String KEY_SCORE_SHAPE_LEVEL1 = "score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shapes_level1);

        ButterKnife.bind(this);

        mStrtDrg = new MyStrtDrggngLstnr();
        mEndDrg = new MyEndDrgLstnr();
        soundPlayer = new SoundPlayer(this);

        mySong =  MediaPlayer.create(ShapesLevel1.this, R.raw.lostjungle);
        mySong.start();

        findViewById(R.id.button_circle).setOnLongClickListener(mStrtDrg);
        findViewById(R.id.button_square).setOnLongClickListener(mStrtDrg);
        findViewById(R.id.button_octagon).setOnLongClickListener(mStrtDrg);


        findViewById(R.id.button_answer_circle).setOnDragListener(mEndDrg);

        tap=(TextView)findViewById(R.id.tap);
        score=(TextView)findViewById(R.id.score);
        home=(Button)findViewById(R.id.home);
        reset=(Button)findViewById(R.id.reset);
        play=(Button)findViewById(R.id.start);
        score.setText("Score: "+mscore);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShapesLevel1.this,CardViewGames.class);
                startActivity(intent);
                mscore=0;
                score.setText("Score: "+mscore);

            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //reset score to zero
                Toast.makeText(ShapesLevel1.this, "Reset", Toast.LENGTH_SHORT).show();
                tap.setVisibility(View.VISIBLE);
                mscore=0;
                score.setText("Score: "+mscore);
            }
        });

        sharedPreferences_shapes_Level1 = getSharedPreferences(SHARED_PREFERENCE_NAME_SHAPE_LEVEL1,MODE_PRIVATE);
        String score = sharedPreferences_shapes_Level1.getString(KEY_SCORE_SHAPE_LEVEL1,null);
        if(score != null){
            Intent intent = new Intent(ShapesLevel1.this,ParentPortal.class);
            startActivity(intent);
        }

    }

    public boolean onTouchEvent(MotionEvent me){
        tap.setVisibility(View.GONE);
        return true;
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
                View view=(View) event.getLocalState();
                Button dropTarget=(Button) v;
                Button dropped=(Button)view;
                //view.setVisibility(View.INVISIBLE);

                String tagDropTarget= (String) dropTarget.getTag(),tagDroppedImage=(String)dropped.getTag();
                if((tagDropTarget!=null)&& tagDropTarget.equals(tagDroppedImage)){
                    // When it's the right answer
                    soundPlayer.playCheerSound();
                    //score updated
                    mscore+=100;
                    //display your new score
                    score.setText("Score: "+mscore);
                    Toast.makeText(ShapesLevel1.this, "Your total Score is " + mscore, Toast.LENGTH_LONG).show();
                    tap.setVisibility(View.VISIBLE);

                    SharedPreferences.Editor editor = sharedPreferences_shapes_Level1.edit();
                    editor.putString(KEY_SCORE_SHAPE_LEVEL1,score.getText().toString());
                    editor.apply();


                    Intent intent = new Intent(ShapesLevel1.this,CardViewGames.class);
                    startActivity(intent);
                    //setd your score 0
                    mscore=0;
                }
                else{
                    //every time it's wrong the wrong answer
                    soundPlayer.playWrongSound();
                    //subtract 10 points
                    mscore-=10;
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