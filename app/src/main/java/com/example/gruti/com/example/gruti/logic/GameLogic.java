package com.example.gruti.com.example.gruti.logic;


import com.example.gruti.Objects.Board;
import com.example.gruti.Objects.Car;
import com.example.gruti.Objects.Fly;
import com.example.gruti.Objects.Hero;
import com.example.gruti.frogger21.R;

import java.util.Iterator;
import java.util.List;

public class GameLogic {


    Event event;

    private int level;
    private int gameScore=0;
    private int lifes=3;

    //pozice žaby
    private int posX=102*3;
    private int posY=102*9;
    public Hero hero= new Hero(posX,posY,3);

    //list aut
    public List<Car> leftCars;
    private int leftCarPosX=102*6;
    private int lefCarPosY=102*8;

    public List<Car> rightCars;
    private int rightCarPosX=-102;
    private int rightCarPosY=102*7;


    //moucha
    public Fly fly=new Fly(102*2,0);


    //dřeva
    public List<Board> leftBoards;
    public List<Board> rightBoards;

    public GameLogic(Event event,int level)
    {
        this.event=event;
        this.level=level;
    }


    public boolean update(int gameTime) {

        hero.getPosX();
        hero.getPosY();
        carLogic();
        boardLogic();
        spawmCar(gameTime);
        spawmFly(gameTime);
        spawnBoard(gameTime);
        checkFly();


        if(checkWater() && checkBounds()&& checkHit()&& checkFire())
        {
            return true;
        }
            else
        {
            lifes--;
            return false;
        }

    }

    public void setLevel(int level)
    {
        this.level=level;
    }


    public boolean checkBounds()
    {
        boolean ok=true;

        if(hero.getPosX()>612 || hero.getPosX()<0 || hero.getPosY()>1100 || hero.getPosY()<=102)
        {
            ok=false;
            event.onDeathListener("Spadl jsi z platformy.");
            event.onSoundTrigger(R.raw.fall);
        }
        return ok;
    }


    public boolean checkWater()
    {
        boolean ok=true;
        if(hero.getPosY()<=510 && hero.getPosY()>306 || hero.getPosY()<=204 && hero.getPosY()>0  )
        {
            if(onLeftBoard() || onRightBoard())
            {
                ok=true;
            }
            else
            {
                event.onDeathListener("Neumíš plavat");
                event.onSoundTrigger(R.raw.water);
                ok=false;
            }

        }
        else
            ok=true;

        return ok;
    }
    public boolean checkHit()
    {
        boolean ok=true;


        for(Iterator<Car> leftCarsIterator = leftCars.iterator(); leftCarsIterator.hasNext();) {
            Car car = (Car) leftCarsIterator.next();

            if ((hero.getPosX() >= car.getPosX() && hero.getPosX() <= car.getPosX() + 70) && hero.getPosY() == car.getPosY()) {
                ok = false;
                event.onDeathListener("Srazilo tě auto");
                event.onSoundTrigger(R.raw.splat);
            }
        }

        for(Iterator<Car> rightCarsIterator = rightCars.iterator(); rightCarsIterator.hasNext();) {
            Car car = (Car) rightCarsIterator.next();

            if ((hero.getPosX() >= car.getPosX() && hero.getPosX() <= car.getPosX() + 70) && hero.getPosY() == car.getPosY()) {
                ok = false;
                event.onDeathListener("Srazilo tě auto");
                event.onSoundTrigger(R.raw.splat);
            }
        }
        return ok;




    }

    public boolean checkFly()
    {
        boolean hit=false;
        if( (hero.getPosX()>=fly.getPosX() && hero.getPosX()<=fly.getPosX()+50 ) && hero.getPosY()==fly.getPosY())
        {
            hit=true;
            gameScore=gameScore+(1*level);
            event.onDeathListener("Score +"+1*level);
            hero.setPosX(posX);
            hero.setPosY(posY);
            event.onSoundTrigger(R.raw.bite);
        }
        return hit;
    }
    public boolean checkFire()
    {
        boolean ok;
        int x=hero.getPosX();
        int y=hero.getPosY();

        if(((x >=0 && x<=102+30)||    (x>=(102*3-30)&& x<=(102*3+30))     ||     (x>=(102*6-30)&& x<=(102*6+30))    )&& y==0)
        {
            event.onDeathListener("Hoříš");
            event.onSoundTrigger(R.raw.fall);
            ok=false;
        }
        else
            ok=true;

        return ok;
    }


    public void spawmCar(int time)
    {
        if(time%(20)==0)//vykreslovani aut
        {
            int randCar=1 + (int)(Math.random()*5);
            switch(randCar)
            {
                case 1:
                    leftCars.add(new Car(leftCarPosX,lefCarPosY,level));
                    break;
                case 2:
                    rightCars.add(new Car(rightCarPosX,rightCarPosY,level));

            }
        }
    }

    public void carLogic()
    {
        for(Iterator<Car> carsIterator = leftCars.iterator(); carsIterator.hasNext();)
        {
            Car car = (Car) carsIterator.next();
            if(car.getPosX()<-50)
            {
                carsIterator.remove();
            }
            car.moveCarLeft();
        }
        for(Iterator<Car> carsIterator = rightCars.iterator(); carsIterator.hasNext();)
        {
            Car car = (Car) carsIterator.next();
            if(car.getPosX()>800)
            {
                carsIterator.remove();
            }
            car.moveCarRight();
        }




    }

    public void spawmFly(int time)
    {
        if(time%50==0)
        {
            int randFly=1 + (int)(Math.random()*3);
            switch(randFly)
            {
                case 1:
                    fly.setPosX(102*2);
                    fly.setPosY(0);
                    fly.setPosition(1);

                    break;
                case 2:
                    fly.setPosX(102*4);
                    fly.setPosY(0);
                    fly.setPosition(2);
                    break;

                case 3:
                    fly.setPosX(102*5);
                    fly.setPosY(0);
                    fly.setPosition(3);
                    break;
            }


        }
    }

    public void spawnBoard(int time)
    {
        if(time%(40)==0)
        {
            int randBoard=1 + (int)(Math.random()*4);//vykreslovani dřev
            switch(randBoard)
            {
                case 1:
                    leftBoards.add(new Board(102*6,102*2,level));

                    break;
                case 2:
                    rightBoards.add(new Board(0,102,level));
                    break;

                case 3:
                    leftBoards.add(new Board(102*6,102*4,level));
                    break;
                case 4:
                    rightBoards.add(new Board(0,102*5,level));
                    break;
            }
        }


    }
    public void boardLogic()
    {
        for(Iterator<Board> leftBoardIteratorMove = leftBoards.iterator(); leftBoardIteratorMove.hasNext();)
        {
            Board board = (Board) leftBoardIteratorMove.next();
            if(board.getPosX()<-102)
            {
                leftBoardIteratorMove.remove();
            }

            if( (hero.getPosX()>=board.getPosX() && hero.getPosX()<=board.getPosX()+204 ) && hero.getPosY()==board.getPosY())
            {
                hero.setPosX(hero.getPosX()-(board.getSpeed()));
            }
            board.moveBoardLeft();
        }


        for(Iterator<Board> rightBoardIteratorMove = rightBoards.iterator(); rightBoardIteratorMove.hasNext();)
        {
            Board board = (Board) rightBoardIteratorMove.next();

            if(board.getPosX()>800)
            {
                rightBoardIteratorMove.remove();
            }

            if( (hero.getPosX()>=board.getPosX() && hero.getPosX()<=board.getPosX()+204 ) && hero.getPosY()==board.getPosY())
            {
                hero.setPosX(hero.getPosX()+board.getSpeed());
            }
            board.moveBoardRight();
        }
    }

    public Board getActualLeft()
    {
        Board actual=null;
        for(Iterator<Board> onBoardIterator = leftBoards.iterator(); onBoardIterator.hasNext();)
        {
            Board board = (Board) onBoardIterator.next();

            if(( (hero.getPosX()>=board.getPosX() && hero.getPosX()<=board.getPosX()+204 ) && hero.getPosY()==board.getPosY()))
            {
                actual=board;
                //System.out.println("dostal jsi actual");
                break;
            }
            else
            {
                actual=null;
                //System.out.println("0");
            }
        }
        return actual;
    }

    public Board getActualRight()
    {
        Board actual=null;
        for(Iterator<Board> onBoardIterator = rightBoards.iterator(); onBoardIterator.hasNext();)
        {
            Board board = (Board) onBoardIterator.next();

            if(( (hero.getPosX()>=board.getPosX() && hero.getPosX()<=board.getPosX()+204 ) && hero.getPosY()==board.getPosY()))
            {
                actual=board;
                //System.out.println("dostal jsi actual");
                break;
            }
            else
            {
                actual=null;
                //System.out.println("0");
            }
        }
        return actual;
    }




    public boolean onLeftBoard()
    {
        boolean onboard=true;
        Board board;

        board=getActualLeft();
        if(board!=null && ((hero.getPosX()>=board.getPosX() && hero.getPosX()<=board.getPosX()+204 ) && hero.getPosY()==board.getPosY()))
        {
            onboard=true;
        }
        else
            onboard=false;

        return onboard;
    }

    public boolean onRightBoard()
    {
        boolean onboard=true;
        Board board;

        board=getActualRight();


        if(board!=null && ((hero.getPosX()>=board.getPosX() && hero.getPosX()<=board.getPosX()+204 ) && hero.getPosY()==board.getPosY()))
        {
            onboard=true;
        }
        else
            onboard=false;


        return onboard;

    }

    public int getLevel()
    {
        return level;
    }
    public int getScore()
    {
        return gameScore;
    }

    public int getLifes()
    {
        return lifes;
    }
}
