package com.mauriciotogneri.cryptostudio.configuration;

public class RangeInteger extends Range<Integer>
{
    public RangeInteger(String input)
    {
        String[] parts = input.split(" ");

        if (parts.length == 1)
        {
            add(Integer.parseInt(parts[0]));
        }
        else if (parts.length == 3)
        {
            Integer start = Integer.parseInt(parts[0].trim());
            Integer end = Integer.parseInt(parts[1].trim());
            Integer step = Integer.parseInt(parts[2].trim());

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