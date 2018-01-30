package com.mauriciotogneri.cryptostudio.state;

import com.mauriciotogneri.cryptostudio.model.PriceData;

public interface State
{
    State update(PriceData priceData);
}