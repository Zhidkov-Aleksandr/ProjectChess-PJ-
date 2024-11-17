import java.util.Scanner;

public class ChessGame {
    private ChessBoard chessBoard;
    private boolean whiteTurn;

    public ChessGame() {
        chessBoard = new ChessBoard(); // Инициализация доски и фигур
        whiteTurn = true; // Первый ход делают белые
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(chessBoard); // Отображение текущей доски
            System.out.println((whiteTurn ? "Белые" : "Черные") + " делают ход. Введите команду (например, D2 D3):");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Игра завершена.");
                break;
            }

            String[] positions = input.split(" ");
            if (positions.length != 2) {
                System.out.println("Неверный формат ввода. Введите две позиции, разделенные пробелом.");
                continue;
            }

            String from = positions[0].toUpperCase();
            String to = positions[1].toUpperCase();
            if (movePiece(from, to)) {
                whiteTurn = !whiteTurn; // Переключаем ход
            }
        }
        scanner.close();
    }

    private boolean movePiece(String from, String to) {
        int fromLine = 8 - Character.getNumericValue(from.charAt(1)); // Преобразование координат
        int fromColumn = from.charAt(0) - 'A';
        int toLine = 8 - Character.getNumericValue(to.charAt(1));
        int toColumn = to.charAt(0) - 'A';

        if (fromLine < 0 || fromLine >= 8 || fromColumn < 0 || fromColumn >= 8
                || toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            System.out.println("Координаты выходят за пределы доски!");
            return false;
        }

        ChessPiece pieceToMove = chessBoard.board[fromLine][fromColumn];
        if (pieceToMove == null || !pieceToMove.getColor().equals(whiteTurn ? "white" : "black")) {
            System.out.println("Выберите правильную фигуру для движения.");
            return false;
        }

        try {
            chessBoard.moveToPosition(pieceToMove, fromLine, fromColumn, toLine, toColumn);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        ChessGame game = new ChessGame();
        game.play();
    }
}