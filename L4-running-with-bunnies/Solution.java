import java.util.Arrays;

public class Solution {
  public static int[] solution(int[][] times, int time_limit) {

    // ----------------------------------------------------------------------------------------------------------
    // On Work
    // ----------------------------------------------------------------------------------------------------------
    return new int[]{0, 1};
  }
  // solution end here
  // ------------------------------------------------------------------------------------------------------------

  // ------------------------------------------------------------------------------------------------------------
  // TESTING
  // ------------------------------------------------------------------------------------------------------------
  public static void main(String[] args) {

    int[][][][] tests = {
        { { {0, 1, 1, 1, 1}, {1, 0, 1, 1, 1}, {1, 1, 0, 1, 1}, {1, 1, 1, 0, 1}, {1, 1, 1, 1, 0} }, { { 3 } } },
        { { {0, 2, 2, 2, -1}, {9, 0, 2, 2, -1}, {9, 3, 0, 2, -1}, {9, 3, 2, 0, -1}, {9, 3, 2, 2, 0} }, { { 1 } } },
    };
    int[][][] responses = {
        { { 0, 1 } },
        { { 1, 2 } },
    };

    int[] response = {};
    for (int i = 0; i < tests.length; i++) {
      response = solution(tests[i][0], tests[i][1][0][0]);
      printTest(i + 1, Arrays.toString(response), Arrays.toString(responses[i][0]));
    }
  }

  private static void printTest(int index, String response, String expected) {
    Boolean correct = (response.equals(expected));
    System.out.println("------------------------------------------");
    System.out.println(" Test " + index + " " + (correct ? "OK" : "KO") + " -> Response: " + response
        + (correct ? "" : " (Expected: " + expected + ")"));
  }
};
