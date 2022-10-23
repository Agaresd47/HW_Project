/**
 * An  class that encodes specific rules for elephant pieces.
 *
 * @author Bruce Dong
 */
public class ElephantPiece  extends ChessPiece{
  // This filed is to store the half size board
  private int limitedRow;

  /**
   * Stores a chess piece
   * @param side the side of the piece
   * @param row  the number of rows at the chessboard
   * @param col  the number of columns at the chessboard
   * @param label  the lable of the piece
   * @param board  the board that its belong to
   */
  public ElephantPiece ( ChessGame.Side side, int row, int col, String label,ChessBoard board){
    super( side, row, col, label, board);
    if(getSide() == ChessGame.Side.NORTH)
      this.limitedRow = board.getNumRows() / 2 - 1;
    else
      this.limitedRow = row - board.getNumRows() / 2 + 1;
  }

  /** Determines if it is legal to move the piece to its given location.
   * @param toRow     the row of the square the piece want to go
   * @param toColumn  the column of the square the piece want to go
   * @return true if the piece is allowed to move there
   */
  public boolean isLegalMove(int toRow, int toColumn){
    if(isKingFace(toRow,toColumn,0))
      return false;

    // It cannot jump over 2 grid
    if(Math.abs( toColumn - getColumn()) !=2)
      return false;
    
    // It cannot jump to the other side
    if(getSide() == ChessGame.Side.NORTH && toRow > limitedRow){
      return false;
    }
    if (getSide() == ChessGame.Side.SOUTH && toRow < limitedRow)
      return false;
    
    // It cannot jump over other piece
    if(getChessBoard().getPiece((toRow + getRow())/2, (toColumn + getColumn())/2) != null)
      return false;

    return isLegalMoveDiagonal(toRow, toColumn);
  }
}