
package baemachina.id3;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import baemachina.Pair;

/**
 * Created by tobias.
 */
public class GainCalculator {
    public double calculate(final List<Instance> data, final String attributeName, final EntropyCalculator entropyCalculator) {
        final double dataLength = data.size();
        final double totalEntropy = entropyCalculator.calculate(data.stream().map(Instance::getLabel).collect(Collectors.toList()));
        final List<Pair<Enum, String>> attributeValueOutcomes = data.stream()
                .filter(i -> i.getAttributes().containsKey(attributeName))
                .map(i -> new Pair<>(i.getAttributes().get(attributeName), i.getLabel()))
                .collect(Collectors.toList());
        final Set<Enum> attributeValues = attributeValueOutcomes.stream()
                .map(Pair::getFirst)
                .collect(Collectors.toSet());
        double sum = 0;
        for (Enum attributeValue : attributeValues) {
            final List<String> outcomes = attributeValueOutcomes.stream()
                    .filter(a -> a.getFirst() == attributeValue)
                    .map(Pair::getSecond)
                    .collect(Collectors.toList());
            sum += -(outcomes.size() / dataLength) * entropyCalculator.calculate(outcomes);
        }
        return totalEntropy + sum;


    }
}
