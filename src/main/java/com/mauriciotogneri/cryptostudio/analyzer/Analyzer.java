package com.mauriciotogneri.cryptostudio.analyzer;

import com.mauriciotogneri.cryptostudio.model.CandleStick;
import com.mauriciotogneri.cryptostudio.result.BuyEvent;
import com.mauriciotogneri.cryptostudio.result.Result;
import com.mauriciotogneri.cryptostudio.strategy.Strategy;

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

        double boughtPrice;
        double boughtAmount;

        for (CandleStick candleStick : candleSticks)
        {
            buyStrategy.update(candleStick);
            sellStrategy.update(candleStick);

            if (state == State.BUYING)
            {
                if (buyStrategy.isTriggered())
                {
                    // TODO
                    state = State.SELLING;

                    boughtPrice = candleStick.price();
                    boughtAmount = parameters.maxCost / boughtPrice;

                    result.event(new BuyEvent(candleStick.openTime, boughtPrice, boughtAmount));
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