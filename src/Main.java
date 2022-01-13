import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        final CarShowroom carShowroom = new CarShowroom();
        final ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int i = 1; i <= carShowroom.getSALES_PLAN(); i++) {
            threadPool.execute(new Customer("Покупатель-" + i, carShowroom));
        }
        for (int i = 1; i <= carShowroom.getSALES_PLAN(); i++) {
            threadPool.execute(new CarProducer(carShowroom));
        }

        threadPool.shutdown();
    }
}
