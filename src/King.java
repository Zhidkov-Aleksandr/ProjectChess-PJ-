public class King extends ChessPiece {

    public King(String color) {
        super(color, "king"); // Вызов конструктора родительского класса
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка, чтобы король не выходил за пределы доски
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false;
        }

        // Проверка, чтобы король не перемещался в ту же позицию
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Король может двигаться на одну клетку в любом направлении
        int deltaX = Math.abs(toColumn - column);
        int deltaY = Math.abs(toLine - line);

        return (deltaX <= 1 && deltaY <= 1); // Может двигаться на 1 клетку
    }

    @Override
    public String getSymbol() {
        return "K"; // Символ для короля
    }

    @Override
    public String toString() {
        return color.equals("white") ? "WK" : "BK"; // 'R' для белой ладьи, 'r' для черной
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        // Здесь вы можете реализовать логику для проверки, находится ли поле под атакой
        // Например, перебрать все возможные фигуры противника и проверить их ходы
        // Для упрощения этот метод возвращает false
        return false; // Здесь добавьте вашу логику проверки
    }
}