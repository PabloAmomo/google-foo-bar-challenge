import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public static void cycleP(int size, int max, List<Integer> part, List<List<Integer>> result) {
        if (size == 0) {
            result.add(0, new ArrayList<>(part));
            return;
        }

        for (int i = 1; i <= Math.min(max, size); i++) {
            part.add(i);
            cycleP(size - i, i, part, result);
            part.remove(part.size() - 1);
        }
    }

    public static BigInteger cycleC(List<Integer> c, int n) {
        Map<Integer, Integer> counter = new HashMap<>();

        for (int value : c)
            counter.put(value, counter.getOrDefault(value, 0) + 1);

        int b;
        BigInteger response = factorial(n);
        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            b = entry.getValue();
            response = response.divide(BigInteger.valueOf(entry.getKey()).pow(b).multiply(factorial(b)));
        }

        return response;
    }

    public static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        if (n != 0) {
            for (int i = 1; i <= n; i++)
                result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    public static BigInteger calculate(BigInteger value, List<Integer> cpw, List<Integer> cph, int s) {
        BigInteger total = BigInteger.valueOf(0);
        for (int i : cpw) {
            for (int j : cph)
                total = total.add(BigInteger.valueOf(i).gcd(BigInteger.valueOf(j)));
        }
        total = BigInteger.valueOf(s).pow(total.intValue()).multiply(value);
        return total;
    }

    public static String solution(int w, int h, int s) {
        List<List<Integer>> wResult = new ArrayList<>();
        List<List<Integer>> hResult = new ArrayList<>();

        cycleP(w, w, new ArrayList<>(), wResult);
        cycleP(h, h, new ArrayList<>(), hResult);

        BigInteger tempValue = BigInteger.valueOf(0);
        for (List<Integer> partsW : wResult) {
            for (List<Integer> partsH : hResult) {
                tempValue = tempValue
                        .add(calculate(cycleC(partsW, w).multiply(cycleC(partsH, h)), partsW, partsH, s));
            }
        }

        return String.valueOf(tempValue.divide(factorial(w).multiply(factorial(h))));
    }
    // solution end here
    // ------------------------------------------------------------------------------------------------------------

    // ------------------------------------------------------------------------------------------------------------
    // TESTING
    // ------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {

        long[][][] test = {
                { { 2, 3, 4 }, { 430 } },
                { { 2, 2, 2 }, { 7 } },
                { { 1, 1, 1 }, { 1 } },
                { { 1, 1, 2 }, { 2 } },
                { { 1, 1, 3 }, { 3 } },
                { { 1, 1, 4 }, { 4 } },
                { { 1, 2, 1 }, { 1 } },
                { { 1, 2, 2 }, { 3 } },
                { { 1, 2, 3 }, { 6 } },
                { { 1, 2, 4 }, { 10 } },
                { { 1, 3, 1 }, { 1 } },
                { { 1, 3, 2 }, { 4 } },
                { { 1, 3, 3 }, { 10 } },
                { { 1, 3, 4 }, { 20 } },
                { { 1, 4, 1 }, { 1 } },
                { { 1, 4, 2 }, { 5 } },
                { { 1, 4, 3 }, { 15 } },
                { { 1, 4, 4 }, { 35 } },
                { { 2, 1, 1 }, { 1 } },
                { { 2, 1, 2 }, { 3 } },
                { { 2, 1, 3 }, { 6 } },
                { { 2, 1, 4 }, { 10 } },
                { { 2, 2, 1 }, { 1 } },
                { { 2, 2, 2 }, { 7 } },
                { { 2, 2, 3 }, { 27 } },
                { { 2, 2, 4 }, { 76 } },
                { { 2, 3, 1 }, { 1 } },
                { { 2, 3, 2 }, { 13 } },
                { { 2, 3, 3 }, { 92 } },
                { { 2, 3, 4 }, { 430 } },
                { { 2, 4, 1 }, { 1 } },
                { { 2, 4, 2 }, { 22 } },
                { { 2, 4, 3 }, { 267 } },
                { { 2, 4, 4 }, { 1996 } },
                { { 3, 1, 1 }, { 1 } },
                { { 3, 1, 2 }, { 4 } },
                { { 3, 1, 3 }, { 10 } },
                { { 3, 1, 4 }, { 20 } },
                { { 3, 2, 1 }, { 1 } },
                { { 3, 2, 2 }, { 13 } },
                { { 3, 2, 3 }, { 92 } },
                { { 3, 2, 4 }, { 430 } },
                { { 3, 3, 1 }, { 1 } },
                { { 3, 3, 2 }, { 36 } },
                { { 3, 3, 3 }, { 738 } },
                { { 3, 3, 4 }, { 8240 } },
                { { 3, 4, 1 }, { 1 } },
                { { 3, 4, 2 }, { 87 } },
                { { 3, 4, 3 }, { 5053 } },
                { { 4, 1, 1 }, { 1 } },
                { { 4, 1, 2 }, { 5 } },
                { { 4, 1, 3 }, { 15 } },
                { { 4, 1, 4 }, { 35 } },
                { { 4, 2, 1 }, { 1 } },
                { { 4, 2, 2 }, { 22 } },
                { { 4, 2, 3 }, { 267 } },
                { { 4, 2, 4 }, { 1996 } },
                { { 4, 3, 1 }, { 1 } },
                { { 4, 3, 2 }, { 87 } },
                { { 4, 3, 4 }, { 131505 } },
                { { 4, 4, 1 }, { 1 } },
                { { 4, 4, 2 }, { 317 } },
                { { 4, 4, 3 }, { 90492 } },
                { { 8, 8, 3 }, { -1 } },
                { { 12, 12, 8 }, { -2 } },
        };

        String response = "";
        for (int i = 0; i < test.length; i++) {
            long[][] value = test[i];
            String val[] = { "2130536585704570302966",
                    "48337501605818559862924286009469010532883944673595031561442959253297672970548096574261128112649493247389104674880" };
            response = solution((int) value[0][0], (int) value[0][1], (int) value[0][2]);
            System.out.println(
                    (String.valueOf(value[1][0] < 0 ? val[-1 - (int) value[1][0]] : value[1][0]).equals(response) ? "OK"
                            : "KO") + " { "
                            + Arrays.toString(value[0]) + " } -> Wait "
                            + (value[1][0] < 0 ? val[-1 - (int) value[1][0]] : value[1][0]) + " response " + response);
        }
    }
}