import java.util.Scanner;

/**
 * This class help the user to start the game
 *
 * @author Bruce Dong
 */
public class GameStarter {
  /**
   * Launch the game
   * @param args the command line arguments are currently ignored
   */
  public static void main(String[] args) {
    // a boolean to stop the require command loop
    boolean stop = false;

    System.out.println("type in the game that you want to play: chess or xiangqi");
    // get the wanted game
    String game = new Scanner(System.in).next();
    //Force the user to type a correct game way
    while(!stop){
      if(game.equals("xiangqi") || game.equals("chess")){
        stop = true;
      }
      else {
        System.out.println("you need to type a correct game name!");
        game = new Scanner(System.in).next();
      }
    }

    stop = false;
    System.out.println("Choose a way of display: JavaFX or Swing?");
    String way = new Scanner(System.in).next();

    // force the user to type a correct way of display
    while(!stop){
      if(way.equals("Swing") || way.equals("JavaFX"))
        stop = true;
      else {
        System.out.println("you need to type a correct way of display!");
        way = new Scanner(System.in).next();
      }
    }
    JavaFXChessBoard.play(game,way);
  }
}
