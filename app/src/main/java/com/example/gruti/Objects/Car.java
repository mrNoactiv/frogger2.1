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

import com.example.gruti.com.example.gruti.logic.EDirection;
import com.example.gruti.frogger21.R;

/**
 *
 * @author Tomáš
 */
public class Car {

    private static final int carX=40;
    private static final int carY=40;

    private int posX;
    private int posY;
    private int speed=10;


    EDirection dir;

    public Car(int posX,int posY)
    {
        this.posX=posX;
        this.posY=posY;
    }



    public void moveCarLeft()
    {

        posX=posX-speed;
    }



    public int getPosX()
    {
        return posX;
    }

    public int getPosY()
    {
        return posY;
    }

    public EDirection getDir() {
        return dir;
    }

    public void setDir(EDirection dir) {
        this.dir = dir;
    }


}
