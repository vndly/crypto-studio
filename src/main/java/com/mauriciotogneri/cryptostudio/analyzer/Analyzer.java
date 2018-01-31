package com.mauriciotogneri.cryptostudio.analyzer;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.model.output.Output;
import com.mauriciotogneri.cryptostudio.source.Source;
import com.mauriciotogneri.cryptostudio.state.State;
import com.mauriciotogneri.cryptostudio.state.BuyingState;

public class Analyzer
{
    public Output run(Parameters parameters)
    {
        Output output = new Output(parameters);
        Source source = parameters.source();
        Session session = new Session(output, parameters);

        State state = new BuyingState(session);

        for (PriceData priceData : source.priceData())
        {
            state = state.update(priceData);
        }

        return output;
    }
}