package com.example.hotel_system.controller;

import com.example.hotel_system.entity.Hotel;
import com.example.hotel_system.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    HotelRepository hotelRepository;

    //GET HOTELS
    @GetMapping
    public List<Hotel> getHotels(){
        return hotelRepository.findAll();
    }

    //GET HOTEL BY ID
    @GetMapping("{id}")
    public Hotel getHotelById(@PathVariable Integer id){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()){
            Hotel hotel = optionalHotel.get();
            return hotel;
        }
        return new Hotel();
    }

    //ADD HOTEL
    @PostMapping
    public String addHotel(@RequestBody Hotel hotel){
        Hotel addhotel = new Hotel();

        addhotel.setName(hotel.getName());
        addhotel.setCity(hotel.getCity());
        addhotel.setStreet(hotel.getStreet());
        addhotel.setHomeNumber(hotel.getHomeNumber());

        hotelRepository.save(addhotel);
        return "Hotel added!";
    }

    //DELETE HOTEL
    @DeleteMapping("/{id}")
    public String deleteHotel(@PathVariable Integer id){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()){
            hotelRepository.deleteById(id);
            return "Hotel deleted";
        }
        return "Hotel not found";
    }

    //EDIT HOTEL
    @PutMapping("/{id}")
    public String editHotel(@PathVariable Integer id, @RequestBody Hotel hotel){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isPresent()){
            Hotel editingHotel = optionalHotel.get();

            editingHotel.setName(hotel.getName());
            editingHotel.setCity(hotel.getCity());
            editingHotel.setStreet(hotel.getStreet());
            editingHotel.setHomeNumber(hotel.getHomeNumber());

            hotelRepository.save(editingHotel);
            return "Hotel edited";
        }
        return "Hotel not found";
    }

}
