package kataTDD.kataTDD.repositories;

import kataTDD.kataTDD.model.Car;

import java.util.Optional;

public interface CarRepo {
    Optional<Car> findById(Long id);
}
