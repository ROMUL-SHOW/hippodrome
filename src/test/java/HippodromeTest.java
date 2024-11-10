import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;

public class HippodromeTest {
    @Test
    public void nullHorses() throws Exception {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
        assertEquals("Horses cannot be null.", throwable.getMessage());
    }

    @Test
    public void emptyHorses() throws Exception {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(new ArrayList<Horse>());
        });
        assertEquals("Horses cannot be empty.", throwable.getMessage());
    }

    @Test
    public void getHorses() throws Exception {
        List<Horse> horses = new ArrayList<>();         // 0
        Collections.addAll(horses
                , new Horse("A", 2.1), new Horse("B", 2.2), new Horse("C", 2.3)
                , new Horse("D", 2.4), new Horse("E", 2.5), new Horse("F", 2.6)
                , new Horse("G", 2.7), new Horse("H", 2.8), new Horse("I", 2.9)
                , new Horse("J", 3.0));    // 10
        Collections.addAll(horses
                , new Horse("U", 4.1), new Horse("V", 4.2), new Horse("W", 4.3)
                , new Horse("X", 4.4), new Horse("Y", 4.5), new Horse("Z", 4.6)
                , new Horse("a", 4.7), new Horse("b", 4.8), new Horse("c", 4.9)
                , new Horse("d", 5.0));    // 20
        Collections.addAll(horses
                , new Horse("e", 4.1), new Horse("f", 4.2), new Horse("g", 4.3)
                , new Horse("h", 4.4), new Horse("i", 4.5), new Horse("j", 4.6)
                , new Horse("k", 4.7), new Horse("l", 4.8), new Horse("m", 4.9)
                , new Horse("n", 5.0));    // 30
        Hippodrome hippodrome = new Hippodrome(horses);
        Assertions.assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void getWinner() throws Exception {
        double maxDistance = 972.54;
        List<Horse> horses = new ArrayList<>();
        Collections.addAll(horses, new Horse("K", 3.1, 654.23), new Horse("L", 3.2, 762.67), new Horse("M", 3.3, 795.35)
                , new Horse("N", 3.4, 153.45), new Horse("O", 3.5, 456.89), new Horse("P", 3.6, 367.79)
                , new Horse("Q", 3.7, 465.78), new Horse("R", 3.8, 972.54), new Horse("S", 3.9, 279.54)
                , new Horse("T", 4.0, 688.45));    // "R" has got max distance;
        Hippodrome hippodrome = new Hippodrome(horses);
        double actual = hippodrome.getWinner().getDistance();
        Assertions.assertEquals(maxDistance, actual);
    }

    @Test
    public void move() {
        List<Horse> horseList = new ArrayList<>();
        for(int i = 0; i < 50; i++){
            horseList.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        hippodrome.move();
        for(Horse horse : horseList) {
            Mockito.verify(horse).move();
        }
    }
}
