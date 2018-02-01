package com.mauriciotogneri.cryptostudio.state;

import com.mauriciotogneri.cryptostudio.model.events.WatchingSellEvent;
import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.model.session.Operation;
import com.mauriciotogneri.cryptostudio.model.session.Session;
import com.mauriciotogneri.cryptostudio.strategy.Strategy;

/**
 * Uses the sell strategy to determine if it should sell the coin.
 */
public class WatchingSellState extends State
{
    private final Session session;
    private final Operation operation;
    private final Strategy sellStrategy;

    public WatchingSellState(Session session, Operation operation, PriceData priceData)
    {
        this.session = session;
        this.sellStrategy = session.input.sellStrategy();
        this.operation = operation;
        this.operation.event(new WatchingSellEvent(priceData, session.input.maxCost));
    }

    @Override
    public State update(PriceData priceData)
    {
        sellStrategy.update(priceData);

        if (sellStrategy.isTriggered())
        {
            return new TrailingSellState(session, operation, priceData);
        }
        else
        {
            return this;
        }
    }
}