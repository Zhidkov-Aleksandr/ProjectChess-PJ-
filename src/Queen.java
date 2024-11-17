public class Queen extends ChessPiece {

    public Queen(String color) {
        super(color, "queen");; // Вызов конструктора родительского класса
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка, чтобы ферзь не выходил за пределы доски
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false;
        }

        // Проверка, чтобы ферзь не перемещался в ту же позицию
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Проверка, что ферзь движется по диагонали или по прямой
        int deltaX = Math.abs(toColumn - column);
        int deltaY = Math.abs(toLine - line);

        // Ферзь может двигаться по горизонтали, вертикали или диагонали
        return (line == toLine || column == toColumn || deltaX == deltaY);
    }

    @Override
    public String getSymbol() {
        return "Q"; // Символ для ферзя
    }

    @Override
    public String toString() {
        return color.equals("white") ? "WQ" : "BQ"; // 'R' для белой ладьи, 'r' для черной
    }
}