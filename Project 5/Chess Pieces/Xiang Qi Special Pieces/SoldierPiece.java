/**
 * An  class that encodes specific rules for soldier pieces.
 *
 * @author Bruce Dong
 */
public class SoldierPiece  extends ChessPiece{
  // count how many time it has moved
  boolean leftRight = false;
  // This filed stores how many time it need to move to the other side.
  private int goOver;

  /**
   * Stores a chess piece
   * @param side the side of the piece
   * @param row  the number of rows at the chessboard
   * @param col  the number of columns at the chessboard
   * @param label  the lable of the piece
   * @param board  the board that its belong to
   */
  public SoldierPiece ( ChessGame.Side side, int row, int col, String label,ChessBoard board){
    super( side, row, col, label, board);
    goOver =getChessBoard().getNumRows()/2 ;
    isLegalMove(row);
  }

  /** Determines if it is legal to move the piece to its given location.
   * @param toRow     the row of the square the piece want to go
   * @param toColumn  the column of the square the piece want to go
   * @return true if the piece is allowed to move there
   */
  public boolean isLegalMove(int toRow, int toColumn){
    // The field is to store the difference of rows
    int rowD = Math.abs(toRow - getRow());

    // The field is to store the difference of columns
    int colD = Math.abs( toColumn - getColumn());

    if(isKingFace(toRow,toColumn,0))
      return false;

    if( isLegalNotMove(toRow, toColumn))
      return false;
    if(!isTargetEmpty(toRow, toColumn) && !isTargetEnemy(toRow, toColumn))
      return false;
    // You cannot go back ward
    if(getSide() == ChessGame.Side.NORTH && toRow < getRow())
      return false;
    if(getSide() == ChessGame.Side.SOUTH && toRow > getRow())
      return false;
    if(!leftRight){
      if(colD != 0)
        return false;
      if(rowD != 1)
        return false;
      isLegalMove(toRow);
      return true;
    }
    return (rowD == colD && colD == 1) || (rowD == 1 && colD == 0) || (rowD == 0 && colD == 1);

  }
  /** Determines if it is legal to go left or right
   * @param toRow     the row of the square the piece want to go
   */
  public void isLegalMove(int toRow){
    if(getSide() == ChessGame.Side.NORTH && toRow >= goOver)
      leftRight = true;
    if(getSide() == ChessGame.Side.SOUTH && toRow < goOver)
      leftRight = true;
  }
}