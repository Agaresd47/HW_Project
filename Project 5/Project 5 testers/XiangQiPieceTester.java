import org.junit.*;
import static org.junit.Assert.*;

/**
 * A tester for all xiang qi pieces.
 *
 * @author Bruce Dong
 */
public class XiangQiPieceTester {
  /**
   * Tests the king cannot face each other situation
   */
  @Test
  public void testKingFace() {
    Xiangqi EC = new Xiangqi();
    SwingXiangqiBoardDisplay ECD = new SwingXiangqiBoardDisplay();
    ChessBoard CP = new SwingChessBoard( 4,5, ECD, EC);
    // add pieces
    XiangqiKingPiece K00 = new XiangqiKingPiece( ChessGame.Side.SOUTH, 0, 0, "K00" , CP);
    CP. addPiece( K00, K00.getRow(),K00.getColumn());
    XiangqiKingPiece K02 = new XiangqiKingPiece( ChessGame.Side.SOUTH, 0, 2, "K02" , CP);
    CP. addPiece( K02, K02.getRow(),K02.getColumn());
    XiangqiKingPiece K03 = new XiangqiKingPiece( ChessGame.Side.SOUTH, 0, 3, "K03" , CP);
    CP. addPiece( K03, K03.getRow(),K03.getColumn());
    XiangqiKingPiece K04 = new XiangqiKingPiece( ChessGame.Side.SOUTH, 0, 4, "K04" , CP);
    CP. addPiece( K04, K04.getRow(),K04.getColumn());
    XiangqiKingPiece K30 = new XiangqiKingPiece( ChessGame.Side.NORTH, 3, 0, "K30" , CP);
    CP. addPiece( K30, K30.getRow(),K30.getColumn());
    XiangqiKingPiece K33 = new XiangqiKingPiece( ChessGame.Side.NORTH, 3, 3, "K33" , CP);
    CP. addPiece( K33, K33.getRow(),K33.getColumn());
    RookPiece R10 = new RookPiece( ChessGame.Side.SOUTH, 1, 0, "R10" , CP);
    CP. addPiece( R10, R10.getRow(),R10.getColumn());
    RookPiece R12 = new RookPiece( ChessGame.Side.SOUTH, 1, 2, "R12" , CP);
    CP. addPiece( R12, R12.getRow(),R12.getColumn());
    RookPiece R20 = new RookPiece( ChessGame.Side.SOUTH, 2, 0, "R20" , CP);
    CP. addPiece( R20, R20.getRow(),R20.getColumn());
    RookPiece R23 = new RookPiece( ChessGame.Side.SOUTH, 2, 3, "R23" , CP);
    CP. addPiece( R23, R23.getRow(),R23.getColumn());


    assertFalse("when other piece covers two king, one piece can move to other location",R10.isKingFace(1,1,0));
    assertFalse("when other piece covers two king, one piece can move to other location",R20.isKingFace(2,1,0));
    assertTrue(" when no other piece is in between, the only piece cannot move",R23.isKingFace(2,2,0));
    assertFalse("it tests that one side king can move to the other column when a king is there with cover",K33.isKingFace(3,2,1));
    assertTrue("  one side king cannot move to the other column when a king is there with no cover.",K33.isKingFace( 3,4,1));
    // add one additional piece
    RookPiece R34 = new RookPiece( ChessGame.Side.SOUTH, 3, 4, "R34" , CP);
    CP. addPiece( R34, R34.getRow(),R34.getColumn());
    assertTrue("  one side king cannot move to the other column when a king is there with no cover.",K33.isKingFace( 3,4,5));
  }

  /**
   * Tests the king piece function
   */
  @Test
  public void testKingPiece() {
    Xiangqi EC = new Xiangqi();
    SwingXiangqiBoardDisplay ECD = new SwingXiangqiBoardDisplay();
    ChessBoard CP = new SwingChessBoard( 4,5, ECD, EC);
    // add pieces
    XiangqiKingPiece k11 = new XiangqiKingPiece( ChessGame.Side.SOUTH, 1, 1, "k11" , CP);
    CP. addPiece( k11, k11.getRow(),k11.getColumn());
    assertTrue("it can move straight", k11.isLegalMove(1,0));
    assertTrue("it can move straight", k11.isLegalMove(0,1));
    assertTrue("it can move diagonally", k11.isLegalMove(0,0));
    assertTrue("it can move diagonally", k11.isLegalMove(0,2));
  }

  /**
   * Tests the guard piece function.
   */
  @Test
  public void testGuardPiece() {
    Xiangqi EC = new Xiangqi();
    SwingXiangqiBoardDisplay ECD = new SwingXiangqiBoardDisplay();
    SwingChessBoard board = new SwingChessBoard( 10,9, ECD, EC);
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
    // add south side Rook
    RookPiece R14 = new RookPiece( ChessGame.Side.SOUTH, 1,4, "SR" , board);
    board. addPiece( R14, R14.getRow(),R14.getColumn());
    // add south side Rook
    RookPiece R84 = new RookPiece( ChessGame.Side.SOUTH, 8,4, "SR" , board);
    board. addPiece( R84, R84.getRow(),R84.getColumn());

    assertTrue("guard can move diagonally", G03.isLegalMove(1,4));
    assertFalse("guard cannot move straight", G03.isLegalMove(0,4));
    assertTrue("guard can capture enemy piece", G03.isLegalMove(1,4));
    assertFalse("guard cannot capture enemy piece", G95.isLegalMove(8,4));
  }

  /**
   * Tests the elephant piece function.
   */
  @Test
  public void testElephantPiece() {
    Xiangqi EC = new Xiangqi();
    SwingXiangqiBoardDisplay ECD = new SwingXiangqiBoardDisplay();
    SwingChessBoard board = new SwingChessBoard( 10,6, ECD, EC);
    ElephantPiece E93 = new ElephantPiece( ChessGame.Side.SOUTH, 9,3, "SE" , board);
    board. addPiece( E93, E93.getRow(),E93.getColumn());
    ElephantPiece R84 = new ElephantPiece( ChessGame.Side.SOUTH, 8,4, "SR" , board);
    board. addPiece( R84, R84.getRow(),R84.getColumn());
    assertTrue("elephant can move diagonally", E93.isLegalMove(7,1));
    assertFalse("elephant cannot move other two square", E93.isLegalMove(7,2));
    assertFalse("elephant cannot move other two square", E93.isLegalMove(6,0));
    assertFalse("elephant cannot move when something is in the path", E93.isLegalMove(7,5));
  }

  /**
   * Tests the horse piece function.
   */
  @Test
  public void testHorsePiece() {
    Xiangqi EC = new Xiangqi();
    SwingXiangqiBoardDisplay ECD = new SwingXiangqiBoardDisplay();
    SwingChessBoard board = new SwingChessBoard( 6,3, ECD, EC);
    RookPiece R30 = new RookPiece( ChessGame.Side.SOUTH, 3,0, "R30" , board);
    board. addPiece( R30, R30.getRow(),R30.getColumn());
    RookPiece R41 = new RookPiece( ChessGame.Side.SOUTH, 4,1, "R41" , board);
    board. addPiece( R41, R41.getRow(),R41.getColumn());
    RookPiece R21 = new RookPiece( ChessGame.Side.SOUTH, 2,1, "R21" , board);
    board. addPiece( R21, R21.getRow(),R21.getColumn());
    RookPiece R32 = new RookPiece( ChessGame.Side.SOUTH, 3,2, "R32" , board);
    board. addPiece( R32, R32.getRow(),R32.getColumn());
    HorsePiece H31 = new HorsePiece( ChessGame.Side.SOUTH, 3,1, "H31" , board);
    board. addPiece( H31, H31.getRow(),H31.getColumn());
    assertFalse("Hourse cannot move to other location when something is next to it in the path", H31.isLegalMove(1,0));
    assertFalse("Hourse cannot move to other location when something is next to it in the path", H31.isLegalMove(1,2));
    assertFalse("Hourse cannot move to other location when something is next to it in the path", H31.isLegalMove(5,0));
    assertFalse("Hourse cannot move to other location when something is next to it in the path", H31.isLegalMove(5,2));
  }

  /**
   * Tests the cannon piece function.
   */
  @Test
  public void testCanonPiece() {
    Xiangqi EC = new Xiangqi();
    SwingXiangqiBoardDisplay ECD = new SwingXiangqiBoardDisplay();
    SwingChessBoard board = new SwingChessBoard( 8,3, ECD, EC);
    RookPiece R10 = new RookPiece( ChessGame.Side.NORTH, 1,0, "R10" , board);
    board. addPiece( R10, R10.getRow(),R10.getColumn());
    RookPiece R11 = new RookPiece( ChessGame.Side.NORTH, 1,1, "R11" , board);
    board. addPiece( R11, R11.getRow(),R11.getColumn());
    RookPiece R12 = new RookPiece( ChessGame.Side.NORTH, 1,2, "R12" , board);
    board. addPiece( R12, R12.getRow(),R12.getColumn());
    CannonPiece C50 = new CannonPiece( ChessGame.Side.SOUTH, 5,0, "C50" , board);
    board. addPiece( C50, C50.getRow(),C50.getColumn());
    CannonPiece C51 = new CannonPiece( ChessGame.Side.SOUTH, 5,1, "C51" , board);
    board. addPiece( C51, C51.getRow(),C51.getColumn());
    CannonPiece C52 = new CannonPiece( ChessGame.Side.SOUTH, 5,2, "C52" , board);
    board. addPiece( C52, C52.getRow(),C52.getColumn());
    RookPiece R21 = new RookPiece( ChessGame.Side.SOUTH, 2,1, "R21" , board);
    board. addPiece( R21, R21.getRow(),R21.getColumn());
    RookPiece R32 = new RookPiece( ChessGame.Side.NORTH, 3,2, "R32" , board);
    board. addPiece( R32, R32.getRow(),R32.getColumn());

    // test first
    assertFalse("cannon cannot capture when nothing is in between", C50.isLegalMove(1,0));
    assertTrue("cannon can capture when something is in between", C51.isLegalMove(1,1));
    assertTrue("cannon can capture when something is in between", C52.isLegalMove(1,2));

    // add one in between
    RookPiece R40 = new RookPiece( ChessGame.Side.NORTH, 4,0, "R40" , board);
    board. addPiece( R40, R40.getRow(),R40.getColumn());
    assertTrue("cannon can capture when something is in between", C50.isLegalMove(1,0));

    // add one more
    RookPiece R30 = new RookPiece( ChessGame.Side.NORTH, 3,0, "R30" , board);
    board. addPiece( R30, R30.getRow(),R30.getColumn());
    assertFalse("cannon cannot capture when more than one thing is in between", C50.isLegalMove(1,0));
    // test back
    CannonPiece C60 = new CannonPiece( ChessGame.Side.SOUTH, 6,0, "C60" , board);
    board. addPiece( C60, C60.getRow(),C60.getColumn());
    CannonPiece C70 = new CannonPiece( ChessGame.Side.NORTH, 7,0, "C70" , board);
    board. addPiece( C70, C70.getRow(),C70.getColumn());
    assertTrue("cannon can capture when something behind", C50.isLegalMove(7,0));
    // test right and left
    CannonPiece C61 = new CannonPiece( ChessGame.Side.SOUTH, 6,1, "C61" , board);
    board. addPiece( C61, C61.getRow(),C61.getColumn());
    CannonPiece C62 = new CannonPiece( ChessGame.Side.NORTH, 6,2, "C62" , board);
    board. addPiece( C62, C62.getRow(),C62.getColumn());
    assertTrue("cannon can capture on right", C60.isLegalMove(6,2));
    assertTrue("cannon can capture on left", C62.isLegalMove(6,0));
  }

  /**
   * Tests the soldier piece function.
   */
  @Test
  public void testSoldierPiece() {
    Xiangqi EC = new Xiangqi();
    SwingXiangqiBoardDisplay ECD = new SwingXiangqiBoardDisplay();
    SwingChessBoard board = new SwingChessBoard( 10,9, ECD, EC);
    SoldierPiece S30 = new SoldierPiece(ChessGame.Side.NORTH, 3,0,"S30",board);
    board. addPiece( S30, S30.getRow(),S30.getColumn());
    SoldierPiece S41 = new SoldierPiece(ChessGame.Side.NORTH, 4,1,"S41",board);
    board. addPiece( S41, S41.getRow(),S41.getColumn());
    SoldierPiece S60 = new SoldierPiece(ChessGame.Side.SOUTH, 6,0,"S60",board);
    board. addPiece( S60, S60.getRow(),S60.getColumn());
    SoldierPiece S51 = new SoldierPiece(ChessGame.Side.SOUTH, 5,1,"S51",board);
    board. addPiece( S51, S51.getRow(),S51.getColumn());
    // The two can go left or right
    SoldierPiece S52 = new SoldierPiece(ChessGame.Side.NORTH, 5,2,"S52",board);
    board. addPiece( S52, S52.getRow(),S52.getColumn());
    SoldierPiece S42 = new SoldierPiece(ChessGame.Side.SOUTH, 4,2,"S42",board);
    board. addPiece( S42, S42.getRow(),S42.getColumn());
    // Test
    assertFalse("South piece cannot go back ward", S60.isLegalMove(7,0));
    assertFalse("North piece cannot go back ward", S30.isLegalMove(2,0));
    assertFalse("South piece cannot go left right without cross the board", S60.isLegalMove(6,1));
    assertFalse("North piece cannot go left right without cross the board", S30.isLegalMove(3,1));
    assertTrue("South piece can go left right after cross the board", S52.isLegalMove(5,3));
    assertTrue("North piece can go left right after cross the board", S42.isLegalMove(4,3));
    assertFalse("South piece cannot go left right when they are on the board line", S51.isLegalMove(5,2));
    assertFalse("North piece cannot go left right when they are on the board line", S41.isLegalMove(4,2));
    assertFalse("piece cannot go more than one line", S52.isLegalMove(5,4));
    assertFalse("piece cannot go more than one line", S52.isLegalMove(5,0));
    assertTrue("South piece can straight", S51.isLegalMove(4,1));
    assertTrue("North piece can straight", S41.isLegalMove(5,1));
    assertTrue("South piece can go left right after cross the board", S52.isLegalMove(5,1));
    assertTrue("North piece can go left right after cross the board", S42.isLegalMove(4,1));
  }
}
