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
    int response = 0;

    response = solution(13, 1);
    System.out.println("value: (13,1) " + response); // 13

    response = solution(17, 4);
    System.out.println("value: (17,4) " + response); // 14

    response = solution(1, 20000);
    System.out.println("value: (1,20000) " + response); // 391607840
  }
}
