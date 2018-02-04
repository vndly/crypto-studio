package com.mauriciotogneri.cryptostudio.source;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mauriciotogneri.cryptostudio.indicator.EMA;
import com.mauriciotogneri.cryptostudio.indicator.Last24Hours;
import com.mauriciotogneri.cryptostudio.indicator.SMA;
import com.mauriciotogneri.cryptostudio.model.price.PriceData;
import com.mauriciotogneri.cryptostudio.model.price.PriceHistory;
import com.mauriciotogneri.cryptostudio.model.session.Input;
import com.mauriciotogneri.cryptostudio.type.Interval;
import com.mauriciotogneri.cryptostudio.type.Pair;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileSource extends Source
{
    private final Pair pair;
    private final Interval interval;

    public FileSource(Pair pair, Interval interval)
    {
        this.pair = pair;
        this.interval = interval;
    }

    @Override
    public List<PriceData> priceData(Input input)
    {
        try
        {
            String filePath = file(pair, interval);
            InputStream inputStream = new FileInputStream(filePath);
            String json = new Scanner(inputStream, "UTF-8").useDelimiter("\\A").next();

            List<PriceHistory> priceHistory = new Gson().fromJson(json, new TypeToken<List<PriceHistory>>()
            {
            }.getType());

            return priceData(priceHistory, input);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    private List<PriceData> priceData(List<PriceHistory> priceHistory, Input input)
    {
        Last24Hours last24Hours = new Last24Hours(input.interval());
        SMA sma = new SMA(input.interval(), input.smaPeriod, input.sma1, input.sma2);
        EMA ema = new EMA(input.interval(), input.emaPeriod, input.ema1, input.ema2);

        List<PriceData> priceData = new ArrayList<>();

        for (PriceHistory price : priceHistory)
        {
            last24Hours.update(price);
            sma.update(price);
            ema.update(price);

            priceData.add(new PriceData(
                    price.time(),
                    price.price(),
                    last24Hours.oldest(),
                    sma.sma1(),
                    sma.sma2(),
                    ema.ema1(),
                    ema.ema2()));
        }

        return priceData;
    }

    public static String file(Pair pair, Interval interval)
    {
        return String.format("data/%s_%s.json", pair, interval);
    }
}