package banking;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Account {


    private static int accountCounts = 0;
    private static ArrayList<Account> AllAcounts = new ArrayList<>();

    private int accountID;
    private int accountType; // 1 - savings, 2 - checking
    private double currentBalance;
    private ArrayList<Transaction> transactionHistory;



    public static void updateAllInterest(){
        for (Account a: AllAcounts){
            a.addInterest();
        }
    }

    Account(int type){

        this.currentBalance = 0;
        this.transactionHistory = new ArrayList<>();
        this.accountID = accountCounts;
        this.accountType = type;
        AllAcounts.add(this);

        accountCounts += 1;

    }
    Account(int type, double amount){

        this.currentBalance = amount;
        this.transactionHistory = new ArrayList<>();

        this.deposit(amount);
        this.accountID = accountCounts;
        this.accountType = type;
        AllAcounts.add(this);

        accountCounts += 1;
    }

    public void deposit(double amount){

        this.currentBalance += amount;

        Transaction newTransaction = new Transaction(1, amount, this.currentBalance);
        this.transactionHistory.add(newTransaction);

    }

    public boolean withdraw(double amount){

        if (amount > this.currentBalance){
            return false;
        }else{

            this.currentBalance -= amount;
            Transaction newTransaction = new Transaction(2, amount, this.currentBalance);
            this.transactionHistory.add(newTransaction);

            return true;

        }

    }

    public double getCurrentBalance(){

        return this.currentBalance;

    }

    private void addInterest(){
        LocalDate today = LocalDate.now();
        LocalDate lastMonth = today.minusMonths(1);

        double interest = 0;
        double percent = 0.5;

        int ind = this.transactionHistory.size();

        if (ind == 0){
            return;
        }

        ind -= 1;

        while (ind >= 0){

            Transaction trans = this.transactionHistory.get(ind);
            LocalDate transTime = trans.tTime.toLocalDate();

            if (transTime.isEqual(lastMonth) || transTime.isAfter(lastMonth)){

                interest += trans.resultAmount * percent;

                // check if current index is also of same date, then skip forward
                while (ind >=0 && transTime.isEqual( this.transactionHistory.get(ind).tTime.toLocalDate() )){
                    --ind;
                }

            }else{
                break;
            }

        }
        this.currentBalance += interest;
        Transaction newTransaction = new Transaction(3, interest, this.currentBalance);
        this.transactionHistory.add(newTransaction);
    }

    public String getStatement(){

        StringBuilder statement = new StringBuilder();
        String transType = "";

        statement.append("Transaction Statement\n==================================\n\n");

        for (Transaction t: transactionHistory){

            if (t.tType == 1){
                transType = "Deposited";
            }else if (t.tType == 2){
                transType = "Withdrew";
            }else{
                transType = "Received Interest on";
            }
            statement.append(String.format("%s %s on %s (ID: %s)", transType, t.tAmount, t.getHumanTime(), t.tID));

        }

        return statement.toString();

    }
}
