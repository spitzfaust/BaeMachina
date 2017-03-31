package baemachina.id3;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by tobias.
 */
public class ID3Impl implements ID3 {

    GainCalculator gainCalculator;
    EntropyCalculator entropyCalculator;

    public ID3Impl(GainCalculator gainCalculator, EntropyCalculator entropyCalculator) {
        this.gainCalculator = gainCalculator;
        this.entropyCalculator = entropyCalculator;
    }

    @Override
    public DecisionTree buildTree(final List<Instance> data) {
        return new DecisionTree(buildNodes(null, data));
    }

    private DecisionTree.Node buildNodes(final DecisionTree.Node parentNode, final List<Instance> data) {
        final Set<String> uniqueLabels = data.stream().map(Instance::getLabel).collect(Collectors.toSet());
        if (uniqueLabels.size() == 1) {
            return new DecisionTree.Node(parentNode, data.get(0).getLabel());
        } else if (data.get(0).getAttributes().size() == 0) {
            final String maxOccuringLabel = data.stream()
                    .collect(Collectors.groupingBy(Instance::getLabel, Collectors.counting()))
                    .entrySet().stream()
                    .max(Comparator.comparingLong(Map.Entry::getValue))
                    .map(Map.Entry::getKey)
                    .get();
            return new DecisionTree.Node(parentNode, maxOccuringLabel);
        }
        final Set<String> attributes = data.get(0).getAttributes().keySet();
        final String maxGainAttributeKey = attributes.stream()
                .max(Comparator.comparingDouble(attribute -> gainCalculator.calculate(data, attribute, entropyCalculator)))
                .get();
        final DecisionTree.Node currentNode = new DecisionTree.Node(parentNode, maxGainAttributeKey);

        final Map<Enum, DecisionTree.Node> children = data.stream()
                .filter(instance -> instance.getAttributes().containsKey(maxGainAttributeKey))
                .map(instance -> instance.getAttributes().get(maxGainAttributeKey))
                .distinct()
                .collect(Collectors.toMap(
                        attribute -> attribute,
                        attribute -> data.stream()
                                .filter(instance -> instance.getAttributes().containsKey(maxGainAttributeKey) && instance.getAttributes().get(maxGainAttributeKey).equals(attribute))
                                .map(i -> {
                                    i.removeAttribute(maxGainAttributeKey);
                                    return i;
                                })
                                .collect(Collectors.toList())
                        )
                ).entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> buildNodes(currentNode, e.getValue())));
        currentNode.setChildren(children);
        return currentNode;
    }
}
