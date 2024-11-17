public class Rook extends ChessPiece {

    public Rook(String color) {
        super(color, "rook"); // Вызов конструктора родительского класса
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка, чтобы ладья не выходила за пределы доски
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false;
        }

        // Проверка, чтобы ладья не перемещалась в ту же позицию
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Проверка, что ладья движется только по вертикали или горизонтали
        if (line == toLine || column == toColumn) {
            return true; // Ладья может двигаться
        }

        return false; // Ладья не может двигаться
    }

    @Override
    public String getSymbol() {
        return "R"; // Символ для ладьи
    }

    @Override
    public String toString() {
        return color.equals("white") ? "WR" : "BR"; // 'R' для белой ладьи, 'r' для черной
    }
}