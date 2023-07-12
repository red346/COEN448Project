package org.example;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        //int[][] robot = new int[Integer.parseInt(args[1])][Integer.parseInt(args[1])];

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter Command");

        String userCommand = myObj.nextLine();  // Read user input
        System.out.println("userCommand is: " + userCommand);  // Output user input


        Commands command = new Commands(userCommand.toLowerCase());


        command.identifyCommand();






        //System.out.println("Hello world!");
    }
}

//Testing