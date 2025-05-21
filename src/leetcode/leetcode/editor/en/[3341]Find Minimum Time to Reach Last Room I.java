package leetcode.leetcode.editor.en;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
There is a dungeon with n x m rooms arranged as a grid. 

 You are given a 2D array moveTime of size n x m, where moveTime[i][j] 
represents the minimum time in seconds after which the room opens and can be moved to. 
You start from the room (0, 0) at time t = 0 and can move to an adjacent room. 
Moving between adjacent rooms takes exactly one second. 

 Return the minimum time to reach the room (n - 1, m - 1). 

 Two rooms are adjacent if they share a common wall, either horizontally or 
vertically. 

 
 Example 1: 

 
 Input: moveTime = [[0,4],[4,4]] 
 

 Output: 6 

 Explanation: 

 The minimum time required is 6 seconds. 

 
 At time t == 4, move from room (0, 0) to room (1, 0) in one second. 
 At time t == 5, move from room (1, 0) to room (1, 1) in one second. 
 

 Example 2: 

 
 Input: moveTime = [[0,0,0],[0,0,0]] 
 

 Output: 3 

 Explanation: 

 The minimum time required is 3 seconds. 

 
 At time t == 0, move from room (0, 0) to room (1, 0) in one second. 
 At time t == 1, move from room (1, 0) to room (1, 1) in one second. 
 At time t == 2, move from room (1, 1) to room (1, 2) in one second. 
 

 Example 3: 

 
 Input: moveTime = [[0,1],[1,2]] 
 

 Output: 3 

 
 Constraints: 

 
 2 <= n == moveTime.length <= 50 
 2 <= m == moveTime[i].length <= 50 
 0 <= moveTime[i][j] <= 10⁹ 
 

 Related Topics Array Graph Heap (Priority Queue) Matrix Shortest Path 👍 482 👎
 158

*/

class LC3341 {
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /*
    We are given a two-dimensional array of size n×m, and the task is to find the shortest time required to move from position
    (0,0) to position (n−1,m−1). While moving, one can go to any of the four adjacent positions (up, down, left, right),
    and each position has an associated earliest move time, meaning one can only move to that position after that time.
     */
    public int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length, m = moveTime[0].length;

        int[][] distance = new int[n][m];
        boolean[][] visited = new boolean[n][m];

        for (int[] ints : distance) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }

        // The 2D array can be regarded as an undirected graph of size n×m, where the position (i,j)
        // has undirected edges connecting it to (i−1,j), (i+1,j), (i,j−1), and (i,j+1).
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

            visited[s.x][s.y] = true;

            // go through the neighbors
            for (int[] dir : dirs) {
                int nx = s.x + dir[0];
                int ny = s.y + dir[1];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                // Unlike the standard Dijkstra algorithm,
                // in this problem we define d[i][j] to represent the shortest time required to reach (i,j) from (0,0)
                // The time to move from (i,j) to an adjacent coordinate (u,v) is given by max(d[i][j],moveTime[u][v])+1.
                int dist = Math.max(distance[s.x][s.y], moveTime[nx][ny]) + 1;

                if (distance[nx][ny] > dist) {
                    distance[nx][ny] = dist;
                    q.offer(new State(nx, ny, dist));
                }
            }
        }

        return distance[n - 1][m - 1];
    }

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
}
//leetcode submit region end(Prohibit modification and deletion)


}