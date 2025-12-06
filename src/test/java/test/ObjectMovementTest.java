package test;

import ObjectMovement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObjectMovementTest {

    /**
     * Unit-тест инициализации объекта класса {@link ObjectMovement} и правильной работы с полученным аргументом скорости
     */
    @Test
    void testInit() {
        double speed = 20.0;
        ObjectMovement velocity = new ObjectMovement(speed);

        double VLen = Math.sqrt(velocity.getVx() * velocity.getVx() + velocity.getVy() * velocity.getVy());
        assertEquals(speed, VLen, 1e-6);
    }

    /**
     * Unit-тест нормализации длины вектора перемещения к скорости перемещения объекта
     * методом {@link ObjectMovement#normalizeToSpeed()}
     */
    @Test
    void testNormalizeToSpeed() {
        ObjectMovement velocity = new ObjectMovement(24);
        double x = velocity.getVx();
        double y = velocity.getVy();
        x = -x;
        y = -y;
        velocity.normalizeToSpeed();
        double len = Math.sqrt(x * x + y * y);
        assertEquals(velocity.getSpeed(), len, 1e-6);
    }

    /**
     * Unit-тест изменения движения по оси x
     * методом {@link ObjectMovement#invX()}
     */
    @Test
    void testInvX() {
        ObjectMovement velocity = new ObjectMovement(54);
        double x = velocity.getVx() * (-1);
        velocity.invX();
        assertEquals(x, velocity.getVx());
    }

    /**
     * Unit-тест изменения движения по оси y
     * методом {@link ObjectMovement#invY()}
     */
    @Test
    void testInvY() {
        ObjectMovement velocity = new ObjectMovement(18);
        double y = velocity.getVy() * (-1);
        velocity.invY();
        assertEquals(y, velocity.getVy());
    }

    /**
     * Unit-тест увеличения скорости объекта на некоторую величину
     * методом {@link ObjectMovement#accelerate(double)}
     */
    @Test
    void testAccelerate() {
        ObjectMovement velocity = new ObjectMovement(45);
        double val = 4.0;
        velocity.accelerate(val);
        double exp = 45 + val;
        assertEquals(exp,velocity.getSpeed());
    }

    /**
     * Unit-тест уменьшения скорости объекта на некоторую величину
     * методом {@link ObjectMovement#decelerate(double)}
     */
    @Test
    void testDecelerate() {
        ObjectMovement velocity = new ObjectMovement(37);
        double val = 7.0;
        velocity.decelerate(val);
        double exp = 37 - val;
        assertEquals(exp, velocity.getSpeed());
    }

}
