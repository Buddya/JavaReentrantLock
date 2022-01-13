import java.util.Random;

public class CarProducer implements Runnable {
    private final CarShowroom carShowroom;
    private final CarBrands[] carBrands = CarBrands.values();
    private final Random random = new Random();

    public CarProducer(CarShowroom carShowroom) {
        this.carShowroom = carShowroom;
    }

    public CarBrands getCarBrand() {
        int index = random.nextInt(carBrands.length);
        return carBrands[index];
    }

    @Override
    public void run() {
        Car car = new Car(getCarBrand());
        carShowroom.put(car);
    }
}
