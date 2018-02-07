package com.mauriciotogneri.cryptostudio.app;

import com.google.gson.GsonBuilder;
import com.mauriciotogneri.cryptostudio.api.Klines;
import com.mauriciotogneri.cryptostudio.model.price.CandleStick;
import com.mauriciotogneri.cryptostudio.model.price.PriceHistory;
import com.mauriciotogneri.cryptostudio.source.FileSource;
import com.mauriciotogneri.cryptostudio.type.Interval;
import com.mauriciotogneri.cryptostudio.type.Pair;
import com.mauriciotogneri.javautils.Resource;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DataCollector
{
    public void run(Pair pair, Interval interval, Integer days) throws Exception
    {
        Integer limit = interval.inDays() * days;
        String filePath = FileSource.file(pair, Interval.TEN_SECONDS);

        Klines klines = new Klines(pair, interval, limit);
        List<CandleStick> candleSticks = klines.execute();
        List<PriceHistory> priceHistory = priceHistory(interval, candleSticks);

        String json = new GsonBuilder().setPrettyPrinting().create().toJson(priceHistory);
        Resource.save(new File(filePath), json);
    }

    public List<PriceHistory> priceHistory(Interval interval, List<CandleStick> candleSticks)
    {
        int sections = interval.inSeconds() / 10;
        List<PriceHistory> list = new ArrayList<>();

        for (CandleStick candleStick : candleSticks)
        {
            long openTime = candleStick.openTime();

            for (int i = 0; i < sections; i++)
            {
                long time = openTime + (i * 10000);
                double price = candleStick.price((double) i / (double) (sections - 1));

                list.add(new PriceHistory(time, price));
            }
        }

        return list;
    }

    public static void main(String[] args) throws Exception
    {
        Pair pair = Pair.ETHBTC;
        Interval interval = Interval.ONE_MINUTE;
        Integer days = 30;

        DataCollector dataCollector = new DataCollector();
        dataCollector.run(pair, interval, days);
    }
}