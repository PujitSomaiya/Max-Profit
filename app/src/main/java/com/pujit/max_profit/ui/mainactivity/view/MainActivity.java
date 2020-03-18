package com.pujit.max_profit.ui.mainactivity.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pujit.max_profit.R;
import com.pujit.max_profit.ui.mainactivity.adapter.TransactionAdapter;
import com.pujit.max_profit.ui.mainactivity.model.ProfitModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private Button btnCalculate;
    private EditText edAmount;
    private TextView tvFinalProfits;
    private TextView tvFinalInvested;
    private TextView tvFinalSells;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<ProfitModel> profitModels = new ArrayList<>();
    private ProfitModel profitModel;
    private TransactionAdapter transactionAdapter;
    private Double enteredAmount = 0.0;
    private Double profit = 0.0;
    private Double invested = 0.0;
    private Double sells = 0.0;
    private RecyclerView rvTransaction;
    private Double[] buy = {100.00, 25.60, 80.00, 250.00, 290.24, 153.95, 456.00, 200.00, 806.00, 190.00, 30.50, 31.60, 140.00, 80.50, 25.60};
    private Double[] sell = {112.00, 28.80, 85.40, 195.00, 62.80, 244.94, 561.00, 205.44, 1008.50, 565.45, 80.54, 81.65, 157.45, 88.50, 00.45};
    private String[] share = {"l&t", "NHPC", "SBICARD", "APPOLLO", "Edelweiss ", "ITC", "TCS", "CEAT", "HDFCBank", "PowerGrid", "AXISBank", "BajajFinsv", "CIPLA", "EKC", "EMCO"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initControls();


    }

    private void initControls() {
        initListeners();
        calculate();

    }

    private void calculate() {
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profitModels.clear();
                enteredAmount = 0.00;
                profit = 0.00;
                sells = 0.00;
                for (int i = 0; i <= 14; i++) {
                    if (Double.parseDouble(edAmount.getText().toString()) >= buy[i]) {
                        if (!(sell[i] - buy[i] < buy[i])) {
                            enteredAmount = enteredAmount + buy[i];
                            if (Double.parseDouble(edAmount.getText().toString()) >= enteredAmount) {
                                profitModel = new ProfitModel(share[i], buy[i], sell[i], sell[i] - buy[i]);
                                profit = profit + (sell[i] - buy[i]);
                                sells = sells + sell[i];
                                transactionView();
                            } else {
                                enteredAmount = enteredAmount - buy[i];
//                                if (!(sell[i] - buy[i] < 0.0)) {
//                                    enteredAmount = enteredAmount + buy[i];
//                                    if (Double.parseDouble(edAmount.getText().toString()) >= enteredAmount) {
//                                        profitModel = new ProfitModel(share[i], buy[i], sell[i], sell[i] - buy[i]);
//                                        profit = profit + (sell[i] - buy[i]);
//                                        sells = sells + sell[i];
//                                        transactionView();
//                                    } else {
//                                        enteredAmount = enteredAmount - buy[i];
//                                    }
//                                }
                            }
                        }

//
//                        tvBuy.setText(String.valueOf(buy[i]));
//                        tvSell.setText(String.valueOf(sell[i]));
//                        tvShare.setText(String.valueOf(share[i]));
                    }
                    tvFinalInvested.setText(String.valueOf(enteredAmount));
                    tvFinalProfits.setText(String.valueOf(profit));
                    tvFinalSells.setText(String.valueOf(sells));
                    hideKeyboard(MainActivity.this);
                }
            }
        });

    }

    private void transactionView() {
        rvTransaction.setLayoutManager(linearLayoutManager);
        profitModels.add(profitModel);
        TransactionAdapter transactionAdapter = new TransactionAdapter(this, profitModels);
        rvTransaction.setAdapter(transactionAdapter);

    }

    private void initListeners() {
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        btnCalculate = findViewById(R.id.btnCalculate);
        rvTransaction = findViewById(R.id.rvTransaction);
        edAmount = findViewById(R.id.edAmount);
        tvFinalProfits = findViewById(R.id.tvFinalProfits);
        tvFinalSells = findViewById(R.id.tvFinalSells);
        tvFinalInvested = findViewById(R.id.tvFinalInvested);
    }


    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        assert imm != null;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
