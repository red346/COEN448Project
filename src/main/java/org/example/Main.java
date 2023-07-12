package org.example;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        //int[][] robot = new int[Integer.parseInt(args[1])][Integer.parseInt(args[1])];
        boolean firstCommandCorrect=true;
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("i:initialize, u:up, d:down, r:right, l:left, m s:move #space, p:print, c:current pos, q:quit ");
        System.out.println("Enter Command");


        String userCommand = myObj.nextLine();  // Read user input

        if(!userCommand.equals("i") || !userCommand.equals("I"))
        {
            System.out.println("First command should be i or I, try again.");
            firstCommandCorrect=false;
            while(!firstCommandCorrect)
            {
                System.out.println("Enter Command");
                userCommand = myObj.nextLine();  // Read user input
                if(userCommand.equals("i") || userCommand.equals("I"))
                {
                    firstCommandCorrect=true;
                }
                else
                {
                    System.out.println("First command should be i or I, try again.");
                }


            }
        }

        if(userCommand.equals("i") || userCommand.equals("I"))
        {
            System.out.println("userCommand is: " + userCommand);  // Output user input

            Commands command = new Commands(userCommand.toLowerCase());

            System.out.println("user command lowercase is: "+userCommand.toLowerCase());

            command.identifyCommand();
        }



    }
}



//Testing