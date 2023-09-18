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

## 03-TASK

## 04-TASK

## 05-TASK

## 06-TASK

## 07-TASK

## 08-TASK

## 09-TASK