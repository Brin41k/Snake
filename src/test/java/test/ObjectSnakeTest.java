package test;

import javafx.scene.layout.Pane;
import org.junit.jupiter.api.Test;

import Snake.ObjectSnake;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ObjectSnakeTest {

    /**
     * Unit-тест обновления положения змейки на экране
     */
    @Test
    void testUpdate() {
        Pane testPane = new Pane();
        ObjectSnake testSnake = new ObjectSnake(7.0, 8);

        testPane.getChildren().addAll(testSnake.getBody());

        double x = testSnake.getBody().get(0).getCenterX();
        double y = testSnake.getBody().get(0).getCenterY();

        testSnake.update(testPane, 0.5);

        assertNotEquals(x, testSnake.getBody().get(0).getCenterX());
        assertNotEquals(y, testSnake.getBody().get(0).getCenterY());
    }

}
