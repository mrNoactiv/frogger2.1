package com.example.gruti.Objects;

/**
 * Created by gruti on 01.12.2016.
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import com.example.gruti.frogger21.R;

/**
 *
 * @author Tomáš
 */
public class Car {

    public int direction = 0;
    private int pos;
    private int speed=10;



    public Car(int pos)
    {
        this.pos=pos;

    }


    public void moveCarLeft()
    {

        pos=pos-speed;
    }



    public int getPos()
    {
        return pos;
    }




}
