package com.mauriciotogneri.cryptostudio.model;

public class CandleStick implements PriceData
{
    private final long openTime;
    private final double open;
    private final double high;
    private final double low;
    private final double close;
    private final double volume;
    private final long closeTime;
    private final double quoteAssetVolume;
    private final int numberOfTrades;
    private final double takerBuyBaseAssetVolume;
    private final double takerBuyQuoteAssetVolume;

    public CandleStick(long openTime,
                       double open,
                       double high,
                       double low,
                       double close,
                       double volume,
                       long closeTime,
                       double quoteAssetVolume,
                       int numberOfTrades,
                       double takerBuyBaseAssetVolume,
                       double takerBuyQuoteAssetVolume)
    {
        this.openTime = openTime;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.closeTime = closeTime;
        this.quoteAssetVolume = quoteAssetVolume;
        this.numberOfTrades = numberOfTrades;
        this.takerBuyBaseAssetVolume = takerBuyBaseAssetVolume;
        this.takerBuyQuoteAssetVolume = takerBuyQuoteAssetVolume;
    }

    @Override
    public long time()
    {
        return openTime;
    }

    @Override
    public double price()
    {
        return (open + close + high + low) / 4;
    }
}