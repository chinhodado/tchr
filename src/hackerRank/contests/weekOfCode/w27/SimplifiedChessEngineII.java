package hackerRank.contests.weekOfCode.w27;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Chin on 23-Dec-16.
 */
public class SimplifiedChessEngineII {

    static final char QUEEN = 'Q';
    static final char ROOK = 'R';
    static final char BISHOP = 'B';
    static final char PAWN = 'P';
    static final char KNIGHT = 'N';
    static final int[][] KNIGHT_MOVES = new int[][]{{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};

    static class Piece {
        char type;
        boolean isWhite;

        Piece(char type, boolean isWhite) {
            this.type = type;
            this.isWhite = isWhite;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("a.txt"));
        Scanner sc = new Scanner(System.in);
        int g = sc.nextInt();
        for (int gx = 0; gx < g; gx++) {
            int w = sc.nextInt();
            int b = sc.nextInt();
            int m = sc.nextInt();

            Piece[][] board = new Piece[4][4];
            for (int i = 0; i < w; i++) {
                char type = sc.next().charAt(0);
                char column = sc.next().charAt(0);
                int row = sc.nextInt() - 1;

                Piece p = new Piece(type, true);
                board[row][column - 'A'] = p;
            }
            for (int i = 0; i < b; i++) {
                char type = sc.next().charAt(0);
                char column = sc.next().charAt(0);
                int row = sc.nextInt() -1 ;

                Piece p = new Piece(type, false);
                board[row][column - 'A'] = p;
            }

            boolean win = exist(board, m);
            System.out.println(win ? "YES" : "NO");
        }
    }

    static Piece[][] copy(Piece[][] tt) {
        Piece[][] t = new Piece[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                t[i][j] = tt[i][j];
            }
        }
        return t;
    }

    static boolean inBoard(int row, int column) {
        return row >= 0 && column >= 0 && row < 4 && column < 4;
    }

    static void numPossible(Piece[][] board, int row, int column, int dRow, int dColumn, ArrayList<int[]> moves) {
        int d = 1;
        while (inBoard(row + d * dRow, column + d * dColumn)) {
            int newR = row + d * dRow;
            int newC = column + d * dColumn;
            if (board[newR][newC] != null && board[row][column].isWhite == board[newR][newC].isWhite) {
                return;
            }
            if (board[newR][newC] != null && board[row][column].isWhite != board[newR][newC].isWhite) {
                moves.add(new int[]{d * dRow, d * dColumn});
                return;
            }
            moves.add(new int[]{d * dRow, d * dColumn});
            d++;
        }
    }

    static void rookMoves(Piece[][] board, int row, int column, ArrayList<int[]> moves) {
        numPossible(board, row, column, 1, 0, moves);
        numPossible(board, row, column, -1, 0, moves);
        numPossible(board, row, column, 0, 1, moves);
        numPossible(board, row, column, 0, -1, moves);
    }

    static void bishopMoves(Piece[][] board, int row, int column, ArrayList<int[]> moves) {
        numPossible(board, row, column, 1, 1, moves);
        numPossible(board, row, column, 1, -1, moves);
        numPossible(board, row, column, -1, 1, moves);
        numPossible(board, row, column, -1, -1, moves);
    }

    static void knightMoves(Piece[][] board, int row, int column, ArrayList<int[]> moves) {
        for (int i = 0; i < KNIGHT_MOVES.length; i++) {
            int newRow = row + KNIGHT_MOVES[i][0];
            int newColumn = column + KNIGHT_MOVES[i][1];
            if (inBoard(newRow, newColumn)) {
                moves.add(new int[]{KNIGHT_MOVES[i][0], KNIGHT_MOVES[i][1]});
            }
        }
    }

    static void pawnMoves(Piece[][] board, int row, int column, ArrayList<int[]> moves) {
        Piece p = board[row][column];
        if (p.isWhite) {
            if (inBoard(row+1, column) && board[row+1][column] == null) {
                moves.add(new int[]{1, 0});
            }

            if (inBoard(row+1, column+1) && board[row+1][column+1] != null && !board[row+1][column+1].isWhite) {
                moves.add(new int[]{1, 1});
            }

            if (inBoard(row+1, column-1) && board[row+1][column-1] != null && !board[row+1][column-1].isWhite) {
                moves.add(new int[]{1, -1});
            }
        }
        else {
            if (inBoard(row-1, column) && board[row-1][column] == null) {
                moves.add(new int[]{-1, 0});
            }

            if (inBoard(row-1, column+1) && board[row-1][column+1] != null && board[row-1][column+1].isWhite) {
                moves.add(new int[]{-1, 1});
            }

            if (inBoard(row-1, column-1) && board[row-1][column-1] != null && board[row-1][column-1].isWhite) {
                moves.add(new int[]{-1, -1});
            }
        }
    }

    static List<int[]> possibleMoves(Piece[][] board, int row, int column) {
        Piece p = board[row][column];
        ArrayList<int[]> moves = new ArrayList<>();

        if (p.type == QUEEN) {
            rookMoves(board, row, column, moves);
            bishopMoves(board, row, column, moves);
        } else if (p.type == ROOK) {
            rookMoves(board, row, column, moves);
        } else if (p.type == BISHOP) {
            bishopMoves(board, row, column, moves);
        } else if (p.type == KNIGHT) {
            knightMoves(board, row, column, moves);
        } else if (p.type == PAWN) {
            pawnMoves(board, row, column, moves);
        }

        return moves;
    }

    static List<Piece[][]> doMove(Piece[][] board, int row, int column, int[] move) {
        List<Piece[][]> boards = new ArrayList<>();
        Piece p = board[row][column];
        board[row][column] = null;
        int newRow = row + move[0];
        int newColumn = column + move[1];
        if (p.type == PAWN && ((p.isWhite && newRow == 3) || (!p.isWhite && newRow == 0))) {
            Piece[][] boardR = copy(board);
            Piece pR = new Piece(ROOK, p.isWhite);
            boardR[newRow][newColumn] = pR;
            boards.add(boardR);

            Piece[][] boardN = copy(board);
            Piece pN = new Piece(KNIGHT, p.isWhite);
            boardN[newRow][newColumn] = pN;
            boards.add(boardN);

            Piece[][] boardB = copy(board);
            Piece pB = new Piece(BISHOP, p.isWhite);
            boardB[newRow][newColumn] = pB;
            boards.add(boardB);
        }
        else {
            board[newRow][newColumn] = p;
            boards.add(board);
        }

        return boards;
    }

    static boolean whiteWin(Piece[][] board) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != null && !board[i][j].isWhite && board[i][j].type == QUEEN) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean whiteLose(Piece[][] board) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != null && board[i][j].isWhite && board[i][j].type == QUEEN) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean exist(Piece[][] board, int m) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != null && board[i][j].isWhite) {
                    List<int[]> moves = possibleMoves(board, i, j);
                    for (int[] move : moves) {
                        Piece[][] copy = copy(board);
                        List<Piece[][]> newBoards = doMove(copy, i, j, move);
                        for (Piece[][] newBoard : newBoards) {
                            if (whiteWin(newBoard)) {
                                return true;
                            }
                            if (m > 1) {
                                if (foreach(newBoard, m - 1)) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    static boolean foreach(Piece[][] board, int m) {
        if (m == 1) {
            return false;
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != null && !board[i][j].isWhite) {
                    List<int[]> moves = possibleMoves(board, i, j);
                    for (int[] move : moves) {
                        Piece[][] copy = copy(board);
                        List<Piece[][]> newBoards = doMove(copy, i, j, move);
                        for (Piece[][] newBoard : newBoards) {
                            if (whiteLose(newBoard)) {
                                return false;
                            }
                            if (m > 1) {
                                if (!exist(newBoard, m - 1)) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
