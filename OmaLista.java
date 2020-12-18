
import java.util.LinkedList;

/**
 * Javan LinkedList-luokasta peritty oma listaluokka
 *
 * @version 0.3
 * @author Henry Andersson, henry.andersson@tuni.fi.
 *
 */
public class OmaLista<E> extends LinkedList<E> implements Ooperoiva<E> {
    // Yleinen virheilmoitus
    public static final String VIRHE = "Error!";
    
    
    /**
    * Lisää listalle uuden alkion sen suuruusjärjestyksen mukaiseen paikkaan.
    * <p>
    * Listan alkioiden välille muodostuu kasvava suuruusjärjestys, jos lisäys
    * tehdään vain tällä metodilla, koska uusi alkio lisätään listalle siten,
    * että alkio sijoittuu kaikkien itseään pienempien tai yhtä suurien alkioiden
    * jälkeen ja ennen kaikkia itseään suurempia alkioita. Alkioiden suuruusjärjestys
    * päätellään Comparable-rajapinnan compareTo-metodilla.
    * <p>
    * Jos parametri viittaa esimerkiksi Integer-olioon, jonka arvo on 2 ja listan
    * muut tietoalkiot ovat arvoltaan 0 ja 3, on listan sisältö metodin suorittamisen
    * jälkeen [0, 2, 3], koska {@literal 0 < 2 < 3}.
    * <p>
    * Käytä toteutuksessa SuppressWarnings-annotaatiota, jotta kääntäjä ei valita
    * vaarallisesta tyyppimuunnoksesta.
    *
    * @param uusi viite olioon, jonka luokan tai luokan esivanhemman oletetaan
    * toteuttaneen Comparable-rajapinnan.
    * @throws IllegalArgumentException jos lisäys epäonnistui, koska uutta alkiota
    * ei voitu vertailla. Vertailu epäonnistuu, kun parametri on null-arvoinen
    * tai siihen liittyvän olion luokka tai luokan esivanhempi ei vastoin oletuksia
    * toteuta Comparable-rajapintaa.
    */     
    @SuppressWarnings({"unchecked"})
    public void lisää(E uusi) throws IllegalArgumentException {
        // heitetään virheilmoitus, jos virheellinen parametri
        if (uusi == null || uusi == "") {
            throw new IllegalArgumentException(VIRHE);
        }
        
        // Tarkistetaan, että luokalla on Comparable rajapinnan toteurus
        if (uusi instanceof Comparable) {

            // Lisätään tyhjään listaan alkio
            if (size() == 0) {
                add(uusi);
            } 
            // Jos lista ei ole tyhjä, lisätään alkio oikeaan paikkaan
            else {
              
                int i = 0;  
                // Apumuuttuja silmukan jatkamiseen
                boolean jatka = true;
                // Käydään listaa läpi
                while (i < size() && jatka) {
                    
                    // Apumuuttuja listan tietyn indeksin hakemiseen
                    Comparable apulainen = (Comparable) get(i);

                    // Verrataan apumuuttujaa metodin parametrilliseen muuttujaan, 
                    // jos apumuuttuja > uusi, käydään listaa läpi, jotta oikea sijoituskohta löytyy
                    if (apulainen.compareTo(uusi) > 0) {
                        // Lisätään parametri listaan inkesiin i:n arvo ja muutetaan lippumuuttujan arvoa.
                        add(i, uusi);
                        jatka = false;

                    // Jos apumuuttuja <= uusi, lisätään parametri oikeaan kohtaan listassa.
                    } else if (apulainen.compareTo(uusi) <= 0) { 
                        // Estetään indexOutOfBoundsException
                        if (i+1 < size()) {
                            // Apumuuttuja seuraavalle indeksille
                            int j = i+1;
                            // Apumuuttuja vertailuun
                            Comparable seuraava = (Comparable) get(j);
                            
                            // Verrataan, onko seuraavalla paikalla oleva alkio suurempi
                            if (seuraava.compareTo(uusi) > 0) {
                                // Ollaan löydetty oikea paikka listalta. i < uusi < j.
                                // Lisätään alkio listaan ja muutetaan lippumuuttujan arvo.
                                add(j, uusi);
                                jatka = false;
                                }
                        // Parametri on suurempi kuin kaikki listan alkiot
                        } else {
                            // Lisätään listan viimeiseksi ja muutetaan lippumuuttujan arvoa.
                            add(uusi);
                            jatka = false;
                        }

                    }
                    i++;
                }
            }
        // Luokalla ei ole ollut Comparable rajapinnan toteutusta, joten heitetään poikkeus.
        } else {
            throw new IllegalArgumentException(VIRHE);
        }
    }
}
