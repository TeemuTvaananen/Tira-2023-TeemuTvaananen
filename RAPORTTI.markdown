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

## 05-TASK

## 06-TASK

## 07-TASK

## 08-TASK

## 09-TASK