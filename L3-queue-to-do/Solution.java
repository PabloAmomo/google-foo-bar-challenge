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
}
