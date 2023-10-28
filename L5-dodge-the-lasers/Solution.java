import java.math.BigInteger;

public class Solution {
  // Use Beatty Sequence https://en.wikipedia.org/wiki/Beatty_sequence
  // Implementation ->
  // http://math.stackexchange.com/questions/2052179/how-to-find-sum-i-1n-left-lfloor-i-sqrt2-right-rfloor-a001951-a-beatty-s
  // Used for scaling and large numbers calculations
  //
  // ------------------------------------------------------------------------------------------------------------
  // The code calculates the nth term of the cumulative sum of the Beatty Sequence
  // for sqrt(2). Here's the purpose and functionality of each variable:
  //
  // n1Value:
  // -----------------
  // Purpose: Represents the nth term of the Beatty Sequence for the given value
  // value.
  // Functionality: This variable computes the product of a value with a scaled
  // approximation of sqrt(2). It then divides by 10^100 to scale it back into the
  // original range. The outcome is the nth term of the Beatty Sequence for
  // sqrt(2) for the number value.
  //
  // totalValues:
  // -----------------
  // Purpose: Represents the cumulative sum of numbers from 1 to value.
  // Functionality: Computes the sum of the first value natural numbers using the
  // formula n(n+1)/2.

  // substract1:
  // -----------------
  // Purpose: Represents the cumulative sum of numbers from 1 to n1Value. This is
  // used to adjust the computation of the nth term of the Beatty Sequence.
  // Functionality: Computes the sum of the first n1Value natural numbers, in a
  // similar manner as totalValues, but for n1Value.
  //
  // substract2:
  // -----------------
  // Purpose: Provides the previous accumulated value needed to compute the
  // current cumulative value.
  // Functionality: Recursively calls the beattyTerm function with the value of
  // n1Value to get the cumulative sum of the Beatty Sequence up to the term
  // n1Value.
  //
  // The implementation leverages properties of the Beatty Sequence for sqrt(2) to
  // reduce the computational cost associated with directly calculating the sum of
  // the Beatty Sequence for a large number.
  //
  //
  // n1Value:
  // n1Value is essentially the floor value of n * sqrt(2).
  // Using scaled computation, this becomes:
  // n1Value = (n * SCALING_SQRT2) / (10^100)
  //
  // totalValues:
  // This is the sum of the first n natural numbers, given by:
  // totalValues = n * (n + 1) / 2
  //
  // substract1:
  // This is the sum of the first n1Value natural numbers:
  // substract1 = n1Value * (n1Value + 1) / 2
  //
  // substract2:
  // This is a recursive call to compute the cumulative sum of the Beatty Sequence
  // for n1Value. It can be viewed as:
  // substract2 = beattyTerm(n1Value)
  //
  // Thus, the cumulative sum of the Beatty Sequence for n is:
  // Beatty Sequence Sum(n) = n * n1Value + totalValues - substract1 - substract2
  //
  // ------------------------------------------------------------------------------------------------------------
  private static final BigInteger TEN_POW_100 = BigInteger.TEN.pow(100);
  private static final BigInteger SCALING_SQRT2 = new BigInteger(
      "4142135623730950488016887242096980785696718753769480731766797379907324784621070388503875343276415727");

  public static BigInteger beattyTerm(BigInteger value) {
    if (value.equals(BigInteger.ONE))
      return value;

    if (value.compareTo(BigInteger.ONE) < 0)
      return BigInteger.ZERO;

    BigInteger n1Value = SCALING_SQRT2.multiply(value).divide(TEN_POW_100);
    BigInteger totalValues = value.multiply(value.add(BigInteger.ONE)).divide(BigInteger.TWO);
    BigInteger substract1 = n1Value.multiply(n1Value.add(BigInteger.ONE)).divide(BigInteger.TWO);
    BigInteger substract2 = beattyTerm(n1Value);

    return value.multiply(n1Value)
        .add(totalValues)
        .subtract(substract1)
        .subtract(substract2);
  }

  public static String solution(String n) {
    BigInteger nBigInteger = new BigInteger(n);
    return beattyTerm(nBigInteger).toString();
  }

  // ------------------------------------------------------------------------------------------------------------
  // Deprecated because is to much slow with large numbers
  // (Simpliest solution is not valid !!!)
  // ------------------------------------------------------------------------------------------------------------
  // Need high precision for great numbers (valid numbers: positive integer's
  // between 1 and 10^100)
  // private static final BigInteger SQRT2 = new
  // BigInteger("14142135623730950488016887242096980785696718753769480731766797379907324784621070388503875343276415727");
  // public static String solution(String s) {
  // BigInteger sBigInteger = new BigInteger(s);
  // if (sBigInteger.compareTo(BigInteger.ONE) <= 0)
  // return s;
  //
  // BigInteger response = BigInteger.ZERO;
  //
  // for (BigInteger i = BigInteger.ONE; i.compareTo(sBigInteger) <= 0; i =
  // i.add(BigInteger.ONE)) {
  // response = response.add(i.multiply(SQRT2).divide(TEN_POW_100));
  // }
  //
  // return response.toString();
  // }
  //
  // solution end here
  // ------------------------------------------------------------------------------------------------------------

  // ------------------------------------------------------------------------------------------------------------
  // TESTING
  // ------------------------------------------------------------------------------------------------------------
  public static void main(String[] args) {
    String resp = "";

    resp = solution("5");
    System.out.println((resp.equals("19") ? "OK" : "KO") + " " + resp + " -> " + "19");

    resp = solution("77");
    System.out.println((resp.equals("4208") ? "OK" : "KO") + " " + resp + " -> " + "4208");

    resp = solution("100000000000000000000000000000000000000000000000000000000000000");
    System.out.println(
        (resp.equals(
            "7071067811865475244008443621048490392848359376884740365883398710664340510965287634336373882123111792158663000001965143958611")
                ? "OK"
                : "KO")
            + " " + resp + " -> "
            + "7071067811865475244008443621048490392848359376884740365883398710664340510965287634336373882123111792158663000001965143958611");
  }
}