
package harjoitustyo.dokumentit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
;

/**
 * Konkreettinen luokka uutisille.
 * 
 * <p> 
 * Olio-ohjelmoinnin perusteet II, kevät 2020
 * 
 * @version 0.1
 * @author Henry Andersson, henry.andersson@tuni.fi.
 * 
 */
public class Uutinen extends Dokumentti{
    /*
    * Attribuutit
    */
    
    /** Päivämäärän kertova attribuutti */
    private LocalDate päivämäärä;

    /*
    * Aksessorit
    */
    
    // Lukevat aksessorit
    public LocalDate päivämäärä() {
        return päivämäärä;
    }

    // Asettavat aksessorit
    public void päivämäärä(LocalDate päiväys) throws IllegalArgumentException {
        if (päiväys == null) {
            throw new IllegalArgumentException(VIRHE);
        } else {
        päivämäärä = päiväys;
        }
    }

    /*
    * Rakentajat
    */
    public Uutinen(int tunnus, LocalDate päiväys, String kirjoitelma) throws IllegalArgumentException {
        super(tunnus, kirjoitelma);
        päivämäärä(päiväys);
    }
    
    /*
    * OBject-luokan metodien korvaukset
    */
    /**
    * Muodostaa dokumentin merkkijonoesityksen, joka koostuu tunnisteesta
    * erottimesta, päiväyksestä ja tekstistä.
    *
    * @return dokumentin merkkijonoesitys lisättynä päiväys.
    */
    @Override
    public String toString() {
        String muotoiltuPäiväys = päivämäärä.format(DateTimeFormatter.ofPattern("d.M.yyyy"));
        return tunniste() + EROTIN + muotoiltuPäiväys + EROTIN + teksti();
    }
}
