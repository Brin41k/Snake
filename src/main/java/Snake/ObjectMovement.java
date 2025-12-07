package Snake;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ObjectMovement {

    private double vx;
    private double vy;
    private double speed;

    private static final Logger log = LogManager.getLogger(ObjectMovement.class);

    /**
     * Регулировка скорости и направления перемещения некоторого объекта.
     * @param speed начальная скорость объекта.
     */
    public ObjectMovement(double speed) {
        this.speed = speed;
        setRandDir();
    }

    /**
     * Задание случайного угла движения объекта.
     */
    public void setRandDir() {
        double angle = Math.random() * Math.PI * 2;
        vx = Math.cos(angle) * speed;
        vy = Math.sin(angle) * speed;
        log.info("Задана случайное направление движения змейки под углом {}", angle);
    }

    /**
     * Изменение направления движения объекта по оси x на противоположное.
     */
    public void invX() {
        vx = -vx;
    }

    /**
     * Изменение направления движения объекта по оси y на противоположное.
     */
    public void invY() {
        vy = -vy;
    }

    /**
     * Возвращает значение вектора скорости перемещения объекта по оси x.
     * @return значение вектора скорости по горизонтали.
     */
    public double getVx() {return vx;}

    /**
     * Возвращает значение вектора скорости перемещения объекта по оси y.
     * @return значение вектора скорости по вертикали.
     */
    public double getVy() {return vy;}

    /**
     * Возвращает значение модуля вектора скорости объекта.
     * @return значение скорости перемещения объекта
     */
    public double getSpeed() { return speed; }

    /**
     * Увеличивает значение скорости перемещения объекта.
     * @param value величина увеличения скорости.
     */
    public void accelerate(double value) {
        speed += value;
        normalizeToSpeed();
    }

    /**
     * Уменьшает значение скорости перемещения объекта. <br>
     * Не может уменьшить значение скорости объекта до 0 и ниже.
     * @param value величина уменьшения скорости.
     */
    public void decelerate(double value) {
        if (speed > value) {
            speed -= value;
            normalizeToSpeed();
        }
    }

    /**
     * Нормализация движения объекта. <br>
     * Изменяет вектор направления движения объекта для соответствия значению скорости его перемещения.
     */
    public void normalizeToSpeed() {
        double len = Math.sqrt(vx*vx+ vy*vy);
        if (len == 0) return;

        vx = (vx / len) * speed;
        vy = (vy/len) * speed;
    }

}
