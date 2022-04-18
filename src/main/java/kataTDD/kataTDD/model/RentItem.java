package kataTDD.kataTDD.model;

public class RentItem {
    private User rentingUser;
    private Car rentedCar;
    private Long rentingId;

    public RentItem(User user, Car car) {
    }

    public User getRentingUser() {
        return rentingUser;
    }

    public void setRentingUser(User rentingUser) {
        this.rentingUser = rentingUser;
    }

    public Car getRentedCar() {
        return rentedCar;
    }

    public void setRentedCar(Car rentedCar) {
        this.rentedCar = rentedCar;
    }

    public Long getRentingId() {
        return rentingId;
    }

    public void setRentingId(Long rentingId) {
        this.rentingId = rentingId;
    }
}
