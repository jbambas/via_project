# VIA project
## Lunch menu viewer

Tato webová aplikace byla vytvořena za účelem získání zápočtu z předmětu Vývoj Internetových Aplikací (VIA) na FEL ČVUT. Jedná se o aplikaci získávající aktuální jídelní menu restaurací pomocí api portálu Zomato. Dále budou jídelní menu doplněny gify z portálu giphy.com získaných taktéž skrz jejich api. Seznam restaurací, ze kterých je možné získávat jídelní menu, bude uložen v lokální databázi aplikace. K této databázi bude aplikace vystavovat api (REST, JSON) umožňující CRUD operace (přidání nové restaurace ke stahování, získání všech restaurací ze kterých lze data stahovat apod.).


K implementaci aplikace byl použit webový framework Grails pro jazyk Groovy. Aplikace je distribuována ve formě souboru \*.war, který je možné nasadit na JSP aplikační server (Tomcat, Glassfish apod.). Klient pro potřeby testování vystaveného api je ve formě html stránky s JS. Vystavované api bude zdokumentováno pomocí nástroje Swagger.
