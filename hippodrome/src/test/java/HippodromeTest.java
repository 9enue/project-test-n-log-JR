import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {

    @Test
    public void nullHorseExc() {
        // пункт а.1
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        // пункт а.2
        assertEquals("Horses cannot be null.", e.getMessage());
    }

    @Test
    public void blankHorseExc() {
        //пункт a.3
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        //пункт a.4
        assertEquals("Horses cannot be empty.", e.getMessage());
    }

    //пункт b.1
    @Test
    public void getHorsesTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            horses.add(new Horse("" + i, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);

        assertEquals(horses, hippodrome.getHorses());
    }

    //пункт c.1
    @Test
    public void moveTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }

        new Hippodrome(horses).move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    //пункт d.1
    @Test
    public void getWinnerTest() {
        Horse horse1 = new Horse("name1", 1, 1.1);
        Horse horse2 = new Horse("name2", 1, 1.2);
        Horse horse3 = new Horse("name3", 1, 1.3);
        Horse horse4 = new Horse("name4", 1, 1.4);
        Horse horse5 = new Horse("name5", 1, 1.5);
        Horse horse6 = new Horse("name6", 1, 1.6);
        Horse horse7 = new Horse("name7", 1, 1.7);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3, horse4, horse5, horse6, horse7));

        assertSame(horse7, hippodrome.getWinner());
    }
}
