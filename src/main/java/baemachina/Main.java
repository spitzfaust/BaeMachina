package baemachina;

import java.util.HashMap;
import java.util.Map;

import baemachina.id3.Instance;
import baemachina.id3.InstanceImpl;

/**
 * Created by tobias.
 */
public class Main {
    public enum Test {
        HOT,
        MILD,
        COOL
    }

    public enum TestEnum2 {
        TOBI,
        MILD,
        COOL
    }

    public static void main(String[] args) {
        Map<String, Enum> testMap = new HashMap<>();
        testMap.put("test1", Test.COOL);
        testMap.put("test2", TestEnum2.TOBI);
        Instance<String> test = new InstanceImpl<>("test", testMap);
        System.out.println(test);
        System.out.println("Hello Tree!");
        System.out.println(Math.log(1) / Math.log(2));
    }
}
