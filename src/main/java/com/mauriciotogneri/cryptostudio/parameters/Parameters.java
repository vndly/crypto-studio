package com.mauriciotogneri.cryptostudio.parameters;

import com.mauriciotogneri.javautils.Resource;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Parameters
{
    public final Range<Double> maxCost;

    public final Range<String> buyStrategy;
    public final Range<Double> buyValue;
    public final Range<Double> trailingBuy;

    public final Range<String> sellStrategy;
    public final Range<Double> sellValue;
    public final Range<Double> trailingProfit;
    public final Range<Double> stopLossTrigger;

    public Parameters(String filePath) throws Exception
    {
        Properties properties = new Properties();
        InputStream input = null;

        try
        {
            input = new FileInputStream(filePath);
            properties.load(input);

            maxCost = new RangeDouble(properties.getProperty("max_cost"));

            buyStrategy = new RangeString(properties.getProperty("buy_strategy"));
            buyValue = new RangeDouble(properties.getProperty("buy_value"));
            trailingBuy = new RangeDouble(properties.getProperty("trailing_buy"));

            sellStrategy = new RangeString(properties.getProperty("sell_strategy"));
            sellValue = new RangeDouble(properties.getProperty("sell_value"));
            trailingProfit = new RangeDouble(properties.getProperty("trailing_profit"));
            stopLossTrigger = new RangeDouble(properties.getProperty("stop_loss_trigger"));
        }
        finally
        {
            Resource.close(input);
        }
    }
}