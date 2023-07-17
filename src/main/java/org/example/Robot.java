package org.example;

public class Robot {

    //The class robot will be used to only identify the position of the robot
    //I still don't know how will it be implemented

    public int posy;
    public int posx;

    int[] RobotPreviousPostiton = new int[2];
    public Robot(int arraysize){
        this.posy = 0;
        this.posx = arraysize-1;
        RobotPreviousPostiton[0] = posx;
        RobotPreviousPostiton[1] = posy;
    }

    public int[] RobotPosition(){
        int[] RobotPosition = new int[2];
        RobotPosition[0] = posx;
        RobotPosition[1] = posy;
        return RobotPosition;
    }

    public void RobotUpdatePosition(int x, int y){
        this.RobotPreviousPostiton = RobotPosition();
        this.posx = x;
        this.posy = y;
    }

    public int[] getRobotPreviousPosition(){
        return RobotPreviousPostiton;
    }
}
