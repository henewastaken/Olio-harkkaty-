Olio-ohjelmointi 2 kurssin harjoitustyö.
Projekti sisälsi muutamia kurssiopettajan tekemiä tiedostoja. Koodi pääasiassa itse tehtyä, sekä toiminnallisuudet myös itse tehty.

Projektin siirto koulun gitistä ei toiminut halutulla tavalla, joten tässä versiossa, on pakkauksia jotka on syytä tarkistaa. 

1.4.20  
- kokeilu toimiiko netbeansin kautta.  
  
10.4.20  
- Uutinen luokka lisätty ja luotu siihen rakentajat.  
- Vitsit luokkaan lisätty rakentajat.  
- Dookumentti yliluokkaan lisätty rakentajat.  
  
12.4.20  
- Lisätty uutinen ja vitsit luokkiin toString metodit.  
- Toteutettu Dokumentti-luokassa Comparable rajapinta ja vertailtu compareTo metodissa.  
- Lisätty Dokumentti-luokkaan equals metodi.  
- Siirretty Tietoinen harjoitustyo.dokumentit-pakkaukseen.  
- Dokumnetti Implement Tietoinen, ja lisätty Dokumenttiin sanatTäsmäävät metodi.  
- Uutinen ja Vitsit implementoitu abstaktit metodit Tietoisesta. (ei mitään hajua onko oikein).  
  
14.4.20  
- Tietoinen siirretty takaisin apulaiset-pakkaukseen.  
  
15.4.20  
- Lisää toiminnallisuutta tehty.  
- Lisää-metodit eivät toimi kunnolla. Kokoelmassa nullPointerException ja omaListassa ei mene järjestykseen.  
- Mainissa on testailua.  
  
16.4.20  
- Lisää-metodit tehty loppuun ja toimiviksi.  
- OmaLista-luokkaan lisätty toiminnallisuutta.  
- Siivoa-metodia aloitettu.  
  
17.4.20  
- Siivoa-metodi tehty toimivaksi.  
- Muuta toiminnallisuutta tehty luokkiin.  
  
18.4.20  
- Siivoa-metodissa havaittu bugi korjattu.  
- Lisää toiminnallisuutta lisätty.  
- Tiedostojen palauttelua WETOon.  
  
19.4.20  
- Hae-metodi tehty.  
- Mainista poistettu testaukset, koska weto on vitun paksaa, ja valittaa 120 merkin kommenteista.  
- Maini commitattu useamman kerran, koska WETO.
- Lisää-meltodin bugi korjattu, ja metodista tehty paremmin luettavaa.  
  
25.4.20  
- Lisää toiminnallisuutta tehty.  
- Kommentoitu koodia.  
  
4.5.20  
- Aloitettu käyttöliittymää.  
- Lisätty tulostus- ja poistokomennot.  
- Tehty tiedoston lukeminen kokoelmaa.  
  
5.5.20  
- Lisätty nollaus- ja lisäyskomennot.  
- Pientä testailua ja bugien korjailua.  
  
6.5.20  
- Polish ja hakukomentoa aloitettu.  
  
7.5.20  
- Polish komento tehty.  
- Harjoitustyö palautettu onnistuneesti.  
  
8.5.20  
- Kommentointia tehty.  
  
  
## Yleistä

WETO käyttää aina alkuperäistä _harjoitustyo.apulaiset_-pakkausta. Pakkauksen
tiedostoja ei siksi ole syytä muuttaa ellet ole Mac- tai Linux-käyttäjä (katso
alla).

Projektipohjan juuressa on _.gitignore_-tiedosto, jolla estetään tavukoodin
tallennus versionhallintaan. Lisää siihen tarvittaessa estoja.

## Mac- ja Linux-käyttäjät

Projektipohjan tiedostojen rivinvaihdot ovat Windows-tyylisiä (CR & LF). Muuta
rivinvaihdot tarvittaessa Mac/Linux-muotoon (LF). Tämä onnistuu todennäköisesti
käyttämälläsi editorilla tai IDE:llä. **Älä muuta** _harjoitustyo.apulaiset_
-pakkausta **millään muulla tavoin**.

## Muuta

Perinteisesti tietovarastoissa on projektista kertova README.md-tiedosto. Korvaa
siksi tämän tiedoston sisältö tekstillä, jossa kuvailet ohjelmaasi omin sanoin.
Kerro lyhyesti mitä ohjelma tekee ja kuinka sitä käytetään. Käytä tekstissä
muotoiluja. Tämän dokumentin mittainen teksti riittää hyvin.