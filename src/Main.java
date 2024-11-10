import banking.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Scanner newInp = new Scanner(System.in);

        boolean loggedIn = false;
        boolean exitRequest = false;

        int logInChoice = 0;

        User currUser = null;
        ArrayList<User> allUsers = new ArrayList<>();

        System.out.println("Welcome to Pranav Bank Ltd!");
        System.out.println("============================");



        while (!loggedIn && !exitRequest) {

            System.out.println("Options: (Enter the number)");
            System.out.println("Log In (1)");
            System.out.println("Sign In (2)");
            System.out.println("Exit (-1)");
            System.out.println("============================");
            System.out.println("Please enter your choice: ");

            logInChoice = newInp.nextInt();

            if (logInChoice == 1){



            } else if (logInChoice == 2) {



            }else if (logInChoice == -1){

                exitRequest = true;

            }else{

                System.out.println("Invalid Choice!! Please enter 1 or 2!");

            }

        }


    }
}   