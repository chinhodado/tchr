package leetcode.leetcode.editor.en;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
The n-queens puzzle is the problem of placing n queens on an n x n chessboard 
such that no two queens attack each other. 

 Given an integer n, return all distinct solutions to the n-queens puzzle. You 
may return the answer in any order. 

 Each solution contains a distinct board configuration of the n-queens' 
placement, where 'Q' and '.' both indicate a queen and an empty space, respectively. 

 
 Example 1: 
 
 
Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown 
above
 

 Example 2: 

 
Input: n = 1
Output: [["Q"]]
 

 
 Constraints: 

 
 1 <= n <= 9 
 

 Related Topics Array Backtracking 👍 13150 👎 320

*/

class LC51 {
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * don't need to actually place the queen,
     * instead, for each row, try to place without violation on
     * col/ diagonal1/ diagonal2.
     * trick: to detect whether 2 positions sit on the same diagonal:
     * if delta(col, row) equals, same diagonal1;
     * if sum(col, row) equals, same diagonal2.
     */
    private final Set<Integer> occupiedCols = new HashSet<>();
    private final Set<Integer> occupiedDiag1s = new HashSet<>();
    private final Set<Integer> occupiedDiag2s = new HashSet<>();

    List<List<Integer>> solutions = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        solve(0, 0, n, new ArrayList<>());

        List<List<String>> res = new ArrayList<>();
        for (List<Integer> solution : solutions) {
            List<String> rowStrings = new ArrayList<>();
            res.add(rowStrings);
            for (int col : solution) {
                StringBuilder row = new StringBuilder();
                for (int i = 0; i < n; i++) {
                    row.append(i == col ? 'Q' : '.');
                }
                rowStrings.add(row.toString());
            }
        }

        return res;
    }

    int solve(int row, int count, int n, List<Integer> cols) {
        for (int col = 0; col < n; col++) {
            if (occupiedCols.contains(col)) {
                continue;
            }
            int diag1 = row - col;
            if (occupiedDiag1s.contains(diag1)) {
                continue;
            }
            int diag2 = row + col;
            if (occupiedDiag2s.contains(diag2)) {
                continue;
            }

            // we can now place a queen here
            if (row == n - 1) {
                cols.add(col);
                solutions.add(new ArrayList<>(cols));
                cols.remove(cols.size() - 1);
                count++;
            } else {
                occupiedCols.add(col);
                occupiedDiag1s.add(diag1);
                occupiedDiag2s.add(diag2);
                cols.add(col);

                count = solve(row + 1, count, n, cols);

                // backtrack
                cols.remove(cols.size() - 1);
                occupiedCols.remove(col);
                occupiedDiag1s.remove(diag1);
                occupiedDiag2s.remove(diag2);
            }
        }

        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}