package com.mauriciotogneri.cryptostudio.analyzer;

import com.mauriciotogneri.cryptostudio.model.CandleStick;
import com.mauriciotogneri.cryptostudio.result.Result;

import java.util.List;

public class Analyzer
{
    public Result run(Parameters parameters)
    {
        Result result = new Result(parameters);

        List<CandleStick> candleSticks = parameters.candleSticks();

        for (CandleStick candleStick : candleSticks)
        {
            // TODO
        }

        return result;
    }
}