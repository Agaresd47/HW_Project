// This is the tester for the all Chess methods 
import org.junit.*;
import static org.junit.Assert.*;

/**
 * A class that tests all pieces and European Chess's regualr methods.
 */
public class EuropeanChessTester {
  /**
   * Tests getter methods.
   */
  @Test
  public void testGetter() {
    EuropeanChess EC = new EuropeanChess();
    EuropeanChessDisplay ECD = new EuropeanChessDisplay();
    ChessBoard CP = new ChessBoard( 8,8, ECD, EC);
    QueenPiece QPS = new QueenPiece( ChessGame.Side.SOUTH, 4,4, "QPS" , CP);
    // Test all the getter method 
    assertEquals("Testing get label correctly", "QPS", QPS.getLabel());
    assertEquals("Testing get side correctly", ChessGame.Side.SOUTH, QPS.getSide());
    assertEquals("Testing get chess board correctly", CP, QPS.getChessBoard());
  }

  /**
   * Tests the movedone methods.
   */
  @Test
  public void testMoveDOne() {
    EuropeanChess EC = new EuropeanChess();
    EuropeanChessDisplay ECD = new EuropeanChessDisplay();
    ChessBoard CP = new ChessBoard( 8,8, ECD, EC);
    QueenPiece QPS = new QueenPiece( ChessGame.Side.SOUTH, 4,4, "QPS" , CP);
    CP.addPiece( QPS, QPS.getRow(),QPS.getColumn());
    QPS.moveDone(QPS, 1,2);
    // Test the piece and board situation
    assertFalse("The old piece on board is removed", CP.hasPiece(4,4));
    assertEquals("The row is changed", 1, QPS.getRow());
    assertEquals("The column is changed", 2, QPS.getColumn());
    assertTrue("The new piece on board is added", CP.hasPiece(1,2));

  }

  /**
   * Tests the set location methods.
   */
  @Test
  public void testSetLocation() {
    EuropeanChess EC = new EuropeanChess();
    EuropeanChessDisplay ECD = new EuropeanChessDisplay();
    ChessBoard CP = new ChessBoard( 8,8, ECD, EC);
    QueenPiece QPS = new QueenPiece( ChessGame.Side.SOUTH, 1,3, "QPS" , CP);
    // Test getter methods
    assertEquals("Testing get row correctly ", 1, QPS.getRow());
    assertEquals("Testing get column correctly", 3, QPS.getColumn());
    QPS.setLocation(2,7);
    // After setting to a new location, verify the parameter is changed 
    assertEquals("Testing set location correctly (only verify row in this test)", 2, QPS.getRow());
    assertEquals("Testing set location correctly (only verify column in this test)", 7, QPS.getColumn());
  }

  /**
   * Tests all the rook methods.
   */
  @Test
  public void testRook() {
    EuropeanChess EC = new EuropeanChess();
    EuropeanChessDisplay ECD = new EuropeanChessDisplay();
    ChessBoard CP = new ChessBoard( 9,8, ECD, EC);
    // Add all the friendly piece into the board
    RookPiece R77 = new RookPiece( ChessGame.Side.SOUTH, 5, 5, "R77" , CP);
    CP. addPiece( R77, R77.getRow(),R77.getColumn());
    RookPiece R70 = new RookPiece( ChessGame.Side.SOUTH, 5, 6, "R70" , CP);
    CP. addPiece( R70, R70.getRow(),R70.getColumn());
    RookPiece R51 = new RookPiece( ChessGame.Side.SOUTH, 5, 1, "R51" , CP);
    CP. addPiece( R51, R51.getRow(),R51.getColumn());
    RookPiece R15 = new RookPiece( ChessGame.Side.SOUTH, 1, 5, "R15" , CP);
    CP. addPiece( R15, R15.getRow(),R15.getColumn());
    RookPiece R75 = new RookPiece( ChessGame.Side.SOUTH, 7, 5, "R75" , CP);
    CP. addPiece( R75, R75.getRow(),R75.getColumn());
    
    // All the isLegalMove Test
    assertTrue("Testing Rook can go straight one unit ",R77.isLegalMove( 6,5 ));
    assertTrue("Testing Rook can go straight one unit ",R77.isLegalMove( 4,5 ));
    assertTrue("Testing Rook can go straight one unit ",R77.isLegalMove( 5,4 ));
    assertTrue("Testing Rook can go straight many unit",R77.isLegalMove( 5,3 ));
    
    //Falie moves
    assertFalse("Testing no piece can move to its original position", R77.isLegalMove(7,7));
    assertFalse("Testing pawn cannot go straight many unit with obstacel", R77.isLegalMove( 8, 5 ));
    assertFalse("Testing pawn cannot go straight many unit with obstacel", R77.isLegalMove( 0,5  ));
    assertFalse("Testing pawn cannot go straight one unit with obstacel", R77.isLegalMove( 5, 7 ));
    assertFalse("Testing pawn cannot go straight many unit with obstacel", R77.isLegalMove( 5,0  ));
    assertFalse("Testing pawn cannot go diagnoal", R77.isLegalMove( 4, 3 ));
    assertFalse("Testing pawn cannot go diagnoal", R77.isLegalMove( 4, 4 ));
    
    // Test rook capture failed
    assertFalse("Testing pawn cannot capture friendly piece in many unit ", R77.isLegalNonCaptureMove( 5,1  ));
    assertFalse("Testing pawn cannot capture friendly piece in one unit ", R77.isLegalNonCaptureMove(5,6  ));
    
    // Add a new enemy to test rook capture it
    RookPiece R07 = new RookPiece( ChessGame.Side.NORTH, 5,2, "R07" , CP);
    CP. addPiece( R07, R07.getRow(),R07.getColumn());
    assertTrue("Testing Rook can go straight one unit unit",R77.isLegalCaptureMove ( 5,2 ));
    RookPiece R00 = new RookPiece( ChessGame.Side.NORTH, 4,5, "R00" , CP);
    CP. addPiece( R00, R00.getRow(),R00.getColumn());
    assertTrue("Testing Rook can go straight one unit unit",R77.isLegalCaptureMove ( 4,5 ));
    
    // Test output of isLegalNonCaptureMove  is the same as isLegalMove
    assertEquals(R77.isLegalNonCaptureMove( 6,5 ),R77.isLegalMove( 6,5 ));
    assertEquals(R77.isLegalNonCaptureMove( 4,5 ),R77.isLegalMove( 4,5 ));

    // Verify Rook cannot capture strange place enemy
    RookPiece R33 = new RookPiece( ChessGame.Side.NORTH, 3,3, "R33" , CP);
    CP. addPiece( R33, R33.getRow(),R33.getColumn());
    assertFalse("Testing Rook can go straight one unit unit",R77.isLegalCaptureMove ( 3,3 ));
  }

  /**
   * Tests all the bishop and queen methods.
   */
  @Test
  public void testBishopQueen() {
    EuropeanChess EC = new EuropeanChess();
    EuropeanChessDisplay ECD = new EuropeanChessDisplay();
    ChessBoard CP = new ChessBoard( 9,9, ECD, EC);
    // Add all the friendly piece into the board
    BishopPiece B55 = new BishopPiece( ChessGame.Side.SOUTH, 5 ,5  , "B55" , CP);
    CP. addPiece( B55, B55.getRow(),B55.getColumn());
    BishopPiece B11 = new BishopPiece( ChessGame.Side.SOUTH, 1,1  , "B11" , CP);
    CP. addPiece( B11, B11.getRow(),B11.getColumn());
    BishopPiece B66 = new BishopPiece( ChessGame.Side.SOUTH, 6,6  , "B66" , CP);
    CP. addPiece( B66, B66.getRow(),B66.getColumn());
    BishopPiece B37 = new BishopPiece( ChessGame.Side.SOUTH, 3,7  , "B37" , CP);
    CP. addPiece( B37, B37.getRow(),B37.getColumn());
    BishopPiece B73 = new BishopPiece( ChessGame.Side.SOUTH, 7,3  , "B73" , CP);
    CP. addPiece( B73, B73.getRow(),B73.getColumn());
    QueenPiece Q60 = new QueenPiece( ChessGame.Side.SOUTH,6,0  , "Q60" , CP);
    CP. addPiece( Q60, Q60.getRow(),Q60.getColumn());
    
    // All the isLegalMove Test
    assertTrue("Testing Bishop can go diagnoal  one unit ",B55.isLegalMove( 4,4 ));
    assertTrue("Testing Bishop can go diagnoal  one unit ",B55.isLegalMove( 4,6 ));
    assertTrue("Testing Bishop can go diagnoal  one unit ",B55.isLegalMove( 6,4 ));
    assertTrue("Testing Queen can go diagnoal one unit ",Q60.isLegalMove( 5,1 ));
    assertTrue("Testing Queen can go straight one unit ",Q60.isLegalMove( 5,0 ));
    assertTrue("Testing Bishop can go diagnoal  many unit ",B55.isLegalMove( 3,3 ));
    assertTrue("Testing Bishop can go straight  many unit ",Q60.isLegalMove( 4,0 ));
    assertTrue("Testing Queen can go diagnoal  many unit ",Q60.isLegalMove( 4,2 ));
    
    //Falie moves
    assertFalse("Testing Bishop cannot go straight", B55.isLegalMove( 4, 5 ));
    assertFalse("Testing Bishop cannot other strange direction", B55.isLegalMove( 3,6));
    assertFalse("Testing Bishop cannot go diagnoal one unit with obstacel", B55.isLegalMove( 7, 7 ));
    assertFalse("Testing Bishop cannot go diagnoal many unit with obstacel", B55.isLegalMove( 8,3  ));
    
     // Test Bishop capture failed
    assertFalse("Testing Bishop cannot capture friendly piece in many unit ", B55.isLegalNonCaptureMove( 7,3 ));
    assertFalse("Testing Bishop cannot capture friendly piece in one unit ", B55.isLegalNonCaptureMove(6,6  ));
    
    // Add a new enemy to test Bishop capture it
    RookPiece B22 = new RookPiece( ChessGame.Side.NORTH, 2,2, "B22" , CP);
    CP. addPiece( B22, B22.getRow(),B22.getColumn());
    assertTrue("Testing Rook can go straight one unit unit",B55.isLegalCaptureMove ( 2,2 ));
    RookPiece B46 = new RookPiece( ChessGame.Side.NORTH, 4,6, "B46" , CP);
    CP. addPiece( B46, B46.getRow(),B46.getColumn());
    assertTrue("Testing Rook can go straight one unit unit",B55.isLegalCaptureMove ( 4,6 ));

    // Verify Bishop cannot capture strange place enemy
    RookPiece B25 = new RookPiece( ChessGame.Side.NORTH, 2,5, "B25" , CP);
    CP. addPiece( B25, B25.getRow(),B25.getColumn());
    assertFalse("Testing Rook can go straight one unit unit",B55.isLegalCaptureMove ( 2,5 ));
  }

  /**
   * Tests all the king and knight methods.
   */
  @Test
  public void testKnightKing() {
    EuropeanChess EC = new EuropeanChess();
    EuropeanChessDisplay ECD = new EuropeanChessDisplay();
    ChessBoard CP = new ChessBoard( 9,5, ECD, EC);
    // Knight Part
    // Add all  pieces into the board
    KnightPiece N72 = new KnightPiece( ChessGame.Side.SOUTH,7,2, "N72" , CP);
    CP. addPiece( N72, N72.getRow(),N72.getColumn());
    KnightPiece N71 = new KnightPiece( ChessGame.Side.SOUTH,7,1, "N71" , CP);
    CP. addPiece( N71, N71.getRow(),N71.getColumn());
    KnightPiece N61 = new KnightPiece( ChessGame.Side.SOUTH,6,1, "N61" , CP);
    CP. addPiece( N61, N61.getRow(),N61.getColumn());
    KnightPiece N62 = new KnightPiece( ChessGame.Side.SOUTH,6,2, "N62" , CP);
    CP. addPiece( N62, N62.getRow(),N62.getColumn());
    KnightPiece N54 = new KnightPiece( ChessGame.Side.SOUTH,5,4, "N54" , CP);
    CP. addPiece( N54, N54.getRow(),N54.getColumn());
    KnightPiece N80 = new KnightPiece( ChessGame.Side.NORTH,8,0, "N80" , CP);
    CP. addPiece( N80, N80.getRow(),N80.getColumn());
    KnightPiece N83 = new KnightPiece( ChessGame.Side.NORTH,8,3, "N83" , CP);
    CP. addPiece( N83, N83.getRow(),N83.getColumn());

    //Verify that Knight can jump L shape no matter how many obstacle are on the path
    assertTrue("Testing pawn Knight can jump L shape no matter how many obstacle are on the path", N72.isLegalMove( 8,4));
    assertTrue("Testing pawn Knight can jump L shape no matter how many obstacle are on the path", N72.isLegalMove(6,0 ));
    assertTrue("Testing pawn Knight can jump L shape no matter how many obstacle are on the path", N72.isLegalMove(5,1 ));
    assertTrue("Testing pawn Knight can jump L shape no matter how many obstacle are on the path", N72.isLegalMove(5,3 ));
    assertTrue("Testing pawn Knight can jump L shape no matter how many obstacle are on the path", N72.isLegalMove(6,4 ));

    // Verify knight cannot move to strange place
    assertFalse("Verify knight cannot move to strange place", N72.isLegalMove(7,0));
    assertFalse("Verify knight cannot move to strange place", N72.isLegalMove(6,3));

    // add extre piece
    KnightPiece N64 = new KnightPiece( ChessGame.Side.SOUTH,6,4, "N64" , CP);
    CP. addPiece( N64, N64.getRow(),N64.getColumn());

    // Verify Knight can/cannot capture friendly/enemy pieces
    assertTrue("Verify Knight can capture enemy piece",N72.isLegalCaptureMove ( 8,0 ));
    assertFalse("Verify Knight cannot capture enemy piece in strange position",N72.isLegalCaptureMove ( 8,3 ));
    assertFalse("Verify Knight cannot capture friendly piece in strange position",N72.isLegalCaptureMove ( 5,4  ));
    assertFalse("Verify Knight cannot capture friendly piece in fine position",N72.isLegalCaptureMove ( 6,4 ));

    // Test King part
    // Add all piece
    KingPiece K11 = new KingPiece( ChessGame.Side.SOUTH,1,1, "K11" , CP);
    CP. addPiece( K11, K11.getRow(),K11.getColumn());
    KingPiece K74 = new KingPiece( ChessGame.Side.SOUTH,1,0, "K74" , CP);
    CP. addPiece( K74, K74.getRow(),K74.getColumn());
    KingPiece K04 = new KingPiece( ChessGame.Side.NORTH,2,2, "K04" , CP);
    CP. addPiece( K04, K04.getRow(),K04.getColumn());

    // Verify King can move all direction
    assertTrue("Verify King can move Straight", K11.isLegalMove(1,2 ));
    assertTrue("Verify King can move Straight", K11.isLegalMove(0,1));
    assertTrue("Verify King can move diagnoal", K11.isLegalMove(0,2 ));
    assertTrue("Verify King can move diagnoal", K11.isLegalMove( 0,0));
    assertTrue("Verify King can move diagnoal", K11.isLegalMove(2,0 ));

    // Verify King cannot move to strange place
    assertFalse("Verify King cannot move to strange place", K11.isLegalMove(3,1 ));
    assertFalse("Verify King cannot move to strange place", K11.isLegalMove(3,4));

    // Verify King can/cannot capture friendly/enemy pieces
    assertTrue("Verify King can capture enemy piece",K11.isLegalCaptureMove ( 2,2 ));
    assertFalse("Verify King cannot capture enemy piece in strange position",K11.isLegalCaptureMove ( 2,3));
    assertFalse("Verify King cannot capture friendly piece",K11.isLegalCaptureMove ( 1,0));
  }

  /** Resets the locaiton and its inner move time for test castle move.
   * @param king1 the first given king
   * @param king2 the second given king
   * @param rook1 the first given rook
   * @param rook2 the second given rook
   * @param rook3 the third given rook
   * @param rook4 the fourth given rook
   */
  public void resetMove(KingPiece king1, KingPiece king2, RookPiece rook1, RookPiece rook2,RookPiece rook3,RookPiece rook4){
    king1.resetMoved();
    rook1.resetMoved();
    king2.resetMoved();
    rook2.resetMoved();
    rook3.resetMoved();
    rook4.resetMoved();
    king1.moveDone(king1,7,4 );
    rook1.moveDone(rook1, 7,7);
    king2.moveDone(king2, 0,4  );
    rook2.moveDone(rook2,7,0 );
    rook3.moveDone(rook3, 0,7);
    rook4.moveDone(rook4, 0,0);
  }

  /**
   * Tests all the castle move method.
   */
  @Test
  public void testCastleMove() {
    EuropeanChess EC = new EuropeanChess();
    EuropeanChessDisplay ECD = new EuropeanChessDisplay();
    ChessBoard CP = new ChessBoard( 8,8, ECD, EC);
    // Add all rook and king
    //South King
    KingPiece K74 = new KingPiece( ChessGame.Side.SOUTH,7,4, "K74" , CP);
    CP. addPiece( K74, K74.getRow(),K74.getColumn());
    //NorthKing
    KingPiece K04 = new KingPiece( ChessGame.Side.NORTH,0,4, "K04" , CP);
    CP. addPiece( K04, K04.getRow(),K04.getColumn());
    //South right rook
    RookPiece R77 = new RookPiece( ChessGame.Side.SOUTH, 7,7, "R77" , CP);
    CP. addPiece( R77, R77.getRow(),R77.getColumn());
    //South right rook
    RookPiece R70 = new RookPiece( ChessGame.Side.SOUTH, 7,0, "R70" , CP);
    CP. addPiece( R70, R70.getRow(),R70.getColumn());
    // North right rook
    RookPiece R07 = new RookPiece( ChessGame.Side.NORTH, 0,7, "R07" , CP);
    CP. addPiece( R07, R07.getRow(),R07.getColumn());
    // North left rook
    RookPiece R00 = new RookPiece( ChessGame.Side.NORTH, 0,0, "R00" , CP);
    CP. addPiece( R00, R00.getRow(),R00.getColumn());

    // Castle Move without interuption
    assertTrue("Verify South King can move right", K74.isLegalMove(7,6));
    this.resetMove(K74, K04, R77, R70, R07,R00 );
    assertTrue("Verify South King can move left", K74.isLegalMove(7,2 ));
    resetMove(K74, K04, R77, R70, R07,R00 );
    assertTrue("Verify North King can move right", K04.isLegalMove(0,6));
    resetMove(K74, K04, R77, R70, R07,R00 );
    assertTrue("Verify North King can move left", K04.isLegalMove(0,2 ));
    resetMove(K74, K04, R77, R70, R07,R00 );

    //add first and second piece of threated enemy and test moving
    RookPiece R55 = new RookPiece( ChessGame.Side.NORTH, 5,5, "R55" , CP);
    CP. addPiece( R55, R55.getRow(),R55.getColumn());
    assertFalse("Verify South King can move with one threaten", K74.isLegalMove(7,6 ));
    resetMove(K74, K04, R77, R70, R07,R00 );
    RookPiece R56 = new RookPiece( ChessGame.Side.NORTH, 5,6, "R56" , CP);
    CP. addPiece( R56, R56.getRow(),R56.getColumn());
    assertFalse("Verify South King can move with many threaten", K74.isLegalMove(7,6 ));
    resetMove(K74, K04, R77, R70, R07,R00 );

    //add first and second piece of obstacle enemy and test method
    RookPiece R01 = new RookPiece( ChessGame.Side.NORTH, 0,1, "R01" , CP);
    CP. addPiece( R01, R01.getRow(),R01.getColumn());
    assertFalse("Verify North King can move with one obstacle", K04.isLegalMove(0,2 ));
    resetMove(K74, K04, R77, R70, R07,R00 );
    RookPiece R03 = new RookPiece( ChessGame.Side.NORTH, 0,3, "R03" , CP);
    CP. addPiece( R03, R03.getRow(),R03.getColumn());
    assertFalse("Verify North King can move with many obstacle", K04.isLegalMove(0,2 ));
    resetMove(K74, K04, R77, R70, R07,R00 );
  }

  /**
   * Tests all the pawn methods.
   */
  @Test
  public void testPawn() {
    EuropeanChess EC = new EuropeanChess();
    EuropeanChessDisplay ECD = new EuropeanChessDisplay();
    ChessBoard CP = new ChessBoard( 8,7, ECD, EC);
    //Add all the friendly piece
    PawnPiece P60 = new PawnPiece( ChessGame.Side.SOUTH, 6,0, "P60" , CP);
    CP. addPiece( P60, P60.getRow(),P60.getColumn());
    PawnPiece P61 = new PawnPiece( ChessGame.Side.SOUTH,6,1 , "P61" , CP);
    CP. addPiece( P61, P61.getRow(),P61.getColumn());
    PawnPiece P64 = new PawnPiece( ChessGame.Side.SOUTH,6,4 , "P64" , CP);
    CP. addPiece( P64, P64.getRow(),P64.getColumn());
    PawnPiece P10 = new PawnPiece( ChessGame.Side.SOUTH,1,0 , "P10" , CP);
    CP. addPiece( P10, P10.getRow(),P10.getColumn());
    PawnPiece P11 = new PawnPiece( ChessGame.Side.SOUTH, 1,1, "P11" , CP);
    CP. addPiece( P11, P11.getRow(),P11.getColumn());
    PawnPiece P12 = new PawnPiece( ChessGame.Side.SOUTH,1,2 , "P12" , CP);
    CP. addPiece( P12, P12.getRow(),P12.getColumn());
    PawnPiece P13 = new PawnPiece( ChessGame.Side.SOUTH, 1,3, "P13" , CP);
    CP. addPiece( P13, P13.getRow(),P13.getColumn());
    PawnPiece P25 = new PawnPiece( ChessGame.Side.SOUTH, 2,5, "P25" , CP);
    CP. addPiece( P25, P25.getRow(),P25.getColumn());
    PawnPiece P55 = new PawnPiece( ChessGame.Side.SOUTH, 5,5, "P55" , CP);
    CP. addPiece( P55, P55.getRow(),P55.getColumn());
    PawnPiece P51 = new PawnPiece( ChessGame.Side.SOUTH, 5,1, "P51" , CP);
    CP. addPiece( P51, P51.getRow(),P51.getColumn());

    // Test it can move one/two spots at first and forbiddened after awards
    assertTrue("Pawn can move forward two spot at start",P60.isLegalMove ( 4,0 ));
    assertTrue("Pawn can move forward one spot ",P60.isLegalMove ( 5,0 ));
    assertTrue("Pawn can move forward one spot ",P60.isLegalMove ( 5,0 ));
    assertFalse("Pawn cannot move forward when something is in fornt ",P61.isLegalMove ( 5,1 ));
    assertFalse("Pawn cannot move other direction ",P64.isLegalMove ( 7,4 ));
    assertFalse("Pawn can move other direction ",P64.isLegalMove ( 6,5 ));

    // add all enemy pieces.
    PawnPiece P63 = new PawnPiece( ChessGame.Side.NORTH, 6,3, "P63" , CP);
    CP. addPiece( P63, P63.getRow(),P63.getColumn());
    PawnPiece P53 = new PawnPiece( ChessGame.Side.NORTH, 5,3, "P53" , CP);
    CP. addPiece( P53, P53.getRow(),P53.getColumn());
    PawnPiece P44 = new PawnPiece( ChessGame.Side.NORTH, 4,4, "P44" , CP);
    CP. addPiece( P44, P44.getRow(),P44.getColumn());

    // Testing capture
    assertTrue("Pawn capture enemy piece on diagnoally in one spot",P64.isLegalMove ( 5,3 ));
    assertFalse("Pawn cannot capture enemy piece on other spot",P64.isLegalMove ( 6,3 ));
    assertFalse("Pawn cannot capture enemy piece on other spot",P64.isLegalMove ( 4,4 ));
    assertFalse("Pawn cannot capture friend piece",P64.isLegalMove ( 5,5 ));
  }




}