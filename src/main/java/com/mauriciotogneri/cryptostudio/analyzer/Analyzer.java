package com.mauriciotogneri.cryptostudio.analyzer;

import com.mauriciotogneri.cryptostudio.model.events.WatchingEvent;
import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.model.session.Input;
import com.mauriciotogneri.cryptostudio.model.session.Output;
import com.mauriciotogneri.cryptostudio.model.session.Session;
import com.mauriciotogneri.cryptostudio.source.Source;
import com.mauriciotogneri.cryptostudio.state.State;
import com.mauriciotogneri.cryptostudio.state.WatchingState;

import java.util.List;

public class Analyzer
{
    public Output run(Input input)
    {
        Output output = new Output(input);
        Source source = input.source();
        Session session = new Session(input, output);
        State state = new WatchingState(session);
        List<PriceData> priceList = source.priceData();

        PriceData firstPrice = priceList.get(0);
        output.event(new WatchingEvent(firstPrice));

        for (PriceData priceData : priceList)
        {
            state = state.update(priceData);
        }

        return output;
    }
}