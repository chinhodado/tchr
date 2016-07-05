package hackerRank;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class BeautifulBinaryString {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String s = in.next();
        int count = 0;

        for (int i = 0; i < n - 2; i++){
            if (s.charAt(i) == '0' && s.charAt(i + 1) == '1' && s.charAt(i + 2) == '0') {
                count++;
                i += 2;
            }
        }

        System.out.println(count);
    }
}
