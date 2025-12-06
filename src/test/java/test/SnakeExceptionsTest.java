package test;

import Snake.SnakeExceptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SnakeExceptionsTest {

    /**
     * Unit-тест проверки полученного значение длины змейки 
     * методом {@link SnakeExceptions#checkLength(int)}
     */
    @Test
    void testCheckLength() {
        assertDoesNotThrow(() -> SnakeExceptions.checkLength(1));
        assertDoesNotThrow(() -> SnakeExceptions.checkLength(45));

        assertThrows(IllegalArgumentException.class, () -> SnakeExceptions.checkLength(0));
        assertThrows(IllegalArgumentException.class, () -> SnakeExceptions.checkLength(-8));
    }

    /**
     * Unit-тест проверки полученного значения скорости змейки 
     * методом {@link SnakeExceptions#checkSpeed(double)}
     */
    @Test
    void testCheckSpeed() {
        assertDoesNotThrow(() -> SnakeExceptions.checkSpeed(4.0));
        assertDoesNotThrow(() -> SnakeExceptions.checkSpeed(19));

        assertThrows(IllegalArgumentException.class, () -> SnakeExceptions.checkSpeed(-0.7));
        assertThrows(IllegalArgumentException.class, () -> SnakeExceptions.checkSpeed(0));
    }

}
