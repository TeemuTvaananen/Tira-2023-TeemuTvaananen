# Raportit tehtävistä

Kirjaa tähän tiedostoon **jokaiseen** tehtävään liittyvät omat raporttisi ja analyysisi. Muista että raportti on myös kurssilla **arvosteltava tehtävä**.

Voit sisällyttää raporttiin tekstimuotoisia taulukoita (tasaukset välilyönnein):

```
n     Fill     Search   Total
500   7        700      707
1000  9        288      297
```

Ja näihin liittyviä kuvatiedostoja:

![Esimerkkikuva](report-sample-image.png)

Nämä näkyvät sitten VS Coden Preview -näkymässä (tai oman repositorysi webbisivulla) oikein muotoiltuna. Käytä tässä dokumentissa olevia muotoiluja esimerkkinä kun kirjoitat raporttiasi. 

Huomaa että jos laitat kuvatiedostot vaikka omaan alihakemistoonsa, Markdown -muotoilussa on oltava suhteellinen polku tiedostoon, esimerkiksi `images/report-sample-image.png`. **Älä** käytä absoluuttisia polkuja `C:\Users\tippaleipa\kurssit\TIRA\kuva.png`, koska nämä eivät tietenkään toimi opettajan koneella. Ei kannata laittaa linkkiä etärepoosikaan, vaan nimenomaan paikalliseen tiedostoon.

Voit myös sisällyttää *lyhyitä* koodinpätkiä vaikkapa Java -formaatilla:

```Java
	@Override
	public int hashCode() {
		// Oma nerokas hajautufunktioni!
	}
```
Tarvittaessa käytä myös paremmin muotoiltuja taulukoita:

| n	| Fill	| Search	| Total |
|-----|--------|--------|-------|
| 500	 | 7	| 700	| 707 |
| 1000 |	9	| 288	| 297 | 

Alaluvut jokaisen tehtävän raportille löydät alta.


## 01-TASK
Ensimmmäisenä tutustuin kurssilla annettuun materiaaliin linkkien https://en.wikipedia.org/wiki/Insertion_sort ja https://docs.oracle.com/en/java/javase/18/docs/api/java.base/java/lang/Comparable.html kautta. Wikipedian sivulta löytyi hyvä pseudokoodi, jonka pohjalta algoritmin toteuttamisen miettimistä oli helppoa jatkaa. Tämän jälkeen tutustuin lisää algoritmiin youtuben ihmeellisessä maailmassa ja opin lopulta hahmottamaan algoritmin toimivuuden ja käytännön totetuttamisen videoiden https://www.youtube.com/watch?v=0lOnnd50cGI&t=445s https://www.youtube.com/watch?v=OGzPmgsI-pQ&t=42s kautta.

Seuraavaksi aloin miettimään käytännön toteutusta. Lajittelun tuli onnistua geneerisesti ja myös käyttäen comparable rajapintaa. Vertailun toteuttaminen algoritmissa tuntui helpolta ja selkeältä, kun käytti compareTo-metodia. Yliajattelin geneerisyyden toteuttamista ensin. Luulin, että metodissa tulee käsitellä niin Integer-kokonaislukuja kuin String-merkkijonoja. Kuitenkin tajusin pian, että Comparable-rajapinta toteuttaa nämä ja minun ei tarvitse kuin kirjoittaa "raaka" algoritmi ilman, että tarvitsisi ajatella tätä. Kannatti siis lukea docs:it. 

Koodin kommenteihin olen merkannut vaiheet tarkemmin kuvattuna. 

Vaikeinta tehtävässä oli miettiä ja saada toteutettua lisäyslajittelu tiettyjen indeksien välille taulukossa. Pitkän pähkäilyn jälkeen huomasin, että taulukon läpikäyntiä ei kannata aloittaa kohdasta fromIndex vaan kohdasta fromIndex+1, jonka jälkeen testit menivät läpi. 


Seuraavaksi aloin toteuttamaan taulukon järjestyksen kääntämistä. Tämä oli entuudestaan itselleni tuttua netissä tekemieni harjoitusten perusteella ja oli helppo toteuttaa. Ideani oli ottaa taulukon aloituspisteeksi indeksi 0, tallettaa se muuttujaan startPoint, ottaa taulukon loppu muuttujaan endpoint. Tässä vaiheessa muistin, että on tärkeää ottaa siihen arvo 
array.length -1, jotta alkioiden vaihto tapahtuu onnistuneesti. Sen jälkeen tein oman metodini nimeltä "swap", joka näyttää seuraavalta.  
```Java
	@Override
	  private static <T> void swap(T[] array, int start, int end) {
      T pointer = array[start];
      array[start] = array[end];
      array[end] = pointer;
   }
```
Tähän sain inspiraation luennolta, jossa demottiin Javan comparator ja Predicate luokkia. Algoritmi toimi odotusten mukaan ja myös jälkeenpäin toteutettu taulukon kääntäminen tiettyjen indeksien välillä oli helppoa tehdä. 

Viimeisenä noudatin ohjeita ja viimeistelin toisen askeleen. Tira-coders sovelluksen käynnistäminen oli helppoa ja sovelluksen käyttäminen on myös selkeää.
Koodin lisääminen Coder.java ja SimpleContainer.java luokkiin oli myös helppoa ja ohjeita noudattamalla sain helposti kiinni siitä mitä tässä askeleessa haettiin omasta mielestäni. 


Kysymykset:
Toteuttamani lajittelualgoritmin aikakompleksisuusluokka on O(n^2). Tämä johtuu kahdesta sisäkkäisestä silmukasta.
Toteuttamani reverse algoritmin aikakompleksisuusluokka on O(n/2) eli O(n). Tämä johtuu siitä, että siinä käytetään yhtä silmukkaa, jossa kaksi osoitinta liikkuvat jokaisella iteraatiolla toisiaan kohti.

"Jos taulukko on jo valmiiksi järjestyksessä nousevaan järjestykseen, ja se aiotaan lajitella laskevaan järjestykseen, kannattaako taulukko lajitella vai kääntää sen järjestys? Miksi, perustele?"

Taulukon järjestys kannattaa kääntää. 
Tämä johtuu useasta syystä:
Taulukon uudelleen lajittelussa menisi paljon aikaa ja esimerkiksi lisäyslajittelu-algoritmille tämä olisi erittäin työlästä, sillä jokainen alkio tulisi siirtää taulukon alkuun ja tällöin aikakompleksisuus olisi "worst-case-scenario":n mukainen eli O(n^2). 
Taulukon kääntäminen sen sijaan voidaan suorittaa ilman ylimääräistä vertailua tehokkaasti vain vaihtamalla alkioiden paikkaa taulukossa, jolloin aikakompleksisuus on lineaarista. 
Kääntäminen on myös helpompaa toteuttaa, sillä kääntämis-algoritmin integrominen koodiin on helpompaa kuin lajittelualgoritmien, jotka yleensä ovat monimutkaisempia ja vaativat paljon testaamista ja työtä. Tämä helpottaa myös koodin luettavuutta ja ylläpitämistä.

Kaikenkaikkiaan opin tämän tehtävän tekemisessä uutta ja hyödyllistä tietoa niin javan comparable-rajapinnasta kuin algoritmien toteuttamisesta yleensäkin.
## 02-TASK

Opin tehtävän tekemisessä toteuttamaan lineaarisen hakufunktion. Tehtävän tekeminen tuntui erittäin luontevalta ja helpolta. Vaikeinta tässä tehtävässä oli taulukon tietoja käyttäen piirtää taulukot ja saada ne näkymään tässä raportissa. Aluksi toteutin lisäyslajittelualgoritmit käyttäen Comparator-rajapintaa. Se oli aikaisemman tehtävän ansiosta jo tuttua ja itse Comparatorin käyttö tuntui loogiselta ja helpolta. Askel 2 eli uusien luokkien luominen ja testaaminen Tira-Coders-sovelluksessa testaaminen. Tämä oli myös helppoa ja kivaa. Oli mielekästä nähdä oman työn tuotos käyttöliittymässä. 

Sitten askel numero 3. Tämän toteuttamisessa ei myöskään ilmennyt mitään onglemia. Ensin täytyi vaan tutustua Predicate luokkaan, jotta osasin käyttää oikeita metodeja oikealla tavalla. Tehtävänanto oli myös erittäin selkeä, mikä helpotti tehtävän tekemistä huomattavasti. 


Toteuttamani lineaarisen hakualgoritmin tulostaulukko ja käyrät: 

![Taulukko](image.png)
![Täyttöaika](tayttoaika.png) 
![Hakuaika](hakuaika.png)


Kuvista ja tuloksista tein seuraavat johtopäätökset. 

Täyttöajan kasvu tietosäiliön elementtien kasvuun testitulosten, kaavion tarkastamisen ja regressioanalyysin jälkeen ei noudata lineaarista kasvua vaan eksponentiaalista kasvua. Tarkemmin tarkasteltuna Excel-ohjelmassa polynomi sopii tuotettuu taulukkoon parhaiten ja antaa isoimman korrelaatiokertoimen. Tästä päättelin lyhyen Googlauksen perusteella ja tarkemman algortimin tarkastelun perusteella, että tietosäiliön kasvaessa myös uudelleenallokointiin voi olla tarvetta enemmän ja isoilla tietomäärillä siinä myös kestää. 

Hakuaikoijen kasvu suhteessa tietosäiliön elementtien kasvuun. Tein tästä syntyneelle kaaviolle myös regressioanalyysin ja lineaarinen suora sopi tuloksiin melko hyvin. Testin alkaessa hakuajat ovat kuitenkin suhteellisen korkealla, kunnes 4000 elementin kohdalla hakuaika pienenee merkittävästi 89 mikrosekuntiin ja alkaa tästä lähtien kasvaa suhteellisen lineaarisesti. 

Näistä päätelmistä tulin siis siihen lopputulokseen, että add-algoritmin aikakompleksisuusluokka tällä testiaineistolla on O(n^2) ja hakualgoritmien aikakompleksisuusluokat ovat O(n).


Seuraavaksi kokeilin lajittelua Tira Coders Sovelluksessa ja huomasin, että kuten tehtävänannossa mainitaan, välillä lajittelu on nopeampaa ja välillä hitaampaa. Tämän näkee Log view ikkunasta. Jos esimerkiksi lajitellaan ensin koodarinimen perusteella niin minulla siinä kesti noin 380ms ja log viewiin tulostui, että sorting took 383ms esimerkiksi. Jos tästä sitten käännetään nimet päinvastaiseen järjestykseen niin sovellus osaakin käytää reverse-algoritmia ja tällöin lajittelussa ei kestä kuin 1-3ms. 

Jos taulukko siis on valmiiksi lajiteltu niin kannattaa mieluummin vain kääntää järjestys sen sijaan, että lajittelisi taulukon kokonaan uudestaan. Reverse algoritmi toimii lineaarisesti ja käy vain kerran kaikki taulukon alkiot läpi. Lajittelualgoritmi toimii puolestaan aikakompleksisuusluokan O(n^2) mukaan ja täysin päinvastaiseen järjestykseen kääntäminen olisi tälle algoritmille ns. "worst case scenario", sillä jokainen alkio pitäisi siirtää aina taulukon alkuun. 

Tässä tehtävässä toteutettuja algoritmeja kutsutaan lineaarisiksi, koska niissä käydään taulukko läpi vain kerran ja etsitään sieltä tietty arvo tai indeksi ja lopetetaan läpikäynti heti, kun tietty ehto löytyy ja palautetaan se kutsujalle. Puolestaan edellisessä tehtävässä toteutettu lisäyslajittelualgoritmi ei ole lineaarinen, koska se etsii ensimmäisenä tietyn arvon taulukosta ja sen jälkeen vielä lajittelee taulukkoa niin, että löydetty arvo löytää oikean paikan taulukossa. 

Lineaaristen algoritmien aikakompleksisuusluokka big O-notaatiolla esiteltynä on O(n).
## 03-TASK 
Tässä tehtävässä opin tekemään puolitushakualgoritmin. Ensin katsoin kurssin tarjoamat luentokalvot ja sen jälkeen tutustuin netissä vielä aiheeseen. Video https://www.youtube.com/watch?v=NFhOrxtXXcM&t=685s auttoi hahmottamaan tämän haun toimintaa. Comparable rajapinnan mukana tulevat metodit olivat myös helppoja toteuttaa tähän algortimiin ja oikeastaan koko tehtävä itsessään oli jälleen niin selkeästi selitetty, ettei sen tekemisessä ilmennyt mitään erikoisempia haasteita ja kaikki näytti toimivan sujuvasti. Toisaalta omalla pöytäkoneellani oli ilmeisesti VScoden asetuksissa asetettu JDK:n polku jotenkin väärin, sillä aluksi näytti siltä, etteivät binarysearchtests lähde ollenkaan toimimaan. Tämäkin ongelma oli onneksi helppo korjata muokkaamalla oikea polku settings.json tiedostoon. 

Tehtävän 3 analyysit:

Kokeiltuani ja testailtuani hakutoimintoja Tira coders-sovelluksessa sain seuraavat tulokset.
Ensimmäiset haut: 34ms ensimmäinen haku ja nopea haku 16ms
Toiset haut: 22ms ja nopea haku 12ms

Keskimäärin suoritusaika nopeassa haussa suhteessa ensimmäiseen hakuun on noin puolet ajasta. 
Huomasin, että listan alusta haettuna hakuajat pienenivät merkittävästi ne olivat 1ms ja 0ms.
 
Fast-search on ilmeisemmin nopeampi kuin search. Tämä johtuu siitä, että sen käyttämä puolitushakualgoritmi on nopeampi kuin lineaarinen haku


Tehtävän 3 tulostukset taulukkoina 

Binarysearch ascending order tests	:

![taulukko1](image-3.png)



Binarysearch descending order tests  :

![taulukko2](image-4.png)

ASCEDING ORDER TESTS KÄYRÄT: 

![taytto1](image-10.png)

![lajittelu1](image-11.png)

![haku1](image-1.png)



DESCENDING ORDERTESTS KÄYRÄT

![taytto2](image-2.png)


![lajittelu2](image-5.png)

![haku2](image-6.png)

Yleisesti ottaen puolitushakualgoritmi tuo etua, kun haetaan tiettyä indeksiä valmiiksi lajitellusta taulukosta. Kuten huomataan myös testin Binarysearch tuloksista :
 
Average linear search duration:    2345860 ns == noin 2.35ms
Average binary search duration:      20560 ns == noin 0.02056ms

Average linear search duration:     368940 ns == noin 0.369ms
Average binary search duration:      30600 ns == noin 0.0306ms


Puolitushakua kannattaa siis käyttää, kun kyseessä on isokokoinen ja valmiiksi lajiteltu tietotaulukko, josta halutaan etsiä jonkin tietty alkio. 

Lineaarisen hakualgoritmin aikakompleksisuusluokka on O(n). Tämä johtuu siitä, että taulukon jokaista alkiota tarkastellaan vain kerran kunnes tietty arvo löytyy tai taulukko on käyty läpi. N ollessa esimerkiksi 50 algoritmin iteraatioiden määrä on korkeintaan 50.

Puolitushakualgoritmin aikakompleksisuusluokka puolestaan on O(log n) eli logaritminen. Tämä johtuu siitä, että hakualue jaetaan jokakerralla kahtia, joten algoritmin täytyy tällöin suorittaa korkeintaan log2(n) iteraatiota, missä n on hakualueen koko. 

Kaikenkaikkiaan tehtävä oli helppo toteuttaa kiitos hyvän tehtävänannon ja muun ohjeistuksen.
## 04-TASK
``Disclaimer: Pohdin tehtävän toteutusta ja debuggaamista yhdessä opiskelija Esko Hautalan kanssa.``

Opin tehtävän tekemisessä paljon siitä mikä pino tietorakenne on Java-ohjelmointikielessä ja mitä kaikkia metodeja ja keinoja sen muokkaamiseen voi käyttää. Mielestäni onnistuin kirjoittamaan oman toteutukseni pinoluokasta siten, että se vastasi tehtävänannossa annettuja aikakompleksisuusvaatimuksia. Käytin silmukkaa ainoastaan siinä, kun pinon kapasiteetti täytyy reallokoida ja myös tulosteessa eli toString-metodissa. Muissa metodeissa onnistuin tekemään kaiken ilman silmukoita.

Vaikeinta tässä tehtävän tekemisessä oli tarkistimen toteuttaminen. Ohessa olen kirjoittanut sen toimintalogiikan. 

1. Ensimmäisenä olen nimennyt tarvittavat muuttujat tarkistimen toteutukseen. ParenthesisTotal on lopullinen sulkumerkkien määrä, lineNumber ja columnNumber kuvastavat rivi ja sarakenumeroita, betweenQuotes:ia käytetään, kun tarkistetaan ollaanko lainausmerkkien välissä.

2. Käynnistetään silmukka ja aletaan käymään annettua merkkijonoa merkki merkiltä läpi. LineNumber muuttuja alkaa luonnollisesti riviltä 1 ja columnNumber-muuttujaa kasvatetaan sen mukaan, kun merkkejä tulee käytyä läpi. Jokaisella iteraatiolla varataan muuttujaan IndexChar kyseisen iteraation tarkastelun kohteena oleva merkki. 

3. Ensimmäisenä tarkistellaan ollaanko kohdattu lainausmerkki ja ollaanko niitten välissä. Jos kohdataan lainausmerkki muutetaan betweenQuotes muuttujan arvoa true arvoon. Nyt olemme siis lainausmerkkien välissä ja täytyy ottaa se huomioon. Tälle löytyy oma tarkastin, joka jättää huomiotta kaikki merkit niin kauan kuin ollaan lainausmerkkien välissä eli, kun betweenQuotes on true. Toisen lainausmerkin tullessa vastaan betweenQuotes muuttuu false arvoon ja tällöin tarkistin siirtyy tarkastelemaan, mikä merkki on kyseessä. 

4. Nyt katsotaan onko iteraation merkki joku sulkevista suluista eli ( { tai [. Jos vastaavuus löytyy, yritetään puskea tämä merkki pinoon käyttämällä stack.push- metodia. Jos tämä epäonnistuu niin tarkistin heittää ilmoille virheilmoituksen hyödyntäen ParenthesisException-luokkaa ja tämän switch case:ia STACK_FAILURE. Viestissä on myös rivi ja sarakenumero. Jos puskeminen onnistuu niin kasvatetaan sulkumerkkien eli parenthesisTotal-muuttujan määrää.

5. Seuraavaksi katsotaan onko kyseinen merkki joku sulkumerkeistä eli ) } tai ]. Jos vastaavuus löytyy, täytyy varmistaa tässä vaiheessa, että onko pino tyhjä. Jos se on tyhjä, tällöin sululle ei ole vastaavaa sulkevaa sulkua ja syötteessä on tällöin syntaksivirhe. Jos pino ei puolestaan ole tyhjä otetaan pinosta ylimmäinen elementti tarkasteluun käyttämällä stack.pop-metodia ja varataan se muuttujaan expected. Tässä vaiheessa voidaan myös kasvattaa kokonaissulkujen määrää.

6. Nyt siirrytään lähempään tarkasteluun ja katsotaan löytyykö tälle pinosta otetulle merkille vastaavuus merkistä, jota käsitellään iteraatiossa. Tarkistetaan siis, että jos indexChar muuttujassa on '(' niin pinosta otettu merkki täytyy olla ')' ja niin edelleen. Jos näin ei ole, on syötteessä virhe ja tarkistin heittää virheilmoituksen käyttäen hyväksi ParenthesisException luokkaa ja sen sisältämässä switch rakenteen case:ssa olevaa PARENTHESIS_IN_WRONG_ORDER. Virheilmoituksessa on myös rivi ja sarakenumero. Muutoin on löytynyt vastaavuus ja siirrytään eteenpäin.

7. Nyt tarkistetaan onko iteraation käsittelemä merkki rivinvaihtomerkki. Jos näin on niin kasvatetaan rivinumeromuuttujaa ja "resetoidaan sarakenumeromuuttuja".

8. Viimeisenä, kun tarkistin on käynyt koko merkkijonon loppuun silmukassa tarkastetaan vielä onko pinoon jäänyt jotain. Jos on voidaan tehtävänannon mukaisesti olettaa, että pinossa on liian vähän sulkevia sulkumerkkejä. Tästä tarkistin myös huomauttaa heittämällä virheilmoituksen TOO_MANY_CLOSING_TAGS ParenthesisException luokasta. Virheilmoituksessa on myös rivi ja sarakenumero. 

9. Lopulta, jos kaikki on mennyt suunnitelmien mukaan ja tarkistin ei ole löytänyt huomautettavaa, palautetaan lopullinen yhteenlaskettu sulkumerkkien määrä eli kokonaislukumuuttuja parenthesisTotal. 

Tämä tehtävä loi haastavuutta erityisesti bugien ilmaantuessa, mutta lopulta sain tehtyä mielestäni tehtävänannon täyttävän ja loogisen kokonaisuuden ja opin paljon uutta ja hyödyllistä tietoa.

## 05-TASK
Lähtiessäni toteuttamaan tehtävää halusin toteuttaa sen alusta alkaen käyttämällä linkitettyä listaa. Halusin näin oppia jotain uutta perinteisen taulukkopohjaisen toteutuksen sijaan ja näin haastaa itseäni. Katsoin siis ensimmäisenä luentomateriaalit aiheesta ja sitten tutustuin netistä löytyviin materiaaleihin. Kuten https://www.geeksforgeeks.org/queue-linked-list-implementation/. Tämän ja tehtävänannon tarjoaman materiaalin pohjalta tehtävä oli loppujen lopuksi helppo ja jokseenkin selkeästi toteutettavissa.

Linkitetyn listan käyttämisessä on joitakin hyötyjä. Muistin reallokoinnille ei ole tarvetta, sillä linkitetyn listan kapasiteettina muistin käyttämiselle on käytännössä tietokoneen RAM-muisti. Näin ollen, jos tämän tehtävän tapauksessa luotavan jonon kokoa ei tiedetä, voi olla järkevämpää käyttää linkitettyä listaa. Taulukko päihittää linkitetyn listan silloin esimerkiksi kun tiedetään etukäteen alkioiden määrä, jotka aiotaan lisätä jonoon. Taulukko käyttää myös vähemmän muistia, sillä siinä ei tarvitse lisätä linkkejä muihin alkioihin datan lisäksi. 

Kokonaisuudessaan tehtävä oli helppo selkeän tehtävänannon ja luentomateriaalin johdosta. Opin tehtävän tekemisessä paljon uutta linkitetystä listasta ja muistin allkoinnista kirjoitettaessa ohjelmia. 

## 06-TASK
Tehtävän aluksi mietin, että minkä algoritmin haluaisin oikein toteuttaa. Katsoin luentomateriaalista esimerkkejä ja myös netistä esimerkiksi https://www.geeksforgeeks.org/quick-sort/ ja myös https://www.geeksforgeeks.org/merge-sort/. Näistä lopulta päätin toteuttaa jälkimmäisen eli Merge Sort-lajittelualgoritmin. Päätökseen vaikutti erityisesti algoritmin loogisuus ja tuntui mielenkiintoiselta ja houkuttelevalta kokeilla tätä "hajoita ja hallitse" logiikkaa.

Tehtävän toteutus itsessään oli melko haastava ja aluksi tuntui erittäin puuduttavalta. Lopulta kuitenkin taisteltuani comparatorien kanssa pääsin tavoitteeseeni tehtävän kanssa ja onnistuin luomaan hyvän mergesort-lajittelualgoritmin. 

Ohessa on  CodersFastComparatorTests tulokset taulukossa ja kuva graafista joka on piirretty taulukon elementtien kasvusta lajittelun keston suhteen.  
 ![FastSortTaulukko](image-7.png)
![FastSortGraafi](image-9.png)

Sitten tulokset CoderSlowComparatorTests taulukossa ja kuva graafista joka on tehty samalla periaatteella kuin ylempi.



![SlowSortTaulukko](image-12.png)
![SlowSortGraafi](image-8.png)

Taulukon tuloksista ja graafeista voidaan havaita seuraavia asioita:
-Nopean lajittelualgoritmin aika per elementti ei muutu niin merkittävästi aineiston kasvaessa kuin mitä hitaan lajittelualgoritmin vaan ne pysyvät melkeinpä samana koko ajan. Näin ollen algoritmi on tehokas ja pystyy täyttämään tehtävän vaatimuksen ( O(nlogn) ) aikakompleksisuuden suhteen.
-Hitaalla lajittelualgoritimilla aika per elementti nousee myös merkittävästi aineiston kasvaessa.

-Kaiken kaikkiaan opin tehtävän tekemisessä paljon uutta nopeista lajittelualgoritmeista ja tehtävä oli erittäin mielenkiintoinen ja opettavainen
## 07-TASK

** DISCLAIMER TOTEUTIN TEHTÄVÄN JAKAEN IDEOITA JA YHDESSÄ TUUMIEN OPISKELIJA ESKO HAUTALAN KANSSA ** 


Tehtävä oli erittäin haastava ja aluksi tarvittiin perinpohjaista tutustumista tietorakenteen toimintaan ja ylipäätään logiikkaan jolla binäärinen hakupuu toimii. Tässä tulivat vastaan jo ensimmäiset haasteet, sillä oli erittäin vaikeaa saada omaan päähäni ne toimintaperiaatteet, mutta lopulta luentovideoiden avulla sain lopulta tulkittua hakupuun logiikkaa ja pääsin tekemään toteutusta. Valitsin algoritmien toteutustavaksi rekursiivisen inorder läpikäynnin. Silmukoiden käyttöä on tullut muilla kursseilla harjoiteltua hyvin joten halusin hieman haastetta rekursiivisen tavan muodossa. Debuggausvaiheessa näiden korjaaminen oli työlästä, mutta mielestäni sain kaiken vaadittavan lopulta toimimaan oikeellisesti ja suhteellisen tehokkaasti. BST performance test täytyi keskeyttää sillä miljoonalla koodarilla testi hidastui merkittävästi eikä enää edennyt mihinkään. Alla on taulukko muodossa tämän testin tuloksia ja näistä piirrettyjä graafeja.

HAKUPUU TAULUKOT JA ANALYYSI
![BST_TAULUKKO](image-13.png)

![ADDTIME](image-14.png)
![SEARCH TIME / N](image-15.png)
![TOARRAY / N](image-16.png)
![GET INDEX /N](image-17.png)

Analyysiä taulukon ja käyrien perusteella 

ADD time: Ensimmäisenä huomasin sen, että arvot joissa tarkastellaan lisäämisaikaa yhtä elementtiä kohden  ovat aikalailla samoja koko ajan mikä voisi viitata logaritmiseen aikakompleksisuuteen. Add time arvot ovat suhteellisen kohtuullisia ja kasvavat tasaisesti N:n kasvaessa. 
TO SORTED ARRAY: Odotin tässä lineaarista aikakompleksisuutta ja mielestäni algoritmi käyttäytyy sen mukaisesti. 
SEARCH: "Search time" -arvot vaikuttavat kasvavan elementtien määrän kasvaessa. Pitäisi testata laajemmalla aineistolla varmistuakseen aikakompleksisuudesta.
"Search time/item" -arvot ovat suhteellisen vakaita, mikä viittaa logaritmiseen käyttäytymiseen.
GET INDEX: Pere elementtiä kohden arvot pysyvät suhteellisen alhaisina. Joissain kohdissa se on suoraan tuhat kertaa alhaisempi kuin getIndex arvo. Esimerkiksi 0.0174 ja vastaava getIndex 174. Isoilla aineistoilla tämä hidastuu merkittävästi vaikuttaen myös käyttöliittymässä.


TAULUKKOPOHJAINEN TAULUKOT JA ANALYYSI

![TAULUKKO_TAULUKKO](image-18.png)
![TO_ARRAY_TAULUKKO](image-19.png)
![ADDTIME_TAULUKKO](image-20.png)
![SEARCHTIME_TAULUKKO](image-21.png)
![GETINDEX TAULUKKO](image-22.png)

Se mikä lukijalle pistää näistä ensimmäisenä silmään räikeytensä vuoksi on getIndex. Todellisuudessa tarkastellessa arvoja huomataan, että se on aikakompleksisuudeltaan O(1) eli operaation suoritusaika on vakio riippumatta aineiston koosta. Binäärisessä hakupuussa tämä arvo kasvoi myös aineiston kasvaessa. 

ADD TIME: Add time kasvaa aineiston kasvaessa ja todennäköisimmin syy johtuu siitä, että taulukkoa joudutaan reallokoimaan paljon. Vertaillessa BST ja taulukkopohjaista toteutusta voidaan huomata, että taulukko voi olla ehkä hieman tehokkaampi pienemmillä dataseteillä, mutta binäärisen hakupuun toteutus pysyy vakaanpana myös suuremmilla dataseteillä.

TOARRAY: Taulukkopohjaisella toteutuksella käyrä ja arvot osoittavat logaritmista käyttäytymistä. Tämä todennäköisesti johtuu nopeasta lajittelualgoritmista. Binäärisen hakupuun etu tässä on se, ettei se vaadi erillistä järjestämistä ja täten saattaa olla hieman nopeampi toteutettuna oikein.
SEARCH Search-operaation aikakompleksisuus kasvaa suurilla dataseteillä, mikä viittaa siihen, että haku on lineaarista.
Aikakompleksisuus per elementti on suhteellisen vakaa. BST:llä hakuoperaatio (search) on keskimäärin nopeampi verrattuna lineaariseen haulle taulukkopohjaisessa toteutuksessa. 

GETINDEX:
Taulukkopohjaisen toteutuksen metodi on huomattavasti nopeampi kuin BST:llä. BST:ssä toteutus vaatii inorder rekursiivisen läpikäynnin ja tämä hidastaa toteutusta suurilla dataseteillä.

Yhteenveto molemmista toteutuksista:

Taulukkopohjainen toteutus voi olla tehokas pienillä dataseteillä ja yksinkertaisissa operaatioissa, mutta sen tehokkuus heikentyy isommilla dataseteillä.
BST-pohjainen toteutus voi olla tehokkaampi isoimmilla dataseteillä ja tarjoaa nopeamman haun ja lisäyksen keskimäärin.

## 08-TASK
** DISCLAIMER TOTEUTIN TEHTÄVÄN JAKAEN IDEOITA JA YHDESSÄ TUUMIEN OPISKELIJA ESKO HAUTALAN KANSSA ** 

Hajautustaulun toteutusta miettiessäni oli alussa samankaltaisia ongelmia kuin BST:n kanssa. Oli vaikeaa saada iskostettua aivoihini kuinka hajautustaulu toimii ja miten erilaisten metodien toteuttamista tulisi miettiä. Kuitenkin katselemalla luentovideoiden tallenteita sain pian hyvän käsityksen hajatustauluun lisäämisestä, tietyn elementin löytämisestä ja poistamisesta. Muita metodeja toteuttaessani sain vielä apua moodlen UKK-tiketit osiosta, jossa esimerkiksi kerrottiin hyvin se, miten tämän Pair-tyyppisen taulukon, jolla toteutukseni toimii, voi lajitella hyödyntäen nopeaa lajittelualgoritmia. Näiden lisäksi sivustolta https://www.freecodecamp.org/news/hash-tables/ sain myös selvyyttä hajautustaulun toimintaan ja siihen miten voisin lähteä tekemään omaa toteutustani siihen. Aluksi oli myös hämmennystä liittyen siihen millä hajautusfunktiolla voin laskea tiivisteen. Kuitenkin tämän kysymyksen vastaus löytyi myös UKK-tiketit osiosta. 
Alla on tuloksia testeistä ja analyysi niiden pohjalta. 

**************************
Hashtable performance tests taulukko

![HASHTABLE_TAULUKKO](image-23.png)

Tämän pohjalta piirretyt graafit

![ADD HASHTABLE](image-24.png)
![TO ARRAY HASHTABLE](image-25.png)
![SEARCH HASH](image-26.png)
*********
Analyysi:

ADD: Koon kasvaessa tämän operaation kesto kasvaa myös tasaisesti. Isoimmilla aineistoilla ajan kasvaminen johtuu siitä, että indeksiä joudutaan luotaamaan tarkemmin ja mahdollisesti pitempään. Add time/item kuitenkin pysyy suhteellisen vakaana mikä on mielestäni merkki onnistuneesta ja tehokkaasta toteutuksesta. 

TOARRAY: Tietoaineistojen kasvaessa tämä suoritusaika kasvaa myös merkittävästi. Johtuu siitä, että isojen aineistojen läpikäymiseen menee aikaa enemmän ja lajittelussa kestää tällöin myös enemmän. Tämä operaatio noudattaa lineaarista aikakompleksisuutta.

Search: Search operaatio vaikuttaa myös tulosten perusteella tehokkaalta vaikkakin se kasvaa jonkin verran odotetulla tavalla tietoaineiston kasvaessa. Kahdella miljoonallakin alkiolla operaatioon menee alle sekunti. Search time/ item pysyy myös vakaana mikä viittaa tehokkaaseen toteutukseen. "Search time/item"-kohta pysyy myös  suhteellisen vakaana, mikä viittaa siihen, että hakuoperaation aikakompleksisuus on suhteellisen pieni ja ei voimakkaasti riipu datasetin koosta.

**********************************************
SimpleKeyedContainer performance tests taulukko

![SKeyedContainer_Taulukko](image-27.png)

Graafit

![ADD time keyedcontainer](image-28.png)
![TO_ArrayKeyedContainer](image-29.png)
![Search KeyedContainer](image-30.png)

Analyysi:
ADD: On erittäin tehokkaasti toteutettu tämänn testiaineiston perusteella. Vaikka käyrä näyttää villiltä niin tarkastellessa aikoja voi huomata, että algoritmi on erittäin tehokas ja toimii melkein samalla nopeudella koko ajan. Pääsin mielestäni tällä kurssin vaatimaan aikakompleksisuuteen ja metodi käyttäytyy odotetulla tavalla. 

TOARRAY AND SORT: Lajittelu toimii erittäin nopeasti myös ja mielestäni täyttää nopean lajittelualgoritmin aikakompleksisuusvaatimuksen O(N logN). 

Search: Search operaatiossa kestää isoilla tietoaineistoilla todella kauan ja se näyttää seuraavan melkeinpä eksponentiaalista kasvua tulosten ja käyrän perusteella. 

Vertailessa toteutuksia Binäärihakupuu (BST) on vahva valinta, kun tarpeena on hoitaa järjestystä tehokkaasti ja toteuttaa esimerkiksi nopeita hakuoperaatioita edellyttäen, että puu, jota tarkastellaan on tasapainoinen ja oikein täytetty. Hajautustaulu on puolestaan mielestäni tehokas valinta, kun tarvitaan nopeaa pääsyä tietoihin ja etenkin kun tilankäyttö on kriittinen tekijä ja halutaan suorittaa tehokkaat lisäys ja hakuoperaatiot. Hajautustaulu säilyttää myös tasapainoisen toiminnan kuten testien perusteella voidaan todeta. 

Tarkempia tuloksia varten tulisi toki toteuttaa lisää testejä erilaisilla aineistoilla, mutta yleisesti ottaen tehtävässä toteutettu hajautustaulu vastaa mielestäni hyvin toteutuksen odotuksia ja sain sen toteutettua oikeellisesti. Toteutin valinnaisena tehtävänä myös hajautustaulusta poistamisen ja tämän testaamista varten tein myös luokan. HashTableTest.java.  

Kaikenkaikkiaan tehtävä oli haastava, mutta hyvän luentomateriaalin ja erinomaisten demovideoiden ansiosta se onnistui mielestäni vaatimukset täyttävällä tavalla ja tehokkaasti.

Toteutuksen lopuksi testasin hajautustaulun toimintaa käytännössä Tira Codersissa. Tarkastelin esimerkiksi koko kurssin käyttämällä repositiorylla CountCodeWord:ia käyttäen  ja tulokset olivat itselläni seuraavat ensimmäisten 25 sanan kohdalla.

![TIRAREPOTULOKSET](image-31.png) 

## 09-TASK
Tehtävän tekeminen oli mielestäni kohtuullisen vaikeaa ja aluksi kesti kauan aikaa tajuta miten verkkotietorakenne ylipäätään toimii ja minkälaisia hakuoperaaioita voitaisiin toteuttaa verkon sisällä. Opin tehtävän tekemisen aikana monipuolisesti käyttämään javan Map rajapinnan toteuttamia tietorakenteita ja erityisesti vielä hajautustaulun logiikkaa sekä erilaisia metodeja joita siihen voi käyttää. Sen lisäksi tehtävä muistutti minua siitä miten tärkeää on lukea ohjeita ja muita dokumentteja, jotka liittyvät jollain tavalla tehtävään. 
Toteutin valinnaisista tehtävistä seuraavat:

Leveyshaku: public List<Vertex<T>> breadthFirstSearch(Vertex<T> from, Vertex<T> target)
Syvyyshaku: public List<Vertex<T>> depthFirstSearch(Vertex<T> from, Vertex<T> target)
Tunnistus sille, onko verkossa disconnected alueita: public List<T> disconnectedVertices(Vertex<T> toStartFrom) 
					sekä public boolean isDisconnected(Vertex<T> toStartFrom)


Ajoin graph performance testit vasta kun olin optimoinut metodin getVertexFor metodin toimivuuden sellaiseksi, että se hakee hajautustaulusta verticies elementin hashCoden avulla ja tämän jälkeen tulokset olivat seuraavat. 

![GRAPH TAULUKKO](image-32.png)

Tämän taulukon pohjalta piirretyt käyrät:


![HashMapGraph Fill](image-33.png)
![HashMapGraph BFS](image-34.png)
![HashMapGrah DFS](image-35.png)

Seuraavaksi vaihdoin Graph luokan Map-rajapinnan toteuttavat tietorakenteet HashMap:in sijaan HashTable:ksi ja tulokset olivat seuraavat:
![HASHTABLE GRAPH IMPLEMENTATION](image-36.png)

Näiden tulosten pohjalta piirisin seuraavanlaiset käyrät. 

![HashTableGraph fill](image-37.png)
![HashTableGraph BFS](image-38.png)
![HashTableGraph DFS](image-39.png)

Tulokset erosivat toisistaan hieman. Hashtablea käyttäessä kaikki ajat ovat hieman korkealla kaikissa testitapauksissa. Esimerkiksi tarkemman tarkastelun perusteella huomataan, että Hashtablella on huomattavasti suurempi suoritusaika BFS:ssä suurimmalla aineistolla. Ero ei välttämättä ole huomattavaa pienten tietorakenteiden kanssa, mutta suuremmilla verkkojen koilla sillä voi olla merkitystä. 

Tarkastellen sitä onko verkko tiheä ja harva voimme ottaa tarkasteluun taulukoissa olevat Vertices count ja Edge Count kohdat ja laskea tästä miten moneen solmuun jokainen solmu on yhteydessä keskimäärin. 

10 solmua 40 reunaa = jokainen solmu on yhteydessä neljään muuhun solmuun
100 solmua 532 reunaa = jokainen solmu on yhteydessä noin viiteen solmuun
1000 solmua 5613 reunaa = jokainen solmu on yhteydessä noin 5.6 solmuun
5000 solmua 27602 reunaa = jokainen solmu on yhteydessä noin 5.5 solmuun 
10000 solmua 56551 reunaa = jokainen solmu on yhteydessä noin 5.7 solmuun
50000 solmua 280174 reunaa = jokainen solmu on yhteydessä noin 5.6 solmuun keskimäärin
1000000 solmua 557681 reunaa = jokainen solmu on yhteydessä noin 5.6 solmuun keskimäärin
Tiheyttä voidaan laskea kaavalla:
 ![LaskuKaava](image-40.png)
Tämän kaavan perusteella käyttäen sitä Excelissä sain seuraavanlaiset tulokset koskien verkon tiheyttä kaavalla = 2*Solmut/(Reunat*(Reunat-1))*100

![Verkon Tiheys](image-41.png)

Tulosten perusteella voidaan todeta, että alussa verkko oli melko tiheä, mutta loppua kohden verkosta tuli todella harva. Esimerkiksi viimeisen arvon eli 100 000 solmun kohdalla verkossa tulisi olla yhteensä 4,999,950,000 kaarta, koska maksimireunojen määrä saadaan kaavalla ![ReunaLasku](image-42.png) jolloin laskulla ![Reunat sadallatuhannella](image-43.png). 

Aikakompleksisuusanalyysit toteuttamistani metodeista tässä tehtävässä:

Verkon perusmetodit eli createVertexFor, getVertices, addEdge, addDirectedEdge, getEdges, getVertexFor toteutuksen jälkeen ovat kaikki vakioaikaisia operaatiota ja niiden aikakompleksisuus on tällöin O(1), missään näissä operaatioissa ei ole tarpeellista käydä läpi kokonaan esimerkiksi reunuslistaa vaan sen sisällön perusteella käyttämällä esimerkiksi containsKey() operaatioita voidaan suoraan katsoa vaikka ennen lisäämistä jos tietyllä avaimella oleva solmu esiintyy listassa. Get-tyyppiset metodit palauttavat kutsujalleen vain listat asioista eivätkä vaadi minkäänlaisia muita operaatioita. Myös paranneltu versio getVertexFor()-metodille toimii O(1) aikakompleksisuudella. Käyttämällä toista hajautustaulua voidaan helposti hakea parametrinä tulevan elementin avain ja palauttaa tarvittava data riippumatta aineiston koosta. 

Valinnaisista toteutettujen BFS ja DFS metodien aikakompleksisuudet ovat O(V+E), missä V on vertexien määrä verkossa ja E on reunojen määrä. Tämä johtuu siitä, että näissä metodeissa käydään jokainen solmu (V) läpi ja jokaisen solmun kaaret (E) käydään myös läpi, täten V+E eikä esimerkiksi N^2. Verkkotietorakenteessa on kaksi muuttujaa, jotka määräävät aikakompleksisuuksia. 

DisconnectedVertices- metodin aikakompleksisuus on myös O(V+E), sillä sen suoritusaika riippuu jälleen solmujen ja reunojen lukumäärästä. Käyttämättömien solmujen läpikäynti lisää tähän metodiin myös lineaarisen läpikäynnin. 

Tämä oli kurssin osalta pahiten pelätyin harjoitus omalla kohdallani, sillä verkot vaikuttivat todella monimutkaisilta ja vaikeilta tietorakenteilta. Kuitenkin tehtävää tehdessäni huomasin niiden lopulta olevan melko yksinkertaisia ja loogisia rakenteita, joita voi soveltaa moneen eri käyttötarkoitukseen esimerkiksi sovellus- ja pelikehityksessä.
​
