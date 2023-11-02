import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    public static int[] solution(int h, int[] q) {
        int label = 0;
        int level = 0;
        List<Integer> response = new ArrayList<Integer>();
        int maxValue = (1 << h) - 1;
        List<List<Integer>> levels = Stream.generate(ArrayList<Integer>::new)
                .limit(h)
                .collect(Collectors.toList());

        while (label != maxValue) {
            label++;
            levels.get(level).add(label);

            if (levels.get(level).size() % 2 == 0) {
                level++;
            } else if (level > 0 && levels.get(level - 1).size() % 2 != (1 << (levels.size() - level - 2))) {
                int i = 0;
                while (i < levels.size() && levels.get(i).size() == (1 << (levels.size() - i - 1))) {
                    i++;
                }
                level = i;
            }
        }

        for (int item : q) {
            for (int i = 0; i < levels.size(); i++) {
                if (levels.get(i).contains(item)) {
                    int toAdd = (i == levels.size() - 1) ? -1
                            : levels.get(i + 1).get((int) Math.floor(levels.get(i).indexOf(item) / 2));
                    response.add(toAdd);
                    break;
                }
            }
        }

        return response.stream().mapToInt(Integer::intValue).toArray();
    }
}
