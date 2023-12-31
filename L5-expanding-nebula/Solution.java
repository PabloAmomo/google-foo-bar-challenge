import java.util.HashMap;
import java.util.Map;

public class Solution {
    private static String complete(String string, int n) {
        return string.length() == n ? string : ("0").repeat(n - string.length()) + string;
    }

    public static int solution(boolean[][] g) {
        int gWidth = g[0].length;
        int gHeight = g.length;
        int finalHeight = g.length + 1;
        int combinationCount = (int) Math.pow(2, finalHeight);
        String[] combinations = new String[combinationCount];
        Map<String, Integer> permutations = new HashMap<>();
        Map<String, Integer> validValues = new HashMap<>();
        String[] values = new String[g[0].length];

        for (int i = 0; i < gWidth; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < gHeight; j++)
                sb.append(g[j][i] ? "1" : "0");
            values[i] = sb.toString();
        }

        String value = "";
        for (int i = 0; i < combinations.length; i++) {
            value = complete(Integer.toBinaryString(i), finalHeight);
            combinations[i] = value;
            permutations.put(value, 1);
        }

        String checking = "";
        String key = "";
        StringBuilder sb = new StringBuilder();
        Map<String, String> tempValues = new HashMap<>();
        for (String row : values) {
            validValues.clear();

            for (String value1 : permutations.keySet()) {
                for (String value2 : combinations) {
                    key = value1 + "-" + value2;
                    checking = "";

                    checking = tempValues.computeIfAbsent(key, k -> {
                        sb.setLength(0);
                        for (int idx = 0; idx < gHeight; idx++) {
                            sb.append(((value1.charAt(idx) - '0')
                                    + (value1.charAt(idx + 1) - '0')
                                    + (value2.charAt(idx) - '0')
                                    + (value2.charAt(idx + 1) - '0')) == 1 ? '1' : '0');
                        }
                        return sb.toString();
                    });

                    if (checking.equals(row))
                        validValues.put(value2, validValues.getOrDefault(value2, 0) + permutations.get(value1));
                }
            }
            permutations = new HashMap<>(validValues);
        }

        return validValues.values().stream().mapToInt(Integer::intValue).sum();
    }
}
