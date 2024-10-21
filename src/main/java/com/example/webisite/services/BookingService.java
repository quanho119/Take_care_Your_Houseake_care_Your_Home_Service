package com.example.webisite.services;

import com.example.webisite.models.Booking;
import com.example.webisite.repositories.BookingRepository;
import com.example.webisite.services.imple.IBookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService implements IBookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> getAllBookings() {
        return this.bookingRepository.findAll();
    }

    @Override
    public void updateBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public void createBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(Booking booking) {
        bookingRepository.delete(booking);
    }

    @Override
    public Booking findBookingById(Long id) {
        return this.bookingRepository.findById(id).orElse(null);
    }
}


