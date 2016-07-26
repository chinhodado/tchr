package hackerRank.contests.wcs5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by tdo on 26-Jul-16.
 */
public class ShortPalindrome {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        char[] s_arr = s.toCharArray();
        int[][] left = new int[s_arr.length][26]; // left[i][c]: number of character c to the left of index i
        int[][] right = new int[s_arr.length][26]; // right[i][c]: number of character c to the right of index i
        int[][] c_indices = new int[26][]; // c_indices[c]: array of indices of character i in the string

        // stupid java...
        ArrayList<ArrayList<Integer>> indicesList = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            indicesList.add(new ArrayList<>());
        }

        // has to first begin with ArrayList...
        for (int i = 0; i < s_arr.length; i++) {
            ArrayList<Integer> indices = indicesList.get(s_arr[i] - 'a');
            indices.add(i);
        }

        // ...then convert to array, since I want to work with direct index using []
        // of course using ArrayList directly is also fine
        for (int i = 0; i < 26; i++) {
            int[] indices_arr = indicesList.get(i).stream().mapToInt(n -> n).toArray();
            c_indices[i] = indices_arr;
        }

        // now fill in left[][]
        // start at i = 1 since left[0][c] is always 0
        for (int i = 1; i < s_arr.length; i++) {
            // copy from last i
            for (int j = 0; j < 26; j++) {
                left[i][j] = left[i-1][j];
            }

            // add one for the last character
            left[i][s_arr[i-1] - 'a']++;
        }

        // fill in right[][]
        for (int i = s_arr.length - 2; i >= 0; i--) {
            // copy from last i
            for (int j = 0; j < 26; j++) {
                right[i][j] = right[i+1][j];
            }

            // add one for the last character
            right[i][s_arr[i+1] - 'a']++;
        }

        long total_count = 0;

        // count subsequences of the form XYYX
        for (int x = 0; x < 26; x++) {
            if (c_indices[x].length < 2) {
                // less than 2 x in the array, can't form palindrome
                continue;
            }

            for (int y = 0; y < 26; y++) {
                if (c_indices[y].length < 2) {
                    // less than 2 x in the array, can't form palindrome
                    continue;
                }

                long left_count = 0;

                // for each pair of Y's, count all the X's before ALL the Y's before the first Y,
                // and count all the X's after the second Y
                for (int i = 0; i < c_indices[y].length - 1; i++) {
                    int y_idx_left = c_indices[y][i];
                    int y_idx_right = c_indices[y][i+1];
                    left_count += left[y_idx_left][x];
                    total_count += left_count * right[y_idx_right][x];
                    total_count %= 1000000007;
                }
            }
        }

        System.out.println(total_count);
    }
}
