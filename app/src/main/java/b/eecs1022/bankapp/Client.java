package b.eecs1022.bankapp;

public class Client {
    public String clientName ="";
    public double clientBalance =0.0;
    Transaction[] history ;
    public  int not;
    boolean error;
    String errorMsg;

    public Client(String name, double amount){
        //ONLY 10 Transactions ALLOWED
        history = new Transaction[10];
        not = 0;
        setClientName(name);
        setClientBalance(amount);
    }

    // Add a Deposit Transaction
    void Deposit(String service, double amount){

            //resetError();
            history[not] = new Transaction(service,amount);
            not++;
            clientBalance = clientBalance +amount;


    }
    // Add a Withdraw Transaction
    void Withdraw(String service, double amount){

            //resetError();
            history[not] = new Transaction(service,amount);
            not++;
            clientBalance = clientBalance -amount;

    }
    //
    public void setClientBalance(double clientBalance) {
        this.clientBalance = clientBalance;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public double getClientBalance() {
        return clientBalance;
    }

    public String getClientName() {
        return clientName;
    }


    /*void setErrorMsg(String errorMsg){
        this.error = true;
        this.errorMsg = "Error: "+errorMsg;
    }
    void resetError(){
        this.error =false;
        this.errorMsg = "";
    }*/

    public String toString(){
        if(error){
            return errorMsg;
        }
        else{
            return"\nClient "+ clientName+": $"+String.format("%.2f", clientBalance);
        }

    }
}
