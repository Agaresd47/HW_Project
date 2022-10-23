/**
 * components of a compund job file
 *
 * @author Bruce Dong
 */
public class Xiangqi implements ChessGame{
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
  public  boolean canChangeSelection(ChessPiece piece, int row, int column) {
    return true;
  }

  /** Return total row of the game
   * @return Return total row of the game
   */
  @Override
  public int getNumRows() {
    return 9;
  }

  /** Return total columns of the game
   * @return Return total columns of the game
   */
  @Override
  public int getNumColumns() {
    return 10;
  }

  /** Return the name of the game
   * @return Return the name of the game
   */
  public String getGameName() {
    return "Xiang Qi";
  }

  /** It will take the given board and set all pieces and then set internal fields and then it can begin
   * @param board The board that want to place
   */
  @Override
  public void startGame(ChessBoard board) {
    // add north side soldier
    SoldierPiece P30 = new SoldierPiece( ChessGame.Side.NORTH, 3,0, "NS" , board);
    board. addPiece( P30, P30.getRow(),P30.getColumn());
    SoldierPiece P32 = new SoldierPiece( ChessGame.Side.NORTH, 3,2, "NS" , board);
    board. addPiece( P32, P32.getRow(),P32.getColumn());
    SoldierPiece P34 = new SoldierPiece( ChessGame.Side.NORTH, 3,4, "NS" , board);
    board. addPiece( P34, P34.getRow(),P34.getColumn());
    SoldierPiece P36 = new SoldierPiece( ChessGame.Side.NORTH, 3,6, "NS" , board);
    board. addPiece( P36, P36.getRow(),P36.getColumn());
    SoldierPiece P38 = new SoldierPiece( ChessGame.Side.NORTH, 3,8, "NS" , board);
    board. addPiece( P38, P38.getRow(),P38.getColumn());
    // add south side soldier
    SoldierPiece P60 = new SoldierPiece( ChessGame.Side.SOUTH, 6,0, "SS" , board);
    board. addPiece( P60, P60.getRow(),P60.getColumn());
    SoldierPiece P62 = new SoldierPiece( ChessGame.Side.SOUTH, 6,2, "SS" , board);
    board. addPiece( P62, P62.getRow(),P62.getColumn());
    SoldierPiece P64 = new SoldierPiece( ChessGame.Side.SOUTH, 6,4, "SS" , board);
    board. addPiece( P64, P64.getRow(),P64.getColumn());
    SoldierPiece P66 = new SoldierPiece( ChessGame.Side.SOUTH, 6,6, "SS" , board);
    board. addPiece( P66, P66.getRow(),P66.getColumn());
    SoldierPiece P68 = new SoldierPiece( ChessGame.Side.SOUTH, 6,8, "SS" , board);
    board. addPiece( P68, P68.getRow(),P68.getColumn());

    // add north side rook
    RookPiece R00 = new RookPiece( ChessGame.Side.NORTH, 0,0, "NR" , board);
    board. addPiece( R00, R00.getRow(),R00.getColumn());
    RookPiece R08 = new RookPiece( ChessGame.Side.NORTH, 0,8, "NR" , board);
    board. addPiece( R08, R08.getRow(),R08.getColumn());
    // add south side rook
    RookPiece R90 = new RookPiece( ChessGame.Side.SOUTH, 9,0, "SR" , board);
    board. addPiece( R90, R90.getRow(),R90.getColumn());
    RookPiece R98 = new RookPiece( ChessGame.Side.SOUTH, 9,8, "SR" , board);
    board. addPiece( R98, R98.getRow(),R98.getColumn());

    // add north side horse
    HorsePiece N01 = new HorsePiece( ChessGame.Side.NORTH, 0,1, "NH" , board);
    board. addPiece( N01, N01.getRow(),N01.getColumn());
    HorsePiece N07 = new HorsePiece( ChessGame.Side.NORTH, 0,7, "NH" , board);
    board. addPiece( N07, N07.getRow(),N07.getColumn());
    // add south side horse
    HorsePiece N91 = new HorsePiece( ChessGame.Side.SOUTH, 9,1, "SH" , board);
    board. addPiece( N91, N91.getRow(),N91.getColumn());
    HorsePiece N97 = new HorsePiece( ChessGame.Side.SOUTH, 9,7, "SH" , board);
    board. addPiece( N97, N97.getRow(),N97.getColumn());

    // add north side Elephant
    ElephantPiece E02 = new ElephantPiece( ChessGame.Side.NORTH, 0,2, "NE" , board);
    board. addPiece( E02, E02.getRow(),E02.getColumn());
    ElephantPiece E06 = new ElephantPiece( ChessGame.Side.NORTH, 0,6, "NE" , board);
    board. addPiece( E06, E06.getRow(),E06.getColumn());
    // add south side Elephant
    ElephantPiece E92 = new ElephantPiece( ChessGame.Side.SOUTH, 9,2, "SE" , board);
    board. addPiece( E92, E92.getRow(),E92.getColumn());
    ElephantPiece E96 = new ElephantPiece( ChessGame.Side.SOUTH, 9,6, "SE" , board);
    board. addPiece( E96, E96.getRow(),E96.getColumn());

    // add north side King
    XiangqiKingPiece K04 = new XiangqiKingPiece( ChessGame.Side.NORTH, 0,4, "NK" , board);
    board. addPiece( K04, K04.getRow(),K04.getColumn());
    // add south side King
    XiangqiKingPiece K94 = new XiangqiKingPiece( ChessGame.Side.SOUTH, 9,4, "SK" , board);
    board. addPiece( K94, K94.getRow(),K94.getColumn());

    // add north side Guard
    GuardPiece G03 = new GuardPiece( ChessGame.Side.NORTH, 0,3, "NG" , board);
    board. addPiece( G03, G03.getRow(),G03.getColumn());
    GuardPiece G05 = new GuardPiece( ChessGame.Side.NORTH, 0,5, "NG" , board);
    board. addPiece( G05, G05.getRow(),G05.getColumn());
    // add south side Guard
    GuardPiece G93 = new GuardPiece( ChessGame.Side.SOUTH, 9,3, "SG" , board);
    board. addPiece( G93, G93.getRow(),G93.getColumn());
    GuardPiece G95 = new GuardPiece( ChessGame.Side.SOUTH, 9,5, "SG" , board);
    board. addPiece( G95, G95.getRow(),G95.getColumn());

    // add north side Cannon
    CannonPiece C21 = new CannonPiece( ChessGame.Side.NORTH, 2,1, "NC" , board);
    board. addPiece( C21, C21.getRow(),C21.getColumn());
    CannonPiece C27 = new CannonPiece( ChessGame.Side.NORTH, 2,7, "NC" , board);
    board. addPiece( C27, C27.getRow(),C27.getColumn());
    // add south side Cannon
    CannonPiece C71 = new CannonPiece( ChessGame.Side.SOUTH, 7,1, "SC" , board);
    board. addPiece( C71, C71.getRow(),C71.getColumn());
    CannonPiece C77 = new CannonPiece( ChessGame.Side.SOUTH, 7,7, "SC" , board);
    board. addPiece( C77, C77.getRow(),C77.getColumn());
  }
}
