package banking;

import java.util.ArrayList;

public class User {

    private ArrayList<Account> userAccounts;
    private String name;
    public Account currAccount;

    public User(String name){
        this.userAccounts = new ArrayList<>();
        this.currAccount = null;
        this.name = name;
    }

    public int setAccount(int accountNo){
        if (accountNo - 1 >= 0 && accountNo - 1 < userAccounts.size()){
            this.currAccount = userAccounts.get(accountNo - 1);
            return 0;
        }else{
            return -1;
        }
    }
    public void clearAccount(){
        this.currAccount = null;
    }

    public ArrayList<Account> getUserAccounts(){
        return this.userAccounts;
    }

    public void createNewAccount(int type, double amount){
        Account newAcc = new Account(type, amount);
        this.userAccounts.add(newAcc);
    }
    public void createNewAccount(int type){
        Account newAcc = new Account(type);
        this.userAccounts.add(newAcc);
    }



}
