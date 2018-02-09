const EVENT_WATCHING_BUY  = 'watching_buy'
const EVENT_TRAILING_BUY  = 'trailing_buy'
const EVENT_BUY           = 'buy'
const EVENT_WATCHING_SELL = 'watching_sell'
const EVENT_TRAILING_SELL = 'trailing_sell'
const EVENT_SELL          = 'sale'

const TYPE_WB = 'WB'
const TYPE_TB = 'TB'
const TYPE_B  = 'B'
const TYPE_WS = 'WS'
const TYPE_TS = 'TS'
const TYPE_S  = 'S'

function eventType(event)
{
    if (event.type == EVENT_WATCHING_BUY)
    {
        return TYPE_WB
    }
    else if (event.type == EVENT_TRAILING_BUY)
    {
        return TYPE_TB
    }
    else if (event.type == EVENT_BUY)
    {
        return TYPE_B
    }
    else if (event.type == EVENT_WATCHING_SELL)
    {
        return TYPE_WS
    }
    else if (event.type == EVENT_TRAILING_SELL)
    {
        return TYPE_TS
    }
    else if (event.type == EVENT_SELL)
    {
        return TYPE_S
    }
    else
    {
        return event.type
    }
}

function eventText(event)
{
    if (event.type == EVENT_BUY)
    {
        return '<b>' + event.type.replace('_', ' ') + '</b><br>price: ' + event.price + '<br>amount: ' + event.amount + '<br>total: ' + event.total
    }
    else if (event.type == EVENT_SELL)
    {
        return '<b>' + event.type.replace('_', ' ') + '</b><br>price: ' + event.price + '<br>amount: ' + event.amount + '<br>total: ' + event.total + '<br>profit total: ' + event.totalProfit + '<br>profit percentage: ' + event.percentageProfit + '%'
    }
    else
    {
        return '<b>' + event.type.replace('_', ' ') + ':</b> ' + event.price
    }
}