package hackerRank.algorithms.strings;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Chin on 30-Jul-16.
 */
public class CaesarCipher {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        char[] arr = in.next().toCharArray();
        int k = in.nextInt();
        for (int i = 0; i < arr.length; i++) {
            if ('a' <= arr[i] && arr[i] <= 'z') {
                arr[i] = (char)(((arr[i] - 'a' + k) % 26) + 'a');
            }
            else if ('A' <= arr[i] && arr[i] <= 'Z') {
                arr[i] = (char)(((arr[i] - 'A' + k) % 26) + 'A');
            }
        }
        System.out.println(new String(arr));
    }
}
