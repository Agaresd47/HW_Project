import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/** Rules for how we want a board to display for a game of Xiang qi
 *
 * @author Harold Connamacher
 * @author Bruce Dong
 */
public class JavaFXXiangqiDisplay  implements JavaFXChessBoardDisplay {
  // The background fill of the gray one
  private final BackgroundFill gray = new BackgroundFill(Color.web("#CAC2C2"), CornerRadii.EMPTY, null);
  // The background fill of the dark gray of the middle three square
  private final BackgroundFill grayD = new BackgroundFill(Color.web("#716E6E"), CornerRadii.EMPTY, Insets.EMPTY);
  // The background fill of the white one
  private final BackgroundFill white = new BackgroundFill(Color.web("#EDEBEB"), CornerRadii.EMPTY, null);
  // The background fill of the south side
  private final BackgroundFill south = new BackgroundFill(Color.web("#9A80C3"), new CornerRadii(50), new Insets(10));
  // The background fill of the north side
  private final BackgroundFill north = new BackgroundFill(Color.web("#d7eed2"), new CornerRadii(50), new Insets(10));
  // The background fill of the choosing side
  private final BackgroundFill chose = new BackgroundFill(Color.web("#45e6c9"), CornerRadii.EMPTY, null);
  /**
   * Display the empty square on the board
   * @param button the wanted button
   * @param row the given row
   * @param column the given column
   */
  @Override
  public void displayEmptySquare(Button button, int row, int column) {
    if (!((row >2 && row < 6) &&((column >=0 && column<3)|| (column >6 && column<11) ))) {
      button.setBackground(new Background(gray));
    } else {
      button.setBackground(new Background(grayD));
    }
    button.setText(null);

  }

  /**
   * Display the given piece on the board
   * @param button the wanted button
   * @param row the given row
   * @param column the given column
   */
  @Override
  public void displayFilledSquare(Button button, int row, int column, ChessPiece piece) {
    int even =1;
    // if it is not the middle 3*3 gird, it is painted to light gray 
    if (!((row >2 && row < 6) &&((column >=0 && column<3)|| (column >6 && column<11) )))
      even =0;

    switch(piece.getSide()){
      case NORTH:
        switch(even) {
          case 0:
            button.setBackground(new Background(white,north));
            break;
          case 1:
            button.setBackground(new Background(grayD,north));
            break;
        }
        break;
      case SOUTH:
        switch(even) {
          case 0:
            button.setBackground(new Background(white,south));
            break;
          case 1:
            button.setBackground(new Background(grayD,south));
            break;
        }
        break;
    }
    button.setText(piece.getLabel());
    button.setTextFill(Color.BLACK);
    button.setStyle("-fx-font-size:25");
  }

  /**
   * Highlight the given square on the board
   * @param button the wanted button
   * @param row the given row
   * @param column the given column
   */
  @Override
  public void highlightSquare(boolean highlight, Button button, int row, int column, ChessPiece piece) {
    if (highlight) {
      if(piece != null){
        switch(piece.getSide()){
          case NORTH:
            button.setBackground(new Background(chose,north));
            break;
          case SOUTH:
            button.setBackground(new Background(chose,south));
            break;
        }
      }
      else{
        button.setBackground(new Background(chose));
      }
    }
    else if (piece == null)
      displayEmptySquare(button, row, column);
    else
      displayFilledSquare(button, row, column, piece);
  }

}
