package com.mauriciotogneri.cryptostudio.configuration;

import com.mauriciotogneri.javautils.Resource;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Configuration
{
    public final Range<String> source;
    public final Range<String> pair;
    public final Range<String> interval;
    public final Range<Double> maxCost;

    public final Range<String> buyStrategy;
    public final Range<Double> buyValue;
    public final Range<Double> trailingBuy;

    public final Range<String> sellStrategy;
    public final Range<Double> sellValue;
    public final Range<Double> trailingProfit;
    public final Range<Double> stopLossTrigger;

    public Configuration(String filePath) throws Exception
    {
        Properties properties = new Properties();
        InputStream input = null;

        try
        {
            input = new FileInputStream(filePath);
            properties.load(input);

            source = new RangeString(properties.getProperty("source"));
            pair = new RangeString(properties.getProperty("pair"));
            interval = new RangeString(properties.getProperty("interval"));
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