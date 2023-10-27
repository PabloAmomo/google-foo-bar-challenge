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
    System.out.println("{4,7}: " + solution("4", "7"));
    System.out.println("{2,1}: " + solution("2", "1"));
  }
}