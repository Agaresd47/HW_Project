/**
 * An  class that encodes specific rules for cannon pieces.
 *
 * @author Bruce Dong
 */
public class CannonPiece  extends ChessPiece{
  /**
   * Stores a chess piece
   * @param side the side of the piece
   * @param row  the number of rows at the chessboard
   * @param col  the number of columns at the chessboard
   * @param label  the lable of the piece
   * @param board  the board that its belong to
   */
  public CannonPiece ( ChessGame.Side side, int row, int col, String label,ChessBoard board){
    super( side, row, col, label, board);
  }

  /** Determines if it is legal to move the piece to its given location.
   * @param toRow     the row of the square the piece want to go
   * @param toColumn  the column of the square the piece want to go
   * @return true if the piece is allowed to move there
   */
  public boolean isLegalMove(int toRow, int toColumn){
    // count how many piece are between the distenation and the piece
    int count = 0;

    if(isKingFace(toRow,toColumn,0))
      return false;

    if(!isTargetEmpty(toRow, toColumn) && isTargetEnemy(toRow, toColumn)){
      if( isLegalNotMove(toRow, toColumn))
        return false;
      // calculate the difference
      int rowD = toRow - getRow();
      int colD = toColumn - getColumn();
      
      // In different moving direction, count how many piece are between the distenation and the piece
      if( rowD == 0){
        if( colD < 0){
          for ( int index =-1; index > colD ; --index){
            if( getChessBoard().hasPiece(  getRow() , getColumn() +index))
              count++;
          }
        }
        if( colD > 0){
          for ( int index =1; index < colD ; ++ index){
            if( getChessBoard().hasPiece(  getRow() , getColumn() +index))
              count++;
          }
        }
      }

      if( colD == 0){
        if( rowD < 0){
          for ( int index =-1; index > rowD ; -- index){
            if( getChessBoard().hasPiece(  getRow() +index, getColumn() ))
              count++;
          }
        }
        if( rowD > 0){
          for ( int index =1; index < rowD ; ++ index){
            if( getChessBoard().hasPiece(  getRow() +index, getColumn() ))
              count++;
          }
        }
      }
      return count == 1;
    }
    return (isLegalMoveStraight(toRow, toColumn));
  }
}