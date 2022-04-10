package repositories;

import model.Car;

import java.util.Optional;

public interface CarRepo {
    Optional<Car> findById(Long id);
}
