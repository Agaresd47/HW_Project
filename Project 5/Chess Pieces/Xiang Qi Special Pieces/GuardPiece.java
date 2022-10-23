/**
 * An  class that encodes specific rules for guard pieces.
 *
 * @author Bruce Dong
 */
public class GuardPiece  extends ChessPiece{

  // This filed is to store the start row of king
  private int startRow;

  // This filed is to store the start column of king
  private int startCol;
  /**
   * Stores a chess piece
   * @param side the side of the piece
   * @param row  the number of rows at the chessboard
   * @param col  the number of columns at the chessboard
   * @param label  the label of the piece
   * @param board  the board that its belong to
   */
  public GuardPiece ( ChessGame.Side side, int row, int col, String label,ChessBoard board){
    super( side, row, col, label, board);
    XiangqiKingPiece kp;
    // get the localtion of king
    if(board.getPiece(row, col-1) != null && board.getPiece(row, col-1).amAKing())
      kp = (XiangqiKingPiece) board.getPiece(row, col-1);
    else
      kp = (XiangqiKingPiece) board.getPiece(row, col+1);
    startRow = kp.startlocation()[0];
    startCol = kp.startlocation()[1];
  }

  /** Determines if it is legal to move the piece to its given location.
   * @param toRow     the row of the square the piece want to go
   * @param toColumn  the column of the square the piece want to go
   * @return true if the piece is allowed to move there
   */
  @Override
  public boolean isLegalMove(int toRow, int toColumn){
    
    // The field is to store the difference of rows
    int rowD = Math.abs(toRow - getRow());

    // The field is to store the difference of columns
    int colD = Math.abs( toColumn - getColumn());

    if(isKingFace(toRow,toColumn,0))
      return false;

    // Test both capture and non capture situation
    if(isKingFace(toRow,toColumn,0))
      return false;

    if(rowD>1 || colD >1)
      return false;

    if( toColumn < startCol-1 || toColumn > startCol +1 )
      return false;

    if(getSide() == ChessGame.Side.NORTH){
      if( toRow > startRow + 2)
        return false;
    }

    if(getSide() == ChessGame.Side.SOUTH){
      if( toRow < startRow - 2)
        return false;
    }

    if( isTargetEmpty(toRow,toColumn) || isTargetEnemy( toRow, toColumn) ) {
      return rowD == 1 && colD == 1;
    }
    return false;
  }

}