var chart = null

$(document).ready(function()
{
    loadJSON('../output.json', function(response)
    {
        var data = JSON.parse(response)

        var select = document.getElementById('select')
        select.addEventListener('change', function()
        {
            processResult(data[select.value])
        })

        for (var index in data)
        {
            var entry = data[index]
            var option = document.createElement('option')
            option.value = index
            option.text = entryName(index, entry)
            select.add(option)
        }
        
        if (data.length)
        {
            select.value = 0
            processResult(data[select.value])
        }
    })
})

function entryName(index, entry)
{
    return (parseInt(index) + 1) + '. ' + operationsSummary(entry.operations) + ' | ' + entry.totalProfit + ' | ' + entry.averagePercentageProfit + '%'
}

function loadJSON(file, callback)
{
    var xobj = new XMLHttpRequest()
    xobj.overrideMimeType('application/json')
    xobj.open('GET', file, true)
    xobj.onreadystatechange = function()
    {
        if ((xobj.readyState == 4) && (xobj.status == '200'))
        {
            callback(xobj.responseText)
        }
    }
    xobj.send(null)
}

function processResult(json)
{
    var fileName = '../../data/' + json.input.pair + '_' + json.input.interval + '.json'

	fillInputTable(json)

    loadJSON(fileName, function(response)
    {
        var data = JSON.parse(response)

        var history = priceHistory(data)
        var events = eventMarkers(json)

        render(history, json.sma1, json.sma2, json.ema1, json.ema2, events)
    })
}

function fillInputTable(result)
{
	fillInputCell('input.operations', operationsSummary(result.operations))
	fillInputCell('input.totalProfit', result.totalProfit)
	fillInputCell('input.averagePercentage', result.averagePercentageProfit + '%')
	fillInputCell('input.source', result.input.source)
	fillInputCell('input.pair', result.input.pair)
	fillInputCell('input.interval', result.input.interval)
	fillInputCell('input.maxCost', result.input.maxCost)
	fillInputCell('input.buyStrategy', result.input.buyStrategy)
	fillInputCell('input.buyValue', round(result.input.buyValue))
	fillInputCell('input.trailingBuy', round(result.input.trailingBuy))
	fillInputCell('input.sellStrategy', result.input.sellStrategy)
	fillInputCell('input.sellValue', round(result.input.sellValue))
	fillInputCell('input.trailingProfit', round(result.input.trailingProfit))
	fillInputCell('input.stopLossTrigger', round(result.input.stopLossTrigger))
	fillInputCell('input.smaPeriod', result.input.smaPeriod)
	fillInputCell('input.sma1', result.input.sma1)
	fillInputCell('input.sma2', result.input.sma2)
	fillInputCell('input.smaCrossCandles', result.input.smaCrossCandles)
	fillInputCell('input.emaPeriod', result.input.emaPeriod)
	fillInputCell('input.ema1', result.input.ema1)
	fillInputCell('input.ema2', result.input.ema2)
	fillInputCell('input.emaCrossCandles', result.input.emaCrossCandles)
	fillInputCell('input.bbPeriod', result.input.bbPeriod)
	fillInputCell('input.bbSma', result.input.bbSma)
}

function operationsSummary(operations)
{
	var positive = 0
	var negative = 0
	
	for (var index in operations)
	{
		var events = operations[index].events
		var last = events[events.length-1]
		
		if (last.totalProfit > 0)
		{
			positive++
		}
		else
		{
			negative++
		}
	}
	
	return operations.length + ' (+' + positive + '/-' + negative + '/' + parseInt(positive/operations.length*100) + '%)'
}

function round(value)
{
	return Math.round(value * 100) / 100
}

function fillInputCell(name, value)
{
	document.getElementById(name).innerHTML = value
}

function priceHistory(json)
{
    var list = []

    for (var index in json)
    {
        var element = json[index]
        list.push([element.time, element.price])
    }

    return list
}

function eventMarkers(json)
{
    var markers = []

    var markerIndex = 1

    for (var index in json.operations)
    {
        var operation = json.operations[index]

        for (var i in operation.events)
        {
            var event = operation.events[i]

            if (isValidEvent(event))
            {
                markers.push(
                {
                    x: event.time,
                    title: markerIndex + eventTitle(event),
                    text: eventText(event)
                })
            }
        }

        markerIndex++
    }

    return markers
}

function render(data, sma1, sma2, ema1, ema2, events)
{
    chart = Highcharts.stockChart('container', {

        chart: {
            zoomType: 'x',
            panning: true,
            panKey: 'shift'
        },

        tooltip: {
            style: {
                width: '200px'
            },
            valueDecimals: 8,
            shared: true
        },

        plotOptions:{
            series:{
                turboThreshold: 100000
            }
        },

        series: [
            {
                name: 'Price',
                data: data,
                id: 'dataseries'
            },
            {
                name: 'SMA 1',
                data: sma1,
                id: 'sma1',
                type: 'spline',
                color: '#da6767'
            },
            {
                name: 'SMA 2',
                data: sma2,
                id: 'sma2',
                type: 'spline',
                dashStyle: 'Dot',
                color: '#da6767'
            },
            {
                name: 'EMA 1',
                data: ema1,
                id: 'ema1',
                type: 'spline',
                color: '#61a053'
            },
            {
                name: 'EMA 2',
                data: ema2,
                id: 'ema2',
                type: 'spline',
                dashStyle: 'Dot',
                color: '#61a053'
            },
            {
                type: 'flags',
                data: events,
                onSeries: 'dataseries',
                shape: 'squarepin',
                width: 30
            }
        ]
    })
}

function updateSMA(checkbox)
{
	if (checkbox.checked)
	{
		chart.series[1].show()
		chart.series[2].show()
	}
	else
	{
		chart.series[1].hide()
        chart.series[2].hide()
	}
}

function updateEMA(checkbox)
{
	if (checkbox.checked)
    {
        chart.series[3].show()
        chart.series[4].show()
    }
    else
    {
        chart.series[3].hide()
        chart.series[4].hide()
    }
}