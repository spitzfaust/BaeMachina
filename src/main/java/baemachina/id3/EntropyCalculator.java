package baemachina.id3;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by tobias.
 */
public class EntropyCalculator {
    public <T extends Comparable> double calculate(List<T> data) {
        double sum = 0;
        final double totalCount = data.size();
        if (totalCount == 0) {
            return 0;
        }
        Map<T, Long> occurrences = data.stream()
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        for (Long labelOccurrence : occurrences.values()) {
            double probability = labelOccurrence / totalCount;
            double log = Math.log(probability) / Math.log(2);
            // 0 * log2(0) defined as 0
            sum += Double.isNaN(log) ? 0 : -probability * log;
        }
        return sum;
    }
}
