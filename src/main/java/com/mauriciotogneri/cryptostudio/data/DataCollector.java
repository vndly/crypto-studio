package com.mauriciotogneri.cryptostudio.data;

import com.google.gson.GsonBuilder;
import com.mauriciotogneri.cryptostudio.api.Klines;
import com.mauriciotogneri.cryptostudio.model.CandleStick;
import com.mauriciotogneri.cryptostudio.types.Interval;
import com.mauriciotogneri.javautils.Resource;

import java.io.File;
import java.util.List;

public class DataCollector
{
    public static void main(String[] args) throws Exception
    {
        String pair = "ETHBTC";
        Interval interval = Interval.ONE_MINUTE;
        Integer limit = 43200; // 30 days
        String filePath = String.format("data/%s_%s.json", pair, interval);

        Klines klines = new Klines(pair, interval, limit);
        List<CandleStick> list = klines.execute();

        String json = new GsonBuilder().setPrettyPrinting().create().toJson(list);
        Resource.save(new File(filePath), json);
    }
}