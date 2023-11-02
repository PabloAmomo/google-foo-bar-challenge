import java.math.BigInteger;
import java.util.Arrays;

public class Solution {
  private static int paired(int a, int b) {
    if (a == b)
      return 0;

    if ((a + b) % 2 == 1)
      return 1;

    int gcd = BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).intValue();
    a = a / gcd;
    b = b / gcd;

    int temp = Math.min(a, b);
    return paired(Math.max(a, b) - temp, 2 * temp);
  }

  public static int solution(int[] banana_list) {
    int list_len = banana_list.length;
    int[][] pair_matrix = new int[list_len][list_len];

    for (int i = 0; i < list_len; i++) {
      for (int j = 0; j < list_len; j++) {
        if (i < j) {
          pair_matrix[i][j] = paired(banana_list[i], banana_list[j]);
          pair_matrix[j][i] = pair_matrix[i][j];
        }
      }
    }

    int result = 0;
    int[] matchs = new int[list_len];
    boolean[] checked = new boolean[list_len];

    Arrays.fill(matchs, -1);
    for (int i = 0; i < list_len; i++) {
      Arrays.fill(checked, false);

      if (bpm(pair_matrix, i, matchs, checked))
        result++;
    }
    return list_len - 2 * (result / 2);
  }

  private static boolean bpm(int[][] pair_matrix, int i, int[] matchs, boolean[] checked) {
    for (int j = 0; j < matchs.length; j++) {
      if (pair_matrix[i][j] == 1 && !checked[j]) {
        checked[j] = true;

        if (matchs[j] == -1 || bpm(pair_matrix, matchs[j], matchs, checked)) {
          matchs[j] = i;
          return true;
        }
      }
    }
    return false;
  }
}
