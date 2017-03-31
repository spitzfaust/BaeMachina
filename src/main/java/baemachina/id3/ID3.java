package baemachina.id3;

import java.util.List;

/**
 * Created by tobias.
 */
public interface ID3 {
    DecisionTree buildTree(List<Instance> data);
}
