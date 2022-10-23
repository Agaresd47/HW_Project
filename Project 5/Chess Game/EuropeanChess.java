/**
 * An  class that encodes specific rules for european chess and the main method to run the game.
 *
 * @author Bruce Dong
 */

public class EuropeanChess  implements ChessGame {
  // This is a variable that when a side makes a legal move, it will add 1. It also helps judge whether is one side's time or not
  public static int count =0;
  // This variable stores the boolean of whether can move the piece or not
  public boolean canMove = false;

  /** Moves a piece to a new position.
   * @param piece     the piece to move
   * @param toRow     the row of the square the piece is moving to
   * @param toColumn  the column of the square the piece is moving to
   * @return true if the move was made, false if the move was not made
   */
  public boolean makeMove(ChessPiece piece, int toRow, int toColumn){
    if( piece.isLegalMove( toRow,  toColumn) ){
      if(piece.isLegalNonCaptureMove ( toRow, toColumn) || piece.isLegalCaptureMove ( toRow, toColumn) ){
        piece.moveDone(piece, toRow, toColumn);
        ++count;
        return true;
      }
    }
    return false;
  }

  /** Determines if it is legal to play a given piece.
   * @param piece   the piece to be played
   * @param row     the row of the square the piece is on
   * @param column  the column of the square the piece is on
   * @return true if the piece is allowed to move on this turn
   */
  public boolean legalPieceToPlay(ChessPiece piece, int row, int column) {
    if( piece.isLegalMove( row,  column) ) {
      if (piece.isLegalNonCaptureMove(row, column) || piece.isLegalCaptureMove(row, column)) {
        canMove= true;
      }
    }
    // means south side turn
    if(count%2 == 0){
      if(piece.getSide() == ChessGame.Side.SOUTH) {
        canMove= true;
        return true;
      }
    }
    // means north side turn
    if(count%2 == 1){
      if(piece.getSide() == ChessGame.Side.NORTH) {
        canMove= true;
        return true;
      }
    }
    canMove= false;
    return false;
  }

  /**
   * Returns whether a user can choose a different piece from the one selected or if they have to move the selected piece.
   * If this method returns false, then the <tt>legalPieceToPlay</tt> method must not return true if that piece has no
   * legal moves.  Otherwise the game could freeze with a player not permitted to change selection of a piece with no legal moves.
   * @param piece   the piece the user selected
   * @param row     the row of the square the piece is on
   * @param column  the column of the square the piece is on
   * @return true if the player can change the piece they selected and false if they cannot and must move that piece
   */
  public  boolean canChangeSelection(ChessPiece piece, int row, int column)  {
    return true;
  }

  /** Return total row of the game
   * @return Return total row of the game
   */
  @Override
  public int getNumRows() {
    return 8;
  }

  /** Return total columns of the game
   * @return Return total columns of the game
   */
  @Override
  public int getNumColumns() {
    return 8;
  }

  /** Return the name of the game
   * @return Return the name of the game
   */
  public String getGameName() {
    return "European Chess";
  }

  /** It will take the given board and set all pieces and then set internal fields and then it can begin
   * @param board The board that want to place
   */
  @Override
  public void startGame(ChessBoard board) {
    // add north side pawn
    PawnPiece P10 = new PawnPiece( ChessGame.Side.NORTH, 1,0, "NP" , board);
    board. addPiece( P10, P10.getRow(),P10.getColumn());
    PawnPiece P11 = new PawnPiece( ChessGame.Side.NORTH, 1,1, "NP" , board);
    board. addPiece( P11, P11.getRow(),P11.getColumn());
    PawnPiece P12 = new PawnPiece( ChessGame.Side.NORTH, 1,2, "NP" , board);
    board. addPiece( P12, P12.getRow(),P12.getColumn());
    PawnPiece P13 = new PawnPiece( ChessGame.Side.NORTH, 1,3, "NP" , board);
    board. addPiece( P13, P13.getRow(),P13.getColumn());
    PawnPiece P14 = new PawnPiece( ChessGame.Side.NORTH,1,4 , "NP" , board);
    board. addPiece( P14, P14.getRow(),P14.getColumn());
    PawnPiece P15 = new PawnPiece( ChessGame.Side.NORTH,1,5 , "NP" , board);
    board. addPiece( P15, P15.getRow(),P15.getColumn());
    PawnPiece P16 = new PawnPiece( ChessGame.Side.NORTH,1,6 , "NP" , board);
    board. addPiece( P16, P16.getRow(),P16.getColumn());
    PawnPiece P17 = new PawnPiece( ChessGame.Side.NORTH,1,7 , "NP" , board);
    board. addPiece( P17, P17.getRow(),P17.getColumn());
    // add south side pawn
    PawnPiece P60 = new PawnPiece( ChessGame.Side.SOUTH, 6,0, "SP" , board);
    board. addPiece( P60, P60.getRow(),P60.getColumn());
    PawnPiece P61 = new PawnPiece( ChessGame.Side.SOUTH, 6,1, "SP" , board);
    board. addPiece( P61, P61.getRow(),P61.getColumn());
    PawnPiece P62 = new PawnPiece( ChessGame.Side.SOUTH, 6,2, "SP" , board);
    board. addPiece( P62, P62.getRow(),P62.getColumn());
    PawnPiece P63 = new PawnPiece( ChessGame.Side.SOUTH, 6,3, "SP" , board);
    board. addPiece( P63, P63.getRow(),P63.getColumn());
    PawnPiece P64 = new PawnPiece( ChessGame.Side.SOUTH,6,4 , "SP" , board);
    board. addPiece( P64, P64.getRow(),P64.getColumn());
    PawnPiece P65 = new PawnPiece( ChessGame.Side.SOUTH,6,5 , "SP" , board);
    board. addPiece( P65, P65.getRow(),P65.getColumn());
    PawnPiece P66 = new PawnPiece( ChessGame.Side.SOUTH,6,6 , "SP" , board);
    board. addPiece( P66, P66.getRow(),P66.getColumn());
    PawnPiece P67 = new PawnPiece( ChessGame.Side.SOUTH,6,7 , "SP" , board);
    board. addPiece( P67, P67.getRow(),P67.getColumn());
    // add north side rook
    RookPiece R00 = new RookPiece( ChessGame.Side.NORTH, 0,0, "NR" , board);
    board. addPiece( R00, R00.getRow(),R00.getColumn());
    RookPiece R07 = new RookPiece( ChessGame.Side.NORTH, 0,7, "NR" , board);
    board. addPiece( R07, R07.getRow(),R07.getColumn());
    // add south side rook
    RookPiece R70 = new RookPiece( ChessGame.Side.SOUTH, 7,0, "SR" , board);
    board. addPiece( R70, R70.getRow(),R70.getColumn());
    RookPiece R77 = new RookPiece( ChessGame.Side.SOUTH, 7,7, "SR" , board);
    board. addPiece( R77, R77.getRow(),R77.getColumn());
    // add north side knight
    KnightPiece N01 = new KnightPiece( ChessGame.Side.NORTH, 0,1, "NN" , board);
    board. addPiece( N01, N01.getRow(),N01.getColumn());
    KnightPiece N06 = new KnightPiece( ChessGame.Side.NORTH, 0,6, "NN" , board);
    board. addPiece( N06, N06.getRow(),N06.getColumn());
    // add south side knight
    KnightPiece N71 = new KnightPiece( ChessGame.Side.SOUTH, 7,1, "SN" , board);
    board. addPiece( N71, N71.getRow(),N71.getColumn());
    KnightPiece N76 = new KnightPiece( ChessGame.Side.SOUTH, 7,6, "SN" , board);
    board. addPiece( N76, N76.getRow(),N76.getColumn());
    // add north side Bishop
    BishopPiece B02 = new BishopPiece( ChessGame.Side.NORTH, 0,2, "NB" , board);
    board. addPiece( B02, B02.getRow(),B02.getColumn());
    BishopPiece B05 = new BishopPiece( ChessGame.Side.NORTH, 0,5, "NB" , board);
    board. addPiece( B05, B05.getRow(),B05.getColumn());
    // add south side Bishop
    BishopPiece B72 = new BishopPiece( ChessGame.Side.SOUTH, 7,2, "SB" , board);
    board. addPiece( B72, B72.getRow(),B72.getColumn());
    BishopPiece B75 = new BishopPiece( ChessGame.Side.SOUTH, 7,5, "SB" , board);
    board. addPiece( B75, B75.getRow(),B75.getColumn());
    // add north side Queen
    QueenPiece Q03 = new QueenPiece( ChessGame.Side.NORTH, 0,3, "NQ" , board);
    board. addPiece( Q03, Q03.getRow(),Q03.getColumn());
    // add south side Queen
    QueenPiece Q73 = new QueenPiece( ChessGame.Side.SOUTH, 7,3, "SQ" , board);
    board. addPiece( Q73, Q73.getRow(),Q73.getColumn());
    // add north side King
    KingPiece K04 = new KingPiece( ChessGame.Side.NORTH, 0,4, "NK" , board);
    board. addPiece( K04, K04.getRow(),K04.getColumn());
    // add south side King
    KingPiece K74 = new KingPiece( ChessGame.Side.SOUTH, 7,4, "SK" , board);
    board. addPiece( K74, K74.getRow(),K74.getColumn());
  }
}