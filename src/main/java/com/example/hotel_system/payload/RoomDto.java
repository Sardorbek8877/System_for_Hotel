package com.example.hotel_system.payload;

import com.example.hotel_system.entity.Hotel;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class RoomDto {

    private Integer number;

    private Integer floor;

    private Integer size;

    private Integer hotelId;
}
