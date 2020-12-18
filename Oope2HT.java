/**
 * Ohjeman pääluokka.
 * 
 * <p> 
 * Olio-ohjelmoinnin perusteet II, kevät 2020
 * 
 * @version 0.1
 * @author Henry Andersson, henry.andersson@tuni.fi.
 * 
 */
public class Oope2HT {

    public static void main(String[] args){
        
        // tervehditään käyttäjää
        System.out.println("Welcome to L.O.T.");
        
        // Kutsutaan käyttöliittymää.
        try {
            Kayttoliittyma käyttis = new Kayttoliittyma(args);
        // Tulostetaan virheilmoitus.    
        } catch (Exception e) {
            System.out.println("Program terminated.");
        }
        
    }
}
