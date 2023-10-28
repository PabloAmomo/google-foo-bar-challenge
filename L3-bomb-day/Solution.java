import java.math.BigInteger;

public class Solution {
  public static String solution(String x, String y) {
    BigInteger f = new BigInteger(y), m = new BigInteger(x), temp, subs, count = new BigInteger("0");
    int diff;

    while (true) {
      diff = m.compareTo(f);
      if (diff == 0)
        break;

      temp = (diff == 1) ? m.subtract(f) : f.subtract(m);
      subs = temp.divide((diff == 1) ? f : m)
          .add(temp.remainder((diff == 1) ? f : m)
              .compareTo(BigInteger.ZERO) == 1 ? BigInteger.ONE : BigInteger.ZERO);

      if (diff == 1)
        m = m.subtract(subs.multiply(f));
      else
        f = f.subtract(subs.multiply(m));

      count = count.add(subs);
    }

    return m.toString().equals("1") ? count.toString() : "impossible";
  }
  // solution end here
  // ------------------------------------------------------------------------------------------------------------

  // ------------------------------------------------------------------------------------------------------------
  // TESTING
  // ------------------------------------------------------------------------------------------------------------
  public static void main(String[] args) {
    String[][] tests = { { "4", "7" }, { "2", "1" } };
    String[] responses = { "4", "1" };

    for (int i = 0; i < tests.length; i++) {
      printTest(i + 1, "" + solution(tests[i][0], tests[i][1]), "" + responses[i]);
    }
  }

  private static void printTest(int index, String response, String expected) {
    Boolean correct = (response.equals(expected));
    System.out.println("------------------------------------------");
    System.out.println(" Test " + index + " " + (correct ? "OK" : "KO") + " -> Response: " + response
        + (correct ? "" : " (Expected: " + expected + ")"));
  }
}