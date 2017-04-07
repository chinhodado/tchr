package hackerRank.contests.weekOfCode.w30;

import java.util.Scanner;

/**
 * Created by Chin on 15-Mar-17.
 */
public class MelodiousPassword {
    private static char[] consonants = new char[] {'b','c','d','f','g','h','j','k','l','m','n','p','q','r','s','t','v','w','x','z'};
    private static char[] vowels = new char[] { 'a', 'i', 'u', 'e', 'o'};
    private static StringBuilder sb = new StringBuilder();
    public static void main (String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

//        long start = System.currentTimeMillis();
        print(true, "", n);
        print(false, "", n);
        System.out.println(sb.toString());
//        long end = System.currentTimeMillis();
//        System.out.println("time: " + (end-start));
    }

    public static void print(boolean isVowel, String sofar, int numLeft) {
        if (numLeft == 0) {
//            System.out.println(sofar);
            sb.append(sofar).append("\n");
            return;
        }

        char[] firstLetters = isVowel? vowels : consonants;
        for (char c : firstLetters) {
            print(!isVowel, sofar + c, numLeft - 1);
        }
    }
}
