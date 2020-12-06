package com.example.shapable;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ParentPortal extends AppCompatActivity {

    private TextView shapesLevel1Score_TextView, shapesLevel2Score_TextView;
    private TextView seaWordLevel1Score_TextView, seaWordLevel2Score_TextView;
    private TextView animalWorldLevel1Score_TextView, animalWorldLevel2Score_TextView;
    private Button logout;

    private SharedPreferences sharedPreferences;
    private SharedPreferences sharedPreferences_animalWorld_Level2;
    private SharedPreferences sharedPreferences_seaWorld_Level1;
    private SharedPreferences sharedPreferences_seaWorld_Level2;
    private SharedPreferences sharedPreferences_shapes_Level1;
    private SharedPreferences sharedPreferences_shapes_Level2;






    private static final String SHARED_PREFERENCE_NAME_ANIMALWORLD_LEVEL1 ="sharedPreferenceAnimalWorldLevel1";
    private static final String SHARED_PREFERENCE_NAME_ANIMALWORLD_LEVEL2 ="sharedPreferenceAnimalWorldLevel2";
    private static final String SHARED_PREFERENCE_NAME_SEAWORLD_LEVEL1 ="sharedPreferenceSeaWorldLevel1";
    private static final String SHARED_PREFERENCE_NAME_SEAWORLD_LEVEL2 ="sharedPreferenceSeaWorldLevel2";
    private static final String SHARED_PREFERENCE_NAME_SHAPE_LEVEL1 ="sharedPreferenceShapesLevel1";
    private static final String SHARED_PREFERENCE_NAME_SHAPE_LEVEL2 ="sharedPreferenceShapesLevel2";

    private static final String KEY_SCORE_SHAPE_LEVEL1 = "score";
    private static final String KEY_SCORE_SHAPE_LEVEL2 = "score";

    private static final String KEY_SCORE_SEAWORLD_LEVEL1 = "score";
    private static final String KEY_SCORE_SEAWORLD_LEVEL2 = "score";

    private static final String KEY_SCORE_ANIMALWORLD_LEVEL1 = "score";
    private static final String KEY_SCORE_ANIMALWORLD_LEVEL2 = "score";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_portal);

        shapesLevel1Score_TextView=findViewById(R.id.textView_shapesLevel1Score);
        shapesLevel2Score_TextView=findViewById(R.id.editText_shapesLevel2Score);

        seaWordLevel1Score_TextView=findViewById(R.id.editText_seaWorldLevel1Score);
        seaWordLevel2Score_TextView=findViewById(R.id.editText_seaWorldLevel2Score);

        animalWorldLevel1Score_TextView=findViewById(R.id.textView_animalWorldLevel1Score);
        animalWorldLevel2Score_TextView=findViewById(R.id.editText_animalWorldLevel2Score);

        logout=findViewById(R.id.button_logout);


        sharedPreferences_shapes_Level1=getSharedPreferences(SHARED_PREFERENCE_NAME_SHAPE_LEVEL1,MODE_PRIVATE);
        sharedPreferences_shapes_Level2=getSharedPreferences(SHARED_PREFERENCE_NAME_SHAPE_LEVEL2,MODE_PRIVATE);
        sharedPreferences_seaWorld_Level1=getSharedPreferences(SHARED_PREFERENCE_NAME_SEAWORLD_LEVEL1,MODE_PRIVATE);
        sharedPreferences_seaWorld_Level2=getSharedPreferences(SHARED_PREFERENCE_NAME_SEAWORLD_LEVEL2,MODE_PRIVATE);
        sharedPreferences=getSharedPreferences(SHARED_PREFERENCE_NAME_ANIMALWORLD_LEVEL1, MODE_PRIVATE);
        sharedPreferences_animalWorld_Level2=getSharedPreferences(SHARED_PREFERENCE_NAME_ANIMALWORLD_LEVEL2,MODE_PRIVATE);

        String shapesLevel1Score = sharedPreferences_shapes_Level1.getString(KEY_SCORE_SHAPE_LEVEL1,null);
        String shapesLevel2Score = sharedPreferences_shapes_Level2.getString(KEY_SCORE_SHAPE_LEVEL2,null);

        String seaWorldLevel1Score = sharedPreferences_seaWorld_Level1.getString(KEY_SCORE_SEAWORLD_LEVEL1,null);
        String seaWorldLevel2Score = sharedPreferences_seaWorld_Level2.getString(KEY_SCORE_SEAWORLD_LEVEL2,null);

        String animalWorldLevel1Score = sharedPreferences.getString(KEY_SCORE_ANIMALWORLD_LEVEL1,null);
        String animalWorldLevel2Score = sharedPreferences_animalWorld_Level2.getString(KEY_SCORE_ANIMALWORLD_LEVEL2,null);


        if(shapesLevel1Score != null || shapesLevel2Score != null || seaWorldLevel1Score != null || seaWorldLevel2Score != null || animalWorldLevel1Score != null || animalWorldLevel2Score != null){
            shapesLevel1Score_TextView.setText(shapesLevel1Score);
            shapesLevel2Score_TextView.setText(shapesLevel2Score);

            seaWordLevel1Score_TextView.setText(seaWorldLevel1Score);
            seaWordLevel2Score_TextView.setText(seaWorldLevel2Score);

            animalWorldLevel1Score_TextView.setText(animalWorldLevel1Score);
            animalWorldLevel2Score_TextView.setText(animalWorldLevel2Score);
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                SharedPreferences.Editor editor_animalWorld_Level2 = sharedPreferences_animalWorld_Level2.edit();
                SharedPreferences.Editor editor_seaWorld_level1 = sharedPreferences_seaWorld_Level1.edit();
                SharedPreferences.Editor editor_seaWorld_level2 = sharedPreferences_seaWorld_Level2.edit();
                SharedPreferences.Editor editor_shapes_level1 = sharedPreferences_shapes_Level1.edit();
                SharedPreferences.Editor editor_shapes_level2 = sharedPreferences_shapes_Level2.edit();

                editor.clear();
                editor_animalWorld_Level2.clear();
                editor_seaWorld_level1.clear();
                editor_seaWorld_level2.clear();
                editor_shapes_level1.clear();
                editor_shapes_level2.clear();


                editor.commit();
                editor_animalWorld_Level2.commit();
                editor_seaWorld_level1.commit();
                editor_seaWorld_level2.commit();
                editor_shapes_level1.commit();
                editor_shapes_level2.commit();

                finish();
                Toast.makeText(ParentPortal.this, "Log out successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}