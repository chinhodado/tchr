package hackerRank;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TriangleCounting {

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("C:\\Users\\Chin\\Downloads\\input16.txt"));
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        int[] sqr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            sqr[i] = arr[i] * arr[i];
        }
        long nRight = 0, nAcute = 0, nObtuse = 0;
        for (int i = 0; i < n - 2; i++) {
            // thanks to j always be ascending, we can retain these values and increase them
            // for each successive j. Save a lot of time!
            int k = i + 2, l = i + 2;
            for (int j = i + 1; j < n - 1; j++) {
                // get the index of the last possible triangle (k - 1)
                while (k < n && arr[k] < arr[i] + arr[j]) {
                    k++;
                }
                // get the index of the last acute triangle (l - 1)
                while (l < n && sqr[l] < sqr[i] + sqr[j]) {
                    l++;
                }
                // j + 1 to k - 1: valid triangles
                // j + 1 to l - 1: acute triangle
                // l to k - 1: obtuse + maybe 1 right
                nAcute += l - j - 1;
                if (l < n && sqr[l] == sqr[i] + sqr[j]) {
                    nRight++;
                    l++;
                }
                nObtuse += k - l;
            }
        }
        System.out.print(nAcute + " " + nRight + " " + nObtuse);
    }
}