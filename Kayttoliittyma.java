
import java.io.File;
import java.util.Scanner;

/**
 * Luokka äyttöliittymälle
 * 
 * <p> 
 * Olio-ohjelmoinnin perusteet II, kevät 2020
 * 
 * @version 0.5
 * @author Henry Andersson, henry.andersson@tuni.fi.
 * 
 */
public class Kayttoliittyma {
    
    /** Yleinen virheilmoitus */
    public static final String VIRHE = "Error!";
    /** Erottimet */
    public static final String EROTIN = "///";
    
    /** Kokoelma jota muokataan*/
    Kokoelma kokoelma;
    /** Apumuuttuja kokoelman tyypin tarkistamiseen. Uutinen = false,  Vitsi = true. */
    boolean onkoVitsi = false;
    
    /**
     * Metodi joka sisältää ohjelman pääsilmukan. Ohjelma pyttää käyttäjältä komentoja, 
     * kunnes käyttäjä syöttää quit.
     * @param arguments kokoelman ja sulkusanojen polku.
     */
    public Kayttoliittyma(String[] arguments) {
        // Lippumuuttuja pääsilmukan lopettamiseen.
        boolean lippu = true;
        // Tarkistetaan, että syötteitä on kaksi
        if (arguments.length != 2) {
            // Tulostetaan virheilmoitus.
            System.out.println("Wrong number of command-line arguments!");
            lippu = false;
        } else {
            // Tarkistetaan, että tiedosto löytyy
            try {
                File tiedosto1 = new File(arguments[0]); 
                File tiedosto2 = new File(arguments[1]); 
                // Liitetään lukija tiedostoon.
                Scanner lukija1 = new Scanner(tiedosto1);
                Scanner lukija2 = new Scanner(tiedosto2);

                // Kutsitaan kokoelmaa jos tietostot 
                kokoelma = new Kokoelma(arguments[0], arguments[1]);
            } catch (Exception e) {
                System.out.println("Missing file!");
                lippu = false;
            }
        }
        
        Scanner user = new Scanner(System.in);
        
        // Apumuuttuja kauitikselle.
        boolean echo = false;
        
        // Asetaan apumuu onkoVitsi oikeaan arvoon.
        if (arguments[0].contains("jokes")) {
            onkoVitsi = true;
        }

        // Luetaan komentoja kunnes käyttäjä syöttää "quit"
        while (lippu) {
            
            // Pyydetään käyttäjältä koemnto.
            System.out.println("Please, enter a command:");
            //Luodaan komento muuttujalle
            String komento = user.nextLine();
            
            // Jaetaan komento välilyönnin kohdalta ja rajoitetaan listan koko kahteen,
            // jolloin saadaan komento ja sisältö erotettua toisistaan.
            String[] jaettu = null;
            try {
                jaettu = komento.split(" ", 2);
            // Siepataan ja heitetään virhe
            } catch (Exception e ) {
                System.out.println(VIRHE);
            }
            
            // Muutetaan kaiutiksen muuttuja oikeaan arvoon.
            if (jaettu[0].equals("echo") && !echo) {
                echo = true;
            } else if(jaettu[0].equals("echo") && echo) {
                echo = false;
            }
            // Kaiutetaan komento.
            if (echo) {
                System.out.println(komento);
            }
            
            // Tulostetaan virhe, jos komennot ovat muuta kuin pyydetyt.
            if (!jaettu[0].equals("quit") && !jaettu[0].equals("add")&& !jaettu[0].equals("remove") && 
                !jaettu[0].equals("polish") && !jaettu[0].equals("reset")&& !jaettu[0].equals("print") &&
                !jaettu[0].equals("echo") && !jaettu[0].equals("find")) {
                System.out.println(VIRHE);
            }
            
            /*
            * Tulostukomento
            */
            if (jaettu[0].equals("print")) {
                // Kutsutaan tulostusmetodia.
                kokoelma.tulosta(jaettu);
            }

            /*
            * Lisäyskomento
            */
            if (jaettu[0].equals("add")) {
                // Kutsutaan lisäysmetodia.
                try {
                    kokoelma.add(jaettu[1], onkoVitsi);
                } catch (Exception e) {
                    System.out.println(VIRHE);
                }
            }
            
            /*
            * Hakukomento
            */
            if (jaettu[0].equals("find")) {
                // Kutsutaan hakumetodia.
                if (jaettu.length != 2) {
                    System.out.println(VIRHE);
                } else {
                    kokoelma.etsi(jaettu[1]);
                }
                
            }
            
            /*
            * Poistokomento
            */
            if (jaettu[0].equals("remove")) {
                // Kutsutaan poista metodia, muutetaan tunnus numeraaliseen muotoon
                try {
                    kokoelma.poista(Integer.valueOf(jaettu[1]));
                // Siepataan ja heitetään poikkeus
                } catch (Exception e) {
                    System.out.println(VIRHE);
                }
            }
            
            /*
            * Nollauskomento
            */
            if (jaettu[0].equals("reset")) {
                // Nollataan vain jos ei ole syötetty muita parametreja
                if (jaettu.length == 1) {
                    kokoelma = new Kokoelma(arguments[0], arguments[1]);
                } else {
                    System.out.println(VIRHE);
                }
            }
            
            /*
            * Siistimiskomento
            */
            if (jaettu[0].equals("polish")) { 
                try {
                    // Tarkastetaan, että parametreja on vain yksi.
                    if (jaettu[1].split(" ").length == 1) {
                        kokoelma.siisti(jaettu[1]);
                    } else {
                        System.out.println(VIRHE);
                    }
                } catch (Exception e) {
                    System.out.println(VIRHE);
                }
            }
            
            /*
             Lopetuskomento     
            */
            if (komento.equals("quit")) {
                // Muutetaan lippumuuttujan arvoa.
                lippu = false;
                
            }
        }
        // Tulostetaan ilmoitus sulkemisesta.
        System.out.println("Program terminated.");
        // Suljetaan lukija.
        user.close();
    }
    
     
    
    
}
