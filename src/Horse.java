public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color, "horse");;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // ��������, ����� ���� �� ������� �� ������� �����
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false;
        }

        // ��������, ����� ���� �� ����������� � �� �� �������
        if (line == toLine && column == toColumn) {
            return false;
        }

        // �������� �������� ���� � ����� ����� "�"
        int dx = Math.abs(toColumn - column);
        int dy = Math.abs(toLine - line);

        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }

    @Override
    public String getSymbol() {
        return "H"; // ������ ��� ����
    }

    @Override
    public String toString() {
        return color.equals("white") ? "WH" : "BH"; // 'R' ��� ����� �����, 'r' ��� ������
    }
}