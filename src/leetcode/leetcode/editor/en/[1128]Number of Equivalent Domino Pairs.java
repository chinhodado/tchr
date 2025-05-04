package leetcode.leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;

/**
Given a list of dominoes, dominoes[i] = [a, b] is equivalent to dominoes[j] = [
c, d] if and only if either (a == c and b == d), or (a == d and b == c) - that 
is, one domino can be rotated to be equal to another domino. 

 Return the number of pairs (i, j) for which 0 <= i < j < dominoes.length, and 
dominoes[i] is equivalent to dominoes[j]. 

 
 Example 1: 

 
Input: dominoes = [[1,2],[2,1],[3,4],[5,6]]
Output: 1
 

 Example 2: 

 
Input: dominoes = [[1,2],[1,2],[1,1],[1,2],[2,2]]
Output: 3
 

 
 Constraints: 

 
 1 <= dominoes.length <= 4 * 10⁴ 
 dominoes[i].length == 2 
 1 <= dominoes[i][j] <= 9 
 

 Related Topics Array Hash Table Counting 👍 1025 👎 365

*/

class LC1128 {
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] domino : dominoes) {
            int key = domino[0] < domino[1] ? domino[0] * 10 + domino[1] : domino[1] * 10 + domino[0];
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        int count = 0;
        for (Integer value : map.values()) {
            if (value > 1) {
                // we need to choose 2 from n (nC2) = n(n-1)/2
                count += value * (value - 1) / 2;
            }
        }

        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}