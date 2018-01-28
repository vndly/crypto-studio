package com.mauriciotogneri.cryptostudio.parameters;

public class RangeFloat extends Range<Float>
{
    public RangeFloat(String input)
    {
        String[] parts = input.split(" ");

        if (parts.length == 1)
        {
            add(Float.parseFloat(parts[0]));
        }
        else if (parts.length == 3)
        {
            Float start = Float.parseFloat(parts[0].trim());
            Float end = Float.parseFloat(parts[1].trim());
            Float step = Float.parseFloat(parts[2].trim());

            while (start <= end)
            {
                add(start);
                start += step;
            }
        }
        else
        {
            throw new RuntimeException("Invalid range: " + input);
        }
    }
}