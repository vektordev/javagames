import java.util.Scanner;
import javax.swing.WindowConstants;

/**
 * Settings beinhaltet alle Einstellungen, die der Spieler eingeben kann. Diese werden im Normalfall über die Konsole erfragt.
 */
public class Settings{
  /**
   * Standart-Konstruktor. Holt über die Konsole die ersten Einstellungen.
   */
  public Settings(){
    sysin = new Scanner(System.in);
    System.out.println("Spielen wir ein Spiel!");
    System.out.println("Spieler 1 steuert mit WASD, Spieler 2 mit den Pfeiltasten.");
    getSettings();
  }
  
  /**
   * Alternativer Konstruktor für hardcoded-settings.
   */
  public Settings(boolean mp, int pu, int dt){;
    multiplayer = mp;
    this.noOfPickups = pu;
    this.deltaTimeMillis = dt;
  }
  
  /**
   * Erzeugt über Konsolendialog Settings für die nächste Runde.
   */
  public void getNextSettings(){
    System.out.println("Neues Spiel? (y/n)");
    boolean parseable = false;
    String in;
    while (!parseable) { 
      in = sysin.nextLine();
      if (in.equals("y") || in.equals("Y")) {
        parseable = true;
      } // end of if
      if (in.equals("n") || in.equals("N")) {
        //parseable = true;
        System.exit(WindowConstants.EXIT_ON_CLOSE);
      } // end of if
    } // end of while
    System.out.println("Die selben Einstellungen? (y/n)");
    parseable = false;
    while (!parseable) { 
      in = sysin.nextLine();
      if (in.equals("y") || in.equals("Y")) {
        parseable = true;
      } // end of if
      if (in.equals("n") || in.equals("N")) {
        parseable = true;
        getSettings();
      } // end of if
    } // end of while
    return;
  }
  
  private void getSettings(){
    System.out.println("2 Spieler? (y/n)");
    boolean parseable = false;
    String input;
    while (!parseable) { 
      while (!sysin.hasNextLine()) { 
        
      } // end of while
      input = sysin.nextLine();
      if (input.equals("y") || input.equals("Y")) {
        multiplayer = true;
        parseable = true;
      } // end of if
      if (input.equals("n") || input.equals("N")) {
        multiplayer = false;
        parseable = true;
      } // end of if
    } // end of while
    
    System.out.println("Spielgeschwindigkeit? (1-3)");
    int in = 0;
    while (in <= 0 || in > 3) { 
      in = sysin.nextInt();
    } // end of while
    deltaTimeMillis = 200 / in;
    
    System.out.println("Anzahl der Pickups? (1-10)");
    in = 0;
    while (in <= 0 || in > 10) { 
      in = sysin.nextInt();
    } // end of while
    noOfPickups = in;
  }
  
  public int getDeltaTimeMillis(){
    return deltaTimeMillis;
  }
  
  public int getNoOfPickups(){
    return noOfPickups;
  }
  
  public boolean isMP(){
    return multiplayer;
  }
  
  protected Scanner sysin;
  
  protected int deltaTimeMillis;
  protected int noOfPickups;
  protected boolean multiplayer;
}