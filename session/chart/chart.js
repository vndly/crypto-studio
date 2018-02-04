$(document).ready(function()
{
    loadJSON('../output.json', function(response)
    {
        var data = JSON.parse(response)

        var select = document.getElementById('select')
        select.value = ''
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
        var markers = eventMarkers(json)

        render(history, markers)
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

function render(data, markers)
{
    Highcharts.stockChart('container', {

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
            }

            ,{
                name: 'SMA 24',
                data: [],
                id: 'sma1',
                color: '#00FF00'
            }
            ,{
                name: 'SMA 12',
                data: [],
                id: 'sma2',
                color: '#FF0000'
            }

            ,{
                type: 'flags',
                data: markers,
                onSeries: 'dataseries',
                shape: 'squarepin',
                width: 30
            }
        ]
    })
}