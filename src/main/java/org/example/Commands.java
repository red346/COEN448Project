package org.example;

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
    public boolean penDown;
    public boolean penUp;
    public boolean turnRight;
    public boolean turnLeft;
    public int posX; //
    public int posY; // position of robot [x,y]
    public String command;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
        identifyCommand();

    }

    public void identifyCommand()
    {
        switch (command)
        {
            case "U":
            case "u":
                setPenUp(true);
                setPenDown(false);
                break;
            case "D":
            case "d":
                setPenDown(true);
                setPenUp(false);
                break;
            case "R":
            case "r":
                setTurnRight(true);
                setTurnLeft(false);
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

    public void setSizeOfArray(int sizeOfArray) {
        this.sizeOfArray = sizeOfArray;
    }

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


}
