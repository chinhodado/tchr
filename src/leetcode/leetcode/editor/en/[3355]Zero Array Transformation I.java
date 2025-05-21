package leetcode.leetcode.editor.en;

/**
You are given an integer array nums of length n and a 2D array queries, where 
queries[i] = [li, ri]. 

 For each queries[i]: 

 
 Select a subset of indices within the range [li, ri] in nums. 
 Decrement the values at the selected indices by 1. 
 

 A Zero Array is an array where all elements are equal to 0. 

 Return true if it is possible to transform nums into a Zero Array after 
processing all the queries sequentially, otherwise return false. 

 
 Example 1: 

 
 Input: nums = [1,0,1], queries = [[0,2]] 
 

 Output: true 

 Explanation: 

 
 For i = 0: 
 

 
 Select the subset of indices as [0, 2] and decrement the values at these 
indices by 1. 
 The array will become [0, 0, 0], which is a Zero Array. 
 
 


 Example 2: 

 
 Input: nums = [4,3,2,1], queries = [[1,3],[0,2]] 
 

 Output: false 

 Explanation: 

 
 For i = 0: 
 

 
 Select the subset of indices as [1, 2, 3] and decrement the values at these 
indices by 1. 
 The array will become [4, 2, 1, 0]. 
 
 
 For i = 1:
 
 Select the subset of indices as [0, 1, 2] and decrement the values at these 
indices by 1. 
 The array will become [3, 1, 0, 0], which is not a Zero Array. 
 
 


 
 Constraints: 

 
 1 <= nums.length <= 10⁵ 
 0 <= nums[i] <= 10⁵ 
 1 <= queries.length <= 10⁵ 
 queries[i].length == 2 
 0 <= li <= ri < nums.length 
 

 Related Topics Array Prefix Sum 👍 352 👎 36

*/

class LC3355 {
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        /*
        Create a difference array deltaArray with a length of n + 1
        For each query interval [left, right], increment deltaArray[left] by +1,
        indicating an increase in the operation count starting from left.

        Decrement deltaArray[right + 1] by 1, indicating that the operation count
        returns to its original value from right + 1.
         */
        int[] deltaArray = new int[nums.length + 1];
        for (int[] query : queries) {
            int left = query[0];
            int right = query[1];
            deltaArray[left] += 1;
            deltaArray[right + 1] -= 1;
        }

        /*
         Perform a prefix sum accumulation on the difference array deltaArray to obtain
         the total operation count at each position in the array, storing these counts in operationCounts.
         */
        int[] operationCounts = new int[deltaArray.length];
        int currentOperations = 0;
        for (int i = 0; i < deltaArray.length; i++) {
            currentOperations += deltaArray[i];
            operationCounts[i] = currentOperations;
        }

        /*
         Traverse the nums array and the operationCounts array, comparing the actual operation counts
         (operations) at each position to see if they meet the minimum number of operations (target)
         required for zeroing. If all positions meet operations >= target, return true;
         otherwise, return false.
         */
        for (int i = 0; i < nums.length; i++) {
            if (operationCounts[i] < nums[i]) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}