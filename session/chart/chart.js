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
        
        data.sort(function(a, b)
        {
			return (a.profitTotal < b.profitTotal) ? 1 : ((a.profitTotal > b.profitTotal) ? -1 : 0)
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
    return (parseInt(index) + 1) + '. ' + entry.input.pair + ' | ' + entry.operations.length + ' | ' + entry.profitTotal + ' | ' + entry.profitPercentage + '%'
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

    loadJSON(fileName, function(response)
    {
        var data = JSON.parse(response)

        var history = priceHistory(data)
        var events = eventMarkers(json)

        render(history, json.sma1, json.sma2, json.ema1, json.ema2, events)
    })
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