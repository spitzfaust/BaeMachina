package baemachina.id3;

import java.util.Map;

/**
 * Created by tobias.
 */
public class InstanceImpl<LabelType extends Comparable> implements Instance<LabelType> {
    protected LabelType label;
    protected Map<String, Enum> attributes;

    public InstanceImpl(LabelType label, Map<String, Enum> attributes) {
        this.label = label;
        this.attributes = attributes;
    }

    @Override
    public LabelType getLabel() {
        return label;
    }

    @Override
    public Map<String, Enum> getAttributes() {
        return attributes;
    }

    @Override
    public String toString() {
        return "InstanceImpl{" +
                "label=" + label +
                ", attributes=" + attributes +
                '}';
    }
}
