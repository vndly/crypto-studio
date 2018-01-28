package com.mauriciotogneri.cryptostudio.analyzer;

import com.mauriciotogneri.cryptostudio.model.CandleStick;
import com.mauriciotogneri.cryptostudio.result.Event;
import com.mauriciotogneri.cryptostudio.result.Result;

import java.util.List;
import java.util.Random;

public class Analyzer
{
    public Result run(Parameters parameters)
    {
        Result result = new Result(parameters);

        List<CandleStick> candleSticks = parameters.candleSticks();
        Random random = new Random();

        for (CandleStick candleStick : candleSticks)
        {
            if (random.nextInt(1000) == 0)
            {
                result.event(new Event());
            }
        }

        return result;
    }
}