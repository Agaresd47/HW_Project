import org.junit.*;
import static org.junit.Assert.*;

/**
 * a tester of the ChessGame class
 *
 * @author Bruce Dong
 */

public class ChessGameTester {
  /**
   * Tests get game rule.
   */
  @Test
  public void testGetRules() {
    // create a chess game and display variable
    EuropeanChess ec = new EuropeanChess();
    SwingEuropeanChessDisplay ECD = new SwingEuropeanChessDisplay();
    // set up the chess board
    ChessBoard cb = new SwingChessBoard(7,7, ECD,ec);
    assertTrue("test that the name of the input one is the same as the output one",
            ec.getGameName().equals(cb.getGameRules().getGameName()));
  }

  /**
   * Tests get game rule.
   */
  @Test
  public void testPieceStuff() {
    //Initial the game 
    EuropeanChess EC = new EuropeanChess();
    SwingEuropeanChessDisplay ECD = new SwingEuropeanChessDisplay();
    ChessBoard CP = new SwingChessBoard( 8,8, ECD, EC);
    // create a chess game variable
    EuropeanChess ec = new EuropeanChess();
    // set up the chess board
    SwingChessBoard cb = new SwingChessBoard(7,7, ECD,ec);
    cb.addPiece(new KingPiece( ChessGame.Side.NORTH,1,5 , "NP" , CP),1,2);
    assertTrue("getPiece and setPiece function works", cb.getPiece(1,2).getLabel().equals("NP"));
    assertTrue("hasPiece function works", cb.hasPiece(1,2));
    cb.removePiece(1,2);
    assertFalse("hasPiece function works", cb.hasPiece(1,2));
    KingPiece kp = new KingPiece( ChessGame.Side.NORTH,1,2 , "NP" , CP);
    cb.addPiece(kp,1,2);
    cb.addPiece(new RookPiece( ChessGame.Side.SOUTH,2,2 , "NP" , CP),2,2);
    assertTrue("squareThreated is working", cb.squareThreatened(1,2,kp));
  }

  /**
   * Tests two added method of Europe chess and xiang qi
   */
  @Test
  public void testAdditionalMethods() {
    //Initialize two chess game
    Xiangqi xq = new Xiangqi();
    EuropeanChess ec = new EuropeanChess();
    assertEquals("The getter of row of Xiang Qi works correctly", 9, xq.getNumRows());
    assertEquals("The getter of row of EuropeanChess works correctly", 8, ec.getNumRows());
    assertEquals("The getter of column of Xiang Qi works correctly", 10, xq.getNumColumns());
    assertEquals("The getter of column of EuropeanChess works correctly", 8, ec.getNumColumns());
  }
}
