import javax.swing.JButton;
import java.awt.*;
import javax.swing.Icon;
/** Rules for how we want a board to display for a game of European chess
  * 
  * @author Harold Connamacher
  * @author Bruce Dong
  */
public class SwingEuropeanChessDisplay implements SwingChessBoardDisplay {
  /** The secondary color of the checkerboard */
  /* The color of the SOUTH player */
  public static Color southPlayerColor = new Color(154, 128, 195);

  /* The color of the NORTH player */
  public static Color northPlayerColor = new Color(215, 238, 210);

  /* The color of the EAST player */
  public static Color eastPlayerColor = new Color(154, 128, 195);

  /* The color of the WEST player */
  public static Color westPlayerColor = Color.gray;

  /** The color used to highlight a square */
  public static Color highlightColor = new Color(69, 230, 201);
  
  /**
   * Display a square that has no piece on it.  Will produce a red/black checkerboard.
   * @param button the button that is used for the chessboard square
   * @param row    the row of this square on the board
   * @param column the column of this square on the board
   */
  public void displayEmptySquare(JButton button, int row, int column) {

    button.setBackground((row + column) % 2 == 0 ? new Color(238, 238, 210) : new Color(118, 150, 86));
    button.setText(null);
    button.setIcon(null);
  }
  
  /**
   * Display a square that has a piece on it.
   * @param button the button that is used for the chessboard square
   * @param row    the row of this square on the board
   * @param column the column of this square on the board
   * @param piece  the piece that is on this square
   */
  public void displayFilledSquare(JButton button, int row, int column, ChessPiece piece) {
    Color pieceColor;
    
    switch (piece.getSide()) {
      case NORTH:   pieceColor = northPlayerColor;
                    break;
      case SOUTH:   pieceColor = southPlayerColor;
                    break;
      case EAST:    pieceColor = eastPlayerColor;
                    break;
      default:      pieceColor = westPlayerColor;
    }
    
    button.setBackground(pieceColor);
    button.setText(piece.getLabel());
    button.setIcon((Icon)piece.getIcon());
    button.setFont(new Font(button.getFont().getName(),button.getFont().getStyle(),40));
  }
  
  /**
   * Highlight a square of the board.
   * @param highlight  do we want the highlight on (true) or off (false)?
   * @param button     the button that is used for the chessboard square
   * @param row        the row of this square on the board
   * @param column     the column of this square on the board
   * @param piece      the piece (if any) that is on this square
   */
  public void highlightSquare(boolean highlight, JButton button, int row, int column, ChessPiece piece) {
    if (highlight) {
      button.setBackground(highlightColor);
    }
    else if (piece == null)
      displayEmptySquare(button, row, column);
    else
      displayFilledSquare(button, row, column, piece);
  }
}