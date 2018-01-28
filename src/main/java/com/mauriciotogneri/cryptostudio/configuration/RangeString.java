package com.mauriciotogneri.cryptostudio.configuration;

import com.mauriciotogneri.javautils.Strings;

public class RangeString extends Range<String>
{
    public RangeString(String input)
    {
        for (String element : input.split(" "))
        {
            String trimmed = element.trim();

            if (Strings.isNotEmpty(trimmed))
            {
                add(trimmed);
            }
        }
    }
}