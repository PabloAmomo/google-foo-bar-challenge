import java.math.BigInteger;

public class Solution {
  private static final BigInteger TEN_POW_100 = BigInteger.TEN.pow(100);
  private static final BigInteger SCALING_SQRT2 = new BigInteger(
      "4142135623730950488016887242096980785696718753769480731766797379907324784621070388503875343276415727");

  public static BigInteger beattyTerm(BigInteger value) {
    if (value.equals(BigInteger.ONE))
      return value;

    if (value.compareTo(BigInteger.ONE) < 0)
      return BigInteger.ZERO;

    BigInteger nTemp = SCALING_SQRT2.multiply(value).divide(TEN_POW_100);
    BigInteger totalValues = value.multiply(value.add(BigInteger.ONE)).divide(BigInteger.TWO);
    BigInteger substract1 = nTemp.multiply(nTemp.add(BigInteger.ONE)).divide(BigInteger.TWO);
    BigInteger substract2 = beattyTerm(nTemp);

    return value.multiply(nTemp)
        .add(totalValues)
        .subtract(substract1)
        .subtract(substract2);
  }

  public static String solution(String n) {
    BigInteger nBigInteger = new BigInteger(n);
    return beattyTerm(nBigInteger).toString();
  }
}