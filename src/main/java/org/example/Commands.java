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
    public boolean turnEast = false;
    public boolean turnWest = false;
    public boolean turnNorth = false;
    public boolean turnSouth = false;
    private boolean Quit;
    public int posX; //
    public int posY; // position of robot [x,y]
    public String command;
    public String PenDirection = "North";

    //Dummy initialization
    //public String[][] arrayString = new String[1][1];
    ArrayList<ArrayList<String> > x = new ArrayList<ArrayList<String> >();
    Robot bipbop ;

    public Commands(String command){
        this.command = command;
        Quit = false;
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

                    if(isTurnWest()){
                        setTurnWest(false);
                        setTurnEast(true);
                    }
                    else
                        setTurnEast(true);

                    switch(PenDirection){
                        case "North":
                            PenDirection = "East";
                            break;

                        case "South":
                            PenDirection = "West";
                            break;

                        case "East":
                            PenDirection = "South";
                            break;

                        case "West":
                            PenDirection = "North";
                            break;

                        default:
                            System.out.println("invalid");
                            break;
                    }
                    /*//check pos then
                    if(isTurnWest()){
                        setTurnWest(false);
                        setTurnEast(true);
                    }
                    else
                        setTurnEast(true);

                    */
                    break;
                case "l":

                    if(isTurnEast()){
                        setTurnWest(true);
                        setTurnEast(false);
                    }
                    else
                        setTurnWest(true);

                    switch(PenDirection){
                        case "North":
                            PenDirection = "West";
                            break;

                        case "South":
                            PenDirection = "East";
                            break;

                        case "East":
                            PenDirection = "North";
                            break;

                        case "West":
                            PenDirection = "South";
                            break;

                        default:
                            System.out.println("invalid");
                            break;
                    }

                    break;

                case "i":
                    Scanner userCommandtwo = new Scanner(System.in);  // Create a Scanner object
                    System.out.println("Enter array size");

                    String myarrsize = userCommandtwo.nextLine();  // Read user input
                    System.out.println("arraysize is: " + myarrsize);  // Output user input

                    this.sizeOfArray = Integer.parseInt(myarrsize);

                    InitializeArray(this.sizeOfArray);
                    break;

                case "p":
                    PrintArray();
                    break;

                case "c":
                    int[] RobotPosition=new int[2];
                    RobotPosition= bipbop.RobotPosition();
                    System.out.println("Robot's Position: "+RobotPosition[0]+","+RobotPosition[1]);

                    String Penstatus = "";
                    if (isPenDown() == false && isPenUp() == true) Penstatus = "Pen Up";
                    else if (isPenDown() == true && isPenUp() == false) Penstatus = "Pen Down";
                    System.out.println("Pen status: " + Penstatus);


                    if (isTurnEast() == false && isTurnWest() == true && isTurnNorth() == false && isTurnSouth() == false) PenDirection = "West";
                    else if (isTurnEast() == true && isTurnWest() == false && isTurnNorth() == false && isTurnSouth() == false) PenDirection = "East";
                    else if (isTurnEast() == false && isTurnWest() == false && isTurnNorth() == true && isTurnSouth() == false) PenDirection = "North";
                    else if (isTurnEast() == false && isTurnWest() == false && isTurnNorth() == false && isTurnSouth() == true) PenDirection = "South";
                    //
                    //
                    System.out.println("Pen Direction: " + PenDirection);

                    break;

                case "m":
                    Scanner userCommandthree = new Scanner(System.in);  // Create a Scanner object
                    System.out.println("Enter step size");

                    String stepsize = userCommandthree.nextLine();  // Read user input
                    System.out.println("step is: " + stepsize);  // Output user input

                    if(isPenDown()){
                        if (isTurnEast() == true && isTurnWest() == false && isTurnNorth() == false && isTurnSouth() == false){
                            MovetoEast(Integer.parseInt(stepsize));
                        }
                        else if (isTurnEast() == false && isTurnWest() == true && isTurnNorth() == false && isTurnSouth() == false) {
                            MovetoWest(Integer.parseInt(stepsize));
                        }
                        else if (isTurnEast() == false && isTurnWest() == false && isTurnNorth() == true && isTurnSouth() == false) {
                            MoveForward(Integer.parseInt(stepsize));
                        }
                        else if (isTurnEast() == false && isTurnWest() == false && isTurnNorth() == false && isTurnSouth() == true) {
                            MoveSouth(Integer.parseInt(stepsize));
                        }
                    }
                    //else if pen up the robot will automatically fly to the designated position without leaving any trail
                    else if (isPenUp()){
                        if (isTurnEast() == true && isTurnWest() == false && isTurnNorth() == false && isTurnSouth() == false){
                            MovetoEastUP(Integer.parseInt(stepsize));
                        }
                        else if (isTurnEast() == false && isTurnWest() == true && isTurnNorth() == false && isTurnSouth() == false) {
                            MovetoWestUP(Integer.parseInt(stepsize));
                        }
                        else if (isTurnEast() == false && isTurnWest() == false && isTurnNorth() == true && isTurnSouth() == false) {
                            MoveForwardUP(Integer.parseInt(stepsize));
                        }
                        else if (isTurnEast() == false && isTurnWest() == false && isTurnNorth() == true && isTurnSouth() == true) {
                            MoveSouthUP(Integer.parseInt(stepsize));
                        }
                    }
                    break;

                case"q":
                    Quit = true;
                    break;


                default:
                    System.out.println("input invalid");
                    break;
            }
            if(!Quit)
            {
                GetNewCommand();
            }

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

    public boolean isTurnEast() {
        return turnEast;
    }

    public void setTurnEast(boolean turnEast) {
        this.turnEast = turnEast;
    }
    public boolean isTurnWest() {
        return turnWest;
    }
    public boolean isTurnNorth() {return turnNorth; }
    public boolean isTurnSouth() {return turnSouth; }
    public void setTurnWest(boolean turnWest) {
        this.turnWest = turnWest;
    }

    public void InitializeArray(int sizeOfArray) {

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
        x.get(robotposition[0]).set(robotposition[1], "*");

        for(int i=0; i<x.size(); i++){
            for(int j=0; j<x.size(); j++){
                System.out.print( x.get(i).get(j)+ " ");

            }
            System.out.println();
        }
    }

    public void MovetoEast(int stepsize){

        //to make sure that the robot can walk in this direction, we need to compare the robot's current position to the stepsize and if the diff is between 0 -> arraysize then it's good to go
        int[] robotposition = bipbop.RobotPosition();
        int colLimit = robotposition[1] + stepsize;
        int maxColIndex = sizeOfArray - 1;
        for (int col = robotposition[1]; col < colLimit; col++) {
            if (col > maxColIndex) {
                System.out.println("Warning: Robot has gone out of bounds!");
                break;
            }
            x.get(robotposition[0]).set(col, "-");
            bipbop.RobotUpdatePosition(robotposition[0], col);
        }
    }


    public void MovetoEastUP(int stepsize){

        //to make sure that the robot can walk in this direction, we need to compare the robot's current position to the stepsize and if the diff is between 0 -> arraysize then it's good to go
        int[] robotposition = bipbop.RobotPosition();
        int colLimit = robotposition[1] + stepsize;
        int maxColIndex = sizeOfArray - 1;
        for (int col = robotposition[1]; col < colLimit; col++) {
            if (col > maxColIndex) {
                System.out.println("Robot is out of bounds!");
                break;
            }
            bipbop.RobotUpdatePosition(robotposition[0], col);
        }
    }

    public void MovetoWest(int stepsize){

        //to make sure that the robot can walk in this direction, we need to compare the robot's current position to the stepsize and if the diff is between 0 -> arraysize then it's good to go
        //another case when stepsize is 1 the robot stays in place, because of the for loop format
        int[] robotposition = bipbop.RobotPosition();
        int colLimit = robotposition[1] - stepsize;
        for (int col = robotposition[1]; col > colLimit; col--) {
            if (col < 0) {
                System.out.println("Robot is out of bounds!");
                break;
            }
            x.get(robotposition[0]).set(col, "-");
            bipbop.RobotUpdatePosition(robotposition[0], col);
        }
    }

    public void MovetoWestUP(int stepsize) {
        int[] robotposition = bipbop.RobotPosition();
        int colLimit = robotposition[1] - stepsize;
        for (int col = robotposition[1]; col > colLimit; col--) {
            if (col < 0) {
                System.out.println("Robot is out of bounds!");
                break;
            }
            bipbop.RobotUpdatePosition(robotposition[0], col);
        }
    }
    public void MoveForward(int stepsize) {
        int[] robotposition = bipbop.RobotPosition();
        int rowLimit = robotposition[0] - stepsize;
        for (int row = robotposition[0]; row > rowLimit; row--) {
            if (row < 0) {
                System.out.println("Robot is out of bounds!");
                break;
            }
            x.get(row).set(robotposition[1], "|");
            bipbop.RobotUpdatePosition(row, robotposition[1]);
        }
    }
    public void MoveForwardUP(int stepsize) {
        int[] robotposition = bipbop.RobotPosition();
        int rowLimit = robotposition[0] - stepsize;
        for (int row = robotposition[0]; row > rowLimit; row--) {
            if (row < 0) {
                System.out.println("Robot is out of bounds!");
                break;
            }
            bipbop.RobotUpdatePosition(row, robotposition[1]);
        }
    }
    public void MoveSouth(int stepsize){
        //to make sure that the robot can walk in this direction, we need to compare the robot's current position to the stepsize and if the diff is between 0 -> arraysize then it's good to go
        int[] robotposition = bipbop.RobotPosition();
        for(int row=robotposition[0]; row>=robotposition[0]-stepsize; row++){
            x.get(row).set(robotposition[1],"|");
            bipbop.RobotUpdatePosition(row,robotposition[1]);
        }
    }

    public void MoveSouthUP(int stepsize){
        //to make sure that the robot can walk in this direction, we need to compare the robot's current position to the stepsize and if the diff is between 0 -> arraysize then it's good to go
        int[] robotposition = bipbop.RobotPosition();
        for(int row=robotposition[0]; row>=robotposition[0]-stepsize; row++){
            bipbop.RobotUpdatePosition(row,robotposition[1]);
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
