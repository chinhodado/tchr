package hackerRank.contests.w24;

import java.util.*;

/**
 * Created by Chin on 12-Oct-16.
 */
public class SimplifiedChessEngine {
    static class Node {
        int row, column;
        char type;
        char side;
        Set<Node> adjacents;
        public Node(int row, int column, char side, char type) {
            this.row = row;
            this.column = column;
            this.side = side;
            this.type = type;
            adjacents = new HashSet<>();
        }

        public Node(int row, int column) {
            this.row = row;
            this.column = column;
            adjacents = new HashSet<>();
        }

        public void addNeighbor(Node v) {
            if (!adjacents.contains(v)) {
                adjacents.add(v);
            }
        }
        public boolean isNeighbor(Node v) {
            return adjacents.contains(v);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (row != node.row) return false;
            return column == node.column;

        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + column;
            return result;
        }

        @Override
        public String toString() {
            return "[" + row + "," + column + "]";
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        for (int gx = 0; gx < g; gx++) {
            int w = in.nextInt(); // # of white pieces
            int b = in.nextInt(); // # of black pieces
            int m = in.nextInt(); // max # of moves

//            String[][] table = new String[4][4];

            ArrayList<Node> wPieces = new ArrayList<>();
            for (int i = 0; i < w; i++) {
                char t = in.next().charAt(0);
                int c;
                String column = in.next();
                switch (column) {
                    case "A": c = 0; break;
                    case "B": c = 1; break;
                    case "C": c = 2; break;
                    case "D": c = 3; break;
                    default: c = 0;
                }

                int r = in.nextInt() - 1;
//                table[r][c] = t;
                wPieces.add(new Node(r, c, 'W', t));
            }

            ArrayList<Node> bPieces = new ArrayList<>();
            Node bQueen = null;
            for (int i = 0; i < b; i++) {
                char t = in.next().charAt(0);
                int c;
                String column = in.next();
                switch (column) {
                    case "A": c = 0; break;
                    case "B": c = 1; break;
                    case "C": c = 2; break;
                    case "D": c = 3; break;
                    default: c = 0;
                }

                int r = in.nextInt() - 1;
//                table[r][c] = t;
                Node newNode = new Node(r, c, 'B', t);
                bPieces.add(newNode);
                if (t == 'Q') {
                    bQueen = newNode;
                }
            }

            boolean lv0_done = false;
            boolean lv1_done = false;
            for (Node p1w : wPieces) {
                // level 1: white, any
                if (lv1_done) break;
                String[][] table = getTable(bPieces, wPieces);
                ArrayList<Node> moves = getPossibleMoves(p1w, table);
                for (Node move : moves) {
                    ArrayList<Node> bPieces_lv1 = new ArrayList<>(bPieces);
                    ArrayList<Node> wPieces_lv1 = new ArrayList<>(wPieces);
                    if (move.equals(bQueen)) {
                        lv1_done = true;
                        break;
                    }
                    else {
                        for (Node tmp_lv1 : bPieces_lv1) {
                            if (move.equals(tmp_lv1)) {
                                bPieces_lv1.remove(tmp_lv1);
                                break;
                            }
                        }

                        wPieces_lv1.remove(p1w);
                        wPieces_lv1.add(new Node(move.row, move.column, 'W', p1w.type));
                    }

                    boolean lv2_done = false;
                    for (Node p2b : bPieces) {
                        // level 2: black, all
                        if (lv2_done) break;
                        String[][] table_lv2 = getTable(bPieces_lv1, wPieces_lv1);
                        ArrayList<Node> moves_lv2 = getPossibleMoves(p1w, table_lv2);
                        for (Node move_lv2 : moves_lv2) {
                            ArrayList<Node> bPieces_lv2 = new ArrayList<>(bPieces_lv1);
                            ArrayList<Node> wPieces_lv2 = new ArrayList<>(wPieces_lv1);
                            if (move_lv2.equals(bQueen)) {
                                lv1_done = true;
                                break;
                            }
                        }
                    }
                }
            }

            if (lv1_done) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
    }

    static ArrayList<Node> getPossibleMoves(Node fromPos, String[][] table) throws Exception {
        String piece = table[fromPos.row][fromPos.column];
        char type = piece.charAt(1);
        ArrayList<Node> moves = new ArrayList<>();
        switch (type) {
            case 'Q':
                // horizontal
                for (int i = fromPos.column + 1; i < 4; i++) {
                    String newPos = table[fromPos.row][i];
                    if (newPos == null) {
                        moves.add(new Node(fromPos.row, i));
                    }
                    else if (!isSameTeam(newPos, piece)) {
                        moves.add(new Node(fromPos.row, i));
                        break;
                    }
                    else if (isSameTeam(newPos, piece)) {
                        break;
                    }
                }
                for (int i = fromPos.column - 1; i >= 0; i--) {
                    String newPos = table[fromPos.row][i];
                    if (newPos == null) {
                        moves.add(new Node(fromPos.row, i));
                    }
                    else if (!isSameTeam(newPos, piece)) {
                        moves.add(new Node(fromPos.row, i));
                        break;
                    }
                    else if (isSameTeam(newPos, piece)) {
                        break;
                    }
                }

                // vertical
                for (int i = fromPos.row + 1; i < 4; i++) {
                    String newPos = table[i][fromPos.column];
                    if (newPos == null) {
                        moves.add(new Node(i, fromPos.column));
                    }
                    else if (!isSameTeam(newPos, piece)) {
                        moves.add(new Node(i, fromPos.column));
                        break;
                    }
                    else if (isSameTeam(newPos, piece)) {
                        break;
                    }
                }
                for (int i = fromPos.row - 1; i >= 0; i--) {
                    String newPos = table[i][fromPos.column];
                    if (newPos == null) {
                        moves.add(new Node(i, fromPos.column));
                    }
                    else if (!isSameTeam(newPos, piece)) {
                        moves.add(new Node(i, fromPos.column));
                        break;
                    }
                    else if (isSameTeam(newPos, piece)) {
                        break;
                    }
                }

                // diagonal
                for (int i = fromPos.row + 1; i < 4; i++) {
                    String newPos = table[i][i];
                    if (newPos == null) {
                        moves.add(new Node(i, fromPos.column));
                    }
                    else if (!isSameTeam(newPos, piece)) {
                        moves.add(new Node(i, fromPos.column));
                        break;
                    }
                    else if (isSameTeam(newPos, piece)) {
                        break;
                    }
                }
                for (int i = fromPos.row - 1; i >= 0; i--) {
                    String newPos = table[i][i];
                    if (newPos == null) {
                        moves.add(new Node(i, fromPos.column));
                    }
                    else if (!isSameTeam(newPos, piece)) {
                        moves.add(new Node(i, fromPos.column));
                        break;
                    }
                    else if (isSameTeam(newPos, piece)) {
                        break;
                    }
                }
                for (int i = fromPos.row + 1, j = fromPos.column - 1; i < 4 && j >= 0; i++, j--) {
                    String newPos = table[i][i];
                    if (newPos == null) {
                        moves.add(new Node(i, fromPos.column));
                    }
                    else if (!isSameTeam(newPos, piece)) {
                        moves.add(new Node(i, fromPos.column));
                        break;
                    }
                    else if (isSameTeam(newPos, piece)) {
                        break;
                    }
                }
                for (int i = fromPos.row - 1, j = fromPos.column + 1; i >= 0 && j < 4; i--, j++) {
                    String newPos = table[i][i];
                    if (newPos == null) {
                        moves.add(new Node(i, fromPos.column));
                    }
                    else if (!isSameTeam(newPos, piece)) {
                        moves.add(new Node(i, fromPos.column));
                        break;
                    }
                    else if (isSameTeam(newPos, piece)) {
                        break;
                    }
                }
                break;
            case 'N':
                int r = fromPos.row;
                int c = fromPos.column;
                int[] rows    = new int[] {r-2, r-2, r-1, r+1, r+2, r+2, r+1, r-1};
                int[] columns = new int[] {c-1, c+1, c+2, c+2, c+1, c-1, c-2, c-2};
                for (int i = 0; i < rows.length; i++) {
                    if (rows[i] >= 0 && rows[i] < 4 && columns[i] >= 0 && columns[i] < 4) {
                        String newPiece = table[rows[i]][columns[i]];
                        if (newPiece == null || !isSameTeam(newPiece, piece)) {
                            moves.add(new Node(rows[i], columns[i]));
                        }
                    }
                }
                break;
            case 'B':
                // diagonal
                for (int i = fromPos.row + 1; i < 4; i++) {
                    String newPos = table[i][i];
                    if (newPos == null) {
                        moves.add(new Node(i, fromPos.column));
                    }
                    else if (!isSameTeam(newPos, piece)) {
                        moves.add(new Node(i, fromPos.column));
                        break;
                    }
                    else if (isSameTeam(newPos, piece)) {
                        break;
                    }
                }
                for (int i = fromPos.row - 1; i >= 0; i--) {
                    String newPos = table[i][i];
                    if (newPos == null) {
                        moves.add(new Node(i, fromPos.column));
                    }
                    else if (!isSameTeam(newPos, piece)) {
                        moves.add(new Node(i, fromPos.column));
                        break;
                    }
                    else if (isSameTeam(newPos, piece)) {
                        break;
                    }
                }
                for (int i = fromPos.row + 1, j = fromPos.column - 1; i < 4 && j >= 0; i++, j--) {
                    String newPos = table[i][i];
                    if (newPos == null) {
                        moves.add(new Node(i, fromPos.column));
                    }
                    else if (!isSameTeam(newPos, piece)) {
                        moves.add(new Node(i, fromPos.column));
                        break;
                    }
                    else if (isSameTeam(newPos, piece)) {
                        break;
                    }
                }
                for (int i = fromPos.row - 1, j = fromPos.column + 1; i >= 0 && j < 4; i--, j++) {
                    String newPos = table[i][i];
                    if (newPos == null) {
                        moves.add(new Node(i, fromPos.column));
                    }
                    else if (!isSameTeam(newPos, piece)) {
                        moves.add(new Node(i, fromPos.column));
                        break;
                    }
                    else if (isSameTeam(newPos, piece)) {
                        break;
                    }
                }
                break;
            case 'R':
                // horizontal
                for (int i = fromPos.column + 1; i < 4; i++) {
                    String newPos = table[fromPos.row][i];
                    if (newPos == null) {
                        moves.add(new Node(fromPos.row, i));
                    }
                    else if (!isSameTeam(newPos, piece)) {
                        moves.add(new Node(fromPos.row, i));
                        break;
                    }
                    else if (isSameTeam(newPos, piece)) {
                        break;
                    }
                }
                for (int i = fromPos.column - 1; i >= 0; i--) {
                    String newPos = table[fromPos.row][i];
                    if (newPos == null) {
                        moves.add(new Node(fromPos.row, i));
                    }
                    else if (!isSameTeam(newPos, piece)) {
                        moves.add(new Node(fromPos.row, i));
                        break;
                    }
                    else if (isSameTeam(newPos, piece)) {
                        break;
                    }
                }

                // vertical
                for (int i = fromPos.row + 1; i < 4; i++) {
                    String newPos = table[i][fromPos.column];
                    if (newPos == null) {
                        moves.add(new Node(i, fromPos.column));
                    }
                    else if (!isSameTeam(newPos, piece)) {
                        moves.add(new Node(i, fromPos.column));
                        break;
                    }
                    else if (isSameTeam(newPos, piece)) {
                        break;
                    }
                }
                for (int i = fromPos.row - 1; i >= 0; i--) {
                    String newPos = table[i][fromPos.column];
                    if (newPos == null) {
                        moves.add(new Node(i, fromPos.column));
                    }
                    else if (!isSameTeam(newPos, piece)) {
                        moves.add(new Node(i, fromPos.column));
                        break;
                    }
                    else if (isSameTeam(newPos, piece)) {
                        break;
                    }
                }
                break;
            default:
                throw new Exception("Invalid type");
        }
        return moves;
    }

    static boolean isSameTeam(String a, String b) {
        return a.charAt(0) == b.charAt(0);
    }

    static String[][] getTable(ArrayList<Node> bPieces, ArrayList<Node> wPieces) {
        String[][] table = new String[4][4];
        for (Node n : bPieces) {
            table[n.row][n.column] = "B" + n.type;
        }

        for (Node n : wPieces) {
            table[n.row][n.column] = "W" + n.type;
        }
        return table;
    }
}
