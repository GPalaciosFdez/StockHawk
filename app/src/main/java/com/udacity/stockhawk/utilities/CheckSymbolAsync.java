package com.udacity.stockhawk.utilities;

import android.os.AsyncTask;

import com.udacity.stockhawk.ui.MainActivity;

import java.io.IOException;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

/**
 * Created by GPalacios on 05/03/17.
 */

public class CheckSymbolAsync extends AsyncTask<String, Void, Boolean> {

    private String symbol;
    private MainActivity mainActivity;

    public CheckSymbolAsync(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    protected Boolean doInBackground(String... params){
        symbol = params[0];
        Boolean exists = false;
        Stock stock;
        try {
            stock = YahooFinance.get(symbol);
            if(!stock.toString().equals(symbol + ": null")){
                exists = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exists;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        mainActivity.addStock(symbol, aBoolean);
    }
}
