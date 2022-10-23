/**
 * An  class that encodes specific rules for king pieces.
 *
 * @author Bruce Dong
 */
public class KingPiece  extends ChessPiece{
  /**
   * Stores a chess piece
   * @param side the side of the piece
   * @param row  the number of rows at the chessboard
   * @param col  the number of columns at the chessboard
   * @param label  the lable of the piece
   * @param board  the board that its belong to
   */
  public KingPiece ( ChessGame.Side side, int row, int col, String label,ChessBoard board){
    super( side, row, col, label, board);
  }

  // This is the variable stores its moved time
  private int move =0;
  // This is the variable stores whether it's moved or not
  private boolean moved;

  /**
   * Rests the time of moved
   */
  public void resetMoved(){
    move =0;
  }

  /** Determines if it is legal to move the piece to its given location.
   * @param toRow     the row of the square the piece want to go
   * @param toColumn  the column of the square the piece want to go
   * @return true if the piece is allowed to move there
   */
  @Override
  public boolean isLegalMove(int toRow, int toColumn){
    if( isLegalNotMove(toRow, toColumn))
      return false;
    // If toColumn ==2, castle move is to the left side
    if( toColumn == 2  && getColumn() == 4) {
      if (move > 0){
        moved = false;
        return false;
      }

      if (getSide() == ChessGame.Side.SOUTH) {
        if (!isTargetEmpty(7, 0)) {
          if (!getChessBoard().getPiece(7, 0).moved()) {
            moved = false;
            return false;
          }
        }
      }
        if (getSide() == ChessGame.Side.NORTH) {
          if (!isTargetEmpty(0, 0)) {
            if (!getChessBoard().getPiece(0, 0).moved()) {
              moved = false;
              return false;
            }
          }
        }

        if (getSide() == ChessGame.Side.SOUTH) {
          if (pieceBetweenEmpty(7, 3, 1)) {
            moveDone(getChessBoard().getPiece(7, 0), 7, 3);
            getChessBoard().removePiece(7,0);
            moveDone(this,7,2);
            ++move;
            moved = true;
            return true;
          }
        }
        if (getSide() == ChessGame.Side.NORTH) {
          if (pieceBetweenEmpty(0, 3, 1)) {
            moveDone(getChessBoard().getPiece(0, 0), 0, 3);
            getChessBoard().removePiece(0,0);
            moveDone(this,0,2);
            ++move;
            moved = true;
            return true;
          }
        }
      }


    // If toColumn ==6, castle move is to the left side
    if( toColumn == 6  && getColumn() == 4){
      if (move > 0){
        moved = false;
        return false;
      }

      if( getSide() == ChessGame.Side.SOUTH){
        if( isTargetEmpty(7,7)){
          if( !getChessBoard().getPiece(7,7).moved()){
            moved = false;
            return false;
          }
        }
      }
      if( getSide() == ChessGame.Side.NORTH ){
        if( isTargetEmpty(0,7)){
          if( !getChessBoard().getPiece(0,7).moved()){
            moved = false;
            return false;
          }
        }
      }
      
      
      if( getSide() == ChessGame.Side.SOUTH ) {
        if (pieceBetweenEmpty(7, 5, 6)) {
          moveDone(getChessBoard().getPiece(7, 7), 7, 5);
          getChessBoard().removePiece(7,7);
          moveDone(this,7,6);
          ++move;
          moved = true;
          return true;
        }
      }
      if( getSide() == ChessGame.Side.NORTH ) {
        if (pieceBetweenEmpty(0,5, 6)) {
          moveDone(getChessBoard().getPiece(0,7), 0, 5);
          getChessBoard().removePiece(0,7);
          moveDone(this,0,6);
          ++move;
          moved = true;
          return true;
        }
      }
    }

    int rowD = Math.abs(toRow - getRow());
    int colD = Math.abs( toColumn - getColumn());
    if( isTargetEmpty(toRow,toColumn)||isTargetEnemy( toRow,  toColumn) ) {
      if( (rowD == colD && colD ==1 )|| (rowD == 1 && colD ==0 )|| (rowD == 0 && colD ==1 )){
        ++move;
        moved = true;
        return true;
      }
    }
    moved = false;
    return false;
  }

  /** Determines if it is legal to move the piece to its given location without capture.
   * @param toRow     the row of the square the piece want to go
   * @param toColumn  the column of the square the piece want to go
   * @return true if the piece is allowed to move there without capture
   */
  public boolean isLegalNonCaptureMove(int toRow, int toColumn){
    return moved;
  }

  /** Determines if it is legal to move the piece to its given location with capture.
   * @param toRow     the row of the square the piece want to go
   * @param toColumn  the column of the square the piece want to go
   * @return true if the piece is allowed to move there with capture
   */
  @Override
  public boolean isLegalCaptureMove(int toRow, int toColumn){
    int rowD = Math.abs(toRow - getRow());
    int colD = Math.abs( toColumn - getColumn());
    if(rowD>1 || colD >1)
      return false;
    if( getChessBoard().hasPiece(toRow, toColumn))
      if(getChessBoard().getPiece(toRow, toColumn).getSide() != getSide())
        return isLegalMove(toRow, toColumn);
    return false;
  }




}