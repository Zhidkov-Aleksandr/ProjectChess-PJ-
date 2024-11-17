public class Bishop extends ChessPiece {

    public Bishop(String color) {
        super(color, "bishop"); // Вызов конструктора родительского класса
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка, чтобы слон не выходил за пределы доски
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false;
        }

        // Проверка, чтобы слон не перемещался в ту же позицию
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Проверка, что слон ходит по диагонали
        int deltaX = Math.abs(toColumn - column);
        int deltaY = Math.abs(toLine - line);

        // Слон может двигаться только по диагонали, то есть по равным изменениям координат
        return deltaX == deltaY;
    }

    @Override
    public String getSymbol() {
        return "B"; // Символ для слона
    }

    @Override
    public String toString() {
        return color.equals("white") ? "WB" : "BB"; // 'R' для белой ладьи, 'r' для черной
    }
}