package harjoitustyo.dokumentit;


/**
 * Konkreettinen luokka vitseille
 * 
 * <p> 
 * Olio-ohjelmoinnin perusteet II, kevät 2020
 * 
 * @version 0.2
 * @author Henry Andersson, henry.andersson@tuni.fi.
 * 
 */
public class Vitsi extends Dokumentti {

    /*
    * Attribuutit
    */
    /** Lajin kertova attribuutti */
    private String laji;
    
    /*
    * Aksessorit
    */
    
    // Lukevat aksessorit
    public String laji() {
        return laji;
    }
    
    public int tunnus() {
        return super.tunniste();
    }
    
    public String teksti() {
        return super.teksti();
    }
    
    // Asettavat aksessorit
    public void laji(String setLajil) throws IllegalArgumentException{
        if (setLajil == "" || setLajil == null) {
            throw new IllegalArgumentException(VIRHE);
        }
            laji = setLajil;
    }

    /*
    * Rakentajat
    */
    public Vitsi(int tunnus, String vitsinLaji, String kirjoitelma) {
        super(tunnus, kirjoitelma);
        laji(vitsinLaji);
    }
    
    
    
    /*
    * OBject-luokan metodien korvaukset
    */
    /**
    * Muodostaa dokumentin merkkijonoesityksen, joka koostuu tunnisteesta
    * erottimesta, lajista ja tekstistä.
    *
    * @return dokumentin merkkijonoesitys lisättynä vitsin laji
    */
    @Override
    public String toString() {
        return tunniste() + EROTIN + laji + EROTIN + teksti();
    }
}