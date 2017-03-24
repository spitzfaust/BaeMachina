package baemachina.id3;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.within;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created by tobias.
 */
public class GainCalculatorTest {
    private GainCalculator sut;
    private enum Humidity {
        NORMAL,
        HIGH
    }
    @Before
    public void setUp() throws Exception {
        // Given
        sut = new GainCalculator();
    }

    @Test
    public void ItShallReturnTheCorrectValue() throws Exception {
        // Given
        HashMap<String, Enum> mapHigh = new HashMap<>();
        mapHigh.put("Humidity", Humidity.HIGH);
        HashMap<String, Enum> mapNormal = new HashMap<>();
        mapNormal.put("Humidity", Humidity.NORMAL);
        List<Instance<String>> data = Arrays.asList(
            new InstanceImpl<>("yes", mapNormal),
            new InstanceImpl<>("yes", mapNormal),
            new InstanceImpl<>("no", mapNormal),
            new InstanceImpl<>("yes", mapHigh),
            new InstanceImpl<>("no", mapHigh)
        );

        // When
        final double result = sut.calculate(data, "Humidity", new EntropyCalculator());

        // Then
        assertThat(result).isCloseTo(0.019, within(0.001));

    }

}