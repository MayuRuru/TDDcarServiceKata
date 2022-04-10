package model;

public class Car {
    protected Long id;
    protected Long plate;
    protected String model;

    public Car(Long id, Long plate, String model) {
        this.id = id;
        this.plate = plate;
        this.model = model;
    }

    public Car() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
