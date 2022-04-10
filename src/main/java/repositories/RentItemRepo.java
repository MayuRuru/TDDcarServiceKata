package repositories;

import model.Car;
import model.RentItem;

import java.util.Optional;

public interface RentItemRepo {
    RentItem save(RentItem rentItem);
    Optional<RentItem> findRentItemByCarId(Car car);
}
