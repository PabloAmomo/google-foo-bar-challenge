import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    public static int[] solution(int h, int[] q) {
        int label = 0;
        int level = 0;
        List<Integer> response = new ArrayList<Integer>();
        int maxValue = (1 << h) - 1;
        List<List<Integer>> levels = Stream.generate(ArrayList<Integer>::new)
                .limit(h)
                .collect(Collectors.toList());

        while (label != maxValue) {
            label++;
            levels.get(level).add(label);

            if (levels.get(level).size() % 2 == 0) {
                level++;
            } else if (level > 0 && levels.get(level - 1).size() % 2 != (1 << (levels.size() - level - 2))) {
                int i = 0;
                while (i < levels.size() && levels.get(i).size() == (1 << (levels.size() - i - 1))) {
                    i++;
                }
                level = i;
            }
        }

        for (int item : q) {
            for (int i = 0; i < levels.size(); i++) {
                if (levels.get(i).contains(item)) {
                    int toAdd = (i == levels.size() - 1) ? -1
                            : levels.get(i + 1).get((int) Math.floor(levels.get(i).indexOf(item) / 2));
                    response.add(toAdd);
                    break;
                }
            }
        }

        return response.stream().mapToInt(Integer::intValue).toArray();
    }
    // solution end here
    // ------------------------------------------------------------------------------------------------------------

    // ------------------------------------------------------------------------------------------------------------
    // TESTING
    // ------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {

        List<Test> tests = new ArrayList<>();
        tests.add(new Test(5, new int[] { 7, 3, 5, 1 }, new int[] { 15, 7, 6, 3 }));
        tests.add(new Test(3, new int[] { 7, 3, 5, 1 }, new int[] { -1, 7, 6, 3 }));
        tests.add(new Test(5, new int[] { 19, 14, 28 }, new int[] { 21, 15, 29 }));

        for (int index = 0; index < tests.size(); index++) {
            Test item = tests.get(index);
            printTest(index + 1, Arrays.toString(solution(item.h, item.q)), Arrays.toString(item.response));
        }
    }

    private static void printTest(int index, String response, String expected) {
        Boolean correct = (response.equals(expected));
        System.out.println("------------------------------------------");
        System.out.println(" Test " + index + " " + (correct ? "OK" : "KO") + " -> Response: " + response
                + (correct ? "" : " (Expected: " + expected + ")"));
    }

    static class Test {
        int h;
        int[] q;
        int[] response;

        Test(int h, int[] q, int[] response) {
            this.h = h;
            this.q = q;
            this.response = response;
        }
    }
}
