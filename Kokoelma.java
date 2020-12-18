 /**
 * Kokoelma-luokka
 * 
 * <p> 
 * Olio-ohjelmoinnin perusteet II, kevät 2020
 * 
 * @version 0.1
 * @author Henry Andersson, henry.andersson@tuni.fi.
 * 
 */
package harjoitustyo.kokoelma;

import harjoitustyo.apulaiset.Kokoava;
import harjoitustyo.dokumentit.Dokumentti;
import harjoitustyo.dokumentit.Uutinen;
import harjoitustyo.dokumentit.Vitsi;
import harjoitustyo.omalista.OmaLista;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;


public class Kokoelma implements Kokoava<Dokumentti>{
    
    /** Yleinen virheilmoitus */
    public static final String VIRHE = "Error!";
    /** Erottimet */
    public static final String EROTIN = "///";
    
    
    /*
    * Attribuutit
    */
    /** Lista dokumenteille */
    private OmaLista<Dokumentti> dokumentit;
    /** Lista sulkusanoille */
    LinkedList sulkusanat;
    /** Polku josta kokoelman tiedosto haetaan */
    String dokumenttiPolku;
    /** Polku josta sulkusanojen tiedosto haetaan */
    String sulkusanaPolku;
    
    /*
    * Aksessorit
    */
    public OmaLista<Dokumentti> dokumentit() {
        return dokumentit;
    }
    
    public void dokumentit(OmaLista<Dokumentti> dokkari) {
        dokumentit = dokkari;
    }
    
    /*
    * Rakentajat
    */
    public Kokoelma(String dokumenttiPolku, String sulkusanaPolku) {
        this.dokumenttiPolku = dokumenttiPolku;
        this.sulkusanaPolku = sulkusanaPolku;
        dokumentit = new OmaLista<Dokumentti>();
        sulkusanat = new LinkedList();
        lataaDokumentit(dokumenttiPolku);
        lataaSulkusanat(sulkusanaPolku);
    }

    /**
     * Metodi dokumenttien lataamiselle.
     * Tiedosto iteroidaan läpi ja lisätään listalle. 
     * @param polku dokumenttien kokoelman tiedoston sijainti.
     */
    public void lataaDokumentit(String polku) {
        // Luetaan tiedostosta
        try {
            // Luodaan tiedostoon liittyvä olio.
            File tiedosto = new File(polku);
            
            // Liitetään lukija tiedostoon.
            Scanner lukija = new Scanner(tiedosto);
            
            // Käydään lista rivi riviltä läpi
            while (lukija.hasNextLine()) {
                // Jaetaan teksti erottimen kohdalta
                String[] jaettu = lukija.nextLine().split(EROTIN);
                
                // Jos dokumentin nimessä on "news", lisätään uutisiin
                if (dokumenttiPolku.contains("news")) {
                    //System.out.println("Lisätään uutiskokoelma"); // Testituloste
                    
                    // Muutetaan päiväyksen lukija oikeaan päiväysmuotoon.
                    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d.M.yyyy");
                    LocalDate paiva = LocalDate.parse(jaettu[1], formatters);
                    
                    //System.out.println("päivä " + paiva); // Testituloste
                    
                    // Lisätään dokumenttiin
                    dokumentit.lisää(new Uutinen(Integer.valueOf(jaettu[0]), paiva, jaettu[2]));
                    
                // Jos dokumentin nimessä on "jokes", lisätään vitseihin
                } else if (dokumenttiPolku.contains("jokes")) {
                    //System.out.println("Lisätään vitsikokoelma"); // Testituloste
                    
                    dokumentit.lisää(new Vitsi(Integer.valueOf(jaettu[0]), jaettu[1], jaettu[2]));
                }
            }
            // Suljetaan lukija.
            lukija.close();
        // Sipeataan virhe ja tulostetaan virheilmoitus.
        } catch (Exception e) {
            System.out.println(VIRHE);
        }
    }
    
    /**
     * Metodi sulkusanojen lataamiselle.
     * 
     * Kokoelma iteroidaan läpi ja lisätään listaan.
     * @param polku polku sulkusanojen kokoelman sijainnille.
     */
    public void lataaSulkusanat(String polku) {
        // Luetaan tiedostosta
        try {
            // Luodaan tiedostoon liittyvä olio.
            File tiedosto = new File(polku);
            
            // Liitetään lukija tiedostoon.
            Scanner lukija = new Scanner(tiedosto);
            
            // Käydään lista rivi riviltä läpi
            // Käydään lista rivi riviltä läpi
            while (lukija.hasNextLine()) {
                // Jaetaan teksti erottimen kohdalta
                String[] jaettu = lukija.nextLine().split("\n");
                // Lisätään sulkusanalistaan.
                sulkusanat.add(jaettu[0]); 
                //System.out.println(jaettu[0]); // Testituloste
            }
            lukija.close();
            
            
        } catch (Exception e) {
            System.out.println(VIRHE);
        }
    }
    
    /**
    * Lisää kokoelmaan käyttöliittymän kautta annetun dokumentin.
    * <p>
    * Metodi kutsuu oman listan lisää-metodia kokoelman dokumentit-attribuutin
    * kautta, jotta uusi dokumentti saadaan lisätyksi oikeaan paikkaan listalla.
    * <p>
    * Lisäys onnistuu, jos parametri liittyy dokumenttiin, jota voidaan vertailla 
    * Comparable-rajapinnan compareTo-metodilla ja jos kokoelmassa ei ole vielä
    * Dokumentti-luokan equals-metodilla mitaten samanlaista dokumenttia.
    * Null-arvon lisäys epäonnistuu aina.
    *
    * @param uusi viite lisättävään dokumenttiin.
    * @throws IllegalArgumentException jos dokumentin vertailu Comparable-rajapintaa
    * käyttäen ei ole mahdollista, listalla on jo equals-metodin mukaan sama
    * dokumentti eli dokumentti, jonka tunniste on sama kuin uuden dokumentin
    * tai parametri on null.
    */
    @Override
    public void lisää(Dokumentti uusi) throws IllegalArgumentException {
        if (uusi == null) {
            throw new IllegalArgumentException(VIRHE);
        }
        
        dokumentit.lisää(uusi);
    }
    /**
    * Metodi add-komennolle
    * Metodi saa parametrina listan, jossa käyttäjän syöttämä komento jaettuna
    * välilyönnin kohdalta.Metodi lisää kokoelmaan vitsin tai uutisen
    *
    * @param teksti käyttäjän syöttämät tiedot dokumentille.
    */
    public void add(String teksti, boolean onkoVitsti) {
        boolean onkoVitsi = onkoVitsti;
        // Jaetaan parametrina saatu tueksti erottimen kohdalta.
        String[] jaettu = teksti.split(EROTIN);

        // Tunniste
        int tunniste = Integer.valueOf(jaettu[0]);
        
        // Tarkistetaan onko samalla tunnisteella jo dokumenti.
        if (hae(tunniste) == null) {

            // Tyyppi
            String tyyppi = null;
            // päivä
            LocalDate paiva = null;
            
            /*
            * Yritetään muuttaa listan toinen alkio päiväysmuotoon, jolloin selviää onko kyseessä
            * uutinen vai vitsi.
            */
            // Apumuuttuja 
            boolean onUutinen = false;
            try {
                // Muutetaan päiväyksen lukija oikeaan päiväysmuotoon.
                DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d.M.yyyy");
                paiva = LocalDate.parse(jaettu[1], formatters);
                
                // Muutetaan apumuuttujan arvoa
                onUutinen = true;
                
            } catch (Exception e) {
                // Varmistetaan, että apumuuttujalla on oikea arvo.
                onUutinen = false;
                // Liitetään tyyppi tyyppimuuttujaan.
                tyyppi = jaettu[1];
            }
            
            // Sisältö
            String sisalto = jaettu[2];

            // Katsotaan onko kokoelma vitsi ja lisättävä ei ole uutinen.
            if (onkoVitsti && !onUutinen) {
                // Liätään dokumentti kokoelmaan
                lisää(new Vitsi(tunniste, tyyppi, sisalto));

            // Katsotaan onko kokoelma uutinen ja lisättävä on uutinen
            } else if (!onkoVitsi && onUutinen){
                // Liätään dokumentti kokoelmaan
                lisää(new Uutinen(tunniste, paiva, sisalto));

            // Tulostetaan virheilmoitus
            } else {
                System.out.println(VIRHE);
            }
        // Tunniste on jo käytössä.
        } else {
            System.out.println(VIRHE);
        }
    }
    
    /**
    * Hakee kokoelmasta dokumenttia, jonka tunniste on sama kuin parametrin
    * arvo.
    * <p>
    * Tästä metodista on paljon hyötyä Kokoelma-luokassa, koska moni ohjelman
    * komennoista yksilöi dokumentin sen tunnisteen perusteella.
    *
    * @param tunniste haettavan dokumentin tunniste.
    * @return viite löydettyyn dokumenttiin. Paluuarvo on null, jos haettavaa
    * dokumenttia ei löydetty.
    */
    @Override
    public Dokumentti hae(int tunniste) throws IllegalArgumentException {

        for (int i = 0; i < dokumentit.size(); i++) {
            
            int dokumentinTunniste = dokumentit.get(i).tunniste();
            //System.out.println("i " + i);  // Testituloste
            //System.out.println("tunniste " + tunniste); // Testituloste
            //System.out.println("dokumentit koko " + dokumentit.size()); // Testituloste
            
            // Jos löytyy samanarvoinen tunniste, palautetaan viite
            if (dokumentinTunniste == tunniste) {
                return dokumentit.get(i);
            }
        }
        return null; 
    }
    
     /**
     * Metodi find-komennolle.Metodi saa parametrina etsittävät sanat. 
     * Metodi etsii kokoelmasta tekstejä joissa esiintyy etsitty sana ja tulostaa tunnisteen.
     * @param sanat etsittävät sanat
     */
    public void etsi(String sanat) {
        
        //Muutetaan sanat listaksi
        LinkedList<String> tekstiListana = new LinkedList<>(Arrays.asList(sanat.split(" ")));
        
        // kutsutaan kokoelman sanahakumetodia ja tulostetaan palautetut arvot.
        sanahaku(tekstiListana); 
    }
    /**
     * Metodi find-komennolle. Metodi saa parametrina linkitetyn listan, jossa haettavat sanat.
     * Metodi kutsuu Dokumentti-luokan sanatTäsmäävät metodia.
     * Metodi tulostaa merkkijonon, jossa on tunnukset dokumentteihin, joista löytyy etsityt sanat.
     * @param hakusanat haettavat sanat. 
     */
    public void sanahaku(LinkedList<String> hakusanat) {
 
        // Muuttuja löydettyjen tiedostojen tunnisteiden ylläpitämiseen.
        String täsmäävät = null;
        
        // Iteroidaan dokumentit läpi
        for (int i = 0; i < dokumentit.size(); i++) {
            
            // Kutsutaan sanatTäsmäävät metodia ja verrataan totuusarvoon
            if (dokumentit.get(i).sanatTäsmäävät(hakusanat) == true) {
                
                // Lisätään ensimmäinen löytö nullin tilalle
                if (täsmäävät == null) {
                    täsmäävät = (String.valueOf(dokumentit.get(i).tunniste())) + "\n"; 
                // Lisätään muut löydöt muuttujaan
                } else {
                    täsmäävät = täsmäävät + (String.valueOf(dokumentit.get(i).tunniste())) + "\n";
                    //System.out.println("i " + i); // Testituloste
                    //System.out.println(dokumentit.get(i)); // Testituloste
                }
            } 
        }
        // Tulostetaan täsmäävien dokumenttien tunnisteet, jos niitä löytyi.
        if (täsmäävät != null) {
            System.out.println(täsmäävät.trim());
        }
        
        
    }
    
    
    /**
     * Metodi kokoelman siistimiseen. Metodi saa parametrina välimerki, jotka poistetaan tekstistä.
     * 
     * @param välimerkit dokumentista poistettavat välimerkit
     */
    public void siisti(String välimerkit) {
        
        // Iteroidaan dokumentit läpi
        for (int i = 0; i < dokumentit.size(); i++) {
            // Kutsutaan Dokumentti-luokan siivoa-metodia.
            dokumentit.get(i).siivoa(sulkusanat, välimerkit);
        }
    }
    
    /**
    * Metodi print-komennolle
    * Metodi saa parametrina listan, jossa käyttäjän syöttämä komento jaettuna
    * välilyönnin kohdalta. Metodi tulostaa uutisen tai vitsin tunnisteen perusteella.
    *
     * @param jaettu käyttäjän syöttämä komento, joka sisältää dokumentin tunnisteen.
    */
    public void tulosta(String[] jaettu) {
        // Yritetään tulostaa dokumentti sen tunnisteen avulla
        try {
            if (jaettu.length == 1) {
                for (int i = 0; i < dokumentit().size(); i++) {
                    System.out.println(dokumentit().get(i));
                }
            }
            else if (hae(Integer.valueOf(jaettu[1])) != null) {
                System.out.println(hae(Integer.valueOf(jaettu[1])));
            } else {
                System.out.println(VIRHE);
        }
        // Siepataan poikkeus ja tulostetaan virheilmoitus
        } catch (Exception e) {
            System.out.println(VIRHE);
        }
    }
    
     /**
     * Metodi remove-komennolle.Metodi saa parametrina tunnisteen. 
     * Metodi poistaa uutisen tai vitsin tunnsiteen avulla.
     * @param tunniste poistettavan dokumentin tunniste
     */
    public void poista(int tunniste) {
        // Jos kokoelmasta löytyy dokumentti tunnsiteella, poistetaan se
        if (dokumentit().contains(hae(tunniste))) {
            dokumentit().remove(hae(tunniste)); 
        // Tulostetaan virheilmoitus
        } else {
            System.out.println(VIRHE);
        }
        
    }
}
