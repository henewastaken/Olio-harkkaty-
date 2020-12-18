
package harjoitustyo.dokumentit;
import harjoitustyo.apulaiset.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Abstrakti juuriluokka dokumenteille.
 * <p> 
 * Olio-ohjelmoinnin perusteet II, kevät 2020
 * 
 * @version 0.3
 * @author Henry Andersson, henry.andersson@tuni.fi.
 * 
 */
public abstract class Dokumentti implements Tietoinen<Dokumentti>, Comparable<Dokumentti>{
    
    /*
    * Julkiset luokkavaliot.
    */
    
    /** Yleinen virheilmoitus */
    public static final String VIRHE = "Error!";
    /** Tietojen erotin kaikille saatavilla olevana vakiona. */
    public static final String EROTIN = "///";
    
    
    /*
    * Attribuutit
    */
    
    /** Dokumentin yksilöivä kokonaisluku */
    private int tunniste;
    /** Dokumentin teksti */
    private String teksti;
    
    
    /*
     * Aksessorit
     */
    public int tunniste() {
        return tunniste;
    }       

    public void tunniste(int setTunniste){
        tunniste = setTunniste;
    }

    public String teksti() {
        return teksti;
    }

    public void teksti(String setTeksti){
        teksti = setTeksti;
    }
    
    
    /*
    * Rakentajat 
    */
    public Dokumentti (int tunnus, String kirjoitelma) throws IllegalArgumentException {
        if (tunnus < 1 || kirjoitelma == null || kirjoitelma == "") {
            throw new IllegalArgumentException(VIRHE);
        } else {
            tunniste(tunnus);
            teksti(kirjoitelma);
        }
    }
    
    
    /*
     * OBject-luokan metodien korvaukset
     */
    
    /**
     * Muodostaa dokumentin merkkijonoesityksen, joka koostuu tunnisteesta
     * erottimesta ja tekstistä.
     *
     * @return dokumentin merkkijonoesitys
     */
    @Override
    public String toString() {
        return tunniste + EROTIN + teksti;
    }       
    
    /**
     * Vertaillee dokumenttien tunnisteita. 
     * 
     * @return totuusarvo
     */
    @Override
    public boolean equals (Object obj) {
        try {
            // Asetetaan olioon Dokumentti-luokan viite, jotta voidaan kutsua
            // Dokumentti-luokan aksessoreita.
            Dokumentti toinen = (Dokumentti)obj;
            
            // Oliot ovat samat, jos tunnisteet ovat samat.
            return ( tunniste == toinen.tunniste());
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Korvataan Comparable-rajapinnan metodi. Geneerinen tyyppi T on kiinnitetty
     * Dokumentti-tyypiksi.
     * 
     * @return -1, 0 tai 1
     */
    @Override
    public int compareTo (Dokumentti toinen) {
        if (tunniste < toinen.tunniste()) {
            return -1;
        }
        else if (tunniste == toinen.tunniste()) {
            return 0;
        } else {
            return 1;
        }
    }
    /**
    * Tutkii sisältääkö dokumentin teksti kaikki siitä haettavat sanat. Kunkin
    * listan sanan Li on vastattava yhtä tai useampaa dokumentin sanan Dj
    * esiintymää täysin (Li.equals(Dj) == true), jotta sanat täsmäävät. Jos parametrin
    * arvo on esimerkiksi ["cat, "dog"], niin hakusanat ja dokumentti täsmäävät
    * vain ja ainostaan, jos dokumentissa esiintyy vähintään kerran sanat "cat"
    * ja "dog". Dokumentin sanan osajonon ei katsota vastaavan hakusanaa.
    * Esimerkiksi hakusana "cat" ja dokumentin sana "catnip" eivät täsmää.
    *
    * @param hakusanat lista dokumentin tekstistä haettavia sanoja.
    * @return true jos kaikki listan sanat löytyvät dokumentista. Muuten palautetaan
    * false.
    * @throws IllegalArgumentException jos sanalista on tyhjä tai se on null-arvoinen.
    */
    @Override
    public boolean sanatTäsmäävät(LinkedList<String> hakusanat) throws IllegalArgumentException {
        // Tarkistetaan, että hakusanat-lista ei ole null tai tyhjä. Heitetään poikkeus jos on.
        if (hakusanat == null || hakusanat.isEmpty()) {
            throw new IllegalArgumentException(VIRHE);
        }
        
        // Muutetaan teksti lsitaksi, vertailun helpottamiseksi
        List<String> tekstiListana = new ArrayList<>(Arrays.asList(teksti.split(" ")));
        // Apumuuttuja tuloksen totuusarvon palautukseen liittyen
        int apu = 0;

        // Käydään hakusanat-listan alkiot läpi
        for (String haku : hakusanat) {
            
            // Käydään teksiListana-listan alkiot läpi
            for (String dokuTeksti : tekstiListana) {
                // jos teksti sisältää hakusanan, kasvatetaan apumuuttujan kokoa yhdellä.
                if (dokuTeksti.contains(haku) && dokuTeksti.equals(haku)) {
                    
                    apu++;
                    break;
                }
            }
        }
        
        // Palautetaan tosi, jos apumuuttujan arvo on >= hakusanojen koko, jolloin tedetään,
        // että kaikki sanat ovat tekstissä.
        if  (apu >= hakusanat.size()) {
            return true;
        // Palautetaan epätosi muissa tapauksissa.
        } else {
            return false;
        }
    }
    /**
    * Muokkaa dokumenttia siten, että tiedonhaku on helpompaa ja tehokkaampaa.
    * <p>
    * Metodi poistaa ensin dokumentin tekstistä kaikki annetut välimerkit ja
    * muuntaa sitten kaikki kirjainmerkit pieniksi ja poistaa lopuksi kaikki
    * sulkusanojen esiintymät.
    * <p>
    * Välimerkit annetaan merkkijonona. Jos testistä haluttaisiin poistaa esimerkiksi
    * pilkut ja pisteet, olisi jälkimmäisen parametrin arvo ",.". Sulkusanan Si ja
    * dokumentin sanan Dj on vastattava täysin toisiaan (Si.equals(Dj) == true),
    * jotta Dj poistetaan. Sulkusanoja poistettaessa on huolehdittava siitä,
    * että tekstin alkuun, tekstin sanojen väliin tai tekstin loppuun ei jää ylimääräisiä
    * väliliyöntejä. Näin ollen tekstin alussa ja lopussa ei ole välejä ja sanojen
    * välissä on yksi välilyönti myös siivouksen jälkeen.
    *
    * @param sulkusanat lista dokumentin tekstistä poistettavia sanoja.
    * @param välimerkit dokumentin tekstistä poistettavat välimerkit merkkijonona.
    * @throws IllegalArgumentException jos jompikumpi parametri on tyhjä tai null-
    * arvoinen.
    */
    public void siivoa(LinkedList<String> sulkusanat, String välimerkit) throws IllegalArgumentException {
        
        // Tarkistetaan, että sulkusanat ja välimerkti eivät ole tyhjiä
        // Heitetään poikkeus jo ovat.
        if (sulkusanat == null || välimerkit == null || sulkusanat.isEmpty() || välimerkit.isEmpty()) {
            throw new IllegalArgumentException(VIRHE);
        }
        
        
        // Luodaan välimerkeille lista
        List<Character> välimerkitListana = new ArrayList<>();

        // Lisätään välimerkit listaan
        for (int i = 0; i < välimerkit.length(); i++) {
            välimerkitListana.add(välimerkit.charAt(i));
        } 
        
        /*
        * Välimerkkien poisto
        */
        // Käydään välimerkkilista silmukalla läpi, ja verrataan siihen tekstiä
        // Vertaillaan ensin teksiListana välimerkitListana-listaan.
        for (int i = 0; i < välimerkitListana.size(); i++) {
            for (int j = 0; j < teksti.length(); j++) {
                // Jos välimerkki löytyy, positetaan se tekstistä
                if (teksti.charAt(j) == välimerkitListana.get(i)) {
                    String poistettava = String.valueOf(teksti.charAt(j));
                    teksti = teksti.replace(poistettava, "");
                } 
            }
        }
        // Muutetaan kaikki kirjaimet pieniksi.
        teksti = teksti.toLowerCase();

        // Tehdään tekstistä lista
        LinkedList<String> tekstiListana = new LinkedList<String>(Arrays.asList(teksti.split(" ")));
        
        /* 
        * Sulkusanojen poisto
        */
        
        // Käydään sulkusanalista silmukalla läpi ja verrataan siihen tekstiä listana.
        for (int i = 0; i < sulkusanat.size(); i++) {
            for (int j = 0; j < tekstiListana.size(); j++) {
                // Jos sanat täsmäävät, poistetaan sana tekstistä
                if (tekstiListana.get(j).equals(sulkusanat.get(i))) {
                    tekstiListana.remove(j);
                    // Pidetään laskuri paikalaan, jotta tekstin peräkkäiset
                    // sulkusanat saadaan poistettua.
                    j--;
                }
            }
        }
        // Lopuksi puretaan tekstiListana takaisin String-muotoon vanhan tekstin tilalle.
        teksti = teksti.join(" ", tekstiListana);
        
        // Poistetaan ylimääräiset välilyönnit
        teksti = teksti.trim().replaceAll(" +", " ");
    }
}
