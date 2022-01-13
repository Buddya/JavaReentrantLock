import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CarShowroom {
    private final int FREQ_OF_PRODUCTION = 1000;
    private final int SALES_PLAN = 10;
    private final List<Car> stock = new ArrayList<>();
    private final ReentrantLock lock;
    private final Condition condition;

    public CarShowroom() {
        this.lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    public void put(Car car) {
        lock.lock();
        try {
            System.out.println("Идет производство машины");
            Thread.sleep(FREQ_OF_PRODUCTION);
            System.out.println("Получена новая машина " + car.carBrand);
            stock.add(car);
            condition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void buy(String name) {
        lock.lock();
        try {
            System.out.printf("%s зашел в автосалон\n", name);
            while (stock.size() == 0) {
                System.out.println("Машин нет");
                condition.await();
            }
            System.out.printf("%s уехал на новеньком авто %s\n", name, stock.get(0).carBrand);
            stock.remove(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public int getSALES_PLAN() {
        return SALES_PLAN;
    }
}
