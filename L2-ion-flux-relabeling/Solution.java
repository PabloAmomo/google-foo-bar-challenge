import java.util.ArrayList;
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
        List<Problem> problems = new ArrayList<>();
        problems.add(new Problem(5, new int[] { 7, 3, 5, 1 }, new int[] { 15, 7, 6, 3 }));
        problems.add(new Problem(3, new int[] { 7, 3, 5, 1 }, new int[] { -1, 7, 6, 3 }));
        problems.add(new Problem(5, new int[] { 19, 14, 28 }, new int[] { 21, 15, 29 }));

        for (int index = 0; index < problems.size(); index++) {
            Problem item = problems.get(index);
            int[] resp = solution(item.h, item.q);
            System.out.println("Problem " + index + ": (Expected: " + arrayToString(item.response) + ") -> "
                    + arrayToString(resp) +
                    (arraysAreEqual(item.response, resp) ? " OK" : " BAD !!!"));
        }
    }

    static class Problem {
        int h;
        int[] q;
        int[] response;

        Problem(int h, int[] q, int[] response) {
            this.h = h;
            this.q = q;
            this.response = response;
        }
    }

    // Función para convertir un arreglo de enteros en una cadena
    static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // Función para verificar si dos arreglos de enteros son iguales
    static boolean arraysAreEqual(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
}
