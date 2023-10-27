public class Solution {
  private static boolean noContinue(int val1, int val2) {
    return (val1 < val2 || val1 % val2 != 0 || val1 < 1 || val1 > 999999);
  }

  public static int solution(int[] l) {

    if (l.length < 3)
      return 0;

    int count = 0;

    for (int i = 0; i < Math.min(l.length, 2001); i++) {
      if (l[i] < 1 || l[i] > 999999)
        continue;

      for (int j = (i + 1); j < Math.min(l.length, 2001); j++) {
        if (noContinue(l[j], l[i]))
          continue;

        for (int k = (j + 1); k < Math.min(l.length, 2001); k++) {
          if (noContinue(l[k], l[j]))
            continue;

          count++;
        }
      }
    }

    return count;
  }
  // solution end here
  // ------------------------------------------------------------------------------------------------------------

  // ------------------------------------------------------------------------------------------------------------
  // TESTING
  // ------------------------------------------------------------------------------------------------------------
  public static void main(String[] args) {
    int response = 0;

    response = solution(new int[] { 1, 1, 1 });
    System.out.println("value: {1,1,1} " + response); // 1

    response = solution(new int[] { 1, 2, 3, 4, 5, 6 });
    System.out.println("value: {1, 2, 3, 4, 5, 6} " + response); // 3
  }
}