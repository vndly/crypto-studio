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
    public final int smaCrossCandles;

    public final int emaPeriod;
    public final int ema1;
    public final int ema2;
    public final int emaCrossCandles;

    public final int bbPeriod;
    public final int bbSma;

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
                 int sma2,
                 int smaCrossCandles,
                 int emaPeriod,
                 int ema1,
                 int ema2,
                 int emaCrossCandles,
                 int bbPeriod,
                 int bbSma)
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
        this.smaCrossCandles = smaCrossCandles;

        this.emaPeriod = emaPeriod;
        this.ema1 = ema1;
        this.ema2 = ema2;
        this.emaCrossCandles = emaCrossCandles;

        this.bbPeriod = bbPeriod;
        this.bbSma = bbSma;
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