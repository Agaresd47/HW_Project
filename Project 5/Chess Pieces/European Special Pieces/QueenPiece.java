/**
 * An  class that encodes specific rules for queen pieces.
 *
 * @author Bruce Dong
 */
public class QueenPiece  extends ChessPiece{
  /**
   * Stores a chess piece
   * @param side the side of the piece
   * @param row  the number of rows at the chessboard
   * @param col  the number of columns at the chessboard
   * @param label  the lable of the piece
   * @param board  the board that its belong to
   */
  public QueenPiece ( ChessGame.Side side, int row, int col, String label,ChessBoard board){
    super( side, row, col, label, board);
  }

  /** Determines if it is legal to move the piece to its given location.
   * @param toRow     the row of the square the piece want to go
   * @param toColumn  the column of the square the piece want to go
   * @return true if the piece is allowed to move there
   */
  public boolean isLegalMove(int toRow, int toColumn){
    return (isLegalMoveStraight(toRow, toColumn) || isLegalMoveDiagonal( toRow, toColumn));
  }
}