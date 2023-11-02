import java.math.BigInteger;
import java.util.Arrays;

public class Solution {
    public static String solution(int[] xs) {

        if (xs == null || xs.length == 0) {
            return "";
        }

        if (xs.length == 1) {
            return String.valueOf(xs[0]);
        }

        int[] sortedXs = Arrays.stream(xs).filter(item -> item != 0).toArray();

        BigInteger response = BigInteger.ONE;
        int maxNegative = Integer.MIN_VALUE;
        int positives = 0;
        int negatives = 0;

        for (int val : sortedXs) {
            if (val < 0) {
                negatives++;
                maxNegative = Math.max(maxNegative, val);
            } else {
                positives++;
            }
            response = response.multiply(BigInteger.valueOf(val));
        }

        if (response.equals(BigInteger.ZERO)) {
            return "0";
        }

        if (response.compareTo(BigInteger.ZERO) < 0) {
            if (positives == 0 && negatives < xs.length) {
                return "0";
            } else if (positives > 0) {
                response = response.divide(BigInteger.valueOf(maxNegative));
            }
        }

        return response.toString();
    }
}