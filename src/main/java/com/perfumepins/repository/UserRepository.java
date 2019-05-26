package com.perfumepins.repository;

import com.perfumepins.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends JpaRepository<User, Integer> {
    ArrayList<User> findUsersByUsernameContains(String username);

//    @Query(value = "SELECT pin.pin_id, pin.pin_name FROM user " +
//            "LEFT JOIN user_x_pin ON user.user_id = user_x_pin.user_id " +
//            "LEFT JOIN pin ON pin.pin_id = user_x_pin.pin_id " +
//            "WHERE user.user_id = :userId", nativeQuery = true)
//    Collection<OwnedPins> findAllUserPins(@Param("userId") Integer userId);
}
