package com.mauriciotogneri.cryptostudio.analyzer;

import com.mauriciotogneri.cryptostudio.strategy.Strategy;

public class Parameters
{
    public final String pair;
    public final double maxCost;
    public final String buyStrategy;
    public final double buyValue;
    public final double trailingBuy;
    public final String sellStrategy;
    public final double sellValue;
    public final double trailingProfit;
    public final double stopLossTrigger;

    public Parameters(String pair,
                      double maxCost,
                      String buyStrategy,
                      double buyValue,
                      double trailingBuy,
                      String sellStrategy,
                      double sellValue,
                      double trailingProfit,
                      double stopLossTrigger)
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

    public Strategy buyStrategy()
    {
        return Strategy.fromString(buyStrategy);
    }

    public Strategy sellStrategy()
    {
        return Strategy.fromString(sellStrategy);
    }
}