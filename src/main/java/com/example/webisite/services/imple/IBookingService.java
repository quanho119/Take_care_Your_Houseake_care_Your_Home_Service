package com.example.webisite.services.imple;

import com.example.webisite.models.Booking;
import com.example.webisite.models.Service;

import java.util.List;

public interface IBookingService {
    List<Booking> getAllBookings();

    void updateBooking(Booking booking);

    void createBooking(Booking booking);

    void deleteBooking(Booking booking);

    Booking findBookingById(Long id);
}
