package hackerRank.algorithms.strings;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Chin on 29-Jul-16.
 */
public class Gemstones {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        List<Set<Character>> setList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Character[] arr = in.next().toLowerCase().chars().mapToObj(c -> (char)c).toArray(Character[]::new);
            Set<Character> s = new HashSet<>(Arrays.asList(arr));
            setList.add(s);
        }

        int count = 0;
        for (char i = 'a'; i <= 'z'; i++) {
            boolean isGood = true;
            for (Set w : setList) {
                if (!w.contains(i)) {
                    isGood = false;
                    break;
                }
            }

            if (isGood)
                count++;
        }
        System.out.println(count);
    }
}
