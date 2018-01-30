package com.mauriciotogneri.cryptostudio.analyzer;

import com.mauriciotogneri.cryptostudio.source.Source;
import com.mauriciotogneri.cryptostudio.strategy.Strategy;

public class Parameters
{
    public final String source;
    public final String pair;
    public final String interval;
    public final double maxCost;
    public final String buyStrategy;
    public final double buyValue;
    public final double trailingBuy;
    public final String sellStrategy;
    public final double sellValue;
    public final double trailingProfit;
    public final double stopLossTrigger;

    public Parameters(String source,
                      String pair,
                      String interval,
                      double maxCost,
                      String buyStrategy,
                      double buyValue,
                      double trailingBuy,
                      String sellStrategy,
                      double sellValue,
                      double trailingProfit,
                      double stopLossTrigger)
    {
        this.source = source;
        this.pair = pair;
        this.interval = interval;
        this.maxCost = maxCost;
        this.buyStrategy = buyStrategy;
        this.buyValue = buyValue;
        this.trailingBuy = trailingBuy;
        this.sellStrategy = sellStrategy;
        this.sellValue = sellValue;
        this.trailingProfit = trailingProfit;
        this.stopLossTrigger = stopLossTrigger;
    }

    public Source source()
    {
        return Source.fromString(source, pair, interval);
    }

    public Strategy buyStrategy()
    {
        return Strategy.fromString(buyStrategy, this);
    }

    public Strategy sellStrategy()
    {
        return Strategy.fromString(sellStrategy, this);
    }
}