package com.example.shapable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShapesLevels extends AppCompatActivity {
    Button level1,level2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shapes_levels);

        level1=findViewById(R.id.shapeslvl1);
        level2=findViewById(R.id.shapeslvl2);

        level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(ShapesLevels.this,ShapesLevel1.class);
                startActivity(LoginIntent);
            }
        });
        level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(ShapesLevels.this,ShapesLevel2.class);
                startActivity(LoginIntent);
            }
        });
    }
}