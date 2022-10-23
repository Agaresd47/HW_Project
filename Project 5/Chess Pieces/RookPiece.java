/**
 * An  class that encodes specific rules for rook pieces.
 *
 * @author Bruce Dong
 */
public class RookPiece  extends ChessPiece{
  // stores how many time it is moved
  public int movetime =0;

  /**
   * Stores a chess piece
   * @param side the side of the piece
   * @param row  the number of rows at the chessboard
   * @param col  the number of columns at the chessboard
   * @param label  the lable of the piece
   * @param board  the board that its belong to
   */  public RookPiece ( ChessGame.Side side, int row, int col, String label,ChessBoard board){
    super( side, row, col, label, board);
  }



  /** Determines if the piece is moved.
   * @return true if the piece is moved
   */
  @Override
  public boolean moved(){
    // If it moved once
    return movetime < 1;
  }

  /**
   * Rests the time of moved
   */
  public void resetMoved(){
    movetime =0;
  }

  /** Determines if it is legal to move the piece to its given location.
   * @param toRow     the row of the square the piece want to go
   * @param toColumn  the column of the square the piece want to go
   * @return true if the piece is allowed to move there
   */
  public boolean isLegalMove(int toRow, int toColumn){
    if(isKingFace(toRow,toColumn,0))
      return false;

    if( isLegalMoveStraight( toRow, toColumn)){
      movetime++;
      return true;
    }
    return false;
  }  
}