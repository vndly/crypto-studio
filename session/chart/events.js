function eventTitle(event)
{
    if (event.type == 'watching_buy')
    {
        return 'WB'
    }
    else if (event.type == 'trailing_buy')
    {
        return 'TB'
    }
    else if (event.type == 'buy')
    {
        return 'B'
    }
    else if (event.type == 'watching_sell')
    {
        return 'WS'
    }
    else if (event.type == 'trailing_sell')
    {
        return 'TS'
    }
    else if (event.type == 'sale')
    {
        return 'S'
    }
    else
    {
        return event.type
    }
}

function eventText(event)
{
    if (event.type == 'buy')
    {
        return '<b>' + event.type.replace('_', ' ') + '</b><br>price: ' + event.price + '<br>amount: ' + event.amount + '<br>total: ' + event.total
    }
    else if (event.type == 'sale')
    {
        return '<b>' + event.type.replace('_', ' ') + '</b><br>price: ' + event.price + '<br>amount: ' + event.amount + '<br>total: ' + event.total + '<br>profit total: ' + event.totalProfit + '<br>profit percentage: ' + event.percentageProfit + '%'
    }
    else
    {
        return '<b>' + event.type.replace('_', ' ') + ':</b> ' + event.price
    }
}