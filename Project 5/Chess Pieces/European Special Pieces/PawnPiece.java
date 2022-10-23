/**
 * An  class that encodes specific rules for pawn pieces.
 *
 * @author Bruce Dong
 */
public class PawnPiece  extends ChessPiece{
  /**
   * Stores a chess piece
   * @param side the side of the piece
   * @param row  the number of rows at the chessboard
   * @param col  the number of columns at the chessboard
   * @param label  the lable of the piece
   * @param board  the board that its belong to
   */
  public PawnPiece ( ChessGame.Side side, int row, int col, String label,ChessBoard board){
    super( side, row, col, label, board);
  }
  // This is the variable stores user's wanted variable
  private String wish ="";
  // This is the variable stores its moved time
  private int moved =0;
  // This is the variable stores its moved situation
  private boolean moves =false;
  // If it is moved by one spot, it will change
  private int movec =0;

  /** Determines if it is legal to move the piece to its given location without capture.
   * @param toRow     the row of the square the piece want to go
   * @param toColumn  the column of the square the piece want to go
   * @return true if the piece is allowed to move there without capture
   */
  public boolean isLegalRegularMove(int toRow, int toColumn){
    // reset parameter
    moves = false;
    // calculate the difference
    int rowD = toRow - getRow();
    int colD = toColumn - getColumn();
    if( !isTargetEmpty( toRow , toColumn))
      return false;

    // South side move one up
    if (getSide() == ChessGame.Side.SOUTH) {
      if (rowD == -1 && colD == 0 ) {
        ++moved;
        movec=20;
        return true;
      }
    }
    // North side move one down
    if (getSide() == ChessGame.Side.NORTH) {
      if (rowD == 1 && colD == 0) {
        ++moved;
        movec=20;
        return true;
      }
    }
    // Judge the first move or not
    if( moved < 4 && movec != 20) {

      if (getSide() == ChessGame.Side.SOUTH ) {
        // south 2 spot up
        if (rowD == -2 && colD == 0) {
          ++moved ;
          return true;
        }
      }

      if (getSide() == ChessGame.Side.NORTH ) {
        // north 2 spot down
        if (rowD == 2 && colD == 0) {
          ++moved ;
          return true;
        }
      }
    }
    return false;
  }

  /** Determines if it is legal to move the piece to its given location with capture.
   * @param toRow     the row of the square the piece want to go
   * @param toColumn  the column of the square the piece want to go
   * @return true if the piece is allowed to move there with capture
   */
  public boolean isLegalRegularCapture(int toRow, int toColumn) {
    // calculate the difference
    int rowD = toRow - getRow();
    int colD = toColumn - getColumn();


    if( isTargetEmpty(toRow, toColumn))
      return false;

    if (!isTargetEnemy(toRow, toColumn))
      return false;

    if (getSide() == ChessGame.Side.SOUTH) {
      if (rowD == -1 && Math.abs(colD) == 1) {
        ++moved;
        movec=20;
        return true;
      }
    }
    if (getSide() == ChessGame.Side.NORTH) {
      if (rowD == 1 && Math.abs(colD) == 1) {
        ++moved;
        movec=20;
        return true;
      }
    }
    return false;
  }

  /** Determines if it is legal to upgrade it.
   * @param toRow     the row of location that user want to upgrade
   * @param toColumn  the column of location that user want to upgrade
   */
  public void upgrade(int toRow, int toColumn){
    if( (getSide() == ChessGame.Side.SOUTH )) {
      // reach the top of the chess board
      if( toRow ==0) {
        // can go there
        if (isLegalRegularMove(toRow, toColumn) || isLegalRegularCapture(toRow, toColumn))
          wish = javax.swing.JOptionPane.showInputDialog("Please type your wanted piece. i.e. (Rook)");
      }
    }

    if((getSide() == ChessGame.Side.NORTH )) {
      // reach the bottom of the chess board
      if( toRow ==7) {
        // can go there
        if (isLegalRegularMove(toRow, toColumn) || isLegalRegularCapture(toRow, toColumn))
          wish = javax.swing.JOptionPane.showInputDialog("Please type your wanted piece. i.e. (Rook)");
      }
    }

    if(wish.equals( "Rook")) {
      // remove the old pawn
      getChessBoard().removePiece(getRow(), getColumn());
      // add new piece
      getChessBoard().addPiece(new RookPiece( getSide(), getRow(), getColumn(), "R" , getChessBoard()),toRow,toColumn);
      moves = true;
    }

    if(wish.equals("Bishop")) {
      // remove the old pawn
      getChessBoard().removePiece(getRow(), getColumn());
      // add new piece
      getChessBoard().addPiece(new BishopPiece( getSide(), getRow(), getColumn(), "B" , getChessBoard()),toRow, toColumn);
      moves = true;
    }

    if(wish.equals("Queen")) {
      // remove the old pawn
      getChessBoard().removePiece(getRow(), getColumn());
      // add new piece
      getChessBoard().addPiece(new QueenPiece( getSide(), getRow(), getColumn(), "Q" , getChessBoard()),toRow, toColumn);
      moves = true;
    }

    if(wish.equals("Knight")) {
      // remove the old pawn
      getChessBoard().removePiece(getRow(), getColumn());
      // add new piece
      getChessBoard().addPiece(new KnightPiece( getSide(), getRow(), getColumn(), "K" , getChessBoard()),toRow, toColumn);
      moves = true;
    }
  }

  /** Determines if it is legal to move the piece to its given location.
   * @param toRow     the row of the square the piece want to go
   * @param toColumn  the column of the square the piece want to go
   * @return true if the piece is allowed to move there
   */
  public boolean isLegalMove(int toRow, int toColumn){
    if( isLegalNotMove(toRow, toColumn))
      return false;

    // if it is the upgrade situation
    if( moves && moved >5)
      return true;
    return (  isLegalRegularMove(toRow, toColumn)||isLegalRegularCapture(toRow, toColumn));
  }

  /** Necessary operation after the piece can move to there
   * @param piece     the piece that is moved
   * @param toRow     the row of the square the piece want to go
   * @param toColumn  the column of the square the piece want to go
   */
  public void moveDone(ChessPiece piece, int toRow, int toColumn){
    upgrade(toRow, toColumn);
    // if it is the upgrade situation
    if( moves && moved >5)
      return;
    getChessBoard().removePiece( getRow(), getColumn());
    piece.setLocation(toRow, toColumn);
    getChessBoard().addPiece( piece, toRow, toColumn);

  }

}