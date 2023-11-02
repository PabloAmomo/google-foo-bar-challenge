import java.math.BigInteger;
import java.util.ArrayList;
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
}