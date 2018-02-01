package com.mauriciotogneri.cryptostudio.state;

import com.mauriciotogneri.cryptostudio.model.events.TrailingSellEvent;
import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.model.session.Operation;
import com.mauriciotogneri.cryptostudio.model.session.Session;
import com.mauriciotogneri.cryptostudio.strategy.Strategy;

public class WatchingSellState extends State
{
    private final Session session;
    private final Operation operation;
    private final Strategy sellStragegy;

    public WatchingSellState(Session session, Operation operation)
    {
        this.session = session;
        this.operation = operation;
        this.sellStragegy = session.input.sellStrategy();
    }

    @Override
    public State update(PriceData priceData)
    {
        sellStragegy.update(priceData);

        if (sellStragegy.isTriggered())
        {
            TrailingSellEvent trailingSellEvent = new TrailingSellEvent(priceData);
            operation.event(trailingSellEvent);

            return new TrailingBuyState(session, operation, priceData.price());
        }
        else
        {
            return this;
        }
    }
}