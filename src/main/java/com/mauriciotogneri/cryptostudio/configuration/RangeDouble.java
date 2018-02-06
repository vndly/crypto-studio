package com.mauriciotogneri.cryptostudio.configuration;

public class RangeDouble extends Range<Double>
{
    public RangeDouble(String input)
    {
        String[] parts = input.split(" ");

        if (parts.length == 1)
        {
            add(Double.parseDouble(parts[0]));
        }
        else if (parts.length == 3)
        {
            Double start = Double.parseDouble(parts[0].trim());
            Double end = Double.parseDouble(parts[1].trim());
            Double step = Double.parseDouble(parts[2].trim());

            if (start < end)
            {
                while (start <= end)
                {
                    add(start);
                    start += step;
                }
            }
            else
            {
                while (start >= end)
                {
                    add(start);
                    start += step;
                }
            }
        }
        else
        {
            throw new RuntimeException("Invalid range: " + input);
        }
    }
}