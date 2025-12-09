package Snake;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * <b>Запуск</b> программы и графического интерфейса.
 */
public class SnakeOnScreen extends Application {

    private static final Logger log = LogManager.getLogger(SnakeOnScreen.class);
    public static int width;
    public static int height;

    private ObjectSnake snake;

    /**
     * Создание графического интерфейса приложения и его отображение на экране
     * @param stage оболочка графического интерфейса приложения.
     */
    @Override
    public void start(Stage stage) {

        log.info("Приложение запущено");

        Pane canvas = new Pane();
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        height = (int) bounds.getHeight();
        width = (int) bounds.getWidth();

        Parameters params = getParameters();
        List<String> raw = params.getRaw();

        double speed = 30.0;
        if (!raw.isEmpty()) {
            try {
                speed = Double.parseDouble(raw.get(0));
            } catch (Exception ignored) {
            }
        }
        int tailLen = 5;
        if (!raw.isEmpty()) {
            try {
                tailLen = Integer.parseInt(raw.get(1));
            } catch (Exception ignored) {}
        }
        snake = new ObjectSnake(speed, tailLen);
        canvas.getChildren().addAll(snake.getBody());

        Scene scene = new Scene(canvas, width, height, Color.BLACK);

        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case PLUS, ADD, UP, W -> snake.speedControl(1.0);
                case MINUS, SUBTRACT, DOWN, S -> snake.speedControl(-1.0);
                case ESCAPE -> {
                    log.info("Приложение завершило работу");
                    Platform.exit();
                }
            }
        });

        stage.setTitle("Змейка");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            long lastTime = 0;
            @Override
            public void handle(long l) {
                if (lastTime > 0) {
                    double dt = (l - lastTime) * 1e-9;
                    snake.update(canvas, dt);
                }
                lastTime = l;
            }
        };
        timer.start();
    }

    /**
     * Проверка получаемых параметров и запуск графического интерфейса приложения.
     * @param args значения начальной скорости и длины змейки, получаемые на входе программы.
     * @throws IllegalArgumentException если получено более двух аргументов (скорость и длина змейки).
     */
    public static void main(String[] args) throws IllegalArgumentException {
        if (args.length == 1) {
            SnakeExceptions.checkSpeed(Double.parseDouble(args[0]));
            launch(args);
        } else if (args.length == 2) {
            SnakeExceptions.checkSpeed(Double.parseDouble(args[0]));
            SnakeExceptions.checkLength(Integer.parseInt(args[1]));
            launch(args);
        } else if (args.length > 2) {
            throw new IllegalArgumentException("Получено слишком много аргументов");
        } else {
            launch(args);
        }
    }

}
