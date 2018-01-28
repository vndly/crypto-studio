package com.mauriciotogneri.cryptostudio.analyzer;

import com.mauriciotogneri.cryptostudio.strategies.Strategy;

public class Analyzer
{
    public Result run(float maxCost,
                      Strategy buyStrategy,
                      float buyValue,
                      float trailingBuy,
                      Strategy sellStrategy,
                      float sellValue,
                      float trailingProfit,
                      float stopLossTrigger)
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