import banking.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static boolean isInteger(String str) { // check if input is integer
        return str.matches("-?\\d+");
    }
    public static boolean isDouble(String str) { // check if input is a double
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static void main(String[] args) {

        Scanner newInp = new Scanner(System.in);

        boolean exitRequest = false;

        String choiceString;
        int choiceInt;


        User currUser;
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
        userReference.get("Pranav").clearAccount();

        LocalDate today = LocalDate.now(); // Check if today is the 1st of the month to add monthly interest
        if (today.getDayOfMonth() == 1) {
            Account.updateAllInterest(); // update monthly interest for all accounts, sets interest added to true
        }else{
            Account.setInterestFlagFalse(); // sets interest added to false when date is not 1st
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

            choiceString = newInp.nextLine();
            if (isInteger(choiceString)){
                choiceInt = Integer.parseInt(choiceString);
            }else{
                System.out.println("\nPlease provide valid input!!");
                continue;
            }

            if (choiceInt == 1){

                System.out.print("Please enter your username: ");

                String uname = newInp.nextLine();
                System.out.print("Please enter your password: ");
                String pswd = newInp.nextLine();

                if (userLoginMap.containsKey(uname)){
                    if (userLoginMap.get(uname).equals(pswd)){
                        System.out.println("\n\nWSuccessfully Logged In!\nWelcome " + uname);
                        currUser = userReference.get(uname);


                        while (currUser != null){

                            System.out.println("\n\nHere are your accounts");

                            ArrayList<Account> userAccs = currUser.getUserAccounts();

                            int count = 1;
                            for (Account a: userAccs){

                                System.out.printf("Index: [%s]\tAccount Id: %s\tAccount Type: %s\n", count,
                                        a.getAccountID(), a.getAccountType());

                                count += 1;

                            }
                            System.out.println("Enter the Index Number to select an account.\nEnter 0 to start an account");
                            System.out.println("Enter -1 to log out");
                            System.out.print("Choice: ");

                            choiceString = newInp.nextLine();

                            if (isInteger(choiceString)){
                                choiceInt = Integer.parseInt(choiceString);

                                if (choiceInt == -1){

                                    currUser = null;
                                    System.out.println("Logging out!!!\n");
                                    continue;

                                }else if (choiceInt == 0){

                                    double initialDeposit = 0.0;
                                    int accType;

                                    System.out.println("Enter [1] for savings account\nEnter [2] for checking account");
                                    System.out.print("Choice: ");

                                    choiceString = newInp.nextLine();
                                    if (isInteger(choiceString)){
                                        accType = Integer.parseInt(choiceString);
                                        if (accType != 1 && accType != 2){
                                            System.out.println("Invalid option!!!");
                                            continue;
                                        }
                                    }else{
                                        System.out.println("Invalid input!!!");
                                        continue;
                                    }

                                    System.out.print("Enter the initial deposit: ");

                                    choiceString = newInp.nextLine();
                                    if (isDouble(choiceString)){
                                        initialDeposit = Double.parseDouble(choiceString);
                                        if (initialDeposit < 0){
                                            System.out.println("Please enter a positive number!");
                                            continue;
                                        }
                                    }

                                    currUser.createNewAccount(accType, initialDeposit);
                                    System.out.println("Account Created Successfully!!");

                                }else{
                                    if (currUser.setAccount(choiceInt) != -1){

                                        System.out.printf("Account Index %s (Account ID: %s) successfully selected!\n",
                                                choiceInt, currUser.currAccount.getAccountID());

                                    }else{
                                        System.out.println("Please enter a valid Index number!!");
                                        continue;
                                    }
                                }

                            }else{
                                System.out.println("\nPlease provide valid input!!");
                                continue;
                            }


                            while (currUser.currAccount != null){
                                System.out.println("Available operations\n[1] - View Balance\n[2] - Deposit\n[3] - Withdraw");
                                System.out.println("[4] - Generate Statement\n[-1] - Exit Account");
                                System.out.print("Choice: ");
                                choiceString = newInp.nextLine();

                                if (isInteger(choiceString)){
                                    choiceInt = Integer.parseInt(choiceString);

                                    if (choiceInt == -1){
                                        currUser.clearAccount();
                                        break;
                                    }else if (choiceInt == 1){
                                        System.out.printf("The current Account balance is: %s\n", currUser.currAccount.getCurrentBalance());

                                    }else if (choiceInt == 2){
                                        System.out.print("Enter the amount to be deposited: ");

                                        double depAmount;
                                        choiceString = newInp.nextLine();
                                        if (isDouble(choiceString)){
                                            depAmount = Double.parseDouble(choiceString);
                                            if (depAmount < 0){
                                                System.out.println("Please enter a positive number!");
                                                continue;
                                            }

                                            currUser.currAccount.deposit(depAmount);
                                        }else{
                                            System.out.println("Invalid Input!!");
                                        }


                                    }else if (choiceInt == 3){
                                        System.out.print("Enter the amount to be withdrawn: ");

                                        double withAmount;
                                        choiceString = newInp.nextLine();
                                        if (isDouble(choiceString)){
                                            withAmount = Double.parseDouble(choiceString);
                                            if (withAmount < 0){
                                                System.out.println("Please enter a positive number!");
                                                continue;
                                            }

                                            if (currUser.currAccount.withdraw(withAmount)){
                                                System.out.println("Withdrawal Successful!!!");
                                            }else{
                                                System.out.println("Withdrawal Failed!! Please check your balance.");
                                            }
                                        }else{
                                            System.out.println("Invalid Input!!!");
                                        }

                                    }else if (choiceInt == 4){
                                        System.out.println(currUser.currAccount.getStatement());
                                    }else{
                                        System.out.println("Invalid Option!!");
                                    }

                                }else{
                                    System.out.println("Invalid Input!!");
                                }

                            }



                        }




                    }else{
                        System.out.println("Wrong Password!!");
                    }
                }else{
                    System.out.println("\nUser does not exist!!! Please check your details or sign up!!");
                }

            } else if (choiceInt == 2) {

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

            }else if (choiceInt == -1){

                exitRequest = true;

            }else{

                System.out.println("\nInvalid Choice!! Please enter 1 or 2!");

            }

        }


    }
}   