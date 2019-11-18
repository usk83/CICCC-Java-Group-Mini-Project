package chess.piece;

import java.util.Map;

public abstract class Piece {
  private Color color;
  private char symbol;
  private boolean isEssential;
  protected int lastMovedTurn;

  public Piece(Color color, Map<Color, Character> symbols) {
    this(color, symbols, false);
  }

  public Piece(Color color, Map<Color, Character> symbols, boolean isEssential) {
    if (color == null) {
      throw new IllegalArgumentException("`color` must be specified.");
    }
    Character s = symbols.get(color);
    if (s == null) {
      throw new IllegalArgumentException("Corresponding symbol is not specified in `symbols`.");
    }

    this.color = color;
    symbol = s;
    this.isEssential = isEssential;
    lastMovedTurn = 0;
  }

  public abstract boolean isValidMove(int x, int y, boolean isEnemyExisted);

  public void recordMoved(int x, int y, int turn) {
    this.lastMovedTurn = turn;
  }

  public Color getColor() {
    return color;
  }

  public boolean isEssential() {
    return isEssential;
  }

  @Override
  public String toString() {
    return String.valueOf(symbol);
  }
}
