package com.mauriciotogneri.cryptostudio.analyzer;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.model.result.Result;
import com.mauriciotogneri.cryptostudio.source.Source;
import com.mauriciotogneri.cryptostudio.state.State;
import com.mauriciotogneri.cryptostudio.state.WatchingState;

public class Analyzer
{
    public Result run(Parameters parameters)
    {
        Result result = new Result(parameters);
        Source source = parameters.source();
        Session session = new Session(result, parameters);

        State state = new WatchingState(session);

        for (PriceData priceData : source.priceData())
        {
            state = state.update(priceData);
        }

        return result;
    }
}