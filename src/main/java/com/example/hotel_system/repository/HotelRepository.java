package com.example.hotel_system.repository;

import com.example.hotel_system.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

}
