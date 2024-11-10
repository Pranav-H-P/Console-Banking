package banking;
import java.util.ArrayList;

public class Account {

    private int currentBalance;
    private ArrayList<Transaction> transactionHistory;

    Account(){

        this.currentBalance = 0;
        this.transactionHistory = new ArrayList<>();

    }
    Account(int amount){

        this.currentBalance = amount;
        this.transactionHistory = new ArrayList<>();

        this.deposit(amount);
    }

    public void deposit(int amount){

        this.currentBalance += amount;

        Transaction newTransaction = new Transaction(1, amount);
        this.transactionHistory.add(newTransaction);

    }

    public boolean withdraw(int amount){

        if (amount > this.currentBalance){
            return false;
        }else{

            this.currentBalance -= amount;
            Transaction newTransaction = new Transaction(2, amount);
            this.transactionHistory.add(newTransaction);

            return true;

        }

    }

    public int getCurrentBalance(){
        return this.currentBalance;
    }

    public String getStatement(){

        StringBuilder statement = new StringBuilder();
        String transType = "";

        statement.append("Transaction Statement\n==================================\n\n");

        for (Transaction t: transactionHistory){

            if (t.tType == 1){
                transType = "Deposited";
            }else{
                transType = "Withdrew";
            }
            statement.append(String.format("%s %s on %s (ID: %s)", transType, t.tAmount, t.getTime(), t.tID));

        }

        return statement.toString();

    }
}
