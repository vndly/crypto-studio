package com.mauriciotogneri.cryptostudio.analyzer;

import com.mauriciotogneri.cryptostudio.strategies.Strategy;

public class Parameters
{
    public final String pair;
    public final double maxCost;
    public final Strategy buyStrategy;
    public final double buyValue;
    public final double trailingBuy;
    public final Strategy sellStrategy;
    public final double sellValue;
    public final double trailingProfit;
    public final double stopLossTrigger;

    public Parameters(String pair, double maxCost, Strategy buyStrategy, double buyValue, double trailingBuy, Strategy sellStrategy, double sellValue, double trailingProfit, double stopLossTrigger)
    {
        this.pair = pair;
        this.maxCost = maxCost;
        this.buyStrategy = buyStrategy;
        this.buyValue = buyValue;
        this.trailingBuy = trailingBuy;
        this.sellStrategy = sellStrategy;
        this.sellValue = sellValue;
        this.trailingProfit = trailingProfit;
        this.stopLossTrigger = stopLossTrigger;
    }
}