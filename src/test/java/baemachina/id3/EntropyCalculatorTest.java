package baemachina.id3;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.within;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created by tobias.
 */
public class EntropyCalculatorTest {
    EntropyCalculator sut;
    @Before
    public void setUp() throws Exception {
        // Given
        sut = new EntropyCalculator();
    }

    @Test
    public void ItShallReturnTheCorrectValue() throws Exception {
        // Given
        List<String> data = Arrays.asList("a", "a", "a", "b", "b");

        // When
        final double result = sut.calculate(data);

        // Then
        assertThat(result).isCloseTo(0.97, within(0.001));
    }

}