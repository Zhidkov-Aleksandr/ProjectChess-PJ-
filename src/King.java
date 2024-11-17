public class King extends ChessPiece {

    public King(String color) {
        super(color, "king"); // ����� ������������ ������������� ������
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // ��������, ����� ������ �� ������� �� ������� �����
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false;
        }

        // ��������, ����� ������ �� ����������� � �� �� �������
        if (line == toLine && column == toColumn) {
            return false;
        }

        // ������ ����� ��������� �� ���� ������ � ����� �����������
        int deltaX = Math.abs(toColumn - column);
        int deltaY = Math.abs(toLine - line);

        return (deltaX <= 1 && deltaY <= 1); // ����� ��������� �� 1 ������
    }

    @Override
    public String getSymbol() {
        return "K"; // ������ ��� ������
    }

    @Override
    public String toString() {
        return color.equals("white") ? "WK" : "BK"; // 'R' ��� ����� �����, 'r' ��� ������
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        // ����� �� ������ ����������� ������ ��� ��������, ��������� �� ���� ��� ������
        // ��������, ��������� ��� ��������� ������ ���������� � ��������� �� ����
        // ��� ��������� ���� ����� ���������� false
        return false; // ����� �������� ���� ������ ��������
    }
}