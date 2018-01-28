package com.mauriciotogneri.cryptostudio.indicators;

import com.mauriciotogneri.cryptostudio.model.CandleStick;

public abstract class Indicator
{
    public abstract void update(CandleStick candleStick);
}