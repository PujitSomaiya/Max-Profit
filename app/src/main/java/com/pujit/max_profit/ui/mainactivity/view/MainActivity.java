package com.pujit.max_profit.ui.mainactivity.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.pujit.max_profit.R;
import com.pujit.max_profit.databinding.ActivityMainBinding;
import com.pujit.max_profit.ui.mainactivity.adapter.DataAdapter;
import com.pujit.max_profit.ui.mainactivity.adapter.TransactionAdapter;
import com.pujit.max_profit.ui.mainactivity.model.DataModel;
import com.pujit.max_profit.ui.mainactivity.model.ProfitModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ArrayList<ProfitModel> profitModels = new ArrayList<>();
    private ArrayList<DataModel> dataModel=new ArrayList<>();
    private ProfitModel profitModel;
    private ActivityMainBinding binding;
    private Double profit = 0.0;
    private Double invested = 0.0;
    private Double sells = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        initControls();
    }

    private void initControls() {
        initListeners();
    }

    private void transactionView() {
        TransactionAdapter transactionAdapter = new TransactionAdapter(this, profitModels);
        binding.rvTransaction.setAdapter(transactionAdapter);
    }

    private void initListeners() {
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.rvTransaction.setLayoutManager(linearLayoutManager1);
        binding.rvDefault.setLayoutManager(linearLayoutManager2);
        binding.btnCalculate.setOnClickListener(this);
        DataAdapter dataAdapter = new DataAdapter(this, setData());
        binding.rvDefault.setAdapter(dataAdapter);
       /* for (int i=0;i<dataModel.size();i++){
            Toast.makeText(this,dataModel.get(i).getProfit().toString(),Toast.LENGTH_LONG).show();
        }*/
    }

    private ArrayList<DataModel> setData() {
        dataModel.add(new DataModel("l&t",100.0,112.00,(((112.0-100.0)/100)*100)));
        dataModel.add(new DataModel("NHPC",25.60,28.80,(((28.80-25.60)/25.60)*100)));
        dataModel.add(new DataModel("SBICARD",80.00,85.40,(((85.40-80.00)/80.00)*100)));
        dataModel.add(new DataModel("APPOLLO",250.00,195.00,(((195.00-250.00)/250.00)*100)));
        dataModel.add(new DataModel("Edelweiss",290.24,62.80,(((290.24-62.80)/62.80)*100)));
        dataModel.add(new DataModel("ITC",153.95,244.94,(((244.94-153.95)/153.95)*100)));
        dataModel.add(new DataModel("TCS",456.00,561.00,(((561.00-456.00)/456.00)*100)));
        dataModel.add(new DataModel("CEAT",200.00,205.44,(((205.44-200.00)/200)*100)));
        dataModel.add(new DataModel("HDFCBank",806.00,1008.50,(((1008.50-806.00)/806.00)*100)));
        dataModel.add(new DataModel("PowerGrid",190.00,565.45,(((565.45-190.00)/190.00)*100)));
        dataModel.add(new DataModel("AXISBank",30.50,80.54,(((80.54-30.50)/30.50)*100)));
        dataModel.add(new DataModel("BajajFinsv",31.60,81.65,(((81.65-31.60)/31.60)*100)));
        dataModel.add(new DataModel("CIPLA",140.00,157.45,(((157.45-140.00)/140.00)*100)));
        dataModel.add(new DataModel("EKC",80.50,88.50,(((88.50-80.50)/80.50)*100)));
        dataModel.add(new DataModel("EMCO",25.60,00.45,(((00.45-25.60)/25.60)*100)));
        return dataModel;
    }

    private void sortDataAccordingToProfit() {
        Collections.sort(dataModel, new Comparator<DataModel>() {
            @Override
            public int compare(DataModel dataModel, DataModel t1) {
                return t1.getProfit().compareTo(dataModel.getProfit());
            }
        });
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

    @Override
    public void onClick(View view) {
        if (R.id.btnCalculate==view.getId()){
            calculate();
        }
    }

    private void calculate() {
        hideKeyboard(this);
        sortDataAccordingToProfit();
        if (binding.btnCalculate.getText().length()!=0){
            profitModels.clear();
            profit = 0.00;
            sells = 0.00;
            invested=0.00;
            double enterAmount=(Double.parseDouble(Objects.requireNonNull(binding.edAmount.getText()).toString()));
            for (int i=0;i<dataModel.size();i++){
                if (enterAmount >= (Double.parseDouble(dataModel.get(i).getBuy().toString()))){
                    enterAmount=enterAmount-Double.parseDouble(dataModel.get(i).getBuy().toString());
                    profitModel = new ProfitModel(dataModel.get(i).getShareName(),dataModel.get(i).getBuy(),dataModel.get(i).getSell(),dataModel.get(i).getProfit());
                    invested = invested + dataModel.get(i).getBuy();
                    profit = profit + dataModel.get(i).getProfit();
                    sells = sells + dataModel.get(i).getSell();
                    profitModels.add(profitModel);
                }
            }
        }else {
            Toast.makeText(this,"please enter amount",Toast.LENGTH_LONG).show();
        }
        if (profitModel!=null){
            binding.tvFinalInvested.setText(String.valueOf(invested));
            binding.tvFinalProfits.setText(String.valueOf(profit));
            binding.tvFinalSells.setText(String.valueOf(sells));
            transactionView();
        }

    }
}
