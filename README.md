# Crypto Studio

Brainstorming:
- Guardar un historial al minuto (o 30 segundos) de todos los precios de las monedas con un volumen superior a 100BTC, así luego podemos decidir si subir o bajar es umbral.
- La herramienta debe ser capaz de calcular las Moving Averages y demás parámetros que calcula el bot para saber cuando comprar o vender
- Analisis general del mercado de BTC de acuerdo con el precio del BTC y valores como el Market Cap total, que tambien deberiamos irlos guardando cada x tiempo, o ver si podemos obtenerlos de webs como https://coinmarketcap.com/, ahora mismo veo que al principio de la web tienen estos valores: 
Market Cap: $546,064,469,514 
/ 24h Vol: $28,381,194,707 / BTC Dominance: 34.6%
- Guardar a su vez valores similares para el mercado de ETH o de BNB para ver si en el futuro nos rentaría añadir otro bot o cambiarlo de mercado
- Simulación de los valores que podemos poner al bot y ver que resultados habríamos obtenido (tenemos que tener en cuenta en las ventas las tasas, habrá también otros parámetros como que no siempre podremos vender al precio máximo que hubo en el mercado, podríamos tenerlo en cuenta consideramos que vendemos por un x% por debajo del precio en el que decidimos vender, que es lo que suele pasar, el bot decide vender y vende a la oferta mas alta q encuentre, que suele ser menor a la que se acaba de vender (no se si me explico))
- En la simulacion estaría bien poder simular varios valores a la vez, y ver los distintos resultados, por ejemplo que podamos observar 10 o 20 resultados a la vez que vayan incrementando un parametro (% de ganancia antes de vender o % de DCA antes de doblarse)
- Idealmente podriamos dar un rango de valores en un parametro y que el bot nos diga cual es el optimo para cierto periodo de tiempo, suponiendo q el resto de valores sea fijo
- Otra evolución, sería lo que hace Greg, multioptimization como diría CB, que sería darle todos los parametros posibles y que el bot calcule cuales son los mejores, pudiendo ajustar el rango de tiempo para el que queremos calcularlo. En esto Greg nos podría ayudar bastante, lo tiene bastante fresco y se pegó bastante con ello
- Como comentaste, una idea muy interesante sería tener varios tipos de archivos de configuracion y que ciertos valores de mercado actuen como trigger y el bot se recalibre solo.
- En un principio los triggers anteriores podrían ser a mano, pero sería muy interesante ver si podemos hacerlo lo suficientemente inteligente como para que determine que tipo de situaciones se suelen dar y como adaptarse a ellas automaticamente (esto sería un machine learning de la hostia)

Links:
https://wiki.profittrailer.io/doku.php/basic_pt_buy_and_sell_logic
https://wiki.profittrailer.io/doku.php/buy_and_sell_strategies
https://wiki.profittrailer.io/doku.php/anderson_dca
https://wiki.profittrailer.io/doku.php/pump_and_dump_protection_strategies

http://forexop.com/martingale-trading-system-overview/

max_cost_percentage
min_buy_volume=500
max_buy_spread=2