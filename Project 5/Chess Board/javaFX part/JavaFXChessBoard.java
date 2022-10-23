import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import java.util.*;


/**
 * <p>Creates a chessboard in a window on the desktop.  The ChessBoard has a ChessBoardDisplay object that determines
 * how the individual squares of the chessboard should be drawn.</p>
 *
 * <p>The chessboard uses a ChessGame object to determine how the game should be played.  The way the chessboard works
 * is as follows.  The player selects a piece by clicking on the board, and
 * and the chessboard calls the <tt>legalPieceToPlay</tt> method of the ChessGame object.
 * If the player is allowed to select the piece, the board highlights it, and the player can select another square on
 * the board.  The chessboard then calls the <tt>makeMove</tt> method of the ChessGame object.  The ChessGame is
 * responsible for determining if the move is valid, and if it is to update the game and the chessboard
 * with the results of making that move.</p>
 *
 * @author Harold Connamacher
 * @author Bruce Dong
 */
public class JavaFXChessBoard extends Application implements ChessBoard{
  // the squares of the board
  private static Button[][] squares;
  // global rules for this particular game
  private static ChessGame gameRules;
  // An array that stores each piece of the board
  private static  ChessPiece[][] pieces;
  // rules for how to draw the chess board
  private static JavaFXChessBoardDisplay display;


  /**
   * Changes the rules of the game
   * @param newRules the new rules for the game
   */
  @Override
  public void setGameRules(ChessGame newRules) {
    gameRules = newRules;
  }

  /**
   * Returns the rules of the game.
   * @return the rules of the game
   */
  @Override
  public ChessGame getGameRules() {
    return gameRules;
  }

  /**
   *  Adds a piece to the board at the desired location.  Any piece currently
   *  at that location is lost.
   *  @param piece   the piece to add
   *  @param row     the row for the piece
   *  @param col     the column for the piece
   */
  @Override
  public void addPiece(ChessPiece piece, int row, int col) {
    pieces[row][col] = piece;
    piece.setLocation(row, col);
    display.displayFilledSquare(squares[col][row],col,row,piece);
  }

  /**
   *  Removes a piece from the board
   *  @param row  the row of the piece
   *  @param col  the column of the piece
   *  @return  the piece removed of null if there was no piece at that square
   */
  @Override
  public ChessPiece removePiece(int row, int col) {
    ChessPiece save = pieces[row][col];
    pieces[row][col] = null;
    return save;
  }

  /**
   *  Returns true if there is a piece at a specific location of the board.
   *  @param row   the row to examine
   *  @param col   the column to examine
   *  @return   true if there is a piece a this row and column and false
   *            if the square is empty
   */
  @Override
  public boolean hasPiece(int row, int col) {
    return (pieces[row][col] != null);
  }

  /**
   *  Returns the chess piece at a specific location on the board.
   *  @param row   the row for the piece
   *  @param col   the column for the piece
   *  @return      the piece at the row and column or null if there is no piece there.
   */
  @Override
  public ChessPiece getPiece(int row, int col) {
    return pieces[row][col];
  }

  /**
   * Returns true if a particular square is threatened by an opposing piece.
   * @param row     the row of the square
   * @param column  the column of the square
   * @param piece   a piece of the game
   * @return  true if the square can be attacked by a piece of an opposing side as the parameter piece
   */
  @Override
  public boolean squareThreatened(int row, int column, ChessPiece piece) {
    for (int i = 0; i < pieces.length; i++) {
      for (int j = 0; j < pieces[i].length; j++) {
        if (hasPiece(i,j) && getPiece(i, j).getSide() != piece.getSide() &&
                getPiece(i, j).isLegalCaptureMove(row, column))
          return true;
      }
    }
    return false;
  }

  /**
   * Returns the number of rows in the board.
   * @return the number of rows
   */
  @Override
  public int getNumRows() {
    return pieces.length;
  }

  /**
   * Returns the number of columns in the board.
   * @return the number of columns
   */
  @Override
  public int getNumColumns() {
    return pieces[0].length;
  }

  /** The code the responds when the user clicks on the game board */
  private class ChessAction implements EventHandler<ActionEvent> {
    private boolean firstPick = true;  // if true, we a selecting a piece
    private int pieceRow;              // remember row of selected piece
    private int pieceCol;              // remember column of selected piece

    /**
     * What we do when the user chooses the piece to move.
     * @param row the row of the chosen piece
     * @param col the column of the chosen piece
     */
    private void processFirstSelection(int row, int col) {
      if ((pieces[col][row] != null) &&
              (getGameRules() == null || getGameRules().legalPieceToPlay(pieces[col][row], row, col))) {
        /*
         * if this is the first pick and a square with a piece was picked,
         * remember the piece's location and highlight the square.
         */
        pieceRow = row;
        pieceCol = col;
        display.highlightSquare(true, squares[row][col], row, col, pieces[col][row]);
        firstPick = false;
      }
    }

    /**
     * What we do when the user chooses the square to move the piece to.
     * @param row the row the piece will move to
     * @param col the column the piece will move to
     */
    private void processSecondSelection(int row, int col) {
      if (row == pieceRow && col == pieceCol)
        return;

      boolean moveMade = getGameRules().makeMove(pieces[pieceCol][pieceRow], col, row);

      // if the move was made or if it was not made and the user select a new piece, then reset to choose a new move
      if (moveMade || getGameRules().canChangeSelection(pieces[pieceCol][pieceRow], pieceRow, pieceCol)) {
        display.highlightSquare(false, squares[pieceRow][pieceCol], pieceRow, pieceCol, pieces[pieceCol][pieceRow]);
        firstPick = true;
      }
    }

    /**
     *  Handle a button click.  The method alternates between selecting a piece
     *  and selecting any square.  After both are selected, the piece's
     *  legalMove is called, and if the move is legal, the piece is moved.
     *  @param e   the event that triggered the method
     */
    public void handle(ActionEvent e) {
      Button b = (Button)e.getSource();
      int col = -1;
      int row = -1;

      // first find which button (board square) was clicked.
      for (int i = 0; i < squares.length; i++) {
        for (int j = 0; j < squares[i].length; j++) {
          if (squares[i][j].equals(b)) {
            row = i;
            col = j;
          }
        }
      }

      if (firstPick) {
        processFirstSelection(row, col);
      }
      else {
        processSecondSelection(row, col);
      }
    }
  }

  /**
   * Creates the JavaFX application with a grid pane
   * @param primaryStage the main window
   */
  @Override
  public void start(Stage primaryStage){
    List<String> list =  getParameters().getRaw();
    // The board that user want to place
    ChessBoard chessBoard;
    // The chess game that we want to play
    ChessAction handler = new ChessAction();
    //If user choose Xiang Qi
    if(list.get(0).equals("xiangqi")){
      gameRules = new Xiangqi();
      if(list.get(1).equals("JavaFX")) {
        display = new JavaFXXiangqiDisplay();
        squares = new Button[9][10];
        pieces = new ChessPiece[10][10];
        primaryStage.setTitle("Xiang Qi Game");
      }
      if(list.get(1).equals("Swing")) {
        SwingXiangqiBoardDisplay display = new SwingXiangqiBoardDisplay();
        chessBoard = new SwingChessBoard( 10,9, display, gameRules);
        gameRules.startGame(chessBoard);
        return;
      }
    }
    else{
      //Since User choose Chess
      gameRules = new EuropeanChess();
      // If User choose FX
      if(list.get(1).equals("JavaFX")) {
        display = new JavaFXEuropeanChessDisplay ();
        pieces = new ChessPiece[8][8];
        squares = new Button[8][8];
        primaryStage.setTitle("Chess Game");
      }
      // If User choose swing
      if(list.get(1).equals("Swing")) {
        SwingEuropeanChessDisplay display = new SwingEuropeanChessDisplay();
        chessBoard = new SwingChessBoard( 8,8, display, gameRules);
        gameRules.startGame(chessBoard);
        return;
      }
    }
    // the game board
    GridPane board = new GridPane();
    // add each button to display 
    for (int i = 0; i < squares.length; i++) {
      for (int j = 0; j < squares[1].length; j++) {
        squares[i][j] = new Button();
        display.displayEmptySquare(squares[i][j], i, j);
        squares[i][j].setPrefSize(100, 100);
        squares[i][j].setOnAction(handler);
        board.add(squares[i][j], i, j);
      }
    }
    board.setGridLinesVisible(true);
    Scene scene = new Scene(board, gameRules.getNumRows()*100, gameRules.getNumColumns()*100);
    primaryStage.setScene(scene);
    primaryStage.setResizable(true);
    primaryStage.show();
    gameRules.startGame(this);
  }

  /**
   * launch the game with wanted version of display
   * @param game the wanted game rule
   * @param display the way of wanted display
   */
  public static void play(String game, String display){
    Application.launch(game,display);
  }

}
