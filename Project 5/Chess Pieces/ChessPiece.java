/**
 * An abstract class that encodes specific rules for a version of pieces.
 *
 * @author Bruce Dong
 */
public abstract class ChessPiece{
  
  // This is the variable stores its side
  private final ChessGame.Side side ;
  // This is the variable stores its row
  private int row;
  // This is the variable stores its column
  private int col;
  // This is the variable stores its label
  private final String label;
  // This is the variable stores its board
  private final ChessBoard board;

  /**
   * Stores a chess piece
   * @param side the side of the piece
   * @param row  the number of rows at the chessboard
   * @param col  the number of columns at the chessboard
   * @param label  the liable of the piece
   * @param board  the board that its belong to
   */
  public ChessPiece ( ChessGame.Side side, int row, int col, String label,ChessBoard board){
    this.side = side;
    this.row = row;
    this.col = col;
    this.label = label;
    this.board = board;
  }

  /**
   * Returns the side of the piece.
   * @return the side of the piece
   */
  public ChessGame.Side getSide(){
    return side;
  }

  /**
   * Returns the label of the piece.
   * @return the label of the piece
   */
  public String getLabel(){
    return label ;
  }

  /**
   * Returns the icon of the piece.
   * @return the icon of the piece
   */
  public Object getIcon(){
    return null;
  }

  /**
   * set its column and row.
   * @param col  the number of columns at the chessboard
   * @param row  the label of the piece
   */
  public void setLocation(int row, int col){
    this.row = row;
    this.col = col;
  }

  /** Determines if it is legal to move the piece to its given location.
   * @param toRow     the row of the square the piece want to go
   * @param toColumn  the column of the square the piece want to go
   * @return true if the piece is allowed to move there
   */
  public abstract boolean isLegalMove(int toRow, int toColumn);

  /**
   * Returns the board of the piece.
   * @return the board of the piece
   */
  public ChessBoard getChessBoard(){
    return board;
  }

  /**
   * Returns the row of the piece.
   * @return the row of the piece
   */
  public int getRow(){
    return row;
  }

  /**
   * Returns the column of the piece.
   * @return the column of the piece
   */
  public int getColumn(){
    return col;
  }

  /** Determines if it is legal to move the piece to its given location without capture.
   * @param toRow     the row of the square the piece want to go
   * @param toColumn  the column of the square the piece want to go
   * @return true if the piece is allowed to move there without capture
   */
  public boolean isLegalNonCaptureMove(int toRow, int toColumn){
    return isLegalMove( toRow, toColumn);
  }

  /** Determines if it is legal to move the piece to its given location with capture.
   * @param toRow     the row of the square the piece want to go
   * @param toColumn  the column of the square the piece want to go
   * @return true if the piece is allowed to move there with capture
   */
  public boolean isLegalCaptureMove(int toRow, int toColumn){
    if( board.hasPiece(toRow, toColumn))
      if(board.getPiece(toRow, toColumn).getSide() != side)
        return isLegalMove(toRow, toColumn);
    return isLegalMove(toRow, toColumn);
  }

  /** Necessary operation after the piece can move to there
   * @param piece     the piece that is moved
   * @param toRow     the row of the square the piece want to go
   * @param toColumn  the column of the square the piece want to go
   */
  public void moveDone(ChessPiece piece, int toRow, int toColumn){
    board.removePiece( row, col);
    piece.setLocation(toRow, toColumn);
    board.addPiece( piece, toRow, toColumn);
  }

  /** Determines if it is legal to move the piece to its given location straightly.
   * @param rowT     the row of the square the piece want to go
   * @param colT  the column of the square the piece want to go
   * @return true if the piece is allowed to move there straightly
   */
  public boolean isLegalMoveStraight(int rowT, int colT){
    if( isLegalNotMove(rowT, colT))
      return false;
    // calculate the difference
    int rowD = rowT-row;
    int colD = colT -col;

    if( board.getPiece(rowT, colT) != null){
      if( board.getPiece(rowT, colT).getSide() == side)
        return false;
    }
    // In differnet move direction, check all space in between the start point and disetenation
    if( rowD == 0){
      if( colD < 0){
        for ( int index =-1; index > colD ; --index){
          if( getChessBoard().hasPiece(  row , col +index))
            return false;
        }
      }
      if( colD > 0){
        for ( int index =1; index < colD ; ++ index){
          if( getChessBoard().hasPiece(  row , col +index))
            return false;
        }
      }
      return true;
    }
    
    if( colD == 0){
      if( rowD < 0){
        for ( int index =-1; index > rowD ; -- index){
          if( getChessBoard().hasPiece(  row +index, col ))
            return false;
        }
      }
      if( rowD > 0){
        for ( int index =1; index < rowD ; ++ index){
          if( getChessBoard().hasPiece(  row +index, col ))
            return false;
        }
      }
      return true;
    }
    return false;
  }

  /** Determines if it is legal to move the piece to its given location diagonally.
   * @param rowT     the row of the square the piece want to go
   * @param colT  the column of the square the piece want to go
   * @return true if the piece is allowed to move there diagonally
   */
  public boolean isLegalMoveDiagonal(int rowT, int colT){
    if( isLegalNotMove(rowT, colT))
      return false;
    if( Math.abs(rowT -row) != Math.abs(colT -col))
      return false;

    if( board.getPiece(rowT, colT) != null){
      if( board.getPiece(rowT, colT).getSide() == side)
        return false;
    }
    
    // This judges when it want to move right and down
    if( rowT > row && colT >col){
      for ( int index =1; index < rowT-row ; ++index){
        if( getChessBoard().hasPiece(  row + index, col + index ))
          return false;
      }
      return true;
    }
    
    // This judges when it want to move left and down
    if( rowT > row && colT <col){
      for ( int index =1; index < rowT-row; ++index){
        if( getChessBoard().hasPiece(  row + index, col - index ))
          return false;
      }
      return true;
    }
    
    // This judges when it want to move right and up
    if( rowT < row && colT >col){
      for ( int index =1; index < row-rowT; ++index){
        if( getChessBoard().hasPiece(  row - index, col + index ))
          return false;
      }
      return true;
    }
    
    // This judges when it want to move left and up
    if( rowT < row && colT <col){
      for ( int index =1; index < row-rowT; ++index){
        if( getChessBoard().hasPiece(  row - index, col - index ))
          return false;
      }
      return true;
    }
    return false;
  }

  /** Determines if the space piece is empty.
   * @param toRow     the given row of the square
   * @param toColumn  the given column of the square
   * @return true if the space given piece is empty
   */
  public boolean isTargetEmpty(int toRow, int toColumn){
    return !board.hasPiece(toRow, toColumn);
  }

  /** Determines if the given space has a enemy.
   * @param toRow     the given row of the square
   * @param toColumn  the given column of the square
   * @return true if the piece given space has a enemy
   */
  public boolean isTargetEnemy(int toRow, int toColumn){
    return board.getPiece(toRow, toColumn).getSide() != side;
  }

  /** Determines if the given space between the will-castle-move king and rook is threaded/ empty.
   * @param rowK     the given row of the king
   * @param colK     the given column of the king
   * @param colR     the given column of the rook
   * @return true if the piece given space between the will-castle-move king and rook is threaded/ empty
   */
  public boolean pieceBetweenEmpty(int rowK, int colK, int colR){
    int colD = colK - colR;
    // If colD <0, rook is at the right side of the king
    if( colK < colR){
      for( int index =colK; index <= colR; ++index) {
        if (!isTargetEmpty(rowK, index))
          return false;
        if (board.squareThreatened(rowK, index, this))
          return false;
      }
    }
    // If colD >0, rook is at the left side of the king
    if( colD >0){
      for( int index =colK; index >= colR; --index) {
        if (!isTargetEmpty(rowK, index))
          return false;
        if (board.squareThreatened(rowK, index, this))
          return false;
      }
    }
    return true;
  }

  /** Determines if the piece is moved.
   * @return true if the piece is moved
   */
  public boolean moved(){
    return false;
  }

  /** Determines if the piece is moving to its original space.
   * @param toRow     the given row of the intended move place
   * @param toColumn  the given column of the intended move place
   * @return true if the piece is moving to its original space
   */
  public boolean isLegalNotMove(int toRow, int toColumn){
    return toRow == row && toColumn == col ;
  }

  /** Determines if the given space a king that can face the other king
   * @param toRow     the given row of the square
   * @param toColumn  the given column of the square
   * @param kingTime if there is a king, it should be 1, other wise 0
   * @return true if the given space a king that can face the other king
   */
  public boolean isKingFace(int toRow, int toColumn, int kingTime){
    int countPieces = 0;
    int toCol = col;
    // If it does not move away of its column, will not cause two king face
    // If the moving piece is king and non capture
    if( kingTime ==1){
      toCol = toColumn;
      countPieces = 2;
    }
    // If the moving piece is king and capture
    if( kingTime ==5){
      toCol = toColumn;
      countPieces = 1;
      kingTime =1;
    }
    if(toColumn == col)
      return false;
    else{
      // count total piece number
      for(int index =0; index < board.getNumRows(); ++index){
        if(board.getPiece(index, toCol) != null) {
          countPieces++;
          // count how many kings are there
          if(board.getPiece(index, toCol).amAKing()) {
            kingTime++;
          }
        }
      }
    }
    countPieces -= 1;
    // only two king are there, they will face each other.
    return kingTime == countPieces && kingTime == 2;
  }

  /** Determines if piece is a king
   * @return true if it is a king
   */
  public boolean amAKing(){
    return false;
  }
}
  
  
  