package com.example.shapable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AnimalWorldLevels extends AppCompatActivity {
    Button level1,level2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_world_levels);
        level1=findViewById(R.id.animallvl1);
        level2=findViewById(R.id.animallvl2);

        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(AnimalWorldLevels.this,AnimalWorldLevel1.class);
                startActivity(LoginIntent);
            }
        });
        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(AnimalWorldLevels.this,AnimalWorldLevel2.class);
                startActivity(LoginIntent);
            }
        });
    }
}