package com.mauriciotogneri.cryptostudio.analyzer;

import com.mauriciotogneri.cryptostudio.strategies.Strategy;

public class Analyzer
{
    public Result run(double maxCost,
                      Strategy buyStrategy,
                      double buyValue,
                      double trailingBuy,
                      Strategy sellStrategy,
                      double sellValue,
                      double trailingProfit,
                      double stopLossTrigger)
    {
        System.out.println(maxCost);
        System.out.println(buyStrategy);
        System.out.println(buyValue);
        System.out.println(trailingBuy);
        System.out.println(sellStrategy);
        System.out.println(sellValue);
        System.out.println(trailingProfit);
        System.out.println(stopLossTrigger);
        System.out.println("======================");

        return new Result();
    }
}