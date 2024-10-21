package com.example.webisite.services.imple;

import com.example.webisite.models.Admin;

import java.util.List;

public interface IAdminService {
    List<Admin> getAllAdmins();

    void updateAdmin(Admin admin);

    void createAdmin(Admin admin);

    void deleteAdmin(Admin admin);

    Admin findAdminById(Long id);
}
