package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean firstCommandCorrect = false;
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("i:initialize, u:up, d:down, r:right, l:left, m s:move #space, p:print, c:current pos, q:quit ");
        System.out.println("Enter Command");

        while (!firstCommandCorrect) {
            String userCommand = myObj.nextLine();  // Read user input

            if (userCommand.equalsIgnoreCase("i")) {
                System.out.println("userCommand is: " + userCommand);  // Output user input

                Commands command = new Commands(userCommand.toLowerCase()
                );

                System.out.println("user command lowercase is: " + userCommand.toLowerCase());

                command.identifyCommand();

                firstCommandCorrect = true;
            } else {
                System.out.println("First command should be i, try again.");
            }
        }
    }
}
