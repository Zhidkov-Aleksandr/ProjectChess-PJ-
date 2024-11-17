public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color, "pawn");
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка, чтобы пешка не выходила за пределы доски
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false;
        }

        // Проверка, чтобы пешка не перемещалась в ту же позицию
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Определяем направление движения пешки
        int direction = color.equals("white") ? 1 : -1;

        // Проверка обычного движения на 1 или 2 клетки вперед
        if (column == toColumn) { // Двигается по вертикали
            if (color.equals("white")) {
                if (line == 1) { // Первое движение на 2 клетки
                    if (toLine == line + 2 * direction && chessBoard.getPieceAt(line + direction, column) == null) {
                        return true; // Возможен двойной ход
                    }
                }
                // Обычное движение на 1 клетку
                if (toLine == line + direction && chessBoard.getPieceAt(toLine, column) == null) {
                    return true; // Возможен один ход вперед
                }
            } else { // Черная пешка
                if (line == 6) { // Первое движение на 2 клетки
                    if (toLine == line + 2 * direction && chessBoard.getPieceAt(line + direction, column) == null) {
                        return true; // Возможен двойной ход
                    }
                }
                // Обычное движение на 1 клетку
                if (toLine == line + direction && chessBoard.getPieceAt(toLine, column) == null) {
                    return true; // Возможен один ход вперед
                }
            }
        }

        // Проверка захвата по диагонали
        if (Math.abs(column - toColumn) == 1 && toLine == line + direction) {
            ChessPiece targetPiece = chessBoard.getPieceAt(toLine, toColumn);
            return targetPiece != null && !targetPiece.color.equals(this.color);
        }

        return false; // Если ни одно условие не выполнилось, вернуть false
    }

    @Override
    public String getSymbol() {
        return "P"; // Символ для пешки
    }

    @Override
    public String toString() {
        return color.equals("white") ? "WP" : "BP"; // 'R' для белой ладьи, 'r' для черной
    }
}