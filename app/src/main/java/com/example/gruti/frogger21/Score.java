package com.example.gruti.frogger21;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Score extends AppCompatActivity {

    List<String> array;
    ArrayAdapter<String> itemsAdapter;
    ListView listView;
    TextView tv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        array=new ArrayList<String>();
        ReadFile();
        itemsAdapter = new ArrayAdapter<String>(this, R.layout.activity_score,R.id.scoreTextView,array);
        listView = (ListView) findViewById(R.id.listScore);
        listView.setAdapter(itemsAdapter);


    }




    public void ReadFile()
    {
        BufferedReader br = null;
        try {
            String sCurrentLine;

            String filePath = getFilesDir().getPath() + "/HighScore.csv";
            br = new BufferedReader(new FileReader(filePath));
            while ((sCurrentLine = br.readLine()) != null) {
                String replace="Jmeno: ";
                replace+=sCurrentLine.replace(",","\nScore: ");

                array.add(replace);
            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}