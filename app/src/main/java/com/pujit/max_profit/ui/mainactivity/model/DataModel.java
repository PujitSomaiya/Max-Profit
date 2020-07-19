package com.pujit.max_profit.ui.mainactivity.model;

public class DataModel {
    private String shareName;
    private Double buy;
    private Double sell;
    private Double profit;

    public DataModel(String shareName, Double buy, Double sell, Double profit) {
        this.shareName = shareName;
        this.buy = buy;
        this.sell = sell;
        this.profit = profit;
    }

    public String getShareName() {
        return shareName;
    }

    public void setShareName(String shareName) {
        this.shareName = shareName;
    }

    public Double getBuy() {
        return buy;
    }

    public void setBuy(Double buy) {
        this.buy = buy;
    }

    public Double getSell() {
        return sell;
    }

    public void setSell(Double sell) {
        this.sell = sell;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }
}

