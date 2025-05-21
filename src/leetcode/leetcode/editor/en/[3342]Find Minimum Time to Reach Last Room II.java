package leetcode.leetcode.editor.en;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
There is a dungeon with n x m rooms arranged as a grid. 

 You are given a 2D array moveTime of size n x m, where moveTime[i][j] 
represents the minimum time in seconds when you can start moving to that room. You start 
from the room (0, 0) at time t = 0 and can move to an adjacent room. Moving 
between adjacent rooms takes one second for one move and two seconds for the next, 
alternating between the two. 

 Return the minimum time to reach the room (n - 1, m - 1). 

 Two rooms are adjacent if they share a common wall, either horizontally or 
vertically. 

 
 Example 1: 

 
 Input: moveTime = [[0,4],[4,4]] 
 

 Output: 7 

 Explanation: 

 The minimum time required is 7 seconds. 

 
 At time t == 4, move from room (0, 0) to room (1, 0) in one second. 
 At time t == 5, move from room (1, 0) to room (1, 1) in two seconds. 
 

 Example 2: 

 
 Input: moveTime = [[0,0,0,0],[0,0,0,0]] 
 

 Output: 6 

 Explanation: 

 The minimum time required is 6 seconds. 

 
 At time t == 0, move from room (0, 0) to room (1, 0) in one second. 
 At time t == 1, move from room (1, 0) to room (1, 1) in two seconds. 
 At time t == 3, move from room (1, 1) to room (1, 2) in one second. 
 At time t == 4, move from room (1, 2) to room (1, 3) in two seconds. 
 

 Example 3: 

 
 Input: moveTime = [[0,1],[1,2]] 
 

 Output: 4 

 
 Constraints: 

 
 2 <= n == moveTime.length <= 750 
 2 <= m == moveTime[i].length <= 750 
 0 <= moveTime[i][j] <= 10⁹ 
 

 Related Topics Array Graph Heap (Priority Queue) Matrix Shortest Path 👍 320 👎
 49

*/

class LC3342 {
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    class State implements Comparable<State> {
        int x, y, dis;

        State(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }

        @Override
        public int compareTo(State other) {
            return Integer.compare(this.dis, other.dis);
        }
    }

    public int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length, m = moveTime[0].length;

        int[][] distance = new int[n][m];
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }

        int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

        distance[0][0] = 0;

        // min heap
        PriorityQueue<State> q = new PriorityQueue<>();
        q.offer(new State(0, 0, 0));

        // Use Dijkstra's algorithm to find the shortest path from (0,0) to (n−1,m−1)
        while (!q.isEmpty()) {
            State s = q.poll();

            if (visited[s.x][s.y]) {
                continue;
            }

            if (s.x == n - 1 && s.y == m - 1) {
                break;
            }

            visited[s.x][s.y] = true;

            for (int[] dir : dirs) {
                int nx = s.x + dir[0];
                int ny = s.y + dir[1];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                int dist = Math.max(distance[s.x][s.y], moveTime[nx][ny]) + ((s.x + s.y) % 2) + 1;

                if (distance[nx][ny] > dist) {
                    distance[nx][ny] = dist;
                    q.offer(new State(nx, ny, dist));
                }
            }
        }

        return distance[n - 1][m - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}