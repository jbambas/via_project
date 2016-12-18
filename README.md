# VIA project
## Lunch menu viewer

Tato webová aplikace byla vytvořena za účelem získání zápočtu z předmětu Vývoj Internetových Aplikací (VIA) na FEL ČVUT. Jedná se o mashup aplikaci získávající aktuální denní jídelní menu pražských restaurací pomocí api portálu Zomato. Aplikace dále zobrazuje polohu restaurace pomocí Google maps JavaScript API. Aplikace dále umožňuje zadat aktuální adresu a pomocí Google Maps Geocoding API získat GPS souřadnice a vypočítat vzdálenost k dané restauraci. Seznam restaurací, ze kterých je možné získávat jídelní menu, bude uložen v lokální databázi aplikace. 

## Úvodní obrazovka
![alt tag](https://raw.githubusercontent.com/jbambas/via_project/master/doc/1.JPG)
## Vyhledávání restaurací
![alt tag](https://raw.githubusercontent.com/jbambas/via_project/master/doc/2.JPG)
## Denní menu restaurace
![alt tag](https://raw.githubusercontent.com/jbambas/via_project/master/doc/3.JPG)

## Vystavované API
Tato aplikace vystavuje API (REST, JSON) umožňující CRUD operace nad databází, obsahující uložené restaurace. Dále API poskytuje endpointy umožňující vyhledávání restaurací na Zomato API podle jména restaurace, zobrazení detailu restaurace s vypočtenou vzdáleností od zvolené adresy a získání údajů o denním menu restaurace pro dnešní den. 

Dokumentace API (Swagger) - http://semester-project.cftest.homeatcloud.cz/static/index.html

## Technologie
K implementaci aplikace byl použit webový framework Grails pro jazyk Groovy. Aplikace je nasazaná ve službě Cloud Foundry, viz. odkaz na umístění aplikace. Aplikace je distribuována ve formě souboru \*.war, který je možné nasadit na JSP aplikační server (Tomcat, Glassfish apod.). Persistentní vrstva je tvořena MySQL databází.

Aplikace - http://semester-project.cftest.homeatcloud.cz/

Tento popis je také dostupný v anglickém jazyce na Wiki projektu.
