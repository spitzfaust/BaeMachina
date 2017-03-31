package baemachina.id3;

import java.util.Map;

/**
 * Created by tobias.
 */
public class InstanceImpl implements Instance {
    protected String label;
    protected Map<String, Enum> attributes;

    public InstanceImpl(String label, Map<String, Enum> attributes) {
        this.label = label;
        this.attributes = attributes;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public Map<String, Enum> getAttributes() {
        return attributes;
    }

    @Override
    public void removeAttribute(String attribute) {
        attributes.remove(attribute);
    }

    @Override
    public String toString() {
        return String.format("InstanceImpl{%n label='%s', %n attributes='%s'%n}", label, attributes);
    }
}
