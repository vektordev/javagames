import java.util.Scanner;
import javax.swing.WindowConstants;

/**
 * Settings contains all the settings the user can make.
 * These are queried by console.
 */
public class Settings{
  /**
   * Default CTor
	 * Generates initial settings by console IO.
   */
  public Settings(){
    sysin = new Scanner(System.in);
    System.out.println("Let's play a game!");
    System.out.println("Player 1 controls: WASD. Player 2 controls: Arrow keys.");
    getSettings();
  }
  
  /**
   * alternative CTor
	 * generates Settings from hardcoded parameters.
   */
  public Settings(boolean mp, int pu, int dt){;
    multiplayer = mp;
    this.noOfPickups = pu;
    this.deltaTimeMillis = dt;
  }
  
  /**
   * Creates the settings for the next round, using console IO.
   */
  public void getNextSettings(){
    System.out.println("New game? (y/n)");
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
    System.out.println("Keep the settings? (y/n)");
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
    System.out.println("2 players? (y/n)");
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
    
    System.out.println("Game speed? (1-3)");
    int in = 0;
    while (in <= 0 || in > 3) { 
      in = sysin.nextInt();
    } // end of while
    deltaTimeMillis = 200 / in;
    
    System.out.println("Number of collectibles? (1-10)");
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
