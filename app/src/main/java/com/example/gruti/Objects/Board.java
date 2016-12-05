package com.example.gruti.Objects;

import com.example.gruti.com.example.gruti.logic.EDirection;

/**
 * Created by gruti on 05.12.2016.
 */

public class Board {


        private static final int boardX=150;
        private static final int boardY=50;

        private int posX;
        private int posY;
        private int speed=2;

        private EDirection dir;

        public Board(int posX,int posY)
        {
            this.posX=posX;
            this.posY=posY;
        }



        public void moveBoardLeft()
        {
            posX=posX-speed;
        }
        public void moveBoardRight()
        {
            posX=posX+speed;
        }



        public int getPosX()
        {
            return posX;
        }

        public int getPosY()
        {
            return posY;
        }
        public int getSpeed(){return speed;}

        public EDirection getDir() {
            return dir;
        }

        public void setDir(EDirection dir) {
            this.dir = dir;
        }


    }
