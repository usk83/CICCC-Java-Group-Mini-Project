package chess;

import chess.piece.*;

public class Game {
  private static final Color PLAY_FIRST = Color.WHITE;

  private Board board;
  private Color turn;

  public Game() {
    board = new Board();
    turn = PLAY_FIRST;
  }

  public void play() {
    /*
     * ToDo: implement below
     */
    System.out.println(""
        + "  ___  _  _  ____  ____  ____     ___   __   _  _  ____  _\n"
        + " / __)/ )( \\(  __)/ ___)/ ___)   / __) / _\\ ( \\/ )(  __)/ \\\n"
        + "( (__ ) __ ( ) _) \\___ \\\\___ \\  ( (_ \\/    \\/ \\/ \\ ) _) \\_/\n"
        + " \\___)\\_)(_/(____)(____/(____/   \\___/\\_/\\_/\\_)(_/(____)(_)\n"
        + "\n");
    boolean isGameOnGoing = true;
    int whitewons = 0;
    int blackwons = 0;
    while (isGameOnGoing) {
      System.out.println("Turn of : " + turn);
      String userInput = InputController.getUserInput("Enter UCI (type 'help' for help): ");
      switch (Command.parse(userInput)) {
        case HELP:
          System.out.println("* type 'help' for help \n " +
              "* type 'board' to see the board again \n " +
              "* type 'resign' to resign \n " +
              "* type 'moves' to list all possible moves \n " +
              "* type a square (e.g. b1, e2) to list possible moves for that square \n " +
              "* type a UCI (e.g. b1c3, e7e8q) to make a move");
          break;
        case BOARD:
          System.out.println(board);
          break;
        case RESIGN:
          if(turn == Color.WHITE) {
            blackwons ++;
            System.out.println("GAME OVER " + whitewons + " - " +  blackwons + " BLACK WON BY RESIGNATION");
          }
          if(turn == Color.BLACK) {
            whitewons ++;
            System.out.println("GAME OVER " + whitewons + " - " +  blackwons + " WHITE WON BY RESIGNATION");
          }
          isGameOnGoing = false;
          break;
        case GO_MOVE:
          int[] moveTo = new int[4];
          char promotionPiece;
          int index = 0;
          for (int i = 0; i < userInput.length(); i++) {
            char c = userInput.charAt(i);
            moveTo[index] = Character.isDigit(c) ? convertBoardNumber(c) : convertLetterToNumber(c);
            index++;
          }
          board.update(new Position(moveTo[0], moveTo[1]), new Position(moveTo[2], moveTo[3]));
          if(turn == Color.WHITE){
            turn = Color.BLACK;
          }
          else {
            turn = Color.WHITE;
          }
      }

    }
  }

  //  https://stackoverflow.com/questions/15027231/java-how-to-convert-letters-in-a-string-to-a-number
  private int convertLetterToNumber (char c) {
    return c - 'a';
  }

  private int convertBoardNumber(char c) {
    return (Character.getNumericValue(c) - 8) * -1;
  }
}
