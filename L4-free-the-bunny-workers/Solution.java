import java.util.ArrayList;
import java.util.List;

public class Solution {
  public static int[][] solution(int num_buns, int num_required) {
    int[][] result = new int[num_buns][];
    int keyCopies = num_buns - num_required + 1;
    int key = 0;

    List<List<Integer>> keyrings = new ArrayList<>();
    for (int i = 0; i < num_buns; i++)
      keyrings.add(new ArrayList<>());

    for (List<Integer> combination : combinations(num_buns, keyCopies)) {
      for (int bunny : combination)
        keyrings.get(bunny).add(key);
      key++;
    }

    for (int i = 0; i < num_buns; i++)
      result[i] = keyrings.get(i).stream().mapToInt(Integer::intValue).toArray();

    return result;
  }

  public static List<List<Integer>> combinations(int num_buns, int keyCopies) {
    List<List<Integer>> results = new ArrayList<>();
    combine(num_buns, keyCopies, 0, new ArrayList<>(), results);
    return results;
  }

  private static void combine(int num_buns, int keyCopies, int start, List<Integer> temporal, List<List<Integer>> results) {
    if (keyCopies == 0) {
      results.add(new ArrayList<>(temporal));
      return;
    }
    for (int i = start; i < num_buns; i++) {
      temporal.add(i);
      combine(num_buns, keyCopies - 1, i + 1, temporal, results);
      temporal.remove(temporal.size() - 1);
    }
  }
};
