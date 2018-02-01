package com.mauriciotogneri.cryptostudio.state;

import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.model.session.Operation;
import com.mauriciotogneri.cryptostudio.model.session.Session;
import com.mauriciotogneri.cryptostudio.strategy.Strategy;

public class SellingState extends State
{
    private final Session session;
    private final Operation operation;
    private final Strategy sellStragegy;

    public SellingState(Session session, Operation operation)
    {
        this.session = session;
        this.operation = operation;
        this.sellStragegy = session.input.sellStrategy();
    }

    @Override
    public State update(PriceData priceData)
    {
        return this;
    }
}