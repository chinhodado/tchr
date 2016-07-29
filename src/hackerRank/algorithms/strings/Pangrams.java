package hackerRank.algorithms.strings;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Chin on 29-Jul-16.
 */
public class Pangrams {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        Character[] arr = in.nextLine().toLowerCase().chars().mapToObj(c -> (char)c).toArray(Character[]::new);
        Set<Character> s = new HashSet<>(Arrays.asList(arr));
        boolean isGood = true;
        for (char i = 'a'; i <= 'z'; i++) {
            if (!s.contains(i)) {
                isGood = false;
                break;
            }
        }
        System.out.println(isGood? "pangram" : "not pangram");
    }
}
