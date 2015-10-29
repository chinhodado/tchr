package topCoder;

public class ABBA {
    public static String canObtain(String initial, String target) {
        while (target.length() != initial.length()) {
            if (target.charAt(target.length() - 1) == 'A') {
                target = target.substring(0, target.length() - 1);
            }
            else {
                target = target.substring(0, target.length() - 1);
                target = new StringBuilder(target).reverse().toString();
            }
        }
        if (target.equals(initial)) {
            return "Possible";
        }
        else {
            return "Impossible";
        }
    }
}
