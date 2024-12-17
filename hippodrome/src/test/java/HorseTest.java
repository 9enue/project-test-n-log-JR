import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {

    @Test
    public void nullNameExc() {
        // пункт a.1
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1.0, 1.0));
        // пункт a.2
        assertEquals("Name cannot be null.", e.getMessage());
    }


    @ParameterizedTest
    @ValueSource(strings = {"", "  ", "\t\t", "\n\n"})
    public void blankNameExc(String name) {
        //пункт a.3
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1.0, 1.0));
        //пункт a.4
        assertEquals("Name cannot be blank.", e.getMessage());
    }

    @Test
    public void negativeSpeedExc() {
        //пункт a.5
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("test name", -1.0, 1.0));
        //пункт а.6
        assertEquals("Speed cannot be negative.", e.getMessage());
    }

    @Test
    public void negativeDistanceExc() {
        //пункт a.7
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("test name", 1.0, -1.0));
        //пункт a.8
        assertEquals("Distance cannot be negative.", e.getMessage());
    }

    //пункт b.1
    @Test
    public void getNameTest() {
        Horse horse = new Horse("testName", 1.0, 1.0);

        assertEquals("testName", horse.getName());
    }

    //пункт c.1
    @Test
    public void getSpeedTest() {
        Horse horse = new Horse("testName", 52.0, 1.0);

        assertEquals(52.0, horse.getSpeed());
    }

    //пункт d.1
    @Test
    public void getDistanceTest() {
        Horse horse = new Horse("testName", 1.0, 555.0);

        assertEquals(555.0, horse.getDistance());
    }

    //пункт d.2
    @Test
    public void getTwoParamDistanceTest() {
        Horse horse = new Horse("testName", 1.0);

        assertEquals(0, horse.getDistance());
    }

    //пункт e.1
    @Test
    public void getRandomMoveTest() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("testName", 10, 52).move();

            mockedStatic.verify(() -> Horse.getRandomDouble(eq(0.2), eq(0.9)));
        }
    }

    //пункт e.2
    @ParameterizedTest
    @ValueSource(doubles = {0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0})
    public void moveHaveFormula(double random) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("name", 15.0, 55.0);
            mockedStatic.when(() -> Horse.getRandomDouble(eq(0.2), eq(0.9))).thenReturn(random);

            horse.move();

            assertEquals(55.0 + 15.0 * random, horse.getDistance());
        }
    }
}
