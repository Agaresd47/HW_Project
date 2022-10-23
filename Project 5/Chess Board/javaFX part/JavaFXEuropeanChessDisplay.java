import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/** Rules for how we want a board to display for a game of European chess
 *
 * @author Harold Connamacher
 * @author Bruce Dong
 */
public class JavaFXEuropeanChessDisplay implements JavaFXChessBoardDisplay {
  // The background fill of the green one
  private BackgroundFill green = new BackgroundFill(Color.web("#769656"), CornerRadii.EMPTY, null);
  // The background fill of the white one
  private BackgroundFill white = new BackgroundFill(Color.web("#eee8d2"), CornerRadii.EMPTY, Insets.EMPTY);
  // The background fill of the south side
  private BackgroundFill south = new BackgroundFill(Color.web("#82e682"), new CornerRadii(50), new Insets(10));
  // The background fill of the north side
  private BackgroundFill north = new BackgroundFill(Color.web("#d7eed2"), new CornerRadii(50), new Insets(10));
  // The background fill of the choosing side
  private BackgroundFill chose = new BackgroundFill(Color.web("#6E6E6E"), CornerRadii.EMPTY, null);
  /**
   * Display the empty square on the board
   * @param button the wanted button
   * @param row the given row
   * @param column the given column
   */
  @Override
  public void displayEmptySquare(Button button, int row, int column) {
    //button.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, null)));
    if ((row + column) % 2 == 0) {
      button.setBackground(new Background(green));
    } else {
      button.setBackground(new Background(white));
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
    int even = (row + column) % 2;
    switch(piece.getSide()){
      case NORTH:
        switch(even) {
          case 0:
            button.setBackground(new Background(green,north));
            break;
          case 1:
            button.setBackground(new Background(white,north));
            break;
        }
        break;
      case SOUTH:
        switch(even) {
          case 0:
            button.setBackground(new Background(green,south));
            break;
          case 1:
            button.setBackground(new Background(white,south));
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