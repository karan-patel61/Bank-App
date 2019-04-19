package b.eecs1022.bankapp;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    Client[] clients;
    public int noc;
    boolean error;
    String errorMsg;

    boolean statement = false;
    String statMsg;

    public Bank(){
        clients = new Client[6];
        noc = 0;
    }
    // ADDING A CLIENT
    void addClient(String clientName , double clientBalance){
        // MAX NUMBER OF CLIENTS
        statement = false;
        if(noc == 6){
            setErrorMsg("Maximum number of clients reached.");
        }
        // FIRST CLIENT
        else if(noc == 0){
            if(clientBalance <0){
                setErrorMsg("Non-positive Initial Balance");
            }
            else{
                resetError();
                clients[noc] = new Client(clientName,clientBalance);
                noc++;
            }
        }
        // SECOND, THIRD, FOURTH, FIFTH, & SIxTH CLIENT
        else if(noc>=1){
            // WHEN CLIENT ALREADY EXISTS
            if(indexofClient(clientName)>=0){
                setErrorMsg("Client "+clientName+ " already exists");
            }
            // NON-POSITIVE INITIAL BALANCE
            else if(clientBalance <0){
                setErrorMsg("Non-positive Initial Balance");
            }
            else{
                resetError();
                clients[noc] = new Client(clientName,clientBalance);
                noc++;
            }

        }

    }

    void addTransaction(String service, String fromClient,String toClient, double amount){
        statement = false;
        // DEPOSIT TRANSACTION
        if (service.equals("DEPOSIT")){
            if(indexofClient(toClient)<0){
                setErrorMsg("ToClient "+toClient+" does not exist.");
            }
            else if(amount <0){
                setErrorMsg("Non-positive Amount");
            }
            else{
                resetError();
                clients[indexofClient(toClient)].Deposit(service,amount);
            }
        }
        // WITHDRAW TRANSACTION
        else if(service.equals("WITHDRAW")){
            if(indexofClient(fromClient)<0){
                setErrorMsg("FromClient "+fromClient+" does not exist.");
            }
            else if(amount <0){
                setErrorMsg("Non-positive Amount");
            }
            else if(amount > clients[indexofClient(fromClient)].getClientBalance()){
                setErrorMsg("Amount too large to Withdraw");
            }
            else{
                resetError();
                clients[indexofClient(fromClient)].Withdraw(service,amount);
            }
        }
        // TRANSFER TRANSACTION
        else if(service.equals("TRANSFER")){
            if(indexofClient(fromClient)<0){
                setErrorMsg("FromClient "+fromClient+" does not exist.");
            }
            else if(indexofClient(toClient)<0){
                setErrorMsg("ToClient "+toClient+" does not exist.");
            }
            else if(amount <0){
                setErrorMsg("Non-positive Amount");
            }
            else if(amount > clients[indexofClient(fromClient)].getClientBalance()){
                setErrorMsg("Amount too large to Transfer" +
                        "");
            }
            else{
                resetError();
                clients[indexofClient(toClient)].Deposit("DEPOSIT",amount);
                clients[indexofClient(fromClient)].Withdraw("WITHDRAW",amount);

            }
        }
    }
    //END of addTransaction

    void statement(String name){

        if(indexofClient(name)<0){
            setErrorMsg("Client "+name+" does not exist.");

        }
        else{
            resetError();
            statement = true;
            double result = clients[indexofClient(name)].getClientBalance();
            statMsg= "Statement of Client "+name+" (current balance $"+String.format("%.2f",result)+"):";
            if(clients[indexofClient(name)].not>0){
                for(int i=0;i<clients[indexofClient(name)].not;i++){
                    statMsg+= "\nTransaction "+clients[indexofClient(name)].history[i].getServiceType()+
                            ": $"+String.format("%.2f",clients[indexofClient(name)].history[i].getAmount());
                }
            }

        }

    }


    void setErrorMsg(String errorMsg){
        this.error = true;
        this.errorMsg = "Error: "+errorMsg;
    }
    void resetError(){
        this.error =false;
        this.errorMsg = "";
    }

    //returns the index of the specific client OR -1 if Client does not exist
    public int indexofClient(String client){
        int result =-1;
        for(int i=0; i<noc;i++){
            if(clients[i] == null){
                break;
            }
            else if(clients[i].getClientName().equals(client)){
                result = i;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        if(error){
            result = errorMsg;
        }
        else if(statement){
            if(statMsg != null){
                result = statMsg;
            }

        }
        else{//no error
            result="Updated Balances of Clients:";
            for(int i =0; i<clients.length;i++){
                if(clients[i] != null){
                    result+= clients[i].toString();
                }

            }
        }
        return result;

    }

}
