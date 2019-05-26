package com.perfumepins.repository;

import com.perfumepins.model.Pin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PinRepository extends JpaRepository<Pin, Integer> {
    ArrayList<Pin> findPinsByPinNameContains(String pinName);

}
