package com.example.gruti.frogger21;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by gruti on 08.12.2016.
 */

public class ScoreFragment extends Fragment {
    int score;
    TextView mTextView;
    public ScoreFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle saveInstanceState )
    {


        //return inflater.inflate(R.layout.fragment_score_fragment,container,false);
        View view = inflater.inflate(R.layout.fragment_score_fragment, container, false);
        mTextView = (TextView) view.findViewById(R.id.textViewFragment);
        return view;

    }

    public void setScore(int score)
    {
        this.score=score;
    }

    public TextView getTextView(){

        return mTextView;
    }


}
