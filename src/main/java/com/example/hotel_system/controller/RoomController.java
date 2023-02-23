package com.example.hotel_system.controller;

import com.example.hotel_system.entity.Hotel;
import com.example.hotel_system.entity.Room;
import com.example.hotel_system.payload.RoomDto;
import com.example.hotel_system.repository.HotelRepository;
import com.example.hotel_system.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelRepository hotelRepository;

    //GET ROOMS
    @GetMapping
    public List<Room> getRooms(){
        return roomRepository.findAll();
    }

    //GET ROOM BY ID
    @GetMapping("/{id}")
    public Room getRoomById(@PathVariable Integer id){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()){
            return optionalRoom.get();
        }
        return new Room();
    }

    //ADD ROOM
    @PutMapping
    public String addRoom(@RequestBody RoomDto roomDto){

        Room room = new Room();

        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (optionalHotel.isEmpty())
            return "Hotel not found";
        Hotel hotel = optionalHotel.get();

        room.setNumber(roomDto.getNumber());
        room.setSize(roomDto.getSize());
        room.setFloor(roomDto.getFloor());
        room.setHotel(hotel);
        roomRepository.save(room);
        return "Room added";
    }

    // DELETE ROOM
    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Integer id){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()){
            roomRepository.deleteById(id);
            return "Room deleted";
        }
        return "Room not found";
    }

    //EDIT ROOM
    @PutMapping("/{id}")
    public String editRoom(@PathVariable Integer id, @RequestBody RoomDto roomDto){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()){
            Room room = optionalRoom.get();

            Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
            if (optionalHotel.isEmpty())
                return "Hotel not found";
            Hotel hotel = optionalHotel.get();

            room.setHotel(hotel);
            room.setNumber(roomDto.getNumber());
            room.setFloor(roomDto.getFloor());
            room.setSize(roomDto.getSize());
            roomRepository.save(room);
            return "Room edited";
        }
        return "Room not found";
    }

    //GET ROOMS BY HOTEL ID
    @GetMapping("/byHtelId/{hotelId}")
    public Page<Room> getRoomsByHotelId(@PathVariable Integer hotelId, @RequestParam int page){
        Pageable pageable = PageRequest.of(page, 10);
        Page<Room> roomPage = roomRepository.findAllByHotelId(hotelId, pageable);
        return roomPage;
    }

}











