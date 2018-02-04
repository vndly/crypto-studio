package com.mauriciotogneri.cryptostudio.model.price;

public class PriceData extends PriceHistory
{
    private final double last24H;
    private final double sma1;
    private final double sma2;
    private final double ema1;
    private final double ema2;

    public PriceData(long time,
                     double price,
                     double last24H,
                     double sma1,
                     double sma2,
                     double ema1,
                     double ema2)
    {
        super(time, price);

        this.last24H = last24H;
        this.sma1 = sma1;
        this.sma2 = sma2;
        this.ema1 = ema1;
        this.ema2 = ema2;
    }

    public double last24H()
    {
        return last24H;
    }

    public double sma1()
    {
        return sma1;
    }

    public double sma2()
    {
        return sma2;
    }

    public double ema1()
    {
        return ema1;
    }

    public double ema2()
    {
        return ema2;
    }
}