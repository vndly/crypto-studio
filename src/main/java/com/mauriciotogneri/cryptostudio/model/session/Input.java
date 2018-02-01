package com.mauriciotogneri.cryptostudio.model.session;

import com.mauriciotogneri.cryptostudio.source.Source;
import com.mauriciotogneri.cryptostudio.strategy.Strategy;

public class Input
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

    public Input(String source,
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
        return buyStrategy(0);
    }

    public Strategy sellStrategy()
    {
        return sellStrategy(0);
    }

    public Strategy buyStrategy(double value)
    {
        return Strategy.fromString(buyStrategy, this, value);
    }

    public Strategy sellStrategy(double value)
    {
        return Strategy.fromString(sellStrategy, this, value);
    }
}