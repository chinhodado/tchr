package hackerRank.contests.worldCodeSprint.wcs7;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Chin on 24-Sep-16.
 */
public class TwoCharacters {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Set<Character> set = new HashSet<>();
        String s = in.next();

        boolean done = false;
        while (!done) {
            done = true;
            for (int i = 0; i < s.length(); i++) {
                if (i < s.length() - 1) {
                    if (s.charAt(i) == s.charAt(i+1)) {
                        set.add(s.charAt(i));
                        done = false;
                    }
                }
            }

            char[] tmp = s.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (char c : tmp) {
                if (!set.contains(c)) {
                    sb.append(c);
                }
            }
            s = sb.toString();
        }

        char[] arr = s.toCharArray();
        set.clear();
        for (char c : arr) {
            set.add(c);
        }

        Character last = null;
        List<Character> list = new ArrayList<>(set);
        int size = 0, maxSize = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                size = 0;
                boolean isGood = true;
                for (char c : arr) {
                    if (!list.get(i).equals(c) && !list.get(j).equals(c)) {
                        continue;
                    } else if (last == null) {
                        last = c;
                        size++;
                    } else if (list.get(i).equals(c)) {
                        if (last.equals(c)) {
                            isGood = false;
                            break;
                        } else {
                            last = c;
                            size++;
                        }
                    } else if (list.get(j).equals(c)) {
                        if (last.equals(c)) {
                            isGood = false;
                            break;
                        } else {
                            last = c;
                            size++;
                        }
                    }
                }

                if (isGood) {
                    maxSize = Math.max(maxSize, size);
                }
            }
        }

        System.out.println(maxSize);
    }
}
