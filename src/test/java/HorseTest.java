import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;


import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertEquals;

public class HorseTest {

    @Test
    public void nullHorse() throws Exception {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 2.9);
        });
        assertEquals("Name cannot be null.", throwable.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {
            "", " ", "\t", "     "
    })
    public void blankHorse(String name) throws Exception {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(name, 2.9);
        });
        assertEquals("Name cannot be blank.", throwable.getMessage());
    }

    @Test
    public void NegativeSpeedHorse() throws Exception {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Red", -184.07);
        });
        assertEquals("Speed cannot be negative.", throwable.getMessage());
    }
    @Test
    public void NegativeDistanceHorse() throws Exception {
        Throwable throwable = assertThrows(IllegalArgumentException.class, () -> {
            new Horse("Black", 2.74, -6360.747);
        });
        assertEquals("Distance cannot be negative.", throwable.getMessage());
    }
    @Test
    public void getName() throws Exception {
        String name = "Ghost";
        Horse horse = new Horse(name, 5.7);
        assertEquals(name, horse.getName());
    }
    @Test
    public void getSpeed() throws Exception {
        double speed = 7.2;
        Horse horse = new Horse("White", speed);
        assertEquals(speed, horse.getSpeed(), 0.002);
    }

    @Test
    public void getDistance() throws Exception {
        double distance = 247.5;
        Horse horse = new Horse("Red", 8.5, distance);
        assertEquals(distance, horse.getDistance(), 0.0);
    }

    @Test
    public void move() throws Exception {
        try(MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            Horse horse = new Horse("Red", 2.86);
            horse.move();
            //assertEquals(horse.getSpeed() * 0.5, horse.getDistance(), 0.00);
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }
}
