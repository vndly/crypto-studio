package com.mauriciotogneri.cryptostudio.analyzer;

import com.mauriciotogneri.cryptostudio.model.CandleStick;
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

        for (CandleStick candleStick : candleSticks)
        {
            if (state == State.BUYING)
            {
                // TODO
            }
            else if (state == State.SELLING)
            {
                // TODO
            }
        }

        return result;
    }
}