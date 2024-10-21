package com.example.webisite.repositories;

import com.example.webisite.models.Booking;
import com.example.webisite.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

}
