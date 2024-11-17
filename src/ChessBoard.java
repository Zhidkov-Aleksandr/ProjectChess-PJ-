public class ChessBoard {
     ChessPiece[][] board; // Двумерный массив для поля
     boolean whiteKingCheck = true; // Переменная для проверки, двигался ли белый король
    boolean blackKingCheck = true; // Переменная для проверки, двигался ли черный король
    boolean whiteRookCheck = false;

    public void movePiece(int fromLine, int fromColumn, int toLine, int toColumn) {
        ChessPiece pieceToMove = board[fromLine][fromColumn];
        if (pieceToMove == null) {
            throw new IllegalArgumentException("Нет фигуры на указанной позиции.");
        }
        // Здесь добавьте вашу логику проверки и перемещения.
        board[toLine][toColumn] = pieceToMove;
        board[fromLine][fromColumn] = null; // Удаляем фигуру из старой позиции
    }

    public ChessPiece getPieceAt(int line, int column) {
        return board[line][column];
    }
    public ChessBoard() {
        // Инициализация доски и фигур
        board = new ChessPiece[8][8];
        initializeBoard(); }
    private void initializeBoard() {
        //  инициализация фигур
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

        // Инициализация пешек
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn("black");
            board[6][i] = new Pawn("white");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Добавляем заголовки колонок
        sb.append("  A  B  C  D  E  F  G  H\n"); // Заголовки колонок

        for (int i = 0; i < 8; i++) {
            sb.append(8 - i).append(" "); // Номера строк

            for (int j = 0; j < 8; j++) {
                ChessPiece piece = board[i][j];
                if (piece != null) {
                    sb.append(piece.toString()).append(" ");
                } else {
                    sb.append(".  "); // Пустая клетка
                }
            }
            sb.append(8 - i).append("\n"); // Номера строк
        }

        sb.append("  A  B  C  D  E  F  G  H\n"); // Повторяем заголовки колонок
        return sb.toString();
    }


    public void moveToPosition(ChessPiece piece, int fromLine, int fromColumn, int toLine, int toColumn) {
        // Проверка, что фигура существует на исходной позиции
        if (piece == null || board[fromLine][fromColumn] != piece) {
            throw new IllegalArgumentException("Фигура не найдена на исходной позиции.");
        }

        // Проверка, что целевая позиция находится в пределах доски
        if (toLine < 0 || toLine >= 8 || toColumn < 0 || toColumn >= 8) {
            throw new IllegalArgumentException("Целевая позиция находится за пределами доски.");
        }
        // Проверка, что фигура существует и ее цвет соответствует текущему игроку
        if (piece == null || !piece.color.equals("white")) {
            System.out.println("Некорректный ход для данной фигуры.");
            return;
        }
        // Проверка на легитимность хода для фигуры
        if (!piece.canMoveToPosition(this, fromLine, fromColumn, toLine, toColumn)) {
            throw new IllegalArgumentException("Некорректный ход для данной фигуры.");
        }
        board[toLine][toColumn] = piece;
        board[fromLine][fromColumn] = null;

        System.out.println("После хода:");
        System.out.println(this); // Предполагается, что метод toString перегружен

        // Проверка, что целевая позиция не занята фигурой того же цвета
        ChessPiece targetPiece = board[toLine][toColumn];
        if (targetPiece != null && targetPiece.getColor().equals(piece.getColor())) {
            throw new IllegalArgumentException("Целевая позиция занята фигурой вашего цвета.");
        }

        // Выполнение перемещения фигуры
        board[toLine][toColumn] = piece; // Перемещение фигуры на новую позицию
        board[fromLine][fromColumn] = null; // Освобождаем старую позицию

        // Обновление состояния check для королей и ладей
        if (piece instanceof King) {
            if (piece.getColor().equals("white")) {
                whiteKingCheck = false; // Указываем, что белый король двигался
            } else {
                blackKingCheck = false; // Указываем, что черный король двигался
            }
        } else if (piece instanceof Rook) {
            // Добавить проверку для ладей (если требуется)
        }

        // После успешного хода обновляем состояние check у фигуры
        if (piece instanceof King) {
            if (piece.getColor().equals("white")) {
                whiteKingCheck = false;
            } else {
                blackKingCheck = false;
            }
        } else if (piece instanceof Rook) {
            if (piece.getColor().equals("white")) {
                // Обновить состояние для белой ладьи
            } else {
                // Обновить состояние для черной ладьи
            }
        }

        // Перемещение фигуры на новую позицию
        board[toLine][toColumn] = piece;
        board[fromLine][fromColumn] = null; // Освобождаем старую позицию
    }

    public boolean castling0() {
        // Проверка рокировки для 0-го столбца
        int kingLine = 0; // Линия для белого короля
        int rookLine = 0; // Линия для ладьи
        ChessPiece king = board[kingLine][0]; // Получаем короля
        ChessPiece rook = board[rookLine][0]; // Получаем ладью

        // Проверка на возможность рокировки
        if (king instanceof King && rook instanceof Rook) {
            if (king.getColor().equals(rook.getColor()) &&
                    whiteKingCheck && whiteRookCheck && // Здесь необходимо учитывать состояние ладьи
                    // Проверка, что между королем и ладьей нет других фигур
                    board[kingLine][1] == null && board[kingLine][2] == null) {
                // Выполнение рокировки
                board[kingLine][2] = king; // Позиция короля после рокировки
                board[kingLine][3] = rook; // Позиция ладьи после рокировки
                board[kingLine][0] = null; // Освобождаем старую позицию короля
                board[kingLine][4] = null; // Освобождаем старую позицию ладьи
                return true; // Рокировка выполнена
            }
        }
        return false; // Рокировка невозможна
    }

    public boolean castling7() {
        // Проверка рокировки для 7-го столбца
        int kingLine = 0; // Линия для белого короля
        int rookLine = 7; // Линия для ладьи
        ChessPiece king = board[kingLine][7]; // Получаем короля
        ChessPiece rook = board[rookLine][7]; // Получаем ладью

        // Проверка на возможность рокировки
        if (king instanceof King && rook instanceof Rook) {
            if (king.getColor().equals(rook.getColor()) &&
                    whiteKingCheck && whiteRookCheck && // Здесь необходимо учитывать состояние ладьи
                    // Проверка, что между королем и ладьей нет других фигур
                    board[kingLine][5] == null && board[kingLine][6] == null) {
                // Выполнение рокировки
                board[kingLine][5] = king; // Позиция короля после рокировки
                board[kingLine][6] = rook; // Позиция ладьи после рокировки
                board[kingLine][7] = null; // Освобождаем старую позицию короля
                board[kingLine][0] = null; // Освобождаем старую позицию ладьи
                return true; // Рокировка выполнена
            }
        }
        return false; // Рокировка невозможна
    }
}
