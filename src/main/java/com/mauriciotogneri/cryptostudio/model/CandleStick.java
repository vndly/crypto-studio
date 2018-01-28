package com.mauriciotogneri.cryptostudio.model;

public class CandleStick
{
    public final long openTime;
    public final double open;
    public final double high;
    public final double low;
    public final double close;
    public final double volume;
    public final long closeTime;
    public final double quoteAssetVolume;
    public final int numberOfTrades;
    public final double takerBuyBaseAssetVolume;
    public final double takerBuyQuoteAssetVolume;

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
}