package repositories;

import model.User;

import java.util.Optional;

public interface UserRepo {
    Optional<User> findById(Long id);
}
