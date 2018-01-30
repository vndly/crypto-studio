package com.mauriciotogneri.cryptostudio.state;

import com.mauriciotogneri.cryptostudio.analyzer.Session;
import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.model.result.Purchase;
import com.mauriciotogneri.cryptostudio.strategy.Strategy;

public class SellingState extends State
{
    private final Session session;
    private final Purchase purchase;
    private final Strategy sellStragegy;

    public SellingState(Session session, Purchase purchase)
    {
        this.session = session;
        this.purchase = purchase;
        this.sellStragegy = session.parameters.sellStrategy();

        // TODO: remove
        session.result.event(purchase);
    }

    @Override
    public State update(PriceData priceData)
    {
        return this;
    }
}