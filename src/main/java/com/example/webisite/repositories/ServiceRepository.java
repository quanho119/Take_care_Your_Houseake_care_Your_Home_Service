package com.example.webisite.repositories;

import com.example.webisite.models.Service;  // Import đúng entity 'Service'
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
}
