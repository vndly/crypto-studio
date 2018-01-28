package com.mauriciotogneri.cryptostudio.parameters;

import com.mauriciotogneri.javautils.Resource;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Parameters
{
    public final Range<Float> maxCost;

    public final Range<String> buyStrategy;
    public final Range<Float> buyValue;
    public final Range<Float> trailingBuy;

    public final Range<String> sellStrategy;
    public final Range<Float> sellValue;
    public final Range<Float> trailingProfit;
    public final Range<Float> stopLossTrigger;

    public Parameters(String filePath) throws Exception
    {
        Properties properties = new Properties();
        InputStream input = null;

        try
        {
            input = new FileInputStream(filePath);
            properties.load(input);

            maxCost = new RangeFloat(properties.getProperty("max_cost"));

            buyStrategy = new RangeString(properties.getProperty("buy_strategy"));
            buyValue = new RangeFloat(properties.getProperty("buy_value"));
            trailingBuy = new RangeFloat(properties.getProperty("trailing_buy"));

            sellStrategy = new RangeString(properties.getProperty("sell_strategy"));
            sellValue = new RangeFloat(properties.getProperty("sell_value"));
            trailingProfit = new RangeFloat(properties.getProperty("trailing_profit"));
            stopLossTrigger = new RangeFloat(properties.getProperty("stop_loss_trigger"));
        }
        finally
        {
            Resource.close(input);
        }
    }
}