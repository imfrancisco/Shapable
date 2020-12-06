package com.example.shapable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SeaWorldLevels extends AppCompatActivity {
    Button level1, level2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sea_world_levels);
        level1=findViewById(R.id.sealvl1);
        level2=findViewById(R.id.sealvl2);

        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeaWorldLevels.this,SeaWorldLevel1.class);
                startActivity(intent);
            }
        });
        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SeaWorldLevels.this,SeaWorldLevel2.class);
                startActivity(intent);
            }
        });
    }
}