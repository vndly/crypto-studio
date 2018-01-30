package com.mauriciotogneri.cryptostudio.analyzer;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.model.result.Purchase;
import com.mauriciotogneri.cryptostudio.model.result.Result;
import com.mauriciotogneri.cryptostudio.source.Source;
import com.mauriciotogneri.cryptostudio.strategy.Strategy;
import com.mauriciotogneri.cryptostudio.utils.Decimal;

public class Analyzer
{
    private enum State
    {
        BUYING,
        SELLING
    }

    public Result run(Parameters parameters)
    {
        Result result = new Result(parameters);

        State state = State.BUYING;
        Source source = parameters.source();
        Strategy buyStrategy = parameters.buyStrategy();
        Strategy sellStrategy = parameters.sellStrategy();

        Purchase purchase;

        for (PriceData priceData : source.priceData())
        {
            buyStrategy.update(priceData);
            sellStrategy.update(priceData);

            if (state == State.BUYING)
            {
                if (buyStrategy.isTriggered())
                {
                    state = State.SELLING;

                    double boughtPrice = Decimal.round(priceData.price());
                    double boughtAmount = Decimal.round(parameters.maxCost / boughtPrice);
                    purchase = new Purchase(priceData.time(), boughtPrice, boughtAmount);

                    result.event(purchase);
                }
            }
            else if (state == State.SELLING)
            {
                if (sellStrategy.isTriggered())
                {
                    // TODO

                    state = State.BUYING;
                }
            }
        }

        return result;
    }
}