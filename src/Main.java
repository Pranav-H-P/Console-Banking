import banking.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static boolean isInteger(String str) { // check if input is integer
        return str.matches("-?\\d+");
    }

    public static void main(String[] args) {

        Scanner newInp = new Scanner(System.in);

        boolean exitRequest = false;

        String logInChoiceSt;
        int logInChoice = 9;


        User currUser = null;
        HashMap<String, String> userLoginMap = new HashMap<>();
        HashMap<String, User> userReference =  new HashMap<>();

        userLoginMap.put("Pranav", "testing@123"); // creating one user just for testing purposes
        userReference.put("Pranav", new User("Pranav"));
        userReference.get("Pranav").createNewAccount(1, 1000);
        userReference.get("Pranav").setAccount(1);
        userReference.get("Pranav").currAccount.deposit(100);
        userReference.get("Pranav").currAccount.deposit(350);
        userReference.get("Pranav").currAccount.withdraw(40);
        userReference.get("Pranav").currAccount.withdraw(340);
        userReference.get("Pranav").currAccount.withdraw(600);

        LocalDate today = LocalDate.now(); // Check if today is the 1st of the month to add monthly interest
        if (today.getDayOfMonth() == 1) {
            Account.updateAllInterest();
        }

        System.out.println("Welcome to Pranav Bank Ltd!");
        System.out.println("============================");

        while (!exitRequest) {

            System.out.println("\n\nOptions: (Enter the number)");
            System.out.println("Log In (1)");
            System.out.println("Sign Up (2)");
            System.out.println("Exit (-1)");
            System.out.println("============================");
            System.out.print("Please enter your choice: ");

            logInChoiceSt = newInp.nextLine();
            if (isInteger(logInChoiceSt)){
                logInChoice = Integer.parseInt(logInChoiceSt);
            }else{
                System.out.println("\nPlease provide valid input!!");
                continue;
            }

            if (logInChoice == 1){

                System.out.print("Please enter your username: ");

                String uname = newInp.nextLine();
                System.out.print("Please enter your password: ");
                String pswd = newInp.nextLine();

                if (userLoginMap.containsKey(uname)){
                    if (userLoginMap.get(uname).equals(pswd)){
                        System.out.println("\n\nWelcome " + uname);

                        boolean loggedIn = true;

                        while (loggedIn){

                            System.out.println("");

                        }




                    }else{
                        System.out.println("Wrong Password!!");
                    }
                }else{
                    System.out.println("\nUser does not exist!!! Please check your details or sign up!!");
                }

            } else if (logInChoice == 2) {

                System.out.print("Please enter your username: ");
                String uname = newInp.nextLine();
                System.out.print("Please enter your password: ");
                String pswd1 = newInp.nextLine();
                System.out.print("Please enter your password Again: ");
                String pswd2 = newInp.nextLine();

                if (pswd1.equals(pswd2)){
                    userLoginMap.put(uname, pswd1);
                    System.out.println("\nUser added successfully!! Please log in now.");

                }else{
                    System.out.println("\nPasswords Do Not Match!!! Please try again.");
                }

            }else if (logInChoice == -1){

                exitRequest = true;

            }else{

                System.out.println("\nInvalid Choice!! Please enter 1 or 2!");

            }

        }


    }
}   