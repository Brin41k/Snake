package Snake;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ObjectSnake {
    private final List<Circle> body = new ArrayList<>();

    private double x;
    private double y;

    private final ObjectMovement snakeSpeed;

    private static final double distance = 18;
    private static final double radius = 14;

    private static final Logger log = LogManager.getLogger(ObjectSnake.class);

    /**
     * Объект-змейка. <br>
     * Создает голову змейки и составляет её хвост, перемещающийся относительно движения головы.
     * @param speed начальная скорость передвижения змейки по экрану.
     * @param length - длина змейки.
     */
    public ObjectSnake(double speed, int length) {
        log.info("Создана змейка. Скорость: {}, Длина: {}", speed, length);
        x = SnakeOnScreen.width / 2.0;
        y = SnakeOnScreen.height / 2.0;

        snakeSpeed = new ObjectMovement(speed);

        Circle head = new Circle(radius, Color.LIMEGREEN);
        head.setCenterX(x);
        head.setCenterY(y);
        body.add(head);
        for (int i = 1; i < length; i++) {
            Circle tail = body.get(body.size() - 1);
            Circle segment = new Circle(radius - 3, Color.LIMEGREEN);
            segment.setCenterX(tail.getCenterX() - distance);
            segment.setCenterY(tail.getCenterY());
            body.add(segment);
        }
    }

    /**
     * Возвращает графическое тело змейки: голову и составные части её хвоста.
     * @return графическое тело змейки.
     */
    public List<Circle> getBody() {
        return body;
    }

    /**
     * Изменение положения змейки на экране. <br>
     * Влияет на скорость перемещения головы змейки и остальных частей её тела к голове.
     * @param pane графическое окно, к которому привязана змейка.
     * @param dt коэффициент изменения положения змейки на экране с течением времени.
     */
    public void update(Pane pane, double dt) {
        x += snakeSpeed.getVx() * dt;
        y += snakeSpeed.getVy() * dt;

        bounceIfHit(pane);

        body.get(0).setCenterX(x);
        body.get(0).setCenterY(y);

        for (int i = 1; i < body.size(); i++) {
            Circle prev = body.get(i - 1);
            Circle cur = body.get(i);

            double px = prev.getCenterX();
            double py = prev.getCenterY();

            double cx = cur.getCenterX();
            double cy = cur.getCenterY();

            double dist = Math.hypot(px - cx, py - cy);

            if (dist > distance) {
                double segSpeed = Math.hypot(snakeSpeed.getVx(), snakeSpeed.getVy());
                double t = Math.min(1, 0.03 + segSpeed / 1500.0);
                cur.setCenterX(cx + (px-cx)*t);
                cur.setCenterY(cy + (py-cy)*t);
            }
        }
    }

    /**
     * Изменение траектории движения змейки по закону отражения света при ударе о край экрана.
     * @param pane графическое окно, к которому привязана змейка.
     */
    private void bounceIfHit(Pane pane) {
        boolean bounce = false;

        if (x - radius < 0) {
            x = radius;
            snakeSpeed.invX();
            bounce = true;
        }
        if (x + radius > pane.getWidth()) {
            x = pane.getWidth() - radius;
            snakeSpeed.invX();
            bounce = true;
        }
        if (y - radius < 0) {
            y = radius;
            snakeSpeed.invY();
            bounce = true;
        }
        if (y + radius >= pane.getHeight()) {
            y = pane.getHeight() - radius;
            snakeSpeed.invY();
            bounce = true;
        }

        if (bounce) {
            log.info("Змейка отскочила от края экрана");
            snakeSpeed.normalizeToSpeed();
        }
    }

    /**
     * Изменение скорости змейки. <br>
     * Используется для регулировки скорости пользователем при запуске приложения.
     * @param delta коэффициент изменения скорости (увеличения/уменьшения).
     */
    public void speedControl(double delta) {
        if (delta > 0) {
            snakeSpeed.accelerate(delta);
        } else {
            snakeSpeed.decelerate(Math.abs(delta));
        }
    }

}
