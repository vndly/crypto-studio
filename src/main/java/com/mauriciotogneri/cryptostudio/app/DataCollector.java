package com.mauriciotogneri.cryptostudio.app;

import com.google.gson.GsonBuilder;
import com.mauriciotogneri.cryptostudio.api.Klines;
import com.mauriciotogneri.cryptostudio.model.price.CandleStick;
import com.mauriciotogneri.cryptostudio.model.price.PriceHistory;
import com.mauriciotogneri.cryptostudio.source.FileSource;
import com.mauriciotogneri.cryptostudio.type.Interval;
import com.mauriciotogneri.javautils.Resource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataCollector
{
    public void run(String pair, Interval interval, Integer days) throws Exception
    {
        Integer limit = interval.onDaySize() * days;
        String filePath = FileSource.file(pair, interval);

        Klines klines = new Klines(pair, interval, limit);
        List<CandleStick> candleSticks = klines.execute();
        List<PriceHistory> priceHistory = priceHistory(candleSticks);

        String json = new GsonBuilder().setPrettyPrinting().create().toJson(priceHistory);
        Resource.save(new File(filePath), json);
    }

    public List<PriceHistory> priceHistory(List<CandleStick> candleSticks)
    {
        List<PriceHistory> list = new ArrayList<>();

        for (CandleStick candleStick : candleSticks)
        {
            // TODO: split
            list.add(new PriceHistory(candleStick.time(), candleStick.price()));
        }

        return list;
    }

    public static void main(String[] args) throws Exception
    {
        String pair = "ETHBTC";
        Interval interval = Interval.ONE_MINUTE;
        Integer days = 7;

        DataCollector dataCollector = new DataCollector();
        dataCollector.run(pair, interval, days);
    }
}