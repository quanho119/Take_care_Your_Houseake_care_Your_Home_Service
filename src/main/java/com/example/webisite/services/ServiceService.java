package com.example.webisite.services;

import com.example.webisite.repositories.ServiceRepository;
import com.example.webisite.services.imple.IServiceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService implements IServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public List<com.example.webisite.models.Service> getAllServices() {
        return this.serviceRepository.findAll();
    }

}

