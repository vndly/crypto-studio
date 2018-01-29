package com.mauriciotogneri.cryptostudio.analyzer;

import com.mauriciotogneri.cryptostudio.model.CandleStick;
import com.mauriciotogneri.cryptostudio.result.Purchase;
import com.mauriciotogneri.cryptostudio.result.Result;
import com.mauriciotogneri.cryptostudio.strategy.Strategy;
import com.mauriciotogneri.cryptostudio.utils.Decimal;

import java.util.List;

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
        List<CandleStick> candleSticks = parameters.candleSticks();
        Strategy buyStrategy = parameters.buyStrategy();
        Strategy sellStrategy = parameters.sellStrategy();

        Purchase purchase;

        for (CandleStick candleStick : candleSticks)
        {
            buyStrategy.update(candleStick);
            sellStrategy.update(candleStick);

            if (state == State.BUYING)
            {
                if (buyStrategy.isTriggered())
                {
                    state = State.SELLING;

                    double boughtPrice = Decimal.round(candleStick.price());
                    double boughtAmount = Decimal.round(parameters.maxCost / boughtPrice);
                    purchase = new Purchase(candleStick.openTime, boughtPrice, boughtAmount);

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