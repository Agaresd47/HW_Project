/**
 * An  class that encodes specific rules for Xiang Qi king pieces.
 *
 * @author Bruce Dong
 */
public class XiangqiKingPiece  extends ChessPiece{
  // This filed is to store the start row
  private int startRow;

  // This filed is to store the start column
  private int startCol;

  /**
   * Stores a chess piece
   * @param side the side of the piece
   * @param row  the number of rows at the chessboard
   * @param col  the number of columns at the chessboard
   * @param label  the lable of the piece
   * @param board  the board that its belong to
   */
  public XiangqiKingPiece ( ChessGame.Side side, int row, int col, String label,ChessBoard board){
    super( side, row, col, label, board);
    this.startRow = row;
    this.startCol = col;
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
    
    // Test both capture and non capture situation
    if(isKingFace(toRow,toColumn,1))
      return false;
    if(isKingFace(toRow,toColumn,5))
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

    if( isTargetEmpty(toRow,toColumn)||isTargetEnemy( toRow, toColumn) ) {
      return (rowD == colD && colD == 1) || (rowD == 1 && colD == 0) || (rowD == 0 && colD == 1);
    }
    return false;
  }

  /** Determines if piece is a king
   * @return true if it is a king
   */
  public boolean amAKing(){
    return true;
  }

  /** Determines if piece is a king
   * @return true if it is a king
   */
  public int[] startlocation(){
    return new int[]{startRow, startCol};
  }
}