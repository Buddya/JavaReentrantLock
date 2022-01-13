public class Customer implements Runnable {
    private final String name;
    private final CarShowroom carShowroom;

    public Customer(String name, CarShowroom carShowroom) {
        this.name = name;
        this.carShowroom = carShowroom;
    }

    @Override
    public void run() {
        carShowroom.buy(name);
    }
}
