package com.example.gruti.frogger21;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class LevelPick extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_pick);
    }

    public void onChooseLevel(View view)
    {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        int id=radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(id);
        int radioId = radioGroup.indexOfChild(radioButton);

        Intent LunchGame=new Intent(this,com.example.gruti.view.GameActivity.class);
        if(radioId==0)
        {
            LunchGame.putExtra("level",1);
        }
        else if (radioId==1)
        {
            LunchGame.putExtra("level",2);
        }
        else
        {
            LunchGame.putExtra("level",3);
        }
        startActivity(LunchGame);
    }



}
