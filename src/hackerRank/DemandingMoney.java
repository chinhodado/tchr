package hackerRank;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by tdo on 29-Jun-16.
 * Week of Code 21
 * Demanding Money
 */
public class DemandingMoney {
    static int maxMoney = 0;
    static Set<Set<Integer>> maxSet = new HashSet<>();
    static int[][] map;
    static int[] money;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("a.txt"));
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        money = new int[n];
        for (int i = 0; i < n; i++) {
            money[i] = in.nextInt();
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
                if (money[i] == 0) {
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

        int max = processMap(hasNeighbor, new HashSet<>());

        for (int i : hasMoneyNoNeighbor) {
            max += money[i];
        }

        int numWay = maxSet.size();
        numWay = numWay * (int)Math.pow(2, zeroMoneyNoNeighbor.size());

        System.out.println(max + " " + numWay);
    }

    static int processMap(Set<Integer> toExplore, Set<Integer> explored) {
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

            Set<Integer> newExplored = new HashSet<>(explored);
            newExplored.add(i);
            int s = money[i] + processMap(newToExplore, newExplored);

            if (s > localMax) {
                localMax = s;
            }
        }

        int exploredMoney = 0;
        for (int i : explored) {
            exploredMoney += money[i];
        }

        if (exploredMoney > maxMoney) {
            maxMoney = exploredMoney;
            maxSet = new HashSet<>();
            maxSet.add(explored);
        }
        else if (exploredMoney == maxMoney) {
            maxSet.add(explored);
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