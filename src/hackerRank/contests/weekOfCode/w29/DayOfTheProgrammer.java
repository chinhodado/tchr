package hackerRank.contests.weekOfCode.w29;

import java.util.Scanner;

/**
 * Created by Chin on 21-Feb-17.
 */
public class DayOfTheProgrammer {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        String result = null;

        if (n <= 1917) {
            if (n % 4 == 0) {
                result = "12.09." + n;
            }
            else {
                result = "13.09." + n;
            }
        }
        else if (n == 1918) {
            result = "26.09." + n;
        }
        else {
            if (isLeapYear(n)) {
                result = "12.09." + n;
            }
            else {
                result = "13.09." + n;
            }
        }

        System.out.println(result);
    }

    private static boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        }

        if (year % 4 == 0 && year % 100 != 0) {
            return true;
        }

        return false;
    }
}
