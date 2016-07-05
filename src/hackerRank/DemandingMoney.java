package hackerRank;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by tdo on 29-Jun-16.
 * Week of Code 21
 * Demanding Money
 */
public class DemandingMoney {
    static int maxMoney = 0;
    static Set<Integer> maxMoneySet = new HashSet<>();
    static int[][] map;
    static int[] arr;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        map = new int[n][n];
        for (int i = 0; i < m; i++) {
            int a = in.nextInt() - 1;
            int b = in.nextInt() - 1;
            map[a][b] = 1;
            map[b][a] = 1;
        }

        Set<Integer> hasMoneyNoNeighbor = new HashSet<>();
        Set<Integer> zeroMoneyNoNeighbor = new HashSet<>();
        Set<Integer> hasNeighbor = new HashSet<>();

        for (int i = 0; i < n; i++) {
            if (getNumNeighbor(map, i) == 0) {
                if (arr[i] == 0) {
                    zeroMoneyNoNeighbor.add(i);
                }
                else {
                    hasMoneyNoNeighbor.add(i);
                }
            }
            else {
                hasNeighbor.add(i);
            }
        }

        int max = processMap(hasNeighbor);

        for (int i : hasMoneyNoNeighbor) {
            max += i;
        }

        System.out.println(max);
    }

    static int processMap(Set<Integer> toExplore) {
        int localMax = 0;
        for (int i : toExplore) {
            Set<Integer> newToExplore = new HashSet<>(toExplore);
            newToExplore.remove(i);

            int[] tmp = map[i];
            for (int j = 0; j < tmp.length; j++) {
                if (tmp[j] == 1) {
                    newToExplore.remove(j);
                }
            }

            int s = arr[i] + processMap(newToExplore);

            if (s > localMax) {
                localMax = s;
            }
        }

        if (localMax > maxMoney) {
            maxMoney = localMax;
        }

        return localMax;
    }

    static int getNumNeighbor(int[][] arr, int n) {
        int neighbors = 0;
        int[] tmp = arr[n];
        for (int aTmp : tmp) {
            if (aTmp == 1) {
                neighbors++;
            }
        }
        return neighbors;
    }
}
