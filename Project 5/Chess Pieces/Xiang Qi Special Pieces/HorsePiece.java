/**
 * An  class that encodes specific rules for horse pieces.
 *
 * @author Bruce Dong
 */
public class HorsePiece  extends ChessPiece{
  /**
   * Stores a chess piece
   * @param side the side of the piece
   * @param row  the number of rows at the chessboard
   * @param col  the number of columns at the chessboard
   * @param label  the lable of the piece
   * @param board  the board that its belong to
   */
  public HorsePiece( ChessGame.Side side, int row, int col, String label,ChessBoard board){
    super( side, row, col, label, board);
  }

  /** Determines if it is legal to move the piece to its given location.
   * @param toRow     the row of the square the piece want to go
   * @param toColumn  the column of the square the piece want to go
   * @return true if the piece is allowed to move there
   */
  public boolean isLegalMove(int toRow, int toColumn){
    if( isLegalNotMove(toRow, toColumn))
      return false;

    if(isKingFace(toRow,toColumn,0))
      return false;

    // friend side then cannot move
    if(getChessBoard().hasPiece(toRow, toColumn)) {
      if (getChessBoard().getPiece(toRow, toColumn).getSide() == getSide())
        return false;
    }
    
    // when piece is next to it and in the path, then cannot move there 
    if(toRow > getRow() ){
      if(toColumn > getColumn()){
        if(getChessBoard().getPiece(toRow -1, toColumn -1) != null)
          return false;
      }
      if(toColumn < getColumn()){
        if(getChessBoard().getPiece(toRow -1, toColumn +1) != null)
          return false;
      }
    }
    if(toRow < getRow() ){
      if(toColumn > getColumn()){
        if(getChessBoard().getPiece(toRow +1, toColumn -1) != null)
          return false;
      }
      if(toColumn < getColumn()){
        if(getChessBoard().getPiece(toRow +1, toColumn +1) != null)
          return false;
      }
    }
    // calculate the difference
    int rowD = Math.abs(toRow - getRow());
    int colD = Math.abs( toColumn - getColumn());
    // judege whether its L shape or not
    return (rowD == 1 && colD == 2) || (rowD == 2 && colD == 1);
  }
  

}