package com.mauriciotogneri.cryptostudio.app;

import com.google.gson.GsonBuilder;
import com.mauriciotogneri.cryptostudio.api.Klines;
import com.mauriciotogneri.cryptostudio.model.price.CandleStick;
import com.mauriciotogneri.cryptostudio.source.FileSource;
import com.mauriciotogneri.cryptostudio.type.Interval;
import com.mauriciotogneri.javautils.Resource;

import java.io.File;
import java.util.List;

public class DataCollector
{
    public void run(String pair, Interval interval, Integer days) throws Exception
    {
        Integer limit = interval.onDaySize() * days;
        String filePath = FileSource.file(pair, interval.code());

        Klines klines = new Klines(pair, interval, limit);
        List<CandleStick> list = klines.execute();

        String json = new GsonBuilder().setPrettyPrinting().create().toJson(list);
        Resource.save(new File(filePath), json);

        ChartGenerator chartGenerator = new ChartGenerator();
        chartGenerator.json(pair, interval);
    }

    public static void main(String[] args) throws Exception
    {
        String pair = "ETHBTC";
        Interval interval = Interval.ONE_MINUTE;
        Integer days = 30;

        DataCollector dataCollector = new DataCollector();
        dataCollector.run(pair, interval, days);
    }
}