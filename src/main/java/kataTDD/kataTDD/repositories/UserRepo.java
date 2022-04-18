package kataTDD.kataTDD.repositories;

import kataTDD.kataTDD.model.User;

import java.util.Optional;

public interface UserRepo {
    Optional<User> findById(Long id);
}
