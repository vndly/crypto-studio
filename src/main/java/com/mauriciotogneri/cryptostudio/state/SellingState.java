package com.mauriciotogneri.cryptostudio.state;

import com.mauriciotogneri.cryptostudio.model.session.Session;
import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.model.events.TrailingBuyEvent;
import com.mauriciotogneri.cryptostudio.strategy.Strategy;

public class SellingState extends State
{
    private final Session session;
    private final TrailingBuyEvent trailingBuyEvent;
    private final Strategy sellStragegy;

    public SellingState(Session session, TrailingBuyEvent trailingBuyEvent)
    {
        this.session = session;
        this.trailingBuyEvent = trailingBuyEvent;
        this.sellStragegy = session.input.sellStrategy();
    }

    @Override
    public State update(PriceData priceData)
    {
        return this;
    }
}