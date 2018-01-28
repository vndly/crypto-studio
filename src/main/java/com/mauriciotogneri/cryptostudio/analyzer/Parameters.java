package com.mauriciotogneri.cryptostudio.analyzer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mauriciotogneri.cryptostudio.model.CandleStick;
import com.mauriciotogneri.cryptostudio.strategy.Strategy;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class Parameters
{
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

    public Parameters(String pair,
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

    public List<CandleStick> candleSticks()
    {
        try
        {
            InputStream inputStream = new FileInputStream(String.format("data/%s_%s.json", pair, interval));
            String json = new Scanner(inputStream, "UTF-8").useDelimiter("\\A").next();

            return new Gson().fromJson(json, new TypeToken<List<CandleStick>>()
            {
            }.getType());
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
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