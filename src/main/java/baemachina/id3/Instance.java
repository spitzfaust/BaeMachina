package baemachina.id3;

import java.util.Map;

/**
 * Created by tobias.
 */
public interface Instance {
    String getLabel();

    Map<String, Enum> getAttributes();

    void removeAttribute(String attribute);

}
