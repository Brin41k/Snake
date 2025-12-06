package Snake;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SnakeExceptions {

    private static final Logger log = LogManager.getLogger(SnakeExceptions.class);

    /**
     * <b>Обработка исключений</b> передаваемых параметров скорости и длины хвоста змейки. <br>
     * Используется в {@link SnakeOnScreen#main(String[])} перед запуском графического интерфейса.
     */
    private SnakeExceptions() {}

    /**
     * Проверка переданного значения скорости змейки. <br>
     * Проверяет, не является ли переданный аргумент для скорости змейки отрицательным числом или нулём.
     * @param speed переданная с запуском программы скорость змейки.
     * @throws IllegalArgumentException если скорость змейки задана неположительным числом или получен аргумент типа, отличного от double
     */

    public static void checkSpeed(double speed) throws IllegalArgumentException {
        log.info("Проверка скорости: {}", speed);
        try {
            if (speed <= 0) {
                log.warn("Получен некорректный аргумент скорости");
                throw new IllegalArgumentException("Скорость должна быть положительным числом");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("В качестве скорости принимается только числовой аргумент (double)", e);
        }
    }

    /**
     * Проверка переданного значения длины змейки. <br>
     * Проверяет, не является ли переданный аргумент для длины змейки отрицательным числом или нулём.
     *
     * @param length переданная с запуском программы длина змейки.
     * @throws IllegalArgumentException если длина змейки задана неположительным числом или получен аргумент типа, отличного от integer.
     */
    public static void checkLength(int length) throws IllegalArgumentException {
        log.info("Проверка длины: {}", length);
        try {
            if (length <= 0) {
                log.warn("Получен некорректный аргумент длины");
                throw new IllegalArgumentException("Длина змейки должна быть неотрицательным числом");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("В качестве длины змейки принимается только целочисленный аргумент (integer)", e);
        }
    }

}


