package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Commands {
    //Pen up => U|u
    //Pen down => D|d
    //Turn Right => R|r (facing: east)
    //Turn Left => L|l (facing: west)
    //Move forward s spaces (s is a non-negative integer) => [M s|m s]
    //Print the N by N array and display the indices => [P|p]
    //Print current position of the pen and whether it is up or down and its facing direction => [C|c]
    //Stop the program => [Q|q]
    //[I n|i n] => Initialize the system:
    // The values of the array floor are zeros and the robot is back to [0, 0], pen up and facing north.
    // n size of the array, an integer greater than zero

    public int sizeOfArray; //n
    public boolean penDown = false;
    public boolean penUp = true;
    public boolean turnRight = false;
    public boolean turnLeft = false;
    private boolean Quit = false;
    public int posX; //
    public int posY; // position of robot [x,y]
    public String command;
    public String stepsize;
    public String myarrsize;
    private String LeaveSymbol = "0";

    //Dummy initialization
    //public String[][] arrayString = new String[1][1];
    public ArrayList<ArrayList<String> > x = new ArrayList<ArrayList<String> >();
    Robot bipbop ;

    public Commands(String command){
        this.command = command;
//        this.sizeOfArray = arraysiz;
//        this.bipbop = new Robot(arraysiz);
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
        identifyCommand();

    }

    public void identifyCommand()
    {
        while (!Quit) {
            switch (command) {
                case "u":
                    setPenUp(true);
                    setPenDown(false);
                    break;
                case "d":
                    setPenDown(true);
                    setPenUp(false);
                    break;
                case "r":
                    if(isTurnLeft()){
                        setTurnLeft(false);
                        setTurnRight(false);
                    }
                    else
                        setTurnRight(true);

                    break;

                case "l":
                    if(isTurnRight()){
                        setTurnLeft(false);
                        setTurnRight(false);
                    }
                    else
                        setTurnLeft(true);

                    break;

                case "i":
                    Scanner userCommandtwo = new Scanner(System.in);  // Create a Scanner object
                    System.out.println("Enter array size");

                    myarrsize = userCommandtwo.nextLine();  // Read user input
                    System.out.println("arraysize is: " + myarrsize);  // Output user input

                    this.sizeOfArray = Integer.parseInt(myarrsize);

                    InitializeArray(this.sizeOfArray);
                    break;

                case "p":
                    PrintArray();
                    break;

                case "c":
                    String Penstatus = "";
                    if (!isPenDown() && isPenUp()) Penstatus = "Pen Up";
                    else if (isPenDown() && !isPenUp()) Penstatus = "Pen Down";
                    System.out.println("Pen status: " + Penstatus);

                    String PenDirection = "";
                    if (!isTurnRight() && isTurnLeft()) PenDirection = "Left";
                    else if (isTurnRight() && !isTurnLeft()) PenDirection = "Right";
                    System.out.println("Pen Direction: " + PenDirection);

                    break;

                case "m":
                    Scanner userCommandthree = new Scanner(System.in);  // Create a Scanner object
                    System.out.println("Enter step size");

                    stepsize = userCommandthree.nextLine();  // Read user input
                    System.out.println("step is: " + stepsize);  // Output user input

                    if (isTurnRight() && !isTurnLeft()){
                        MovetoRight(Integer.parseInt(stepsize));
                    }
                    else if (!isTurnRight() && isTurnLeft()) {
                        MovetoLeft(Integer.parseInt(stepsize));
                    }
                    else if (!isTurnRight() && !isTurnLeft()) {
                        MoveForward(Integer.parseInt(stepsize));
                    }
                    //else if pen up the robot will automatically fly to the designated position without leaving any trail
                    break;

                case"q":
                    Quit = true;
                    break;
            }
            GetNewCommand();
            if(Quit)
                break;
        }
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getSizeOfArray() {
        return sizeOfArray;
    }

    // public void setTurnRight(boolean turnRight) {
    //     this.turnRight = turnRight;
    // }

    public boolean isPenDown() {
        return penDown;
    }

    public void setPenDown(boolean penDown) {
        this.penDown = penDown;
    }

    public boolean isPenUp() {
        return penUp;
    }

    public void setPenUp(boolean penUp) {
        this.penUp = penUp;
    }

    public boolean isTurnRight() {
        return turnRight;
    }

    public void setTurnRight(boolean turnRight) {
        this.turnRight = turnRight;
    }

    public boolean isTurnLeft() {
        return turnLeft;
    }

    public void setTurnLeft(boolean turnLeft) {
        this.turnLeft = turnLeft;
    }

     public void InitializeArray(int sizeOfArray) {

/*        String[][] temparray = new String[sizeOfArray][sizeOfArray];
         for(int i=0; i<temparray.length; i++){
             for(int j=0; j<temparray.length; j++){
                 temparray[i][j] = "0";
             }
         }

         for(int i=0; i<temparray.length; i++){
             for(int j=0; j<temparray.length; j++){
                 System.out.print(temparray[i][j] + " ");

             }
             System.out.println();
         }
         this.arrayString = temparray;*/

         for(int row=0; row<sizeOfArray; row++){
             x.add(new ArrayList<String>());
             for(int col=0; col<sizeOfArray; col++){
                 x.get(row).add(col,"0");
             }
         }

         bipbop = new Robot(sizeOfArray);

     }

    public void PrintArray(){
        int[] robotposition = bipbop.RobotPosition();
        int[] robotpreviousposition = bipbop.getRobotPreviousPosition();
        x.get(robotpreviousposition[0]).set(robotpreviousposition[1],LeaveSymbol);

        if(isPenUp())   LeaveSymbol = "0";

        x.get(robotposition[0]).set(robotposition[1], "*");

        for(int i=0; i<x.size(); i++){
            for(int j=0; j<x.size(); j++){
                System.out.print( x.get(i).get(j)+ " ");

            }
            System.out.println();
        }
    }

    public void MovetoRight(int stepsize){

        //to make sure that the robot can walk in this direction, we need to compare the robot's current position to the stepsize and if the diff is between 0 -> arraysize then it's good to go
        int[] robotposition = bipbop.RobotPosition();
        int col = robotposition[1];
        int limit = sizeOfArray - 1;
        if (col + stepsize > limit) {
            System.out.println("Robot is out of bounds");
            col = limit;
            stepsize = limit - robotposition[1];
        } else {
            col += stepsize;
        }


        x.get(robotposition[0]).set(robotposition[1], "_");

        for (int i = robotposition[1]; i < col; i++) {
            if (isPenDown()) {
                x.get(robotposition[0]).set(i, "-");
            }
        }

        bipbop.RobotUpdatePosition(robotposition[0],col);

        if(isPenDown()) LeaveSymbol = "-";
    }

    public void MovetoLeft(int stepsize){

        //to make sure that the robot can walk in this direction, we need to compare the robot's current position to the stepsize and if the diff is between 0 -> arraysize then it's good to go
        //another case when stepsize is 1 the robot stays in place, because of the for loop format
        int[] robotposition = bipbop.RobotPosition();
        int col = robotposition[1];
        int limit = 0;
        if (col - stepsize < limit) {
            System.out.println("Robot is out of bounds");
            col = limit;
            stepsize = robotposition[1];
        } else {
            col -= stepsize;
        }

        x.get(robotposition[0]).set(robotposition[1], "_");

        for (int i = robotposition[1]; i > col; i--) {
            if (isPenDown()) {
                x.get(robotposition[0]).set(i, "-");
            }
        }
        bipbop.RobotUpdatePosition(robotposition[0], col);

        if(isPenDown()) {LeaveSymbol = "-";
    }}

    public void MoveForward(int stepsize){

        //to make sure that the robot can walk in this direction, we need to compare the robot's current position to the stepsize and if the diff is between 0 -> arraysize then it's good to go
        int[] robotposition = bipbop.RobotPosition();
        int row = robotposition[0];
        int limit = 0;

        // Check if moving forward will exceed the array bounds
        if (row - stepsize < limit) {
            System.out.println("Robot is out of bounds");
            row = limit;
            stepsize = robotposition[0];
        } else {
            row -= stepsize;
        }

        x.get(robotposition[0]).set(robotposition[1], "|");

        for (int i = robotposition[0]; i > row; i--) {
            if (isPenDown()) {
                x.get(i).set(robotposition[1], "|");
            }
        }

        bipbop.RobotUpdatePosition(row, robotposition[1]);

        if (isPenDown()) {
            LeaveSymbol = "|";
        }
    }

    private void GetNewCommand(){
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter Command");

        String userCommand = myObj.nextLine();  // Read user input
        System.out.println("userCommand is: " + userCommand);  // Output user input

        this.command = userCommand.toLowerCase();
    }

}
