package baemachina.id3;

import java.util.Map;

/**
 * Created by tobias.
 */
public interface Instance<LabelType extends Comparable> {
    LabelType getLabel();
    Map<String, Enum> getAttributes();
}
