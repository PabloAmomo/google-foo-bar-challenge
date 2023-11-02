import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Solution {

  public static void addPositionInt(List<double[]> positions, int[] position, int x, int y, int max, int type) {
    double dist = Math.sqrt(Math.pow(position[0] - x, 2) + Math.pow(position[1] - y, 2));
    if (dist <= max && dist > 0)
      positions.add(new double[] { x, y, dist, type });
  }

  public static void addPosition(List<double[]> positions, int[] position, int x, int y, int max, int type) {
    addPositionInt(positions, position, x, y, max, type);
    addPositionInt(positions, position, x, -y, max, type);
    addPositionInt(positions, position, -x, -y, max, type);
    addPositionInt(positions, position, -x, y, max, type);
  }

  public static int solution(int[] dimensions, int[] your_position, int[] trainer_position, int distance) {
    int maxX = (int) Math.ceil((your_position[0] + distance) / dimensions[0]) + 1;
    int maxY = (int) Math.ceil((your_position[1] + distance) / dimensions[1]) + 1;
    List<double[]> positions = new ArrayList<>();
    int[] tempYour = { 0, 0 };
    int[] tempTrainer = { 0, 0 };

    addPosition(positions, your_position, your_position[0], your_position[1], distance, 0);
    addPosition(positions, your_position, trainer_position[0], trainer_position[1], distance, 1);

    for (int[] values : new int[][] { { 0, 0 }, { 0, dimensions[1] }, { dimensions[0], 0 },
        { dimensions[0], dimensions[1] } }) {
      addPositionInt(positions, your_position, values[0], values[1], distance, 2);
    }

    for (int i = 0; i < maxX; i++) {
      for (int j = 0; j < maxY; j++) {

        if (i == 0 && j == 0)
          continue;

        tempYour[0] = your_position[0] + (i * dimensions[0])
            + ((i % 2 != 0) ? -(2 * your_position[0]) + dimensions[0] : 0);
        tempYour[1] = your_position[1] + (j * dimensions[1])
            + ((j % 2 != 0) ? -(2 * your_position[1]) + dimensions[1] : 0);

        tempTrainer[0] = trainer_position[0] + (i * dimensions[0])
            + ((i % 2 != 0) ? -(2 * trainer_position[0]) + dimensions[0] : 0);

        tempTrainer[1] = trainer_position[1] + (j * dimensions[1])
            + ((j % 2 != 0) ? -(2 * trainer_position[1]) + dimensions[1] : 0);

        addPosition(positions, your_position, tempYour[0], tempYour[1], distance, 0);
        addPosition(positions, your_position, tempTrainer[0], tempTrainer[1], distance, 1);
      }
    }

    Collections.sort(positions, new Comparator<double[]>() {
      public int compare(double[] a, double[] b) {
        return (a[2] > b[2]) ? 1 : ((a[2] < b[2]) ? -1 : 0);
      }
    });

    Map<Double, double[]> angles = new HashMap<>();
    for (int i = 0; i < positions.size(); i++) {
      double[] pos = positions.get(i);
      angles.putIfAbsent(Math.atan2(pos[1] - your_position[1], pos[0] - your_position[0]), pos);
    }

    return (int) angles.values().stream().filter(position -> position[3] == 1).count();
  }
}