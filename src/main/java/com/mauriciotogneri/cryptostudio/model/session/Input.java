package com.mauriciotogneri.cryptostudio.model.session;

import com.mauriciotogneri.cryptostudio.source.Source;
import com.mauriciotogneri.cryptostudio.source.Source.SourceType;
import com.mauriciotogneri.cryptostudio.strategy.Strategy;
import com.mauriciotogneri.cryptostudio.strategy.Strategy.StrategyType;
import com.mauriciotogneri.cryptostudio.type.Interval;
import com.mauriciotogneri.cryptostudio.type.Pair;

public class Input
{
    public final SourceType source;
    public final Pair pair;
    public final String interval;
    public final double maxCost;

    public final StrategyType buyStrategy;
    public final double buyValue;
    public final double trailingBuy;

    public final StrategyType sellStrategy;
    public final double sellValue;
    public final double trailingProfit;
    public final double stopLossTrigger;

    public final int smaPeriod;
    public final int sma1;
    public final int sma2;

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
                 double stopLossTrigger,
                 int smaPeriod,
                 int sma1,
                 int sma2)
    {
        this.source = SourceType.valueOf(source);
        this.pair = Pair.valueOf(pair);
        this.interval = interval;
        this.maxCost = maxCost;
        this.buyStrategy = StrategyType.valueOf(buyStrategy);
        this.buyValue = buyValue;
        this.trailingBuy = trailingBuy;
        this.sellStrategy = StrategyType.valueOf(sellStrategy);
        this.sellValue = sellValue;
        this.trailingProfit = trailingProfit;
        this.stopLossTrigger = stopLossTrigger;
        this.smaPeriod = smaPeriod;
        this.sma1 = sma1;
        this.sma2 = sma2;
    }

    public Interval interval()
    {
        return Interval.fromCode(interval);
    }

    public Source source()
    {
        return Source.fromString(source, pair, Interval.fromCode(interval));
    }

    public Strategy buyStrategy()
    {
        return Strategy.buyStrategy(buyStrategy, this);
    }

    public Strategy sellStrategy(double value)
    {
        return Strategy.sellStrategy(sellStrategy, this, value);
    }
}