package com.example.shapable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CardViewGames extends AppCompatActivity {

    Button shapes,seaWorld,animalWorld;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view_games);
        shapes=(Button)findViewById(R.id.game1);
        seaWorld=(Button)findViewById(R.id.game2);
        animalWorld=(Button)findViewById(R.id.game3);

        shapes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(CardViewGames.this,ShapesLevels.class);
                startActivity(LoginIntent);
            }
        });

        seaWorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(CardViewGames.this,SeaWorldLevels.class);
                startActivity(LoginIntent);
            }
        });

        animalWorld.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(CardViewGames.this,AnimalWorldLevels.class);
                startActivity(LoginIntent);
            }
        });
    }
}