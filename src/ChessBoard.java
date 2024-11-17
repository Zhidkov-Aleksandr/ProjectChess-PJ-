public class ChessBoard {
     ChessPiece[][] board; // ��������� ������ ��� ����
     boolean whiteKingCheck = true; // ���������� ��� ��������, �������� �� ����� ������
    boolean blackKingCheck = true; // ���������� ��� ��������, �������� �� ������ ������
    boolean whiteRookCheck = false;

    public void movePiece(int fromLine, int fromColumn, int toLine, int toColumn) {
        ChessPiece pieceToMove = board[fromLine][fromColumn];
        if (pieceToMove == null) {
            throw new IllegalArgumentException("��� ������ �� ��������� �������.");
        }
        // ����� �������� ���� ������ �������� � �����������.
        board[toLine][toColumn] = pieceToMove;
        board[fromLine][fromColumn] = null; // ������� ������ �� ������ �������
    }

    public ChessPiece getPieceAt(int line, int column) {
        return board[line][column];
    }
    public ChessBoard() {
        // ������������� ����� � �����
        board = new ChessPiece[8][8];
        initializeBoard(); }
    private void initializeBoard() {
        //  ������������� �����
        board[0][0] = new Rook("black");
        board[0][1] = new Horse("black");
        board[0][2] = new Bishop("black");
        board[0][3] = new Queen("black");
        board[0][4] = new King("black");
        board[0][5] = new Bishop("black");
        board[0][6] = new Horse("black");
        board[0][7] = new Rook("black");

        board[7][0] = new Rook("white");
        board[7][1] = new Horse("white");
        board[7][2] = new Bishop("white");
        board[7][3] = new Queen("white");
        board[7][4] = new King("white");
        board[7][5] = new Bishop("white");
        board[7][6] = new Horse("white");
        board[7][7] = new Rook("white");

        // ������������� �����
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn("black");
            board[6][i] = new Pawn("white");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // ��������� ��������� �������
        sb.append("  A  B  C  D  E  F  G  H\n"); // ��������� �������

        for (int i = 0; i < 8; i++) {
            sb.append(8 - i).append(" "); // ������ �����

            for (int j = 0; j < 8; j++) {
                ChessPiece piece = board[i][j];
                if (piece != null) {
                    sb.append(piece.toString()).append(" ");
                } else {
                    sb.append(".  "); // ������ ������
                }
            }
            sb.append(8 - i).append("\n"); // ������ �����
        }

        sb.append("  A  B  C  D  E  F  G  H\n"); // ��������� ��������� �������
        return sb.toString();
    }


    public void moveToPosition(ChessPiece piece, int fromLine, int fromColumn, int toLine, int toColumn) {
        // ��������, ��� ������ ���������� �� �������� �������
        if (piece == null || board[fromLine][fromColumn] != piece) {
            throw new IllegalArgumentException("������ �� ������� �� �������� �������.");
        }

        // ��������, ��� ������� ������� ��������� � �������� �����
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            throw new IllegalArgumentException("������� ������� ��������� �� ��������� �����.");
        }
        // ��������, ��� ������ ���������� � �� ���� ������������� �������� ������
        if (piece == null || !piece.color.equals("white")) {
            System.out.println("������������ ��� ��� ������ ������.");
            return;
        }
        // �������� �� ������������ ���� ��� ������
        if (!piece.canMoveToPosition(this, fromLine, fromColumn, toLine, toColumn)) {
            throw new IllegalArgumentException("������������ ��� ��� ������ ������.");
        }
        board[toLine][toColumn] = piece;
        board[fromLine][fromColumn] = null;

        System.out.println("����� ����:");
        System.out.println(this); // ��������������, ��� ����� toString ����������

        // ��������, ��� ������� ������� �� ������ ������� ���� �� �����
        ChessPiece targetPiece = board[toLine][toColumn];
        if (targetPiece != null && targetPiece.getColor().equals(piece.getColor())) {
            throw new IllegalArgumentException("������� ������� ������ ������� ������ �����.");
        }

        // ���������� ����������� ������
        board[toLine][toColumn] = piece; // ����������� ������ �� ����� �������
        board[fromLine][fromColumn] = null; // ����������� ������ �������

        // ���������� ��������� check ��� ������� � �����
        if (piece instanceof King) {
            if (piece.getColor().equals("white")) {
                whiteKingCheck = false; // ���������, ��� ����� ������ ��������
            } else {
                blackKingCheck = false; // ���������, ��� ������ ������ ��������
            }
        } else if (piece instanceof Rook) {
            // �������� �������� ��� ����� (���� ���������)
        }

        // ����� ��������� ���� ��������� ��������� check � ������
        if (piece instanceof King) {
            if (piece.getColor().equals("white")) {
                whiteKingCheck = false;
            } else {
                blackKingCheck = false;
            }
        } else if (piece instanceof Rook) {
            if (piece.getColor().equals("white")) {
                // �������� ��������� ��� ����� �����
            } else {
                // �������� ��������� ��� ������ �����
            }
        }

        // ����������� ������ �� ����� �������
        board[toLine][toColumn] = piece;
        board[fromLine][fromColumn] = null; // ����������� ������ �������
    }

    public boolean castling0() {
        // �������� ��������� ��� 0-�� �������
        int kingLine = 0; // ����� ��� ������ ������
        int rookLine = 0; // ����� ��� �����
        ChessPiece king = board[kingLine][0]; // �������� ������
        ChessPiece rook = board[rookLine][0]; // �������� �����

        // �������� �� ����������� ���������
        if (king instanceof King && rook instanceof Rook) {
            if (king.getColor().equals(rook.getColor()) &&
                    whiteKingCheck && whiteRookCheck && // ����� ���������� ��������� ��������� �����
                    // ��������, ��� ����� ������� � ������ ��� ������ �����
                    board[kingLine][1] == null && board[kingLine][2] == null) {
                // ���������� ���������
                board[kingLine][2] = king; // ������� ������ ����� ���������
                board[kingLine][3] = rook; // ������� ����� ����� ���������
                board[kingLine][0] = null; // ����������� ������ ������� ������
                board[kingLine][4] = null; // ����������� ������ ������� �����
                return true; // ��������� ���������
            }
        }
        return false; // ��������� ����������
    }

    public boolean castling7() {
        // �������� ��������� ��� 7-�� �������
        int kingLine = 0; // ����� ��� ������ ������
        int rookLine = 7; // ����� ��� �����
        ChessPiece king = board[kingLine][7]; // �������� ������
        ChessPiece rook = board[rookLine][7]; // �������� �����

        // �������� �� ����������� ���������
        if (king instanceof King && rook instanceof Rook) {
            if (king.getColor().equals(rook.getColor()) &&
                    whiteKingCheck && whiteRookCheck && // ����� ���������� ��������� ��������� �����
                    // ��������, ��� ����� ������� � ������ ��� ������ �����
                    board[kingLine][5] == null && board[kingLine][6] == null) {
                // ���������� ���������
                board[kingLine][5] = king; // ������� ������ ����� ���������
                board[kingLine][6] = rook; // ������� ����� ����� ���������
                board[kingLine][7] = null; // ����������� ������ ������� ������
                board[kingLine][0] = null; // ����������� ������ ������� �����
                return true; // ��������� ���������
            }
        }
        return false; // ��������� ����������
    }
}
