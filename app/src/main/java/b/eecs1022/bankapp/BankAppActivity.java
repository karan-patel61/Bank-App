package b.eecs1022.bankapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class BankAppActivity extends AppCompatActivity {
    Bank bank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_app);
        bank = new Bank();
    }

    //method that retrives user input
    private void setContentsofTextView(int id, String newContent)
    {
        View view = findViewById(id);
        TextView textview = (TextView) view;
        textview.setText(newContent);
    }
    //method for getting the user inputs from the GUI
    private String getInputofTextField(int id)
    {
        View view = findViewById(id);
        EditText edit = (EditText) view;
        String input = edit.getText().toString();
        return input;
    }

    //method for retrieving current spinner option
    private String getItemSelected(int id){
        View view = findViewById(id);
        Spinner spinner = (Spinner) view;
        String option = spinner.getSelectedItem().toString();
        return option;
    }

    public void createAccountClicked(View view){
        String name = getInputofTextField(R.id.clientName);
        double balance = Double.parseDouble(getInputofTextField(R.id.clientBalance));
        bank.addClient(name,balance);
        setContentsofTextView(R.id.Result,bank.toString());
    }

    public void transactionClicked(View view){
        String service = getItemSelected(R.id.spinner);
        String from = getInputofTextField(R.id.fromClient);
        String to = getInputofTextField(R.id.toClient);
        double amount = Double.parseDouble(getInputofTextField(R.id.amount));
        bank.addTransaction(service,from,to,amount);
        setContentsofTextView(R.id.Result,bank.toString());
    }

    public void outputClicked(View view){
        String client = getInputofTextField(R.id.outputClient);
        bank.statement(client);
        setContentsofTextView(R.id.Result,bank.toString());
    }
}
