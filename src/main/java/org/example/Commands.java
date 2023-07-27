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
    public boolean turnNorth = true;
    public boolean turnSouth = false;
    //public boolean turnRight = false;
    //public boolean turnLeft = false;
    private boolean Quit = false;
    public int posX; //
    public int posY; // position of robot [x,y]
    public String command;
    public String stepsize;
    public String myarrsize;
    private String LeaveSymbol = " ";
    public String PenDirection = "North";


    //Dummy initialization
    //public String[][] arrayString = new String[1][1];
    ArrayList<ArrayList<String>> x = new ArrayList<ArrayList<String>>();
    public Robot bipbop;

    public Commands(String i) {
        this.command = i;
//        this.sizeOfArray = arraysiz;
//        this.bipbop = new Robot(arraysiz);
    }

    public Robot getBipbop() {
        return bipbop;
    }

    public void setBipbop(Robot bipbop) {
        this.bipbop = bipbop;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
        identifyCommand();

    }


    public void identifyCommand() {

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
                    setTurnRight(true);
                    setTurnLeft(false);
                    break;

                case "l":
                    setTurnLeft(true);
                    setTurnRight(false);
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

                    // System.out.println("The robot's position: "+bipbop.posx+","+bipbop.posy);
                    String Penstatus = "";
                    if (isPenDown() == false && isPenUp() == true) Penstatus = "Pen Up";
                    else if (isPenDown() == true && isPenUp() == false) Penstatus = "Pen Down";
                    System.out.println("Pen status: " + Penstatus);

                    System.out.println("Pen Direction: " + PenDirection);
                    System.out.println("The robot's position: (" + (sizeOfArray - bipbop.posx) + "," + bipbop.posy + ")");

                    break;

                case "m":
                    Scanner userCommandthree = new Scanner(System.in);  // Create a Scanner object
                    System.out.println("Enter step size");

                    stepsize = userCommandthree.nextLine();  // Read user input
                    System.out.println("step is: " + stepsize);  // Output user input

                    if (isTurnEast() == true && isTurnWest() == false && isTurnNorth() == false && isTurnSouth() == false) {
                        MovetoRight(Integer.parseInt(stepsize));
                    } else if (isTurnEast() == false && isTurnWest() == true && isTurnNorth() == false && isTurnSouth() == false) {
                        MovetoLeft(Integer.parseInt(stepsize));
                    } else if (isTurnEast() == false && isTurnWest() == false && isTurnNorth() == true && isTurnSouth() == false) {
                        MoveForward(Integer.parseInt(stepsize));
                    } else if (isTurnEast() == false && isTurnWest() == false && isTurnNorth() == false && isTurnSouth() == true) {
                        MoveBackwards(Integer.parseInt(stepsize));
                    }
                    //else if pen up the robot will automatically fly to the designated position without leaving any trail
                    break;

                case "q":
                    Quit = true;
                    System.exit(0);
                    break;

                default:
                    System.out.println("input invalid");
                    break;
            }

            GetNewCommand();

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

    public boolean isTurnNorth() {
        return turnNorth;
    }

   public boolean isTurnSouth() {
        return turnSouth;
    }

    public void setTurnRight(boolean turnRight) {
        if (turnRight) {
            switch (PenDirection) {
                case "North":
                    PenDirection = "East";
                    this.turnNorth = false;
                    this.turnEast = turnRight;
                    break;

               case "South":
                    PenDirection = "West";
                    this.turnSouth = false;
                    this.turnWest = turnRight;
                    break;

                case "East":
                    PenDirection = "South";
                    this.turnEast = false;
                    this.turnSouth = turnRight;
                    break;

                case "West":
                    PenDirection = "North";
                    this.turnWest = false;
                    this.turnNorth = turnRight;
                    break;

                default:
                    System.out.println("invalid");
                    break;
            }
        }

    }

    public boolean isTurnWest() {
        return turnWest;
    }

    public void setTurnLeft(boolean turnLeft) {
        if (turnLeft) {
            switch (PenDirection) {
                case "North":
                    PenDirection = "West";
                    this.turnNorth = false;
                    this.turnWest = turnLeft;
                    break;

                case "South":
                    PenDirection = "East";
                    this.turnSouth = false;
                    this.turnEast = turnLeft;
                    break;

                case "East":
                    PenDirection = "North";
                    this.turnEast = false;
                    this.turnNorth = turnLeft;
                    break;

                case "West":
                    PenDirection = "South";
                   this.turnWest = false;
                    this.turnSouth = turnLeft;
                    break;

                default:
                    System.out.println("invalid");
                    break;
            }
        }
    }

    public void InitializeArray(int sizeOfArray) {

        for (int row = 0; row < sizeOfArray; row++) {
            x.add(new ArrayList<String>());
            for (int col = 0; col < sizeOfArray; col++) {
                x.get(row).add(col, " ");
            }
        }

        bipbop = new Robot(sizeOfArray);

    }

    public void PrintArray() {
        int[] robotposition = bipbop.RobotPosition();
        int[] robotpreviousposition = bipbop.getRobotPreviousPosition();
        x.get(robotpreviousposition[0]).set(robotpreviousposition[1], LeaveSymbol);

        if (isPenUp()) LeaveSymbol = " ";

        x.get(robotposition[0]).set(robotposition[1], "*");

        for (int i = 0; i < x.size(); i++) {
            for (int j = 0; j < x.size(); j++) {
                System.out.print(x.get(i).get(j) + " ");

            }
            System.out.println();
        }
    }

    public void MovetoRight(int stepsize) {

        //to make sure that the robot can walk in this direction, we need to compare the robot's current position to the stepsize and if the diff is between 0 -> arraysize then it's good to go
        int[] robotposition = bipbop.RobotPosition();
        int col = robotposition[1];
        int limit = 0;
        if (sizeOfArray != 0) {
            limit = sizeOfArray - 1;
        } else {
            limit = 0;
        }

        if (col + stepsize > limit) {
            System.out.println("Robot is out of bounds");
            col = limit;
            stepsize = limit - robotposition[1];
        } else {
            col += stepsize;
        }
        x.get(robotposition[0]).set(robotposition[1], "*");
        col = 0;
        for (col = robotposition[1]; col < robotposition[1] + stepsize; col++) {

           if (isPenDown()) {
                x.get(robotposition[0]).set(col, "*");
            }
        }

        System.out.println("The new y value is: " + col);
        bipbop.RobotUpdatePosition(robotposition[0], col);

        if (isPenDown()) LeaveSymbol = "*";
    }


    public void MovetoLeft(int stepsize) {

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

        x.get(robotposition[0]).set(robotposition[1], "*");
        col = posY;
        for (col = robotposition[1]; col > robotposition[1] - stepsize; col--) {
            if (isPenDown()) {
                x.get(robotposition[0]).set(col, "*");
            }
        }
        bipbop.RobotUpdatePosition(robotposition[0], col);

        if (isPenDown()) LeaveSymbol = "*";
    }

    public void MoveForward(int stepsize) {

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

        x.get(robotposition[0]).set(robotposition[1], "*");
        row = 0;
        for (row = robotposition[0]; row > robotposition[0] - stepsize; row--) {
            if (isPenDown()) {
                x.get(row).set(robotposition[1], "*");
            }
        }
        bipbop.RobotUpdatePosition(row, robotposition[1]);

        if (isPenDown()) LeaveSymbol = "*";
    }

    public void MoveBackwards(int stepsize) {

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

        x.get(robotposition[0]).set(robotposition[1], "*");
        int row = 0;
        for (row = robotposition[0]; row < robotposition[0] + stepsize; row++) {
            if (isPenDown()) {
                x.get(row).set(robotposition[1], "*");
            }
        }
        bipbop.RobotUpdatePosition(row, robotposition[1]);

        if (isPenDown()) LeaveSymbol = "*";
    }

    public void GetNewCommand() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter Command");

        String userCommand = myObj.nextLine();  // Read user input
        System.out.println("userCommand is: " + userCommand);  // Output user input

        this.command = userCommand.toLowerCase();
    }

}
