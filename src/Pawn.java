public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color, "pawn");
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // ��������, ����� ����� �� �������� �� ������� �����
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            return false;
        }

        // ��������, ����� ����� �� ������������ � �� �� �������
        if (line == toLine && column == toColumn) {
            return false;
        }

        // ���������� ����������� �������� �����
        int direction = color.equals("white") ? 1 : -1;

        // �������� �������� �������� �� 1 ��� 2 ������ ������
        if (column == toColumn) { // ��������� �� ���������
            if (color.equals("white")) {
                if (line == 1) { // ������ �������� �� 2 ������
                    if (toLine == line + 2 * direction && chessBoard.getPieceAt(line + direction, column) == null) {
                        return true; // �������� ������� ���
                    }
                }
                // ������� �������� �� 1 ������
                if (toLine == line + direction && chessBoard.getPieceAt(toLine, column) == null) {
                    return true; // �������� ���� ��� ������
                }
            } else { // ������ �����
                if (line == 6) { // ������ �������� �� 2 ������
                    if (toLine == line + 2 * direction && chessBoard.getPieceAt(line + direction, column) == null) {
                        return true; // �������� ������� ���
                    }
                }
                // ������� �������� �� 1 ������
                if (toLine == line + direction && chessBoard.getPieceAt(toLine, column) == null) {
                    return true; // �������� ���� ��� ������
                }
            }
        }

        // �������� ������� �� ���������
        if (Math.abs(column - toColumn) == 1 && toLine == line + direction) {
            ChessPiece targetPiece = chessBoard.getPieceAt(toLine, toColumn);
            return targetPiece != null && !targetPiece.color.equals(this.color);
        }

        return false; // ���� �� ���� ������� �� �����������, ������� false
    }

    @Override
    public String getSymbol() {
        return "P"; // ������ ��� �����
    }

    @Override
    public String toString() {
        return color.equals("white") ? "WP" : "BP"; // 'R' ��� ����� �����, 'r' ��� ������
    }
}