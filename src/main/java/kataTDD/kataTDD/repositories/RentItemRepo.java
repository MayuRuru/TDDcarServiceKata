package kataTDD.kataTDD.repositories;

import kataTDD.kataTDD.model.RentItem;
import kataTDD.kataTDD.model.Car;

import java.util.Optional;

public interface RentItemRepo {
    RentItem save(RentItem rentItem);
    Optional<RentItem> findRentItemByCarId(Car car);
}
