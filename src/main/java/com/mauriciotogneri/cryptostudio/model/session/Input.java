package com.mauriciotogneri.cryptostudio.model.session;

import com.mauriciotogneri.cryptostudio.indicator.Indicator;
import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.source.Source;
import com.mauriciotogneri.cryptostudio.source.Source.SourceType;
import com.mauriciotogneri.cryptostudio.strategy.Strategy;
import com.mauriciotogneri.cryptostudio.strategy.Strategy.StrategyType;
import com.mauriciotogneri.cryptostudio.type.Interval;

public class Input
{
    public final SourceType source;
    public final String pair;
    public final String interval;
    public final double maxCost;
    public final StrategyType buyStrategy;
    public final double buyValue;
    public final double trailingBuy;
    public final StrategyType sellStrategy;
    public final double sellValue;
    public final double trailingProfit;
    public final double stopLossTrigger;

    public transient final Indicator indicator;

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
        this.source = SourceType.valueOf(source);
        this.pair = pair;
        this.interval = interval;
        this.maxCost = maxCost;
        this.buyStrategy = StrategyType.valueOf(buyStrategy);
        this.buyValue = buyValue;
        this.trailingBuy = trailingBuy;
        this.sellStrategy = StrategyType.valueOf(sellStrategy);
        this.sellValue = sellValue;
        this.trailingProfit = trailingProfit;
        this.stopLossTrigger = stopLossTrigger;
        this.indicator = Indicator.fromStrategy(StrategyType.valueOf(buyStrategy), this);
    }

    public void update(PriceData priceData)
    {
        indicator.update(priceData);
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