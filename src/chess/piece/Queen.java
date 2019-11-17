package chess.piece;

import java.util.HashMap;
import java.util.Map;

public class Queen extends Piece {
  private static final Map<Color, Character> symbols = new HashMap<Color, Character>(){{
    put(Color.WHITE, '♕');
    put(Color.BLACK, '♛');
  }};

  public Queen(Color color) {
    super(color, symbols);
  }

  @Override
  public boolean isValidMove(int x, int y, boolean isEnemyExisted) {
    if (!super.isValidMove(x, y, isEnemyExisted)) {
      return false;
    }

    if (Math.abs(x) == Math.abs(y) || x == 0 || y == 0) return true;

    return false;
  }
}
