package org.example;

public class Robot {

    //The class robot will be used to only identify the position of the robot
    //I still don't know how will it be implemented

    int posy;
    int posx;
    public Robot(int arraysize){
        this.posy = 0;
        this.posx = arraysize-1;
    }

    public int[] RobotPosition(){
        int[] RobotPosition = new int[2];
        RobotPosition[0] = posx;
        RobotPosition[1] = posy;
        return RobotPosition;
    }

    public void RobotUpdatePosition(int x, int y){
        this.posx = x;
        this.posy = y;
    }
}
