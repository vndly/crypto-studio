package com.mauriciotogneri.cryptostudio.app;

import com.google.gson.Gson;
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
    public void run(Pair pair, Interval inputInterval, Interval outputInterval, Integer days) throws Exception
    {
        Integer subdivision = inputInterval.inSeconds() / outputInterval.inSeconds();
        Integer limit = inputInterval.inDays() * days;
        String filePath = FileSource.file(pair, outputInterval);

        Klines klines = new Klines(pair, inputInterval, limit);
        List<CandleStick> candleSticks = klines.execute();
        List<PriceHistory> priceHistory = priceHistory(subdivision, candleSticks);

        String json = new Gson().toJson(priceHistory);
        Resource.save(new File(filePath), json);
    }

    public List<PriceHistory> priceHistory(Integer subdivision, List<CandleStick> candleSticks)
    {
        List<PriceHistory> list = new ArrayList<>();

        for (CandleStick candleStick : candleSticks)
        {
            long openTime = candleStick.openTime();

            for (int i = 0; i < subdivision; i++)
            {
                double fraction = (subdivision == 1) ? 1 : ((double) i / (double) (subdivision - 1));
                long time = openTime + (i * subdivision * 1000);
                double price = candleStick.price(fraction);

                list.add(new PriceHistory(time, price));
            }
        }

        return list;
    }

    public static void main(String[] args) throws Exception
    {
        Pair[] pairs = new Pair[] {
                //Pair.ADABTC,
                Pair.ETHBTC,
                //Pair.LTCBTC,
                //Pair.NEOBTC,
                //Pair.XLMBTC,
                //Pair.XRPBTC
        };
        Interval inputInterval = Interval.ONE_MINUTE;
        Interval outputInterval = Interval.TEN_SECONDS;
        Integer days = 7;

        for (Pair pair : pairs)
        {
            DataCollector dataCollector = new DataCollector();
            dataCollector.run(pair, inputInterval, outputInterval, days);
        }
    }
}