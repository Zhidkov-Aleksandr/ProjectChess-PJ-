public abstract class ChessPiece {
    protected String color; // ���� ������
    private String type;  // "pawn", "rook", "knight", "bishop", "queen", "king"
    protected boolean check; // ����������, �� ��������� true




    // �����������, ����������� ��������� ���������� color
    public ChessPiece(String color, String type) {
        if (color == null || type == null) {
            throw new IllegalStateException("Color or type is not initialized.");
        }
        this.color = color;
        this.type = type;
    }

    public ChessPiece(String color) {

    }

    // ����� ��� ����������� ������ ����� ��������
    @Override
    public String toString() {
        if (color == null || type == null) {
            throw new IllegalStateException("Color or type is not initialized.");
        }

        if (color.equals("white")) {
            switch (type) {
                case "pawn": return "P";
                case "rook": return "R";
                case "knight": return "N";
                case "bishop": return "B";
                case "queen": return "Q";
                case "king": return "K";
                default: return " ";
            }
        } else { // ������ ������
            switch (type) {
                case "pawn": return "p";
                case "rook": return "r";
                case "knight": return "n";
                case "bishop": return "b";
                case "queen": return "q";
                case "king": return "k";
                default: return " ";
            }
        }
    }

    // ����� ��� ��������� ����� ������
    public String getColor() {
        return color;
    }

    // ����������� ����� ��� �������� ����������� ��������
    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn);

    // ����������� ����� ��� ��������� ������� ������
    public abstract String getSymbol();
}