public class Queen extends ChessPiece {

    public Queen(String color) {
        super(color, "queen");; // ����� ������������ ������������� ������
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // ��������, ����� ����� �� ������� �� ������� �����
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false;
        }

        // ��������, ����� ����� �� ����������� � �� �� �������
        if (line == toLine && column == toColumn) {
            return false;
        }

        // ��������, ��� ����� �������� �� ��������� ��� �� ������
        int deltaX = Math.abs(toColumn - column);
        int deltaY = Math.abs(toLine - line);

        // ����� ����� ��������� �� �����������, ��������� ��� ���������
        return (line == toLine || column == toColumn || deltaX == deltaY);
    }

    @Override
    public String getSymbol() {
        return "Q"; // ������ ��� �����
    }

    @Override
    public String toString() {
        return color.equals("white") ? "WQ" : "BQ"; // 'R' ��� ����� �����, 'r' ��� ������
    }
}