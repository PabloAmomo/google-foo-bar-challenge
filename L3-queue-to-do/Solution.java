public class Solution {
  public static int solution(int start, int length) {

    if (length == 1)
      return start;

    int response = 0;

    int value = 0;
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < length - i; j++) {
        value = start + (i * length) + j;
        if (value >= 0 || value <= 2000000000)
          response = response ^ value;
      }
    }

    return response;
  }
  // solution end here
  // ------------------------------------------------------------------------------------------------------------

  // ------------------------------------------------------------------------------------------------------------
  // TESTING
  // ------------------------------------------------------------------------------------------------------------
  public static void main(String[] args) {

    int[][] tests = { { 13, 1, 13 }, { 17, 4, 14 }, { 1, 20000, 391607840 } };
    
    for (int i = 0; i < tests.length; i++) {
      printTest(i + 1, "" + solution(tests[i][0], tests[i][1]), "" + tests[i][2]);
    }
  }

  private static void printTest(int index, String response, String expected) {
    Boolean correct = (response.equals(expected));
    System.out.println("------------------------------------------");
    System.out.println(" Test " + index + " " + (correct ? "OK" : "KO") + " -> Response: " + response
        + (correct ? "" : " (Expected: " + expected + ")"));
  }
}
