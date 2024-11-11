package banking;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
* A class for holding individual transaction data
* It keeps track of the total number of transactions done (hence giving each transaction a unique ID)
* Supposed to be a simple datatype but with the automatic ID
* */


public class Transaction {

    private static int transactionCount = 0; // To keep track of total transactions made (for assigning ID)

    public LocalDateTime tTime; // Time of transaction occuring
    int tType; // Type of transaction (1 - deposit, 2 - withdrawal, 3 - interest)
    int tID; // ID of transaction
    double tAmount; // Amount of money in transaction
    double resultAmount;


    static int getTotalTransactions(){
        return transactionCount;
    }

    Transaction(int tType, double tAmount, double resultAmount){

        this.tID = transactionCount;
        this.tType = tType;
        this.tAmount = tAmount;
        this.tTime = LocalDateTime.now();
        this.resultAmount = resultAmount;

        transactionCount += 1;

    }

    public String getHumanTime(){ // for returning human readable date and time

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return this.tTime.format(formatter);

    }

}
